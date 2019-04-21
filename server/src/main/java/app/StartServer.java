package app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("app")
@EntityScan("app.entity")
public class StartServer {


    private static final Logger log = LoggerFactory.getLogger(StartServer.class);

    public static void main(String[] args) {
        SpringApplication.run(StartServer.class, args);
    }


//    @Bean
//    public CommandLineRunner demo(UserRepository repository) {
//        return (args) -> {
//            repository.deleteAll();
//            ChessUser user1 = new ChessUser();
//            user1.setUserType(UserType.APPLICATION);
//            user1.setFullName("Gabs");
//            repository.save(user1);
//            ChessUser user2 = new ChessUser();
//            user2.setUserType(UserType.GUEST);
//            user2.setFullName("Dad");
//            repository.save(user2);
//
//            log.error("SavedGames found with findAll():");
//            log.info("-------------------------------");
//            for (ChessUser user : repository.findAll()) {
//                log.info(user.toString());
//            }
//            log.info("");
//
//
//        };
//    }
}
