package VIEW;

import CONTROLLER.ControllerGUI;
import MODEL.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class ControllerPrincipal {

    public ControllerPrincipal() {}

    //Atributos FX

    //->Atributos inicio sesión

    @FXML private RadioButton BParticipante;
    @FXML private RadioButton BAdministrador;
    @FXML private PasswordField contrasenna;
    @FXML private TextField alias;
    @FXML private TextField contraVer;
    @FXML private ImageView contraVersigno;
    @FXML private ImageView contranNoVerSigno;

    //->Atributos Tareas

    @FXML private Pane panelInicioS;
    @FXML private Pane panelTareas;
    @FXML private ImageView flecha1;
    @FXML private ImageView flecha2;
    @FXML private ImageView flecha3;
    @FXML private ImageView flecha4;
    @FXML private ImageView flecha5;
    @FXML private ImageView flecha6;
    @FXML private ImageView flecha7;

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
    @FXML private Button blanco;

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
    @FXML private Label nombreEditar;
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
    @FXML private ComboBox<Integer> numeros;
    @FXML private ComboBox<String> tipoEd;
    @FXML private TextField numeroETF;
    @FXML private Pane agregarNTel;
    @FXML private ComboBox<String> tipoAgregar;
    @FXML private TextField numeroAgregar;

    //->>>Subastas Activas

    @FXML private ComboBox<String> categoriasCB;
    @FXML private ComboBox<String> subCategoriasCB;
    @FXML private TableView<Subasta> tablaSubastasAc;
    @FXML private TableColumn<Subasta, String> itemSA;
    @FXML private TableColumn<Subasta, Float> mejorPujaSA;
    @FXML private TableColumn<Subasta,Date> fechaFinSa;
    @FXML private Pane detallesSusAcPanel;
    @FXML private Pane tablasSusActsPanel;

    @FXML private Label nombreSuAc;
    @FXML private TextArea descSuA;
    @FXML private ImageView fotoSuAct;
    @FXML private Label nombrePSA;
    @FXML private Label montoSA;

    //->>> Historial pujas x subasta
    @FXML private ComboBox<String> subastasHistPCB;
    @FXML private TableView<Puja> TpujasXsubasta;
    @FXML private TableColumn<Puja, Date> Cfecha;
    @FXML private TableColumn<Puja, Float> CcantPuja;


    //->>> Historial compras x comprador
    @FXML private ComboBox<String> usuariosCompra;
    @FXML private TableView<Subasta> TcomprasXcomprador;
    @FXML private TableColumn<Subasta,String> CitemComprador;
    @FXML private TableColumn<Subasta,Float> CprecioBC;
    @FXML private TableColumn<Subasta,Float> CprecioFinalC;
    @FXML private TableColumn<Subasta,Date> CfechaCo;
    @FXML private Pane tablaCXCpanel;
    @FXML private Pane comentarioCXCPanel;
    @FXML private Label nombreUsuarioCom;
    @FXML private Label vendedorCXC;
    @FXML private TextArea comenCXC;
    @FXML private ImageView estrella1C;
    @FXML private ImageView estrella2C;
    @FXML private ImageView estrella3C;
    @FXML private ImageView estrella4C;
    @FXML private ImageView estrella5C;

    //->>> Historial subastas x vendedor
    @FXML private ComboBox<String> vendedores;
    @FXML private TableView<Subasta> tablaSubsXven;
    @FXML private TableColumn<Subasta, String> colItemSXV;
    @FXML private TableColumn<Subasta, Date> colfechaSXV;
    @FXML private TableColumn<Subasta, Float> colprecioISXV;
    @FXML private TableColumn<Subasta, Float> colprecioFSXV;
    @FXML private Pane tablaSubsVenPanel;
    @FXML private Pane comentarioSXVPanel;
    @FXML private Label NombreGanadorSXV;
    @FXML private TextArea comenSXV;
    @FXML private Label nombreVendedor;
    @FXML private ImageView estrella1;
    @FXML private ImageView estrella2;
    @FXML private ImageView estrella3;
    @FXML private ImageView estrella4;
    @FXML private ImageView estrella5;

    //->>Atributos Participante

    @FXML private Label titPart;
    @FXML private Button iniciarSubastaB;
    @FXML private Button pujarIB;
    @FXML private Button susSubastasB;
    @FXML private Pane iniciarSubastaPanel;
    @FXML private Pane pujarPanel;
    @FXML private Pane susSubastasPanel;
    @FXML private Label montoIP;
    @FXML private Label fechaFIP;
    @FXML private ImageView fotoIP;
    @FXML private TextArea detallesIP;
    @FXML private TextField montoPujar;

    //->>> Iniciar subasta
    @FXML private TextField nombreItemIS;
    @FXML private TextArea descrIS;
    @FXML private ComboBox<String> categoriaIS;
    @FXML private ComboBox<String> subCateIS;
    @FXML private TextField precioInicialIS;
    @FXML private DatePicker fechaFinIS;
    @FXML private TextField horaFinIS;
    @FXML private TextArea entregaIS;
    @FXML private TextField montoMinimo;
    @FXML private ImageView check;

    //->>>Pujar por ítem

    @FXML private ComboBox<String> subastasPujarCB;

    //->>> susSubastas
    @FXML private RadioButton ganadasRB;
    @FXML private RadioButton hechasRB;
    @FXML private ComboBox<String> subastasCB;
    @FXML private TextArea descpSubastas;
    @FXML private ImageView fotoSubastas;
    @FXML private TextArea entregaSubasta;
    @FXML private TextArea comentarioSubasta;
    @FXML private Slider calificacionSubasta;
    @FXML private Label labelComprador;
    @FXML private RadioButton si;
    @FXML private RadioButton no;
    @FXML private Pane detallesPa;
    @FXML private ImageView es1;
    @FXML private ImageView es2;
    @FXML private ImageView es3;
    @FXML private ImageView es4;
    @FXML private ImageView es5;


    //Atributos extras

    private ArrayList<Telefono> telefonos = new ArrayList<>();
    private ControllerGUI GUI = ControllerGUI.getInstance();
    private boolean esAdmin;
    private ArrayList<String> ceds = new ArrayList<>();
    private String cedulaEditar;
    private ArrayList<String> cedsE = new ArrayList<>();
    private ArrayList<Usuario> usuariosE;
    private String path;
    private ArrayList<ImageView> estrellas = new ArrayList<>();
    private ArrayList<Subasta> susSubastas;

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
            flecha7.setVisible(false);
            modificarUP.setVisible(false);
            subastasActivasP.setVisible(false);
            pujasSubastaP.setVisible(false);
            subasvendedorP.setVisible(false);
            comprasCompP.setVisible(false);
            pujarPanel.setVisible(false);
            susSubastasPanel.setVisible(false);

            if(BAdministrador.isSelected()){

                registrarB.setVisible(true);
                modificarB.setVisible(true);
                iniciarSubastaB.setVisible(false);
                pujarIB.setVisible(false);
                titAdm.setVisible(true);
                titPart.setVisible(false);
                registrarUP.setVisible(true);
                iniciarSubastaPanel.setVisible(false);
                susSubastasB.setVisible(false);
                blanco.setVisible(true);
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
                susSubastasB.setVisible(true);
                blanco.setVisible(false);
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

    @FXML
    public void verContrasena(MouseEvent event){
        contraVer.setText(contrasenna.getText());
        contrasenna.setVisible(false);
        contraVer.setVisible(true);
        contraVersigno.setVisible(true);
        contranNoVerSigno.setVisible(false);
    }

    @FXML
    public void ocultarContrasena(MouseEvent event){
        contrasenna.setVisible(true);
        contrasenna.setText(contraVer.getText());
        contraVer.setVisible(false);
        contraVersigno.setVisible(false);
        contranNoVerSigno.setVisible(true);
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

    @FXML
    public void subastasActivas(ActionEvent event){

        tablasSusActsPanel.setVisible(true);
        detallesSusAcPanel.setVisible(false);

        categoriasCB.setValue("No seleccionado");

        ObservableList<String> categorias = FXCollections.observableArrayList(GUI.categorias());
        ObservableList<Subasta> subastasA = FXCollections.observableArrayList(GUI.subastasTablaSin());

        categoriasCB.setItems(categorias);
        subCategoriasCB.setValue("No seleccionado");

        itemSA.setCellValueFactory(new PropertyValueFactory<>("nomIt"));
        fechaFinSa.setCellValueFactory(new PropertyValueFactory<>("fachaFinal"));
        mejorPujaSA.setCellValueFactory(new PropertyValueFactory<>("mejorMonto"));
        tablaSubastasAc.setItems(subastasA);

        tablaSubastasAc.setItems(subastasA);

        registrarUP.setVisible(false);
        flecha1.setVisible(false);

        modificarUP.setVisible(false);
        flecha2.setVisible(false);

        susSubastasPanel.setVisible(false);
        flecha3.setVisible(false);

        subastasActivasP.setVisible(true);
        flecha4.setVisible(true);

        pujasSubastaP.setVisible(false);
        flecha5.setVisible(false);

        subasvendedorP.setVisible(false);
        flecha6.setVisible(false);

        comprasCompP.setVisible(false);
        flecha7.setVisible(false);

        iniciarSubastaPanel.setVisible(false);
        pujarPanel.setVisible(false);
        flecha3.setVisible(false);

    }

    @FXML
    public void pujasPorSubasta(ActionEvent event){

        ObservableList<String> nombres = FXCollections.observableArrayList(GUI.nombreSubastas());
        subastasHistPCB.setItems(nombres);

        subastasHistPCB.setValue("No seleccionado");
        ObservableList<Puja> nada = FXCollections.observableArrayList();
        TpujasXsubasta.setItems(nada);

        registrarUP.setVisible(false);
        flecha1.setVisible(false);

        modificarUP.setVisible(false);
        flecha2.setVisible(false);

        susSubastasPanel.setVisible(false);
        flecha3.setVisible(false);

        subastasActivasP.setVisible(false);
        flecha4.setVisible(false);

        pujasSubastaP.setVisible(true);
        flecha5.setVisible(true);

        subasvendedorP.setVisible(false);
        flecha6.setVisible(false);

        comprasCompP.setVisible(false);
        flecha7.setVisible(false);

        iniciarSubastaPanel.setVisible(false);
        pujarPanel.setVisible(false);
        flecha3.setVisible(false);
    }

    @FXML
     public void subastasPorVendedor(ActionEvent event) {

        estrellas.add(estrella1); estrellas.add(estrella2); estrellas.add(estrella3); estrellas.add(estrella4); estrellas.add(estrella5);

        for(ImageView estrella: estrellas){
            estrella.setVisible(false);
        }

        ArrayList<String> ceds = new ArrayList<>();
        for (Usuario u : GUI.usuariosMostrar()) {
            ceds.add(u.getDocIdent());
        }

        vendedores.setValue("No seleccionado");
        tablaSubsXven.getItems().clear();
        nombreVendedor.setText("");

        tablaSubsVenPanel.setVisible(true);
        comentarioSXVPanel.setVisible(false);

        ObservableList<String> vend = FXCollections.observableArrayList(ceds);
        vendedores.setItems(vend);

        registrarUP.setVisible(false);
        flecha1.setVisible(false);

        modificarUP.setVisible(false);
        flecha2.setVisible(false);

        susSubastasPanel.setVisible(false);
        flecha3.setVisible(false);

        subastasActivasP.setVisible(false);
        flecha4.setVisible(false);

        pujasSubastaP.setVisible(false);
        flecha5.setVisible(false);

        subasvendedorP.setVisible(true);
        flecha6.setVisible(true);

        comprasCompP.setVisible(false);
        flecha7.setVisible(false);

        iniciarSubastaPanel.setVisible(false);
        pujarPanel.setVisible(false);
        flecha3.setVisible(false);

    }

    @FXML
    public void comprasPorComprador(ActionEvent event){

        estrellas.clear();
        estrellas.add(estrella1C);
        estrellas.add(estrella2C);
        estrellas.add(estrella3C);
        estrellas.add(estrella4C);
        estrellas.add(estrella5C);

        tablaCXCpanel.setVisible(true);
        comentarioCXCPanel.setVisible(false);

        for(ImageView estrella: estrellas){
            estrella.setVisible(false);
        }

        usuariosCompra.setValue("No seleccionado");
        TcomprasXcomprador.getItems().clear();
        nombreUsuarioCom.setText("");


        for (Usuario user: GUI.usuariosMostrar()){
            ceds.add(user.getDocIdent());
        }
        ObservableList<String> cedulas = FXCollections.observableArrayList(ceds);
        usuariosCompra.setItems(cedulas);

        registrarUP.setVisible(false);
        flecha1.setVisible(false);

        modificarUP.setVisible(false);
        flecha2.setVisible(false);

        susSubastasPanel.setVisible(false);
        flecha3.setVisible(false);

        subastasActivasP.setVisible(false);
        flecha4.setVisible(false);

        pujasSubastaP.setVisible(false);
        flecha5.setVisible(false);

        subasvendedorP.setVisible(false);
        flecha6.setVisible(false);

        comprasCompP.setVisible(true);
        flecha7.setVisible(true);

        iniciarSubastaPanel.setVisible(false);
        pujarPanel.setVisible(false);
        flecha3.setVisible(false);
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
        flecha4.setVisible(false);

        pujasSubastaP.setVisible(false);
        flecha5.setVisible(false);

        subasvendedorP.setVisible(false);
        flecha6.setVisible(false);

        comprasCompP.setVisible(false);
        flecha7.setVisible(false);


    }

    @FXML
    public void modificarUsuario(ActionEvent event){

        selecionar1P.setVisible(true);
        editarDatosP.setVisible(false);
        adminModR.setSelected(false);
        partModR.setSelected(false);
        usuariosCB.setValue("No seleccionado");
        usuariosCB.getItems().clear();
        aliasEd.clear();
        contE.clear();
        docIE.clear();
        nombreE.clear();
        dirE.clear();
        agregarNTel.setVisible(false);
        editarTelEx.setVisible(false);
        editarExR.setSelected(false);
        agregarR.setSelected(false);
        numeros.setValue(0);
        tipoEd.setValue("Ninguno");
        numeroETF.clear();
        tipoAgregar.setValue("Ninguno");
        numeroAgregar.clear();
        nombreEditar.setText("");

        registrarUP.setVisible(false);
        flecha1.setVisible(false);

        modificarUP.setVisible(true);
        flecha2.setVisible(true);

        subastasActivasP.setVisible(false);
        flecha4.setVisible(false);

        pujasSubastaP.setVisible(false);
        flecha5.setVisible(false);

        subasvendedorP.setVisible(false);
        flecha6.setVisible(false);

        comprasCompP.setVisible(false);
        flecha7.setVisible(false);
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
        usuariosE = GUI.mostrarUsuariosEditar(true);
        cedsE = new ArrayList<>();
        for(Usuario u: usuariosE){
            cedsE.add(u.getDocIdent());
        }
        usuariosCB.getItems().clear();
        ObservableList<String> cedulas = FXCollections.observableArrayList(cedsE);
        usuariosCB.setItems(cedulas);
        partModR.setSelected(false);


    }

    @FXML
    public void escoUnoPm(ActionEvent event){
        usuariosE = GUI.mostrarUsuariosEditar(false);
        cedsE = new ArrayList<>();
        for(Usuario u: usuariosE){
            cedsE.add(u.getDocIdent());
        }

        usuariosCB.getItems().clear();
        ObservableList<String> cedulas = FXCollections.observableArrayList(cedsE);
        usuariosCB.setItems(cedulas);

        adminModR.setSelected(false);
        //llenar combobox de usuarios BD
    }

    @FXML
    public void editar(ActionEvent event){
        if(!usuariosCB.getSelectionModel().isEmpty()) {
            editarDatosP.setVisible(true);
            selecionar1P.setVisible(false);

            Usuario usuario = GUI.mostrarInfoEd(cedulaEditar);
            aliasEd.setText(usuario.getAlias());
            contE.setText(usuario.getContrasenna());
            docIE.setText(usuario.getDocIdent());
            nombreE.setText(usuario.getNombreApellidos());
            dirE.setText(usuario.getDireccion());
        }

    }

    @FXML
    public void editarExistente(ActionEvent event){
        ObservableList<String> items = FXCollections.observableArrayList(GUI.cargarTipos());
        tipoEd.setItems(items);
        agregarR.setSelected(false);
        numeros.setValue(0);
        tipoEd.setValue("Ninguno");
        numeroETF.clear();
        ObservableList<Integer> nums = FXCollections.observableArrayList(GUI.mostrarTelsUs(cedulaEditar));
        numeros.setItems(nums);
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
        if(!numeroETF.getText().isEmpty() && !tipoEd.getSelectionModel().isEmpty() && !numeros.getSelectionModel().isEmpty()) { //

            int cod = GUI.modificarTel(numeros.getValue(), Integer.parseInt(numeroETF.getText()), tipoEd.getValue());

            if(cod != 1){
                nombreFXML = "FXMLS/Error8.fxml";
            }

            else{
                nombreFXML = "FXMLS/Aviso3.fxml";
                tipoEd.setValue("Ninguno");
                numeros.setValue(0);
                numeroETF.clear();
            }

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
        String fxml;
        String ced;
        ced = docIE.getText();
        int cod = GUI.modificarUsuario(cedulaEditar, nombreE.getText(), aliasEd.getText(), contE.getText(), docIE.getText(), dirE.getText());
        if (cod == 1) {
            Usuario usuario = GUI.mostrarInfoEd(ced);
            aliasEd.setText(usuario.getAlias());
            contE.setText(usuario.getContrasenna());
            docIE.setText(usuario.getDocIdent());
            nombreE.setText(usuario.getNombreApellidos());
            dirE.setText(usuario.getDireccion());
            for(Telefono tel: telefonos){
                GUI.agregarTelefono(usuario.getAlias(), usuario.getContrasenna(), tel.getNumero(), tel.getTipo());
            }

            editarExR.setSelected(false);
            agregarR.setSelected(false);
            editarTelEx.setVisible(false);
            agregarNTel.setVisible(false);
            fxml = "FXMLS/Aviso4.fxml";
        }
        else{
            fxml = "FXMLS/Error9.fxml";
        }

        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Stage ventana = new Stage();
        ventana.initStyle(StageStyle.TRANSPARENT);
        ventana.setScene(new Scene(root));
        ventana.setResizable(false);
        ventana.show();
    }

    @FXML
    public void agregarNuevoT(ActionEvent event) throws IOException {
        String nombreFxml;
        if(!numeroAgregar.getText().isEmpty() && !tipoAgregar.getSelectionModel().isEmpty()) { //
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

    @FXML
    public void ponerNombre(MouseEvent event){
        if(!usuariosCB.getSelectionModel().isEmpty()){
            String ced = usuariosCB.getValue();
            Usuario usuario = usuariosE.get(cedsE.indexOf(ced));
            nombreEditar.setText(usuario.getNombreApellidos());
            cedulaEditar = ced;

        }
    }

    //Subastas activas

    @FXML
    public void atrasSusActD(MouseEvent event){
        tablasSusActsPanel.setVisible(true);
        detallesSusAcPanel.setVisible(false);
    }

    @FXML
    public void mostrarSubCategorias(MouseEvent event){
        ObservableList<String> cats = FXCollections.observableArrayList(GUI.categorias());
        categoriasCB.setItems(cats);
        if(!categoriasCB.getSelectionModel().isEmpty()){
            System.out.println(categoriasCB.getValue());
            ObservableList<Subasta> subastas = FXCollections.observableArrayList(GUI.subastasCategoria(categoriasCB.getValue()));
            tablaSubastasAc.setItems(subastas);

            ObservableList<String> subCats = FXCollections.observableArrayList(GUI.subCategorias(categoriasCB.getValue()));
            subCategoriasCB.setItems(subCats);
        }
    }

    @FXML
    public void llenarTablaSubastasActivas(ActionEvent event){
        if(!subCategoriasCB.getSelectionModel().isEmpty()) {
            ObservableList<Subasta> subastas = FXCollections.observableArrayList(GUI.subastasFinal(categoriasCB.getValue(), subCategoriasCB.getValue()));
            tablaSubastasAc.setItems(subastas);
        }
    }

    @FXML
    public void verDetallesSubastaActiva(MouseEvent e) throws FileNotFoundException {


        if(e.getClickCount() >= 2) {
            Subasta sub = tablaSubastasAc.getSelectionModel().getSelectedItem();
            int subastaId = sub.getID();

            System.out.println(subastaId);
            Subasta subasta = GUI.detallesSubas(subastaId);

            detallesSusAcPanel.setVisible(true);
            tablasSusActsPanel.setVisible(false);

            nombreSuAc.setText(sub.getNomIt());
            descSuA.setText(subasta.getItem().getDetalles());
            FileInputStream fileInputStream = new FileInputStream(subasta.getItem().getPathFoto());
            fotoSuAct.setImage(new Image(fileInputStream));
            nombrePSA.setText(subasta.getComprador());

        }


    }

    //Métodos historial pujas x subasta
    @FXML
    public void verSubastas(MouseEvent event){
        if(!subastasHistPCB.getSelectionModel().isEmpty()) {
            ObservableList<Puja> pujas = FXCollections.observableArrayList(GUI.pujasXsubasta(subastasHistPCB.getValue()));
            CcantPuja.setCellValueFactory(new PropertyValueFactory<>("monto"));
            Cfecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
            TpujasXsubasta.setItems(pujas);
        }
    }

    //Métodos historial compra x comprador

    @FXML
    public void atrasResennaCompra(MouseEvent event) {

        tablaCXCpanel.setVisible(true);
        comentarioCXCPanel.setVisible(false);
    }

    @FXML
    public void llenarTablaYnombre(MouseEvent event) {
        if (!usuariosCompra.getSelectionModel().isEmpty()) {
            Usuario usuario;
            usuario = GUI.usuariosMostrar().get(ceds.indexOf(usuariosCompra.getValue()));
            nombreUsuarioCom.setText(usuario.getNombreApellidos());

            CitemComprador.setCellValueFactory(new PropertyValueFactory<>("nomIt"));
            CprecioBC.setCellValueFactory(new PropertyValueFactory<>("precioIni"));
            CprecioFinalC.setCellValueFactory(new PropertyValueFactory<>("mejorMonto"));
            CfechaCo.setCellValueFactory(new PropertyValueFactory<>("fechaInicio"));

            ObservableList<Subasta> itemsGanados = FXCollections.observableArrayList(GUI.llenarTablaComprasComprador(usuariosCompra.getValue()));
            TcomprasXcomprador.setItems(itemsGanados);

        }
    }

    @FXML
    public void verComentarioComp(MouseEvent event){
        if(!TcomprasXcomprador.getSelectionModel().isEmpty() && event.getClickCount() >= 2){
            Subasta subasta = TcomprasXcomprador.getSelectionModel().getSelectedItem();

            vendedorCXC.setText(subasta.getVendedor());
            comenCXC.setText(subasta.getComentario().getComentario());
            for(int i = 0; i<subasta.getComentario().getPuntaje(); i++){
                estrellas.get(i).setVisible(true);
            }
            tablaCXCpanel.setVisible(false);
            comentarioCXCPanel.setVisible(true);
        }
    }

    // subastas x vendedor
    @FXML
    public void atrasResennaSubasta(MouseEvent event) {

        tablaSubsVenPanel.setVisible(true);
        comentarioSXVPanel.setVisible(false);
    }

    @FXML
    public void tablaSusVend(MouseEvent event){
        colItemSXV.setCellValueFactory(new PropertyValueFactory<>("nomIt"));
        colfechaSXV.setCellValueFactory(new PropertyValueFactory<>("fachaFinal"));
        colprecioFSXV.setCellValueFactory(new PropertyValueFactory<>("mejorMonto"));
        colprecioISXV.setCellValueFactory(new PropertyValueFactory<>("precioIni"));

        if(!vendedores.getSelectionModel().isEmpty()) {
            nombreVendedor.setText(GUI.nombreVend(vendedores.getValue()));
            ObservableList<Subasta> subastas = FXCollections.observableArrayList(GUI.subastasXvendedor(vendedores.getValue()));
            tablaSubsXven.setItems(subastas);
        }
    }

    @FXML
    public void verComentario(MouseEvent event){
        if(!tablaSubsXven.getSelectionModel().isEmpty() && event.getClickCount() >=2) {

            comentarioSXVPanel.setVisible(true);
            tablaSubsVenPanel.setVisible(false);
            Subasta subasta = tablaSubsXven.getSelectionModel().getSelectedItem();
            NombreGanadorSXV.setText(subasta.getComprador());
            comenSXV.setText(subasta.getComentario().getComentario());

            for(int i = 0; i<subasta.getComentario().getPuntaje(); i++){
                estrellas.get(i).setVisible(true);
            }
            System.out.println(subasta.getComentario().getPuntaje());
        }
    }

    //Métodos participante
    @FXML
    public void pujar(ActionEvent event){
        ObservableList<String> nombres = FXCollections.observableArrayList(GUI.nombreSubastas());
        subastasPujarCB.setItems(nombres);

        subastasPujarCB.setValue("No seleccionado");
        fechaFIP.setText("");
        montoIP.setText("");
        fotoIP.setImage(null);
        montoPujar.clear();
        detallesIP.clear();

        iniciarSubastaPanel.setVisible(false);
        flecha1.setVisible(false);

        pujarPanel.setVisible(true);
        flecha2.setVisible(true);

        susSubastasPanel.setVisible(false);
        flecha3.setVisible(false);

        subastasActivasP.setVisible(false);
        flecha4.setVisible(false);

        pujasSubastaP.setVisible(false);
        flecha5.setVisible(false);

        subasvendedorP.setVisible(false);
        flecha6.setVisible(false);

        comprasCompP.setVisible(false);
        flecha7.setVisible(false);
    }

    @FXML
    public void iniciarSubasta(ActionEvent event){

        nombreItemIS.clear();
        descrIS.clear();
        categoriaIS.setValue("No seleccionado");
        subCateIS.setValue("No seleccionado");
        precioInicialIS.clear();
        horaFinIS.clear();
        entregaIS.clear();
        montoMinimo.setText("0");
        check.setVisible(false);

        ObservableList<String> categorias = FXCollections.observableArrayList(GUI.categorias());
        categoriaIS.setItems(categorias);

        iniciarSubastaPanel.setVisible(true);
        flecha1.setVisible(true);

        pujarPanel.setVisible(false);
        flecha2.setVisible(false);

        susSubastasPanel.setVisible(false);
        flecha3.setVisible(false);

        subastasActivasP.setVisible(false);
        flecha4.setVisible(false);

        pujasSubastaP.setVisible(false);
        flecha5.setVisible(false);

        subasvendedorP.setVisible(false);
        flecha6.setVisible(false);

        comprasCompP.setVisible(false);
        flecha7.setVisible(false);
    }

    @FXML
    public void susSubastas(ActionEvent event){
        labelComprador.setVisible(false);
        si.setVisible(false);
        no.setVisible(false);
        detallesPa.setDisable(true);
        estrellas.add(es1);
        estrellas.add(es2);
        estrellas.add(es3);
        estrellas.add(es4);
        estrellas.add(es5);

        for(ImageView es: estrellas){
            es.setVisible(false);
        }

        iniciarSubastaPanel.setVisible(false);
        flecha1.setVisible(false);

        pujarPanel.setVisible(false);
        flecha2.setVisible(false);

        susSubastasPanel.setVisible(true);
        flecha3.setVisible(true);

        subastasActivasP.setVisible(false);
        flecha4.setVisible(false);

        pujasSubastaP.setVisible(false);
        flecha5.setVisible(false);

        subasvendedorP.setVisible(false);
        flecha6.setVisible(false);

        comprasCompP.setVisible(false);
        flecha7.setVisible(false);
    }

    //Métodos iniciar subasta

    @FXML
    public void buscarImagen(ActionEvent event){

        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Buscar Imagen");

            Node source = (Node) event.getSource();
            Stage stageActual = (Stage) source.getScene().getWindow();

            File imgFile = fileChooser.showOpenDialog(stageActual);
            String[] pathAbsoluto = imgFile.getPath().split("src");
            path = ("src" + pathAbsoluto[1]).replace("\\", "/");
            check.setVisible(true);
        }
        catch (Exception e){
            System.out.println();
        }


    }

    @FXML
    public void crearSubasta(ActionEvent event) throws IOException {
        String nombreFxml;

        if(!descrIS.getText().isEmpty() && !subCateIS.getSelectionModel().isEmpty() && !categoriaIS.getSelectionModel().isEmpty()
                && !fechaFinIS.getValue().equals("") && !horaFinIS.getText().isEmpty() && !entregaIS.getText().isEmpty()) {

            String fecha = fechaFinIS.getValue() + " " + horaFinIS.getText();


            int codigo = GUI.iniciarSubasta(nombreItemIS.getText(), descrIS.getText(), path, subCateIS.getValue(), Float.valueOf(precioInicialIS.getText()),
                    fecha, entregaIS.getText(), Float.parseFloat(montoMinimo.getText()));

            if (codigo == 1) {
                nombreFxml = "FXMLS/Aviso6.fxml";
            }
            else{
                nombreFxml = "FXMLS/Error10.fxml";
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

    @FXML
    public void cargarCategorias(MouseEvent event){
        ObservableList<String> categorias = FXCollections.observableArrayList(GUI.categorias());
        categoriaIS.setItems(categorias);


    }

    @FXML
    public void cargarSubCategorias(MouseEvent event){
        if(!categoriaIS.getSelectionModel().isEmpty()){
            ObservableList<String> subs = FXCollections.observableArrayList(GUI.subCategorias(categoriaIS.getValue()));
            System.out.println(subs);
            subCateIS.setItems(subs);
        }
    }

    //Métodos pujar

    @FXML
    public void ponerDetallesItem(MouseEvent event) throws FileNotFoundException {
        if(!subastasPujarCB.getSelectionModel().isEmpty() && !subastasPujarCB.getValue().equals("No seleccionado")){
            String itemSelec = subastasPujarCB.getValue();
            Subasta subastaUsar = null;
            for(Subasta subasta: GUI.getSubastasActivas()){
                Item i = subasta.getItem();
                if(i.getNombre().equals(itemSelec)){
                    subastaUsar = subasta;
                }
            }
            detallesIP.setText(subastaUsar.getItem().getDetalles());
            FileInputStream fileInputStream = new FileInputStream(subastaUsar.getItem().getPathFoto());
            fotoIP.setImage(new Image(fileInputStream));
            fechaFIP.setText(subastaUsar.getFachaFinal().toString());
            montoIP.setText(String.valueOf(subastaUsar.getMejorMonto()));
        }
    }

    @FXML
    public void realizarOferta(ActionEvent event) throws IOException {
        String nombreFxml = "";
        if(!subastasPujarCB.getSelectionModel().isEmpty() && !montoPujar.getText().isEmpty()) {
            int codigo = GUI.pujar(Float.parseFloat(montoPujar.getText()), subastasPujarCB.getValue());
            if(codigo == 1){
                nombreFxml = "FXMLS/Aviso5.fxml";
            }
            else if(codigo == 2){
                nombreFxml = "FXMLS/Error6.fxml";
            }
            else if(codigo == 3){
                nombreFxml = "FXMLS/Error7.fxml";
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

    //Métodos sus subastas
    @FXML
    public void botonHechas(ActionEvent event){
        ganadasRB.setSelected(false);
        labelComprador.setVisible(false);
        si.setVisible(false);
        no.setVisible(false);
        susSubastas = GUI.subastasVentas(); //GUI.devolverCedulaUser()
        ArrayList<String> nombresItems = new ArrayList<>();
        for(Subasta subasta: susSubastas){
            nombresItems.add(subasta.getItem().getNombre());
        }
        ObservableList<String> subasItems = FXCollections.observableArrayList(nombresItems);
        subastasCB.setItems(subasItems);

    }

    @FXML
    public void botonGanadas(ActionEvent event){
        hechasRB.setSelected(false);
        labelComprador.setVisible(true);
        si.setVisible(true);
        no.setVisible(true);

        susSubastas = GUI.subastasCompras(); //GUI.devolverCedulaUser()
        ArrayList<String> nombresItems = new ArrayList<>();
        for(Subasta subasta: susSubastas){
            nombresItems.add(subasta.getItem().getNombre());
        }
        ObservableList<String> subasItems = FXCollections.observableArrayList(nombresItems);
        subastasCB.setItems(subasItems);
    }

    @FXML
    public void agregarDetalles(MouseEvent event) throws FileNotFoundException {
        if(!subastasCB.getSelectionModel().isEmpty()){
            detallesPa.setDisable(false);
            Subasta subasta = new Subasta();
            for(Subasta s: susSubastas){
                if(s.getItem().getNombre().equals(subastasCB.getValue()))
                    subasta = s;
            }
            descpSubastas.setText(subasta.getItem().getDetalles());
            FileInputStream fileInputStream = new FileInputStream(subasta.getItem().getPathFoto());
            fotoSubastas.setImage(new Image(fileInputStream));
            entregaSubasta.setText(subasta.getEnvio());

        }
    }

    @FXML
    public void verCalf(MouseEvent event){
        int cal = (int) calificacionSubasta.getValue();
        switch (cal){
            case 0: es1.setVisible(false); es2.setVisible(false); es3.setVisible(false); es4.setVisible(false); es5.setVisible(false); break;
            case 1: es1.setVisible(true); es2.setVisible(false); es3.setVisible(false); es4.setVisible(false); es5.setVisible(false); break;
            case 2: es1.setVisible(true); es2.setVisible(true); es3.setVisible(false); es4.setVisible(false); es5.setVisible(false); break;
            case 3: es1.setVisible(true); es2.setVisible(true); es3.setVisible(true); es4.setVisible(false); es5.setVisible(false); break;
            case 4: es1.setVisible(true); es2.setVisible(true); es3.setVisible(true); es4.setVisible(true); es5.setVisible(false); break;
            case 5: es1.setVisible(true); es2.setVisible(true); es3.setVisible(true); es4.setVisible(true); es5.setVisible(true); break;
        }
    }

    @FXML
    public void enviarComentario(ActionEvent event) throws IOException {
        String nombreFXML;
        int calificacion = (int)calificacionSubasta.getValue();
        if(!comentarioSubasta.getText().isEmpty() && calificacion != 0) {
            boolean esVend = true;
            boolean compra = true;
            if (ganadasRB.isSelected()) {
                esVend = false;
                if (si.isSelected())
                    compra = true;
                else if (no.isSelected())
                    compra = false;
            } else if (hechasRB.isSelected()) {
                esVend = true;
                compra = false;
            }
            int cod = GUI.comentarios(comentarioSubasta.getText(), calificacion, esVend, compra, subastasCB.getValue());
            if(cod == 1){
                nombreFXML = "FXMLS/Aviso7.fxml";
            }
            else{
                nombreFXML = "FXMLS/Error1.fxml";
            }
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

    //Otros métodos

    public boolean confirmarUsuario(String contrasena, String alias){
        int codigo = GUI.verificarInicioSesion(esAdmin, alias, contrasena);
        if(!contrasena.isEmpty() && !alias.isEmpty() && codigo == 1){
            return true;
        }
        return false;
    }

}
