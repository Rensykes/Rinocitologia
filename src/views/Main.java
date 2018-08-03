package views;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import rinocitologia.*;
import utility.Sequence;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oXCToo
 */
public class Main extends Application {
    private Patient patient;
    private HomeController controller;

    @Override
    public void start(Stage stage) throws Exception{
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("Home.fxml"));
        try {
            Loader.load();
        } catch (IOException ex){
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE,null, ex);
        }

        controller = Loader.<HomeController>getController();
        Sequence seq = new Sequence();
        int number = 0;
        try {
            File f = new File(seq.getPATH());

            if (f.exists()){
                number = seq.readSeq() + 1;
                seq.writeSeq(number);
            } else {
                seq.writeSeq(number);

            }
            System.out.println(seq.readSeq());
        } catch(Exception e){
            e.printStackTrace();
        }
        patient = new Patient("Anonimo", Integer.toString(number));
        controller.setPatient(patient);

        //Inizio Carica View
        /*Parent parent = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene scene = new Scene(parent);

        stage.setOnHidden(e -> {
            controller.shutdown();
            Platform.exit();
        });
        stage.setTitle("Cell Explorer");
        stage.setScene(scene);
        stage.show();*/

        Parent p = Loader.getRoot();
        stage.setScene(new Scene(p));
        stage.setOnHidden(e -> {
            controller.shutdown();
            Platform.exit();
        });
        stage.show();
    }

    @Override
    public void stop(){
        System.out.println("Stage is closing");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
