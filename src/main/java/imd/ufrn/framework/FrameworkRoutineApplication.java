package imd.ufrn.framework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"imd.ufrn.framework", "imd.ufrn.instancefamilyroutine"})
public class FrameworkRoutineApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrameworkRoutineApplication.class, args);
	}

}
