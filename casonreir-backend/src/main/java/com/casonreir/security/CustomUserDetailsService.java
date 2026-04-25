package com.casonreir.security;

import com.casonreir.model.SfGuardUser;
import com.casonreir.model.SfGuardUserGroup;
import com.casonreir.repository.SfGuardUserRepository;
import com.casonreir.repository.SfGuardUserGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private SfGuardUserRepository userRepository;

    @Autowired
    private SfGuardUserGroupRepository userGroupRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. Buscar el usuario
        SfGuardUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        // 2. Buscar sus grupos en la tabla sf_guard_user_group
        List<SfGuardUserGroup> groups = userGroupRepository.findByUser(user);

        // 3. Convertir los nombres de los grupos a Roles de Spring (ej: "admin" -> "ROLE_ADMIN")
        List<SimpleGrantedAuthority> authorities = groups.stream()
                .map(g -> new SimpleGrantedAuthority("ROLE_" + g.getGroup().getName().toUpperCase()))
                .collect(Collectors.toList());

        // 4. Retornar el usuario con sus roles REALES de la base de datos
        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}
