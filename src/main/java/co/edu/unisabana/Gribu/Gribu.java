package co.edu.unisabana.Gribu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("co.edu.unisabana.Gribu.entity")
public class Gribu {

	public static void main(String[] args) {
		SpringApplication.run(Gribu.class, args);
	}

}
