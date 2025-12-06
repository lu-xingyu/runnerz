package com.xing.runnerz;


import com.xing.runnerz.run.Location;
import com.xing.runnerz.run.Run;
import com.xing.runnerz.run.RunRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.core.Local;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@SpringBootApplication
public class Application {
	private static final Logger log = LoggerFactory.getLogger(Application.class); // .class is the metadata for this class (class name, method name, variables
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		/* what does SpringApplication.run() do?
		* 1. create Spring container
		* 2. initialize all Beans
		* 3. call the run() method of all CommandLineRunner Beans
		* */
		log.info("something changed again"); // different levels of log can be chosen (config which level you want to see)
	}



//	@Bean
//	CommandLineRunner runner(RunRepository runRepository) {
//		return args -> {
//			Run run = new Run(1, "First Run", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS), 5, Location.OUTDOOR);
//			runRepository.create(run);
//		};
//	}

	/*
	* Anonymous Class syntax: (implement an interface and create an instance)
	* 	new ClassName(args) {
	* 		...implement methods
	* 	}
	*
	* Lambda -> syntax: (only for functional interface which has only one abstract method)
	*	args -> { method body }
	*   compiler will match the method body to the only method and return an interface of this class
	* */

}

/*
* Application Context is a container of instances, which are called Bean
* Bean: an instance of class  with some metadata around it
* */

/*
* 	ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
	WelcomeMessage welcomeMessage = (WelcomeMessage) context.getBean("welcomeMessage"); // return type of getBean is Object
	System.out.println(welcomeMessage);
	* WelcomeMessage class must be inside of main package
	*
	* Spring Boot by default only scan for the components in the package where main application class is located and its subpackages
	* a class has to satisfy two condition to be managed by Spring:
		* the class can be scanned by Spring
		* the class is registered as a Bean in Application Context (@Component、@Service、@Repository、@Controller、@Configuration、@Bean...)
* */

/* Spring Boot DevTools monitor the compiled classes (target/classes)*/

/* Bean Scope:
* singleton (by default, there is only one instance in the container, all application share this one instance
* prototype: every time you getBean, it will create a new instance and gives it to you
* request: each HTTP request gets aan instance for itself
* session: each session gets an instance for itself
* application: each web application get an instance for itself
* websocket: each WebSocket session gets an instance
* */