package Projetolivro;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AutorDAO autorDAO = new AutorDAO();
        LivroDAO livroDAO = new LivroDAO();

        // Inserindo um autor
        Autor autor = new Autor("J.K. Rowling", "Autora de Harry Potter", LocalDate.of(1965, 7, 31));
        autorDAO.inserirAutor(autor);

        // Inserindo livros para o autor
        Livro livro1 = new Livro("Harry Potter e a Pedra Filosofal", "9780747532699", LocalDate.of(1997, 6, 26), autor);
        Livro livro2 = new Livro("Harry Potter e a Câmara Secreta", "9780747538493", LocalDate.of(1998, 7, 2), autor);
        livroDAO.inserirLivro(livro1);
        livroDAO.inserirLivro(livro2);

        // Listando todos os livros do autor
        List<Livro> livrosDoAutor = livroDAO.listarLivrosPorAutor(autor.getId());
        System.out.println("Livros do autor " + autor.getNome() + ":");
        for (Livro livro : livrosDoAutor) {
            System.out.println(livro.getTitulo());
        }

        // Fechar conexão
        ConexaoBD.getInstancia().fecharConexao();
    }
}