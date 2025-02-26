package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("../FXML/HomePageFaxMail.fxml"));
        primaryStage.setTitle("Report Maker");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**************   ShowInfoView **********************************/
    public  void ShowInfoView() throws IOException {

        //creo un Loader per caricare questo layout nella mia classe Main
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("../FXML/InfoView.fxml"));

        //associo alla variabile loginLayout di tipo "AnchorPane" il file login.fxml
        Pane infoLayout = loader.load();

        //creo un nuovo stage per mostrare la nuova finestra Home
        Stage stage = new Stage();
        stage.setScene(new Scene(infoLayout));

        //blocco l'accesso alla la primaryStage del Login
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setTitle("Info");

        //mostro lo stage Homepage
        stage.show();

    }




    public static void main(String[] args) {
        launch(args);
    }
}
