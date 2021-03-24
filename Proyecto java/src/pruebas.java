import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

public class pruebas {

    public static void main(String[] args){
        Connection connection = null;

        try{
           Class.forName("org.postgresql.Driver");
           connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "gatoscools");

           if(connection!= null){
               System.out.println("Conexión exitosa");
           }
           else{
               System.out.println("Error en la conexión");
           }

           CallableStatement cts = connection.prepareCall("{CALL prueba_proc(?,?)}");

           cts.setString(1, "hola");
//           cts.setInt(2, 40);
           cts.registerOutParameter(2, Types.VARCHAR);

           cts.execute();

           int res = cts.getInt(2);
           System.out.println(res);

        }
        catch (Exception e){
            System.out.println("Falló");
            System.out.println(e);
        }
    }
}
