
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;

public class ProdutosDAO {

    ConectaDAO conn = new ConectaDAO();
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public int cadastrarProduto(ProdutosDTO produtos) {
        conn.connectDB();

        try {
            prep = conn.getConn().prepareStatement("INSERT INTO produtos(nome, valor, status) VALUES(?,?,?)");
            prep.setString(1, produtos.getNome());
            prep.setInt(2, produtos.getValor());
            prep.setString(3, produtos.getStatus());
            int status = prep.executeUpdate();
            return status;
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar dados: " + e.getMessage());
            return e.getErrorCode();
        }
    }

    public ArrayList<ProdutosDTO> listarProdutos() {

        return listagem;
    }

}
