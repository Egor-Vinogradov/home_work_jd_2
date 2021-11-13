package by.it_academy.jd2.food_control;

import by.it_academy.jd2.food_control.controller.filter.JWTAuthorizationFilter;
import by.it_academy.jd2.food_control.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SpringBootApplication
public class FoodControlApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodControlApplication.class, args);
    }

    @EnableWebSecurity
    @Configuration
    class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                    .authorizeRequests()
                    // единственный разрешенный путь для получения токена для всех пользователей
                    // временно отключил
//                    .antMatchers(HttpMethod.POST, "/api/user").permitAll()
                    // доступ разрешен всем пользователям
//                    .antMatchers("/**").permitAll()
                    // второй вариант для не зарегестрированных пользователей
                    .antMatchers("/**").not().fullyAuthenticated()
                    .anyRequest().authenticated();
        }
    }

}
