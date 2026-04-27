package tn.esprit._4ds11.championnat.championnat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAspectJAutoProxy
public class ChampionnatApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChampionnatApplication.class, args);
    }

}
