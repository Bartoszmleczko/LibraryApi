package pl.mleczkobartosz.LibraryApi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.mleczkobartosz.LibraryApi.service.CustomUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private CustomUserDetailService customUserDetailService;



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/register").permitAll()
                .antMatchers(HttpMethod.GET,"/authors/**").hasAuthority("USER")
                .antMatchers(HttpMethod.GET,"/books/**").hasAuthority("USER")
                .antMatchers(HttpMethod.POST,"/authors/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT,"/authors/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/authors/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST,"/books/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT,"/books/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/books/**").hasAuthority("ADMIN")
                .antMatchers("/users/**").hasAuthority("ADMIN")
                .and().csrf().disable().
                httpBasic()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }



}
