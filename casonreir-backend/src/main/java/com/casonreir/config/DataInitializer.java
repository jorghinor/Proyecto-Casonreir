package com.casonreir.config;

import com.casonreir.model.SfGuardGroup;
import com.casonreir.model.SfGuardUser;
import com.casonreir.model.SfGuardUserGroup;
import com.casonreir.model.SfGuardUserGroupId;
import com.casonreir.repository.SfGuardGroupRepository;
import com.casonreir.repository.SfGuardUserRepository;
import com.casonreir.repository.SfGuardUserGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private SfGuardUserRepository userRepository;

    @Autowired
    private SfGuardGroupRepository groupRepository;

    @Autowired
    private SfGuardUserGroupRepository userGroupRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        System.out.println(">>> Iniciando configuración de seguridad por defecto...");

        // 1. Asegurar que existe el grupo 'admin'
        SfGuardGroup adminGroup = groupRepository.findByName("admin")
                .orElseGet(() -> {
                    SfGuardGroup g = new SfGuardGroup();
                    g.setName("admin");
                    g.setDescription("Administrador del Sistema");
                    g.setCreatedAt(LocalDateTime.now());
                    g.setUpdatedAt(LocalDateTime.now());
                    return groupRepository.save(g);
                });

        // 2. Asegurar que existe el usuario 'admin'
        SfGuardUser adminUser = userRepository.findByUsername("admin")
                .orElseGet(() -> {
                    SfGuardUser u = new SfGuardUser();
                    u.setUsername("admin");
                    u.setPassword(passwordEncoder.encode("admin123"));
                    u.setEmail_address("admin@casonreir.org");
                    u.setIsActive(true);
                    u.setIsSuperAdmin(true);
                    u.setCreatedAt(LocalDateTime.now());
                    u.setUpdatedAt(LocalDateTime.now());
                    return userRepository.save(u);
                });

        // 3. Vincular el usuario 'admin' con el grupo 'admin'
        SfGuardUserGroupId mappingId = new SfGuardUserGroupId(adminUser.getId(), adminGroup.getId());
        if (!userGroupRepository.existsById(mappingId)) {
            SfGuardUserGroup relation = new SfGuardUserGroup();
            relation.setUser(adminUser);
            relation.setGroup(adminGroup);
            relation.setCreatedAt(LocalDateTime.now());
            relation.setUpdatedAt(LocalDateTime.now());
            userGroupRepository.save(relation);
            System.out.println(">>> Vínculo Usuario-Grupo creado con éxito.");
        }

        System.out.println(">>> Configuración de seguridad finalizada.");
    }
}
