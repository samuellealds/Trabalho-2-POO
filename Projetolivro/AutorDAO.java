package Projetolivro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutorDAO {

    public void inserirAutor(Autor autor) {
        String sql = "INSERT INTO Autores (nome, biografia, data_nascimento) VALUES (?, ?, ?)";
        try (Connection conn = ConexaoBD.getInstancia().getConexao();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, autor.getNome());
            stmt.setString(2, autor.getBiografia());
            stmt.setDate(3, Date.valueOf(autor.getDataNascimento()));
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                autor.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir autor", e);
        }
    }

    public void atualizarAutor(Autor autor) {
        String sql = "UPDATE Autores SET nome = ?, biografia = ?, data_nascimento = ? WHERE id = ?";
        try (Connection conn = ConexaoBD.getInstancia().getConexao();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, autor.getNome());
            stmt.setString(2, autor.getBiografia());
            stmt.setDate(3, Date.valueOf(autor.getDataNascimento()));
            stmt.setInt(4, autor.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar autor", e);
        }
    }

    public void excluirAutor(int id) {
        String sql = "DELETE FROM Autores WHERE id = ?";
        try (Connection conn = ConexaoBD.getInstancia().getConexao();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir autor", e);
        }
    }

    public List<Autor> listarTodosAutores() {
        String sql = "SELECT * FROM Autores";
        List<Autor> autores = new ArrayList<>();
        try (Connection conn = ConexaoBD.getInstancia().getConexao();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Autor autor = new Autor(rs.getInt("id"), rs.getString("nome"),
                        rs.getString("biografia"), rs.getDate("data_nascimento").toLocalDate());
                autores.add(autor);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar autores", e);
        }
        return autores;
    }

    public Autor buscarAutorPorId(int id) {
        String sql = "SELECT * FROM Autores WHERE id = ?";
        try (Connection conn = ConexaoBD.getInstancia().getConexao();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Autor(rs.getInt("id"), rs.getString("nome"),
                        rs.getString("biografia"), rs.getDate("data_nascimento").toLocalDate());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar autor", e);
        }
        return null;
    }
}