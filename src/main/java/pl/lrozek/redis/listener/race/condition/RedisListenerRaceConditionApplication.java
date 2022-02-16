package pl.lrozek.redis.listener.race.condition;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RedisListenerRaceConditionApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisListenerRaceConditionApplication.class, args);
	}

}
