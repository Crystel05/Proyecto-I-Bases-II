package VIEW;

import CONTROLLER.ControllerGUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ControllerVenPrin {

    public ControllerVenPrin() {}

    private final ControllerGUI controllerGUI = ControllerGUI.getInstance();


    //Atributos FX
    @FXML private RadioButton BoracleDB;
    @FXML private RadioButton BpostBD;


    //Método FX
    @FXML
    public void escogerUnBotonO(ActionEvent event) {
        BpostBD.setSelected(false);
        controllerGUI.setBaseDatoUsada(false);
    }

    @FXML
    public void escogerUnBotonP(ActionEvent event) {
        BoracleDB.setSelected(false);
        controllerGUI.setBaseDatoUsada(true);

    }

    @FXML
    public void confirmar(ActionEvent event) throws IOException {

        if(BoracleDB.isSelected() || BpostBD.isSelected()) {
            Node source = (Node) event.getSource();
            Stage stageActual = (Stage) source.getScene().getWindow();
            stageActual.close();

            Parent root = FXMLLoader.load(getClass().getResource("FXMLS/VentanaPrincipal.fxml"));
            Stage ventana = new Stage();
            ventana.initStyle(StageStyle.TRANSPARENT);
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            ventana.setScene(scene);
            ventana.setResizable(false);
            ventana.show();
        }
        else{
            Parent root = FXMLLoader.load(getClass().getResource("FXMLS/Error2.fxml"));
            Stage ventana = new Stage();
            ventana.setTitle("Error");
            ventana.setScene(new Scene(root));
            ventana.setResizable(false);
            ventana.initStyle(StageStyle.TRANSPARENT);
            ventana.show();
        }

    }

    @FXML
    public void cerrar(MouseEvent event){
        Node source = (Node) event.getSource();
        Stage stageActual = (Stage) source.getScene().getWindow();
        stageActual.close();
    }

    //Métodos BD Oracle
    //Métodos BD postgres
}
