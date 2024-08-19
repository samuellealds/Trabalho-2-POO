package Projetolivro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    public void inserirLivro(Livro livro) {
        String sql = "INSERT INTO Livros (titulo, isbn, data_publicacao, autor_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexaoBD.getInstancia().getConexao();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getIsbn());
            stmt.setDate(3, Date.valueOf(livro.getDataPublicacao()));
            stmt.setInt(4, livro.getAutor().getId());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                livro.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir livro", e);
        }
    }

    public void atualizarLivro(Livro livro) {
        String sql = "UPDATE Livros SET titulo = ?, isbn = ?, data_publicacao = ?, autor_id = ? WHERE id = ?";
        try (Connection conn = ConexaoBD.getInstancia().getConexao();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getIsbn());
            stmt.setDate(3, Date.valueOf(livro.getDataPublicacao()));
            stmt.setInt(4, livro.getAutor().getId());
            stmt.setInt(5, livro.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar livro", e);
        }
    }

    public void excluirLivro(int id) {
        String sql = "DELETE FROM Livros WHERE id = ?";
        try (Connection conn = ConexaoBD.getInstancia().getConexao();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir livro", e);
        }
    }

    public List<Livro> listarTodosLivros() {
        String sql = "SELECT * FROM Livros";
        List<Livro> livros = new ArrayList<>();
        try (Connection conn = ConexaoBD.getInstancia().getConexao();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Autor autor = new AutorDAO().buscarAutorPorId(rs.getInt("autor_id"));
                Livro livro = new Livro(rs.getInt("id"), rs.getString("titulo"), rs.getString("isbn"),
                        rs.getDate("data_publicacao").toLocalDate(), autor);
                livros.add(livro);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar livros", e);
        }
        return livros;
    }

    public List<Livro> listarLivrosPorAutor(int autorId) {
        String sql = "SELECT * FROM Livros WHERE autor_id = ?";
        List<Livro> livros = new ArrayList<>();
        try (Connection conn = ConexaoBD.getInstancia().getConexao();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, autorId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Autor autor = new AutorDAO().buscarAutorPorId(autorId);
                Livro livro = new Livro(rs.getInt("id"), rs.getString("titulo"), rs.getString("isbn"),
                        rs.getDate("data_publicacao").toLocalDate(), autor);
                livros.add(livro);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar livros por autor", e);
        }
        return livros;
    }
}