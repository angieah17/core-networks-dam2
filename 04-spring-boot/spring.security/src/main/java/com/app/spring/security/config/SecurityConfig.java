package com.app.spring.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/*La seguridad básica se configura con estas anotaciones,
 * la tercera se podría omitir.
 * */

@Configuration //es una clase de configuración
@EnableWebSecurity //habilitar seguridad web
@EnableMethodSecurity
public class SecurityConfig {
	
	
	
	//1. Configuramos el primer componente SecurityFilterChain
	
	/*El HttpSecutiry es un Objeto, sobre el cual, se le van
	 * a pasar todos los filtros de seguridad.
	 * */
	
	@Bean 
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
		//se configuran las condiciones personalizadas
		return httpSecurity.build(); //retorna el objeto construido
	}
	
	//Se configura el AuthenticationManager
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	
	//Se configura el AuthenticationProvider
	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider(null);
	}
	
	

}
