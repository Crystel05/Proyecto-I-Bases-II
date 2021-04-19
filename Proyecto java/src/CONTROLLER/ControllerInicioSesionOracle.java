package CONTROLLER;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

import java.io.StringReader;
import java.sql.CallableStatement;
import java.sql.ResultSet;

public class ControllerInicioSesionOracle {

    public ControllerInicioSesionOracle() {}

    public boolean iniciarSesion(boolean esAdmin, String alias, String contrasena){
        int entrada = 0;
        if (esAdmin)
            entrada = 1;
        try {
            CallableStatement statement = OracleConexion.getInstance().connection.prepareCall("CALL VERIFICARINICIOSESION(?,?,?)");
            statement.setInt(1, entrada);
            statement.setString(2, alias);
            statement.registerOutParameter(3, OracleTypes.CURSOR);
            statement.execute();
            ResultSet resultado = ((OracleCallableStatement) statement).getCursor(3);
            while (resultado.next()) {
                System.out.println(resultado.getString(1));

                if(resultado.getString(1).equals(contrasena))
                    return true;
            }
        } catch (Exception e){
            System.out.println("Error de conexión");
            System.out.println(e);
            return false;
        }
        return false;
    }

    public static void main(String[] args){
        ControllerInicioSesionOracle oracle = new ControllerInicioSesionOracle();
        System.out.println(oracle.iniciarSesion(false, "Batman", "12345"));
    }

}
