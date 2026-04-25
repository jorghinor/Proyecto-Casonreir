package com.casonreir.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // 1. Recursos estáticos abiertos para todos
                        .requestMatchers("/login", "/css/**", "/js/**", "/images/**", "/webjars/**").permitAll()

                        // 2. EL HUB DEBE SER ACCESIBLE PARA TODOS LOS ROLES LOGUEADOS
                        .requestMatchers("/config/hub").hasAnyRole("ADMIN", "MEDICO", "ENCARGADO", "AUXILIAR")

                        // 3. SECCIÓN SEGURIDAD (Solo Admin)
                        .requestMatchers("/config/usuarios/**", "/config/grupos/**", "/config/permisos/**",
                                "/config/usuarios-grupos/**", "/config/grupos-permisos/**").hasRole("ADMIN")

                        // 4. SECCIÓN SALUD (Admin y Medico)
                        .requestMatchers("/config/medicos/**", "/config/historial-medico/**", "/config/tipos-especialidad/**",
                                "/config/centrosalud/**", "/config/tipos-centrosalud/**").hasAnyRole("ADMIN", "MEDICO")

                        // 5. SECCIÓN GESTIÓN (Admin y Encargado)
                        .requestMatchers("/config/centrosapoyo/**", "/config/encargados/**", "/config/instituciones/**",
                                "/config/encargados-centros/**", "/config/donantes/**").hasAnyRole("ADMIN", "ENCARGADO")

                        // 6. SECCIÓN CONTENIDO (Admin, Encargado, Auxiliar, Medico)
                        .requestMatchers("/becados/**", "/config/historial-academico/**", "/config/noticias/**",
                                "/config/reglamentos/**", "/config/padrino-becado/**", "/donaciones-becados/**",
                                "/config/actividades-becados/**", "/config/reportes/**").hasAnyRole("ADMIN", "ENCARGADO", "AUXILIAR", "MEDICO")

                        // 7. Cualquier otra ruta requiere estar logueado
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
