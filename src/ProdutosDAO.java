/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutosDAO {

    private conectaDAO conexao;
    private Connection conn;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public ProdutosDAO() {
        this.conexao = new conectaDAO();
        this.conn = this.conexao.connectDB();
    }

    public void cadastrarProduto(ProdutosDTO produto) {

        String sql = "INSERT INTO produtos(nome, valor, status) VALUES (?, ?, ?)";
        try {
            PreparedStatement prep = this.conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            prep.execute();

        } catch (SQLException ex) {
            System.out.println("Erro ao cadastrar produto :" + ex.getMessage());
        }

    }

    public ArrayList<ProdutosDTO> listarProdutos() {

        String sql = "SELECT* FROM produtos";

        try {

            PreparedStatement pstm = this.conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            ArrayList<ProdutosDTO> listaProdutos = new ArrayList<>();

            while (rs.next()) {
                ProdutosDTO produtos = new ProdutosDTO();

                produtos.setId(rs.getInt("id"));
                produtos.setNome(rs.getString("nome"));
                produtos.setValor(rs.getInt("valor"));
                produtos.setStatus(rs.getString("Status"));

                listaProdutos.add(produtos);
            }

            return listaProdutos;

        } catch (SQLException e) {
            return null;
        }

    }

}
