package mk.ukim.finki.labb;

import mk.ukim.finki.labb.security.CustomUsernamePasswordAuthenticationProvider;
import mk.ukim.finki.labb.web.filters.JwtFilter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableScheduling
public class LabBApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabBApplication.class, args);
    }
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public CommandLineRunner checkBeans(ApplicationContext context) {
        return args -> {
            System.out.println("JwtFilter: " + context.getBean(JwtFilter.class));
            System.out.println("AuthProvider: " + context.getBean(CustomUsernamePasswordAuthenticationProvider.class));
        };
    }

}
