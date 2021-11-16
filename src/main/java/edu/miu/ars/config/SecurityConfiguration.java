package edu.miu.ars.config;

import edu.miu.ars.constant.AppConstant;
import edu.miu.ars.filter.JwtAuthorizationFilter;
import edu.miu.ars.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.servlet.support.csrf.CsrfRequestDataValueProcessor;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.support.RequestDataValueProcessor;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final AppUserService appUserService;

    @Autowired
    public SecurityConfiguration(@Lazy AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                .authorizeRequests()
                .antMatchers("/webjars/**", "/swagger-resources/**").permitAll()
                .antMatchers("/api/auth").permitAll()
                .antMatchers("/","/h2-console/**").permitAll()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/v2/**").permitAll()

                .antMatchers("/api/airports/**").permitAll()
                .antMatchers("/api/flights/**").permitAll()
                .antMatchers("/api/agents/**").permitAll()
                .antMatchers("/api/flightInfo/**/**").permitAll()
                .antMatchers("/api/reservations/**").permitAll()
                .antMatchers("/api/users/**").hasAuthority(AppConstant.ROLE_ADMIN)
                .antMatchers("/api/airports/**").permitAll()
                .antMatchers("/api/flights/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutRequestMatcher(
                        new AntPathRequestMatcher("/login?logout"))
                .logoutSuccessUrl("/").permitAll()
                .and().csrf().disable()
                .addFilter(new JwtAuthorizationFilter(authenticationManager()))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.headers().frameOptions().sameOrigin();
    }

    @Bean
    public RequestDataValueProcessor requestDataValueProcessor() {
        return new CsrfRequestDataValueProcessor();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {

        //TODO:Narayan - Remove inmemory auth after everything done
        auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder().encode("admin"))
                .roles("ADMIN");
        auth.inMemoryAuthentication().withUser("passenger").password(passwordEncoder().encode("passenger"))
                .roles("USER");
        auth.inMemoryAuthentication().withUser("agent").password(passwordEncoder().encode("agent"))
                .roles("AGENT");
        auth.userDetailsService(appUserService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}


