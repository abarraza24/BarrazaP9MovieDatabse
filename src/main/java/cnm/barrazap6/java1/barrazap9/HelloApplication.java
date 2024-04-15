//ALexis Barraza abarraza24@cnm.edu
//Using DataBase in JAVA Intellij Idea
//BarrazaP9 Fav Movie DataBase.
package cnm.barrazap6.java1.barrazap9;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 620, 450);
        stage.setTitle("AlexisBarrazaP9- JAVA-Movie DATABASE ");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}