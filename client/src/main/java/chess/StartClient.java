package chess;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class StartClient extends Application {

    private ConfigurableApplicationContext context;

    @Override
    public void init() {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(StartClient.class);
        this.context = builder.run(getParameters().getRaw().toArray(new String[0]));
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main.fxml"));

        Parent root = loader.load();
//        GridPane root = new GridPane();
//        final int size = 3;
//        for (int row = 0; row < size; row++) {
//            for (int col = 0; col < size; col++) {
//                StackPane square = new StackPane();
//                String color;
//                if ((row + col) % 2 == 0) {
//                    color = "white";
//                } else {
//                    color = "grey";
//                }
//                square.setStyle("-fx-background-color: " + color + ";");
//                root.add(square, col, row);
//            }
//        }
//        for (int i = 0; i < size; i++) {
//            root.getColumnConstraints().add(new ColumnConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, HPos.CENTER, true));
//            root.getRowConstraints().add(new RowConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, VPos.CENTER, true));
//        }
//        Circle sourceFld = new Circle(10);
//        sourceFld.setOnMouseClicked(event -> System.out.println("clicked"));
//
//        root.getChildren().set(0, sourceFld);
        primaryStage.setScene(new Scene(root));


        primaryStage.show();


    }

    private void writelog(String s) {
        System.out.println(s);
    }

    @Override
    public void stop() {
        context.close();
    }
}