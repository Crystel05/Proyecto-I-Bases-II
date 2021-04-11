package VIEW;

import CONTROLLER.ControllerGUI;
import MODEL.Telefono;
import MODEL.Tipo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.security.Guard;
import java.util.ArrayList;

public class ControllerPrincipal {

    public ControllerPrincipal() {}
//    private static ControllerPrincipal controllerPrincipal;
//
//
//    public static ControllerPrincipal getInstance(){
//        if(controllerPrincipal == null){
//            controllerPrincipal = new ControllerPrincipal();
//        }
//        return controllerPrincipal;
//    }

    //Atributos FX

    //->Atributos inicio sesión

    @FXML private RadioButton BParticipante;
    @FXML private RadioButton BAdministrador;
    @FXML private PasswordField contrasenna;
    @FXML private TextField alias;

    //->Atributos Tareas

    @FXML private Pane panelInicioS;
    @FXML private Pane panelTareas;
    @FXML private ImageView flecha1;
    @FXML private ImageView flecha2;
    @FXML private ImageView flecha3;
    @FXML private ImageView flecha4;
    @FXML private ImageView flecha5;
    @FXML private ImageView flecha6;

    //->>Atributos Admin

    @FXML private Button registrarB;
    @FXML private Button modificarB;
    @FXML private Pane registrarUP;
    @FXML private Pane modificarUP;
    @FXML private Pane subastasActivasP;
    @FXML private Pane pujasSubastaP;
    @FXML private Pane subasvendedorP;
    @FXML private Pane comprasCompP;
    @FXML private Label titAdm;

    //->>>Atributos registrarUsuario

    @FXML private RadioButton BparticipanteR;
    @FXML private RadioButton BAdmR;
    @FXML private javafx.scene.control.TextField NumTelTF;
    @FXML private javafx.scene.control.TextField aliasTF;
    @FXML private PasswordField paswPF;
    @FXML private javafx.scene.control.TextField cedTF;
    @FXML private javafx.scene.control.TextField nombreTF;
    @FXML private javafx.scene.control.TextField apellTF;
    @FXML private javafx.scene.control.TextArea direcTF;
    @FXML private ComboBox<String> tipoTelC;

    //->>>Atributos Modificar usuario
    @FXML private Pane selecionar1P;
    @FXML private RadioButton adminModR;
    @FXML private RadioButton partModR;
    @FXML private ComboBox<String> usuariosCB;
    @FXML private Pane editarDatosP;
    @FXML private TextField aliasEd;
    @FXML private TextField contE;
    @FXML private TextField docIE;
    @FXML private TextField nombreE;
    @FXML private TextArea dirE;
    @FXML private TextField apellidosE;
    @FXML private RadioButton agregarR;
    @FXML private RadioButton editarExR;
    @FXML private Pane editarTelEx;
    @FXML private ComboBox<String> numeros;
    @FXML private ComboBox<Tipo> tipoEd;
    @FXML private TextField numeroETF;
    @FXML private Pane agregarNTel;
    @FXML private ComboBox<String> tipoAgregar;
    @FXML private TextField numeroAgregar;

    //->>>Subastas Activas

    @FXML private ComboBox<String> categoriasCB;
    @FXML private ComboBox<String> subCategoriasCB;

    //->>Atributos Participante

    @FXML private Label titPart;
    @FXML private Button iniciarSubastaB;
    @FXML private Button pujarIB;
    @FXML private Pane iniciarSubastaPanel;
    @FXML private Pane pujarPanel;

    //Atributos extras

    private ArrayList<Telefono> telefonos = new ArrayList<>();
    private ControllerGUI GUI = ControllerGUI.getInstance();
    private boolean esAdmin;

    //Métodos FX

    @FXML
    public void cerrar(MouseEvent event){
        Node source = (Node) event.getSource();
        Stage stageActual = (Stage) source.getScene().getWindow();
        stageActual.close();
    }

    @FXML
    public void cerrar2(ActionEvent event){
        Node source = (Node) event.getSource();
        Stage stageActual = (Stage) source.getScene().getWindow();
        stageActual.close();
    }

    //Métodos inicio sesión

    @FXML
    public void escogerUnBotonU(ActionEvent event) {
        BParticipante.setSelected(false);
        esAdmin = true;
    }

    @FXML
    public void escogerUnBotonPa(ActionEvent event) {
        BAdministrador.setSelected(false);
        esAdmin = false;
    }

    @FXML
    public void InicioSesion(ActionEvent event) throws IOException {
        if ((confirmarUsuario(contrasenna.getText(), alias.getText())) && (BAdministrador.isSelected() || BParticipante.isSelected())){
            GUI.setAlias(alias.getText());
            GUI.setContrasena(contrasenna.getText());

            panelTareas.setVisible(true);
            panelInicioS.setVisible(false);
            flecha1.setVisible(true);
            flecha2.setVisible(false);
            flecha3.setVisible(false);
            flecha4.setVisible(false);
            flecha5.setVisible(false);
            flecha6.setVisible(false);
            modificarUP.setVisible(false);
            subastasActivasP.setVisible(false);
            pujasSubastaP.setVisible(false);
            subasvendedorP.setVisible(false);
            comprasCompP.setVisible(false);
            pujarPanel.setVisible(false);

            if(BAdministrador.isSelected()){

                registrarB.setVisible(true);
                modificarB.setVisible(true);
                iniciarSubastaB.setVisible(false);
                pujarIB.setVisible(false);
                titAdm.setVisible(true);
                titPart.setVisible(false);
                registrarUP.setVisible(true);
                iniciarSubastaPanel.setVisible(false);
            }

            else if(BParticipante.isSelected()){

                registrarUP.setVisible(false);
                titAdm.setVisible(false);
                titPart.setVisible(true);
                iniciarSubastaB.setVisible(true);
                pujarIB.setVisible(true);
                registrarB.setVisible(false);
                modificarB.setVisible(false);
                iniciarSubastaPanel.setVisible(true);
            }
        }

        else{
            Parent root = FXMLLoader.load(getClass().getResource("FXMLS/Error4.fxml"));
            Stage ventana = new Stage();
            ventana.initStyle(StageStyle.TRANSPARENT);
            ventana.setScene(new Scene(root));
            ventana.setResizable(false);
            ventana.show();
        }

    } //**

    @FXML
    public void atrasInicioS(MouseEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage stageActual = (Stage) source.getScene().getWindow();
        stageActual.close();

        Parent root = FXMLLoader.load(getClass().getResource("FXMLS/EscogerBD.fxml"));
        Stage ventana = new Stage();
        ventana.initStyle(StageStyle.TRANSPARENT);
        ventana.setScene(new Scene(root));
        ventana.setResizable(false);
        ventana.show();
    }

    //Métodos tareas usuario
    @FXML
    public void atrasTareas(MouseEvent event) throws IOException {
        panelInicioS.setVisible(true);
        panelTareas.setVisible(false);
        alias.clear();
        contrasenna.clear();
        BAdministrador.setSelected(false);
        BParticipante.setSelected(false);
    }

    //Métodos administrador

    @FXML
    public void registrarUsuario(ActionEvent event){

        ObservableList<String> tipos = FXCollections.observableArrayList(GUI.cargarTipos());
        tipoTelC.setItems(tipos);

        BAdmR.setSelected(false);
        BparticipanteR.setSelected(false);
        tipoTelC.setValue("Ninguno");

        registrarUP.setVisible(true);
        flecha1.setVisible(true);

        modificarUP.setVisible(false);
        flecha2.setVisible(false);

        subastasActivasP.setVisible(false);
        flecha3.setVisible(false);

        pujasSubastaP.setVisible(false);
        flecha4.setVisible(false);

        subasvendedorP.setVisible(false);
        flecha5.setVisible(false);

        comprasCompP.setVisible(false);
        flecha6.setVisible(false);

        iniciarSubastaPanel.setVisible(false);
        pujarPanel.setVisible(false);

    }

    @FXML
    public void modificarUsuario(ActionEvent event){

        selecionar1P.setVisible(true);
        editarDatosP.setVisible(false);
        adminModR.setSelected(false);
        partModR.setSelected(false);

        aliasEd.clear();
        contE.clear();
        docIE.clear();
        nombreE.clear();
        apellidosE.clear();
        dirE.clear();
        agregarNTel.setVisible(false);
        editarTelEx.setVisible(false);
        editarExR.setSelected(false);
        agregarR.setSelected(false);
        numeros.setValue("");
        tipoEd.setValue(Tipo.Ninguno);
        numeroETF.clear();
        tipoAgregar.setValue("Ninguno");
        numeroAgregar.clear();


        registrarUP.setVisible(false);
        flecha1.setVisible(false);

        modificarUP.setVisible(true);
        flecha2.setVisible(true);

        subastasActivasP.setVisible(false);
        flecha3.setVisible(false);

        pujasSubastaP.setVisible(false);
        flecha4.setVisible(false);

        subasvendedorP.setVisible(false);
        flecha5.setVisible(false);

        comprasCompP.setVisible(false);
        flecha6.setVisible(false);

        iniciarSubastaPanel.setVisible(false);
        pujarPanel.setVisible(false);

    }

    @FXML
    public void subastasActivas(ActionEvent event){

        registrarUP.setVisible(false);
        flecha1.setVisible(false);

        modificarUP.setVisible(false);
        flecha2.setVisible(false);

        subastasActivasP.setVisible(true);
        flecha3.setVisible(true);

        pujasSubastaP.setVisible(false);
        flecha4.setVisible(false);

        subasvendedorP.setVisible(false);
        flecha5.setVisible(false);

        comprasCompP.setVisible(false);
        flecha6.setVisible(false);

        iniciarSubastaPanel.setVisible(false);
        pujarPanel.setVisible(false);

    }

    @FXML
    public void pujasPorSubasta(ActionEvent event){

        registrarUP.setVisible(false);
        flecha1.setVisible(false);

        modificarUP.setVisible(false);
        flecha2.setVisible(false);

        subastasActivasP.setVisible(false);
        flecha3.setVisible(false);

        pujasSubastaP.setVisible(true);
        flecha4.setVisible(true);

        subasvendedorP.setVisible(false);
        flecha5.setVisible(false);

        comprasCompP.setVisible(false);
        flecha6.setVisible(false);

    }

    @FXML
    public void subastasPorVendedor(ActionEvent event){

        registrarUP.setVisible(false);
        flecha1.setVisible(false);

        modificarUP.setVisible(false);
        flecha2.setVisible(false);

        subastasActivasP.setVisible(false);
        flecha3.setVisible(false);

        pujasSubastaP.setVisible(false);
        flecha4.setVisible(false);

        subasvendedorP.setVisible(true);
        flecha5.setVisible(true);

        comprasCompP.setVisible(false);
        flecha6.setVisible(false);

        iniciarSubastaPanel.setVisible(false);
        pujarPanel.setVisible(false);

    }

    @FXML
    public void comprasPorComprador(ActionEvent event){

        registrarUP.setVisible(false);
        flecha1.setVisible(false);

        modificarUP.setVisible(false);
        flecha2.setVisible(false);

        subastasActivasP.setVisible(false);
        flecha3.setVisible(false);

        pujasSubastaP.setVisible(false);
        flecha4.setVisible(false);

        subasvendedorP.setVisible(false);
        flecha5.setVisible(false);

        comprasCompP.setVisible(true);
        flecha6.setVisible(true);

        iniciarSubastaPanel.setVisible(false);
        pujarPanel.setVisible(false);
    }

    //Registrar Usuario

    @FXML
    public void escogerUnoA(ActionEvent event){
        BparticipanteR.setSelected(false);
    }

    @FXML
    public void escogerUnoP(ActionEvent event){
        BAdmR.setSelected(false);
    }

    @FXML
    public void setItemsTipos(MouseEvent event){

        ObservableList<String> tipos = FXCollections.observableArrayList(GUI.cargarTipos());
        tipoTelC.setItems(tipos);
    }

    @FXML
    public void agregarTelefono(ActionEvent event) throws IOException {
        String nombreFxml;
        if(!tipoTelC.getSelectionModel().isEmpty() && !NumTelTF.getText().isEmpty()) {
            int tel = Integer.parseInt(NumTelTF.getText());
            String tipo = tipoTelC.getSelectionModel().getSelectedItem();
            telefonos.add(new Telefono(tel, tipo));
            NumTelTF.clear();
            nombreFxml = "FXMLS/Aviso1.fxml";
            tipoTelC.setValue("Ninguno");
        }
        else{
            nombreFxml = "FXMLS/Error3.fxml";
        }
        Parent root = FXMLLoader.load(getClass().getResource(nombreFxml));
        Stage ventana = new Stage();
        ventana.initStyle(StageStyle.TRANSPARENT);
        ventana.setScene(new Scene(root));
        ventana.setResizable(false);
        ventana.show();
    }

    @FXML
    public void agregarUsuario(ActionEvent event) throws IOException {
        String nombreFxml;
        boolean esAdmin;
        if((BAdmR.isSelected() || BparticipanteR.isSelected()) && !aliasTF.getText().isEmpty() && !paswPF.getText().isEmpty() && !cedTF.getText().isEmpty() && !nombreTF.getText().isEmpty()
                && !apellTF.getText().isEmpty() && !direcTF.getText().isEmpty() && !NumTelTF.getText().isEmpty() && !tipoTelC.getSelectionModel().isEmpty()){
            if(BAdmR.isSelected())
                esAdmin = true;
            else
                esAdmin = false;
            if(GUI.registrarUsuario(esAdmin, aliasTF.getText(), paswPF.getText(), cedTF.getText(), nombreTF.getText(), apellTF.getText(),direcTF.getText()) == 1) {

                telefonos.add(new Telefono(Integer.parseInt(NumTelTF.getText()), tipoTelC.getSelectionModel().getSelectedItem()));
                for(Telefono telefono: telefonos){
                    GUI.agregarTelefono(aliasTF.getText(), paswPF.getText(), telefono.getNumero(), telefono.getTipo());
                }
                aliasTF.clear();
                paswPF.clear();
                cedTF.clear();
                nombreTF.clear();
                apellTF.clear();
                direcTF.clear();
                NumTelTF.clear();
                BAdmR.setSelected(false);
                BparticipanteR.setSelected(false);
                tipoTelC.setValue("Niguno");
                nombreFxml = "FXMLS/Aviso2.fxml";

            }
            else{
                nombreFxml = "FXMLS/Error5.fxml";
            }
        }
        else{
            nombreFxml = "FXMLS/Error3.fxml";
        }
        Parent root = FXMLLoader.load(getClass().getResource(nombreFxml));
        Stage ventana = new Stage();
        ventana.initStyle(StageStyle.TRANSPARENT);
        ventana.setScene(new Scene(root));
        ventana.setResizable(false);
        ventana.show();
    }

    //Modificar usuario

    @FXML
    public void escoUnoAm(ActionEvent event){
        partModR.setSelected(false);
        //llenar combobox de usuarios BD
    }

    @FXML
    public void escoUnoPm(ActionEvent event){
        adminModR.setSelected(false);
        //llenar combobox de usuarios BD
    }

    @FXML
    public void editar(ActionEvent event){
//        if(!usuariosCB.getSelectionModel().isEmpty())
        editarDatosP.setVisible(true);
        selecionar1P.setVisible(false);

    }

    @FXML
    public void editarExistente(ActionEvent event){
        ObservableList<Tipo> items = FXCollections.observableArrayList(Tipo.values());
        tipoEd.setItems(items);
        agregarR.setSelected(false);
        numeros.setValue("");
        tipoEd.setValue(Tipo.Ninguno);
        numeroETF.clear();
        if(editarExR.isSelected() || agregarR.isSelected()) {
            editarTelEx.setVisible(true);
            agregarNTel.setVisible(false);
        }
        else{
            editarTelEx.setVisible(false);
        }
    }

    @FXML
    public void agregarNuevo(ActionEvent event){
        ObservableList<String> items = FXCollections.observableArrayList(GUI.cargarTipos());
        tipoAgregar.setItems(items);
        editarExR.setSelected(false);
        tipoAgregar.setValue("Ninguno");
        numeroAgregar.clear();
        if(editarExR.isSelected() || agregarR.isSelected()) {
            editarTelEx.setVisible(false);
            agregarNTel.setVisible(true);
        }
        else{
            agregarNTel.setVisible(false);
        }
    }

    @FXML
    public void modificar(ActionEvent event) throws IOException {
        String nombreFXML;
        if(!numeroETF.getText().isEmpty() && !tipoEd.getSelectionModel().isEmpty()) { // && !numeros.getSelectionModel().isEmpty()
            nombreFXML = "FXMLS/Aviso3.fxml";
            tipoEd.setValue(Tipo.Ninguno);
            numeros.setValue("Ninguno");
            numeroETF.clear();
        }
        else{
            nombreFXML = "FXMLS/Error3.fxml";
        }
        Parent root = FXMLLoader.load(getClass().getResource(nombreFXML));
        Stage ventana = new Stage();
        ventana.initStyle(StageStyle.TRANSPARENT);
        ventana.setScene(new Scene(root));
        ventana.setResizable(false);
        ventana.show();
    }

    @FXML
    public void guardarCambios(ActionEvent event) throws IOException {
        aliasEd.clear();
        contE.clear();
        docIE.clear();
        nombreE.clear();
        apellidosE.clear();
        dirE.clear();
        editarExR.setSelected(false);
        agregarR.setSelected(false);
        editarTelEx.setVisible(false);
        agregarNTel.setVisible(false);

        Parent root = FXMLLoader.load(getClass().getResource("FXMLS/Aviso4.fxml"));
        Stage ventana = new Stage();
        ventana.initStyle(StageStyle.TRANSPARENT);
        ventana.setScene(new Scene(root));
        ventana.setResizable(false);
        ventana.show();
    }
    @FXML
    public void agregarNuevoT(ActionEvent event) throws IOException {
        String nombreFxml;
        if(!numeroAgregar.getText().isEmpty()) { //!tipoAgregar.getSelectionModel().isEmpty() &&
            int tel = Integer.parseInt(numeroAgregar.getText());
            String tipo = tipoAgregar.getSelectionModel().getSelectedItem();
            telefonos.add(new Telefono(tel, tipo));
            numeroAgregar.clear();
            nombreFxml = "FXMLS/Aviso1.fxml";
            tipoAgregar.setValue("Ninguno");
        }
        else{
            nombreFxml = "FXMLS/Error3.fxml";
        }
        Parent root = FXMLLoader.load(getClass().getResource(nombreFxml));
        Stage ventana = new Stage();
        ventana.initStyle(StageStyle.TRANSPARENT);
        ventana.setScene(new Scene(root));
        ventana.setResizable(false);
        ventana.show();
    }

    //Subastas activas
    @FXML
    public void mostrarSubcategorías(MouseEvent event){
        ObservableList<String> subcategorias = FXCollections.observableArrayList("oro", "gatos", "hola");
        String categoria = categoriasCB.getValue();
        //cosas de bd
        subCategoriasCB.setItems(subcategorias);
    }

    //Métodos participante

    //Otros métodos

    public boolean confirmarUsuario(String contrasena, String alias){
        int codigo = GUI.verificarInicioSesion(esAdmin, alias, contrasena);
        if(!contrasena.isEmpty() && !alias.isEmpty() && codigo == 1){
            return true;
        }
        return false;
    }


}
