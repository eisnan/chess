package app.datalayer.repo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("app")
@EntityScan("app.domain")
public class StartServer {


    private static final Logger log = LoggerFactory.getLogger(StartServer.class);

    public static void main(String[] args) {
        SpringApplication.run(StartServer.class, args);
    }


//    @Bean
//    public CommandLineRunner demo(GameRepository<SavedGame> repository) {
//        return (args) -> {
//            repository.deleteAll();
//            repository.save(new SavedGame("FirstGame"));
//            repository.save(new SavedGame("Second game"));
//
//            log.info("SavedGames found with findAll():");
//            log.info("-------------------------------");
//            for (SavedGame savedGame : repository.findAll()) {
//                log.info(savedGame.toString());
//            }
//            log.info("");
//
//            repository.findByName("FirstGame")
//                    .ifPresent(savedGame -> {
//                        log.info("Saved found with name:");
//                        log.info("--------------------------------");
//                        log.info(savedGame.toString());
//                        log.info("");
//                    });
//
//        };
//    }
}
