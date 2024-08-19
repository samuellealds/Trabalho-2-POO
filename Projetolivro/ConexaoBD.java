package Projetolivro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    private static ConexaoBD instancia;
    private Connection conexao;

    private static final String URL = "jdbc:mysql://localhost:3306/loja_livros";
    private static final String USUARIO = "root";
    private static final String SENHA = "root";

    private ConexaoBD() {
        try {
            this.conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar com o banco de dados", e);
        }
    }

    public static ConexaoBD getInstancia() {
        if (instancia == null) {
            instancia = new ConexaoBD();
        }
        return instancia;
    }

    public Connection getConexao() {
        return conexao;
    }

    public void fecharConexao() {
        if (conexao != null) {
            try {
                conexao.close();
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar a conexão", e);
            }
        }
    }
}
