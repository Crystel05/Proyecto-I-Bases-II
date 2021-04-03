//package VIEW;
//
//import MODEL.Telefono;
//import MODEL.Tipo;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Node;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.image.ImageView;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.Pane;
//import javafx.scene.paint.Color;
//import javafx.stage.Stage;
//import javafx.stage.StageStyle;
//import java.io.IOException;
//import java.util.ArrayList;
//
//
//public class Controller {
//
//    private ArrayList<Telefono> telefonos = new ArrayList<>();
//
////    @FXML private RadioButton BoracleDB;
////    @FXML private RadioButton BpostBD;
////    @FXML private RadioButton BAdministrador;
////    @FXML private RadioButton BParticipante;
////    @FXML private PasswordField contrasenna;
////    @FXML private TextField alias;
////    @FXML private Pane PanelPrincipal;
////    @FXML private Pane PregPar;
//    @FXML private Pane registrarUP;
//    @FXML private Pane modificarUP;
//    @FXML private Pane subastasActivasP;
//    @FXML private Pane pujasSubastaP;
//    @FXML private Pane subasvendedorP;
//    @FXML private Pane comprasCompP;
//    @FXML private ImageView flecha1;
//    @FXML private ImageView flecha2;
//    @FXML private ImageView flecha3;
//    @FXML private ImageView flecha4;
//    @FXML private ImageView flecha5;
//    @FXML private ImageView flecha6;
//    @FXML private RadioButton BparticipanteR;
//    @FXML private RadioButton BAdmR;
//    @FXML private TextField NumTelTF;
//    @FXML private TextField aliasTF;
//    @FXML private PasswordField paswPF;
//    @FXML private TextField cedTF;
//    @FXML private TextField nombreTF;
//    @FXML private TextField apellTF;
//    @FXML private TextArea direcTF;
//    @FXML private ComboBox<Tipo> tipoTelC;
//    @FXML private RadioButton adminModR;
//    @FXML private RadioButton partModR;
//    @FXML private Pane editarDatosP;
//    @FXML private Pane selecionar1P;
//    @FXML private ComboBox<String> usuariosCB;
//    @FXML private TextField aliasEd;
//    @FXML private TextField contE;
//    @FXML private TextField docIE;
//    @FXML private TextField nombreE;
//    @FXML private TextArea dirE;
//    @FXML private TextField apellidosE;
//    @FXML private Pane editarTelEx;
//    @FXML private Pane agregarNTel;
//    @FXML private RadioButton agregarR;
//    @FXML private RadioButton editarExR;
//    @FXML private TextField numeroETF;
//    @FXML private ComboBox<String> numeros;
//    @FXML private ComboBox<Tipo> tipoEd;
//    @FXML private ComboBox<Tipo> tipoAgregar;
//    @FXML private ComboBox<String> categoriasCB;
//    @FXML private ComboBox<String> subCategoriasCB;
//    @FXML private TextField numeroAgregar;
//
//
////
////    @FXML
////    public void escogerUnBotonO(ActionEvent event) {
////        BpostBD.setSelected(false);
////    }
////
////    @FXML
////    public void escogerUnBotonP(ActionEvent event) {
////        BoracleDB.setSelected(false);
////    }
//
//    @FXML
//    public void escogerUnoA(ActionEvent event){
//        BparticipanteR.setSelected(false);
//    }
//
//    @FXML
//    public void escogerUnoP(ActionEvent event){
//        BAdmR.setSelected(false);
//    }
//
//    @FXML
//    public void escoUnoAm(ActionEvent event){
//        partModR.setSelected(false);
//        //llenar combobox de usuarios BD
//    }
//
//    @FXML
//    public void escoUnoPm(ActionEvent event){
//        adminModR.setSelected(false);
//        //llenar combobox de usuarios BD
//    }
//
//
//    @FXML
//    public void setItemsTipos(MouseEvent event){
//        ObservableList<Tipo> tipos = FXCollections.observableArrayList(Tipo.values());
//        tipoTelC.setItems(tipos);
//    }
//
//    @FXML
//    public void agregarTelefono(ActionEvent event) throws IOException {
//        String nombreFxml;
//        if(!tipoTelC.getSelectionModel().isEmpty() && !NumTelTF.getText().isEmpty()) {
//            int tel = Integer.parseInt(NumTelTF.getText());
//            Tipo tipo = tipoTelC.getSelectionModel().getSelectedItem();
//            telefonos.add(new Telefono(tel, tipo));
//            NumTelTF.clear();
//            nombreFxml = "FXMLS/Aviso1.fxml";
//            tipoTelC.setValue(Tipo.Ninguno);
//        }
//        else{
//            nombreFxml = "FXMLS/Error3.fxml";
//        }
//        Parent root = FXMLLoader.load(getClass().getResource(nombreFxml));
//        Stage ventana = new Stage();
//        ventana.initStyle(StageStyle.TRANSPARENT);
//        ventana.setScene(new Scene(root));
//        ventana.setResizable(false);
//        ventana.show();
//
//
//    }
//
//    @FXML
//    public void agregarUsuario(ActionEvent event) throws IOException {
//        String nombreFxml;
//        //if cedula no repetida, alias no repetido BD
//        if((BAdmR.isSelected() || BparticipanteR.isSelected()) && !aliasTF.getText().isEmpty() && !paswPF.getText().isEmpty() && !cedTF.getText().isEmpty() && !nombreTF.getText().isEmpty()
//        && !apellTF.getText().isEmpty() && !direcTF.getText().isEmpty() && !NumTelTF.getText().isEmpty() && !tipoTelC.getSelectionModel().isEmpty()){
//            aliasTF.clear();
//            paswPF.clear();
//            cedTF.clear();
//            nombreTF.clear();
//            apellTF.clear();
//            direcTF.clear();
//            NumTelTF.clear();
//            BAdmR.setSelected(false);
//            BparticipanteR.setSelected(false);
//            tipoTelC.setValue(Tipo.Ninguno);
//            nombreFxml = "FXMLS/Aviso2.fxml";
//            //agregar a la base de datos
//            //usar la lista
//        }
//        else{
//            nombreFxml = "FXMLS/Error3.fxml";
//        }
//        Parent root = FXMLLoader.load(getClass().getResource(nombreFxml));
//        Stage ventana = new Stage();
//        ventana.initStyle(StageStyle.TRANSPARENT);
//        ventana.setScene(new Scene(root));
//        ventana.setResizable(false);
//        ventana.show();
//    }
//
//    @FXML
//    public void aceptar(ActionEvent event){
//        Node source = (Node) event.getSource();
//        Stage stageActual = (Stage) source.getScene().getWindow();
//        stageActual.close();
//    }
//
//    @FXML
//    public void cerrar(MouseEvent event){
//        Node source = (Node) event.getSource();
//        Stage stageActual = (Stage) source.getScene().getWindow();
//        stageActual.close();
//    }
//    @FXML
//    public void cerrar2(ActionEvent event){
//        Node source = (Node) event.getSource();
//        Stage stageActual = (Stage) source.getScene().getWindow();
//        stageActual.close();
//    }
//
//
////    @FXML
////    public void atrasInicioS(ActionEvent event) throws IOException {
////        Node source = (Node) event.getSource();
////        Stage stageActual = (Stage) source.getScene().getWindow();
////        stageActual.close();
////
////        Parent root = FXMLLoader.load(getClass().getResource("FXMLS/EscogerBD.fxml"));
////        Stage ventana = new Stage();
////        ventana.setTitle("Escoger BD");
////        ventana.setScene(new Scene(root));
////        ventana.setResizable(false);
////        ventana.show();
////    }
//
//    @FXML
//    public void atrasAdmin(MouseEvent event) throws IOException {
//        Node source = (Node) event.getSource();
//        Stage stageActual = (Stage) source.getScene().getWindow();
//        stageActual.close();
//
//        Parent root = FXMLLoader.load(getClass().getResource("FXMLS/IniciarSesion.fxml"));
//        Stage ventana = new Stage();
////        ventana.initStyle(StageStyle.TRANSPARENT);
//        ventana.setScene(new Scene(root));
//        ventana.setResizable(false);
//        ventana.show();
//    }
//
////    @FXML
////    public void confirmar(ActionEvent event) throws IOException {
////
////        if(BoracleDB.isSelected() || BpostBD.isSelected()) {
////            Node source = (Node) event.getSource();
////            Stage stageActual = (Stage) source.getScene().getWindow();
////            stageActual.close();
////
////            Parent root = FXMLLoader.load(getClass().getResource("FXMLS/IniciarSesion.fxml"));
////            Stage ventana = new Stage();
////            ventana.setTitle("Iniciar Sesión");
////            ventana.setScene(new Scene(root));
////            ventana.setResizable(false);
////            ventana.show();
////        }
////        else{
////            Parent root = FXMLLoader.load(getClass().getResource("FXMLS/Error2.fxml"));
////            Stage ventana = new Stage();
////            ventana.setTitle("Error");
////            ventana.setScene(new Scene(root));
////            ventana.setResizable(false);
////            ventana.initStyle(StageStyle.TRANSPARENT);
////            ventana.show();
////        }
////
////    }
//
////    @FXML
////    public void escogerUnBotonU(ActionEvent event) {
////        BParticipante.setSelected(false);
////    }
////
////    @FXML
////    public void escogerUnBotonPa(ActionEvent event) {
////        BAdministrador.setSelected(false);
////    }
//
////    public boolean confirmarUsuario(String contrasena, String alias){
////        if(!contrasena.isEmpty() && !alias.isEmpty()){
////            return true;
////        }
////        return false;
////    }
//
////    @FXML
////    public void InicioSesion(ActionEvent event) throws IOException {
////        if (BAdministrador.isSelected() && confirmarUsuario(contrasenna.getText(), alias.getText())){
////            Node source = (Node) event.getSource();
////            Stage stageActual = (Stage) source.getScene().getWindow();
////            stageActual.close();
////
////            Parent root = FXMLLoader.load(getClass().getResource("FXMLS/VentanaPrincipal.fxml"));
////            Stage ventana = new Stage();
////            ventana.setTitle("ADMINISTRADOR");
////            Scene scene = new Scene(root);
////            scene.setFill(Color.TRANSPARENT);
////            ventana.setScene(scene);
////            ventana.setResizable(false);
////            ventana.initStyle(StageStyle.TRANSPARENT);
////            ventana.show();
////        }
////        else if(BParticipante.isSelected() && confirmarUsuario(contrasenna.getText(), alias.getText())){
////            Node source = (Node) event.getSource();
////            Stage stageActual = (Stage) source.getScene().getWindow();
////            stageActual.close();
////
////            Parent root = FXMLLoader.load(getClass().getResource("FXMLS/ParticipanteTareas.fxml"));
////            Stage ventana = new Stage();
////            ventana.setTitle("PARTICIPANTE");
////            ventana.setScene(new Scene(root));
////            ventana.setResizable(false);
////            ventana.show();
////        }
////        else{
////            Parent root = FXMLLoader.load(getClass().getResource("FXMLS/Error.fxml"));
////            Stage ventana = new Stage();
////            ventana.setTitle("ERROR");
////            ventana.setScene(new Scene(root));
////            ventana.setResizable(false);
////            ventana.show();
////        }
////
////    }
//
//    @FXML
//    public void registrarUsuario(ActionEvent event){
//        ObservableList<Tipo> tipos = FXCollections.observableArrayList(Tipo.values());
//        tipoTelC.setItems(tipos);
//
//        BAdmR.setSelected(false);
//        BparticipanteR.setSelected(false);
//        tipoTelC.setValue(Tipo.Ninguno);
//
//        registrarUP.setVisible(true);
//        flecha1.setVisible(true);
//
//        modificarUP.setVisible(false);
//        flecha2.setVisible(false);
//
//        subastasActivasP.setVisible(false);
//        flecha3.setVisible(false);
//
//        pujasSubastaP.setVisible(false);
//        flecha4.setVisible(false);
//
//        subasvendedorP.setVisible(false);
//        flecha5.setVisible(false);
//
//        comprasCompP.setVisible(false);
//        flecha6.setVisible(false);
//
//    }
//
//    @FXML
//    public void modificarUsuario(ActionEvent event){
//
//        selecionar1P.setVisible(true);
//        editarDatosP.setVisible(false);
//        adminModR.setSelected(false);
//        partModR.setSelected(false);
//
//        aliasEd.clear();
//        contE.clear();
//        docIE.clear();
//        nombreE.clear();
//        apellidosE.clear();
//        dirE.clear();
//        agregarNTel.setVisible(false);
//        editarTelEx.setVisible(false);
//        editarExR.setSelected(false);
//        agregarR.setSelected(false);
//        numeros.setValue("");
//        tipoEd.setValue(Tipo.Ninguno);
//        numeroETF.clear();
//        tipoAgregar.setValue(Tipo.Ninguno);
//        numeroAgregar.clear();
//
//
//        registrarUP.setVisible(false);
//        flecha1.setVisible(false);
//
//        modificarUP.setVisible(true);
//        flecha2.setVisible(true);
//
//        subastasActivasP.setVisible(false);
//        flecha3.setVisible(false);
//
//        pujasSubastaP.setVisible(false);
//        flecha4.setVisible(false);
//
//        subasvendedorP.setVisible(false);
//        flecha5.setVisible(false);
//
//        comprasCompP.setVisible(false);
//        flecha6.setVisible(false);
//
//    }
//
//    @FXML
//    public void editar(ActionEvent event){
////        if(!usuariosCB.getSelectionModel().isEmpty())
//        editarDatosP.setVisible(true);
//        selecionar1P.setVisible(false);
//
//    }
//    @FXML
//    public void editarExistente(ActionEvent event){
//        ObservableList<Tipo> items = FXCollections.observableArrayList(Tipo.values());
//        tipoEd.setItems(items);
//        agregarR.setSelected(false);
//        numeros.setValue("");
//        tipoEd.setValue(Tipo.Ninguno);
//        numeroETF.clear();
//        if(editarExR.isSelected() || agregarR.isSelected()) {
//            editarTelEx.setVisible(true);
//            agregarNTel.setVisible(false);
//        }
//        else{
//            editarTelEx.setVisible(false);
//        }
//    }
//
//    @FXML
//    public void agregarNuevo(ActionEvent event){
//        ObservableList<Tipo> items = FXCollections.observableArrayList(Tipo.values());
//        tipoAgregar.setItems(items);
//        editarExR.setSelected(false);
//        tipoAgregar.setValue(Tipo.Ninguno);
//        numeroAgregar.clear();
//        if(editarExR.isSelected() || agregarR.isSelected()) {
//            editarTelEx.setVisible(false);
//            agregarNTel.setVisible(true);
//        }
//        else{
//            agregarNTel.setVisible(false);
//        }
//    }
//
//    @FXML
//    public void modificar(ActionEvent event) throws IOException {
//        String nombreFXML;
//        if(!numeroETF.getText().isEmpty() && !tipoEd.getSelectionModel().isEmpty()) { // && !numeros.getSelectionModel().isEmpty()
//            nombreFXML = "FXMLS/Aviso3.fxml";
//            tipoEd.setValue(Tipo.Ninguno);
//            numeros.setValue("Ninguno");
//            numeroETF.clear();
//        }
//        else{
//            nombreFXML = "FXMLS/Error3.fxml";
//        }
//        Parent root = FXMLLoader.load(getClass().getResource(nombreFXML));
//        Stage ventana = new Stage();
//        ventana.initStyle(StageStyle.TRANSPARENT);
//        ventana.setScene(new Scene(root));
//        ventana.setResizable(false);
//        ventana.show();
//    }
//
//    @FXML
//    public void agregarOtro(ActionEvent event) throws IOException {
//        String nombreFXML;
//        if(!numeroAgregar.getText().isEmpty() && !tipoAgregar.getSelectionModel().isEmpty()) { //
//            nombreFXML = "FXMLS/Aviso1.fxml";
//            tipoAgregar.setValue(Tipo.Ninguno);
//            numeroAgregar.clear();
//        }
//        else{
//            nombreFXML = "FXMLS/Error3.fxml";
//        }
//        Parent root = FXMLLoader.load(getClass().getResource(nombreFXML));
//        Stage ventana = new Stage();
//        ventana.initStyle(StageStyle.TRANSPARENT);
//        ventana.setScene(new Scene(root));
//        ventana.setResizable(false);
//        ventana.show();
//    }
//
//    @FXML
//    public void agregarNuevoT(ActionEvent event) throws IOException {
//        String nombreFxml;
//        if(!numeroAgregar.getText().isEmpty()) { //!tipoAgregar.getSelectionModel().isEmpty() &&
//            int tel = Integer.parseInt(numeroAgregar.getText());
//            Tipo tipo = tipoAgregar.getSelectionModel().getSelectedItem();
//            telefonos.add(new Telefono(tel, tipo));
//            numeroAgregar.clear();
//            nombreFxml = "FXMLS/Aviso1.fxml";
//            tipoAgregar.setValue(Tipo.Ninguno);
//        }
//        else{
//            nombreFxml = "FXMLS/Error3.fxml";
//        }
//        Parent root = FXMLLoader.load(getClass().getResource(nombreFxml));
//        Stage ventana = new Stage();
//        ventana.initStyle(StageStyle.TRANSPARENT);
//        ventana.setScene(new Scene(root));
//        ventana.setResizable(false);
//        ventana.show();
//    }
//
//    @FXML
//    public void subastasActivas(ActionEvent event){
//
//        registrarUP.setVisible(false);
//        flecha1.setVisible(false);
//
//        modificarUP.setVisible(false);
//        flecha2.setVisible(false);
//
//        subastasActivasP.setVisible(true);
//        flecha3.setVisible(true);
//
//        pujasSubastaP.setVisible(false);
//        flecha4.setVisible(false);
//
//        subasvendedorP.setVisible(false);
//        flecha5.setVisible(false);
//
//        comprasCompP.setVisible(false);
//        flecha6.setVisible(false);
//
//    }
//
//    @FXML
//    public void mostrarSubcategorías(MouseEvent event){
//        ObservableList<String> subcategorias = FXCollections.observableArrayList("oro", "gatos", "hola");
//        String categoria = categoriasCB.getValue();
//        //cosas de bd
//        subCategoriasCB.setItems(subcategorias);
//    }
//
//    @FXML
//    public void pujasPorSubasta(ActionEvent event){
//
//        registrarUP.setVisible(true);
//        flecha1.setVisible(true);
//
//        modificarUP.setVisible(false);
//        flecha2.setVisible(false);
//
//        subastasActivasP.setVisible(false);
//        flecha3.setVisible(false);
//
//        pujasSubastaP.setVisible(false);
//        flecha4.setVisible(false);
//
//        subasvendedorP.setVisible(false);
//        flecha5.setVisible(false);
//
//        comprasCompP.setVisible(false);
//        flecha6.setVisible(false);
//
//    }
//
//    @FXML
//    public void subastasPorVendedor(ActionEvent event){
//
//        registrarUP.setVisible(true);
//        flecha1.setVisible(true);
//
//        modificarUP.setVisible(false);
//        flecha2.setVisible(false);
//
//        subastasActivasP.setVisible(false);
//        flecha3.setVisible(false);
//
//        pujasSubastaP.setVisible(false);
//        flecha4.setVisible(false);
//
//        subasvendedorP.setVisible(false);
//        flecha5.setVisible(false);
//
//        comprasCompP.setVisible(false);
//        flecha6.setVisible(false);
//
//    }
//
//    @FXML
//    public void comprasPorComprador(ActionEvent event){
//
//        registrarUP.setVisible(true);
//        flecha1.setVisible(true);
//
//        modificarUP.setVisible(false);
//        flecha2.setVisible(false);
//
//        subastasActivasP.setVisible(false);
//        flecha3.setVisible(false);
//
//        pujasSubastaP.setVisible(false);
//        flecha4.setVisible(false);
//
//        subasvendedorP.setVisible(false);
//        flecha5.setVisible(false);
//
//        comprasCompP.setVisible(false);
//        flecha6.setVisible(false);
//
//    }
//
//    //**************************************************************
////
////    @FXML
////    private TableColumn<Prueba, String> colum2;
////
////    @FXML
////    private TableColumn<Prueba, Integer> colum1;
////
////    @FXML
////    private TableView<Prueba> tabla;
////
////    ObservableList<Prueba> observableList = FXCollections.observableArrayList(
////
////        new Prueba(1, "Gato"),
////        new Prueba(2, "Bellacat"),
////        new Prueba(2, "Max"),
////        new Prueba(3, "Manchas"),
////        new Prueba(4, "Manchas2"),
////        new Prueba(5, "Peter"),
////        new Prueba(6, "Amarilla"),
////        new Prueba(7, "Pinto"),
////        new Prueba(8, "Rayas")
////    );
////
////    @FXML
////    public void prueba(ActionEvent event){
////        colum1.setCellValueFactory(new PropertyValueFactory<Prueba, Integer>("columna1"));
////        colum2.setCellValueFactory(new PropertyValueFactory<Prueba, String>("columna2"));
////
////        tabla.setItems(observableList);
////    }
//}
