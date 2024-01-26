package com.connectjob.Config.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception { 
		http.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests((authorize) -> authorize
					.requestMatchers("/", "/login", "/css/**", "/js/**", "/img/**").permitAll()
					.requestMatchers("/empresa/cadastro", "/empresa/cadastrar", "/usuario/cadastro", "/usuario/cadastrar", "/usuario/login").permitAll()
					.requestMatchers("/usuario/**").hasRole("USUARIO")
					.requestMatchers("/vaga/**").hasAnyRole("EMPRESA", "USUARIO")
					.requestMatchers("/empresa/**").hasRole("EMPRESA")
					)
			
					.formLogin(form -> form.loginPage("/login").loginProcessingUrl("/login").defaultSuccessUrl("/").permitAll())
					.logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
							.invalidateHttpSession(true)
			                .deleteCookies("JSESSIONID")
			                .logoutSuccessUrl("/login")
							.permitAll());
					
		return http.build();
	}
    
	  @Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		 auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
}