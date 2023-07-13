import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectaDAO {

    Connection conn;

    public boolean connectDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/leiloes", "root", "MySQLcourse8");
            return true;
        } catch (ClassNotFoundException | SQLException erro) {
            System.out.println("Erro ConectaDAO" + erro.getMessage());
            return false;
        }
    }

    public Connection getConn() {
        return conn;
    }    
        
    public void desconectar() {
        try {
            conn.close();
        } catch (SQLException ex) {
            
        }
    }

}
