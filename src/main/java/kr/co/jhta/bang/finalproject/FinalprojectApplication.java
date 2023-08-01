package kr.co.jhta.bang.finalproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class FinalprojectApplication {

	@Bean
	PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); };


	public static void main(String[] args) {
		SpringApplication.run(FinalprojectApplication.class, args);
	}

}
