package Projetolivro;

import java.time.LocalDate;

public class Livro {
    private int id;
    private String titulo;
    private String isbn;
    private LocalDate dataPublicacao;
    private Autor autor;

    public Livro(String titulo, String isbn, LocalDate dataPublicacao, Autor autor) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.dataPublicacao = dataPublicacao;
        this.autor = autor;
    }

    public Livro(int id, String titulo, String isbn, LocalDate dataPublicacao, Autor autor) {
        this.id = id;
        this.titulo = titulo;
        this.isbn = isbn;
        this.dataPublicacao = dataPublicacao;
        this.autor = autor;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}