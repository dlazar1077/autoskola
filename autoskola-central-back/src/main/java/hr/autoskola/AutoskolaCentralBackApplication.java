package hr.autoskola;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class AutoskolaCentralBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutoskolaCentralBackApplication.class, args);
	}

}
