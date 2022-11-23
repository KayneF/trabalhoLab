package persistence;

import model.Produto;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDao implements IProdutoDao {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/testdb?allowMultiQueries=true";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "321321";
    private Connection conn;

    public ProdutoDao() {
        try {
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    // =====================================================
    // Insere item no Banco
    @Override
    public void inserir(Produto produto) {
        String query = "INSERT INTO produtos " +
                "(id, item, marca, modelo, cor, specs, preco) " +
                " VALUES " +
                "(0, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, produto.getItem());
            statement.setString(2, produto.getMarca());
            statement.setString(3, produto.getModelo());
            statement.setString(4, produto.getCor());
            statement.setString(5, produto.getSpecs());
            statement.setString(6, String.valueOf(produto.getPreco()));
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto adicionado com sucesso! ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // =====================================================
    // Pesquisa Item no Banco
    @Override
    public List<Produto> pesquisar(String item) {
        List<Produto> lista = new ArrayList<>();
        String query = "SELECT * FROM produtos WHERE item LIKE '%" + item + "%'";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(Integer.parseInt(rs.getString("id")));
                produto.setItem(rs.getString("item"));
                produto.setMarca(rs.getString("marca"));
                produto.setModelo(rs.getString("modelo"));
                produto.setCor(rs.getString("cor"));
                produto.setSpecs(rs.getString("specs"));
                produto.setPreco(Float.parseFloat(rs.getString("preco")));
                lista.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // =====================================================
    // Altera item do Banco
    @Override
    public void alterar(Produto produto) {
        PreparedStatement statement;
        String query = "UPDATE produtos " +
                "SET  item = ?, marca = ?, modelo = ?, cor = ?, specs = ?, preco = ? " +
                "WHERE id = ? ";
        try {
            statement = conn.prepareStatement(query);
            statement.setString(1, produto.getItem());
            statement.setString(2, produto.getMarca());
            statement.setString(3, produto.getModelo());
            statement.setString(4, produto.getCor());
            statement.setString(5, produto.getSpecs());
            statement.setDouble(6, produto.getPreco());
            statement.setInt(7, produto.getId());
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto alterado com sucesso! ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // =====================================================
    // Exclui item do banco
    @Override
    public void excluir(Produto produto) {
        PreparedStatement statement;
        String query = "DELETE FROM produtos WHERE id = ? ";
        try {
            statement = conn.prepareStatement(query);
            statement.setInt(1, produto.getId());
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto removido com sucesso! ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // =====================================================
    // Lista todos os itens
    @Override
    public List<Produto> listarItems() {
        List<Produto> lista = new ArrayList<>();
        String query = "SELECT * FROM produtos";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(Integer.parseInt(rs.getString("id")));
                produto.setItem(rs.getString("item"));
                produto.setMarca(rs.getString("marca"));
                produto.setModelo(rs.getString("modelo"));
                produto.setCor(rs.getString("cor"));
                produto.setSpecs(rs.getString("specs"));
                produto.setPreco(Float.parseFloat(rs.getString("preco")));
                lista.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
