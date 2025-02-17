package connection; // Corrigido de 'connction' para 'connection'

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        var root = FXMLLoader.load(getClass().getResource("/org/openjfx/sistemaigreja/Login.fxml"));
        primaryStage.setTitle("Sistema de Gerenciamento de Igreja");
        primaryStage.setScene(new Scene((Parent) root, 400, 300)); // ajuste o tamanho conforme necessário
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
