import java.sql.PreparedStatement;
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
        String sql = "SELECT * FROM produtos";
        conn.connectDB();
        
        try {
            prep = conn.getConn().prepareStatement(sql);
            resultset = prep.executeQuery();
            
            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                listagem.add(produto);
            }            
            
            return listagem;
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return null;
        }
    }
    
    public int venderProduto(ProdutosDTO produto) {
        conn.connectDB();
        
        try {
            prep = conn.getConn().prepareStatement("UPDATE produtos SET status = 'Vendido' WHERE id = ?");
            prep.setInt(1, produto.getId());
            int status = prep.executeUpdate();
            return status;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
            return e.getErrorCode();
        }
    }
    
    public ProdutosDTO listarProdutosVendidos(String status) {
        conn.connectDB();
        
        try {
            ProdutosDTO produto = new ProdutosDTO();
            prep = conn.getConn().prepareStatement("SELECT * FROM produtos WHERE status = 'Vendido'");
            resultset = prep.executeQuery();
            
            if (resultset.next()) {
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                return produto;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao fazer a consulta: " + e.getMessage());
            return null;
        }
    }

}
