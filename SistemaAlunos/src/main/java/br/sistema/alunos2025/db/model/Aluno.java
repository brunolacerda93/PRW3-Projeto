package br.sistema.alunos2025.db.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "alunos")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String ra;
    private String email;
    private BigDecimal nota1;
    private BigDecimal nota2;
    private BigDecimal nota3;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getNota1() {
        return nota1;
    }

    public void setNota1(BigDecimal nota1) {
        this.nota1 = nota1;
    }

    public BigDecimal getNota2() {
        return nota2;
    }

    public void setNota2(BigDecimal nota2) {
        this.nota2 = nota2;
    }

    public BigDecimal getNota3() {
        return nota3;
    }

    public void setNota3(BigDecimal nota3) {
        this.nota3 = nota3;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + "\n" +
                "RA: " + ra + "\n" +
                "Email: " + email + "\n" +
                "Notas: [" + displayGrade(nota1) + "] [" + displayGrade(nota2) + "] [" + displayGrade(nota3) + "]";
    }

    private String displayGrade(BigDecimal grade) {
        return grade.setScale(1, RoundingMode.HALF_EVEN).toString();
    }
}
