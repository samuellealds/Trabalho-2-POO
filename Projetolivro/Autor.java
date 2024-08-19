package Projetolivro;

import java.time.LocalDate;

public class Autor {
    private int id;
    private String nome;
    private String biografia;
    private LocalDate dataNascimento;

    public Autor(String nome, String biografia, LocalDate dataNascimento) {
        this.nome = nome;
        this.biografia = biografia;
        this.dataNascimento = dataNascimento;
    }

    public Autor(int id, String nome, String biografia, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.biografia = biografia;
        this.dataNascimento = dataNascimento;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}