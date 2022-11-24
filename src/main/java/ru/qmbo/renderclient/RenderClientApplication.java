package ru.qmbo.renderclient;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.qmbo.renderclient.service.StartService;

@SpringBootApplication
public class RenderClientApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(RenderClientApplication.class, args);
	}

	@Override
	public void run(String... args) {
		StartService.start();
	}
}
