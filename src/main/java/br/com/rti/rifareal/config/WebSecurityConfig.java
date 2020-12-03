package br.com.rti.rifareal.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure( HttpSecurity http ) throws Exception {
		// @// @formatter:off
		http
			.csrf().disable()
			.cors().disable()
			.authorizeRequests().antMatchers( "/site/**", "/admin/**").permitAll()
			//.antMatchers("/admin/**").hasRole("ADMIN")
			.anyRequest().authenticated()
			//.and().httpBasic()
			.and().formLogin().disable().logout().disable()
//			.and().formLogin().loginPage( "/login" ).permitAll()
//			.and().logout().permitAll();
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			;
		// @formatter:on
	}

	// @Bean
	// @Override
	// public UserDetailsService userDetailsService() {
	// // password: 123
	// UserDetails user = User.withUsername( "user" ).password(
	// "$2a$10$mjGfPLiEFsOypC3f3n9eLemfJZKOvuFAHgcmb9/t8.VTSid/OO9Q6" ).roles(
	// "USER" ).build();
	// UserDetails userAdmin = User.withUsername( "userAdmin" ).password(
	// "$2a$10$mjGfPLiEFsOypC3f3n9eLemfJZKOvuFAHgcmb9/t8.VTSid/OO9Q6" ).roles(
	// "ADMIN" ).build();
	//
	// return new InMemoryUserDetailsManager( user, userAdmin );
	// }
}
