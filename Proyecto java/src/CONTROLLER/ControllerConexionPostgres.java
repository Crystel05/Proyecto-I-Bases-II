package CONTROLLER;

import java.sql.*;

public class ControllerConexionPostgres {

    private static ControllerConexionPostgres conexionPostgres;
    public Connection connection;

    private ControllerConexionPostgres(){
        crearConexion();
    }

    public static ControllerConexionPostgres getInstance(){
        if (conexionPostgres == null){
            conexionPostgres = new ControllerConexionPostgres();
        }
        return conexionPostgres;
    }

    public void crearConexion(){

        try{
            this.connection= DriverManager.getConnection("jdbc:postgresql://localhost:5432/BDProyecto", "postgres", "Admin");
            System.out.println("Conexión a POSTGRESQL DATA BASE exitosa");
        }
        catch (Exception e){
            System.out.println("Error al conectarse a POSTGRESQL DATE BASE");
            e.printStackTrace();
        }
    }


}
