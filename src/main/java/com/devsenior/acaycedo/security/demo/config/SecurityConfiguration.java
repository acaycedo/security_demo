package com.devsenior.acaycedo.security.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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
                        .requestMatchers("/api/hello").permitAll()
                        .anyRequest().authenticated())
                //.formLogin(log -> {});
                .httpBasic(basic -> {});
                
        return http.build();
    }
}
