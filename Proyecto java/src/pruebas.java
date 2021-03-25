import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;


public class pruebas {

    public static void main(String[] args){

        try{
           //Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pruebas", "postgres", "Admin");
            System.out.println("Conexión exitosa pvtos");

            String sqlLine = "CALL pruebaprro (?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sqlLine);
            statement.setInt(1,10);
            statement.setInt(2,9);
            statement.setInt(3,0);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
              System.out.println(resultSet.getString(1));
            }

//            CallableStatement callableStatement = connection.prepareCall("SELECT nombre FROM ?");
//            callableStatement.setString(1, "pruebaJava");
//            //callableStatement.registerOutParameter(2, Types.VARCHAR);
//            ResultSet resultSet = callableStatement.executeQuery();
//            while (resultSet.next()){
//                System.out.println(resultSet.getString("nombre"));
//            }
        }
        catch (Exception e){
            System.out.println("Error de conexión");
            System.out.println(e);
        }
    }
}
