package com.devsenior.acaycedo.security.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration Etiqueta para indicar que esta clase se inicia antes de cualquier cosa
@Configuration
// @EnableWebSecurity Etiqueta para indicar las reglas del sitio web
@EnableWebSecurity

public class SecurityConfiguration {
    // @Bean Se usa para definir un objeto que sera gestionado por el contenedor de
    // Spring
    /*
     * @Bean le dice a Spring: “Crea una instancia de este objeto y guárdalo en el
     * contenedor para que pueda inyectarse donde lo necesite”.
     */
    /*
     * “El resultado de este método es un objeto importante para la aplicación.
     * Guárdalo en el contenedor para poder reutilizarlo donde sea necesario.”
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(crsf -> crsf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/","index.html").permitAll()
                        //.requestMatchers("/api/hello").permitAll()
                        //Implementacion de HTTP metodos para permitir acciones
                        // para roles dependiendo la peticion HTTP.
                        .requestMatchers(HttpMethod.GET).hasAnyRole("ADMIN","USER")
                        .requestMatchers(HttpMethod.POST).hasRole("ADMIN")
                        .anyRequest().denyAll())
                //.formLogin(log -> {});
                .httpBasic(basic -> {});
                
        return http.build();
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsManager userDetailsManager(PasswordEncoder passwordEncoder){

        var user1 = User.builder()
        .username("acaycedo")
        .password(passwordEncoder.encode("admin123"))
        .authorities("ROLE_ADMIN")// Esto viene ya preestablecido con un ENUM con roles básicos
        .build();

        var user2 = User.builder()
        .username("ajimenez")
        .password(passwordEncoder.encode("admin12"))
        .authorities("ROLE_USER")// Esto viene ya preestablecido con un ENUM con roles básicos
        .build();

        return new InMemoryUserDetailsManager(user1, user2);
    }
}
