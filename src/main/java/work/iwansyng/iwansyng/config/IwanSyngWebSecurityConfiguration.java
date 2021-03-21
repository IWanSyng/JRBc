package work.iwansyng.iwansyng.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import work.iwansyng.iwansyng.models.UserRepository;

//@Configuration
@EnableWebSecurity
public class IwanSyngWebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UserRepository userRepository;

//    @Autowired
//    AuthenticationSuccessHandler authenticationSuccessHandler;

    @Qualifier("iwanSyngUserDetailsService")
    UserDetailsService userDetailsService;

    public IwanSyngWebSecurityConfiguration(
            @Qualifier("iwanSyngUserDetailsService") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .passwordEncoder(passwordEncoder())
//                .withUser("admin").password(passwordEncoder()
//                .encode("password")).roles("USER", "ADMIN");
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers(
                    "/",
                    "/index",
                    "/all",
                    "/two_users",
                    "/resources/**",
                    "/resources/public/**",
                    "/resources/templates/**",
                    "/resources/templates/css/**",
                    "/resources/templates/js/**",
                    "/resources/templates/img/**",
                    "/webjars/**",
                    "/test",
                    "/Training/Java",
                    "/add_user").permitAll()
                .anyRequest().authenticated()
            .and()
                .formLogin()
                    .loginPage("/login")
                        .defaultSuccessUrl("/quiz", true)
                        .permitAll()
            .and()
                .logout()
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login?logout")
            .permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
        //return NoOpPasswordEncoder.getInstance();
    }
}