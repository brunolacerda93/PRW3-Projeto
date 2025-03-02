package br.sistema.alunos2025;

import br.sistema.alunos2025.client.TelaAluno;
import br.sistema.alunos2025.db.dao.AlunoDAO;
import br.sistema.alunos2025.db.model.Aluno;

import java.util.logging.Level;

public class Main {

    public static void main(String[] args) {

        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.WARNING);

        TelaAluno telaAluno = new TelaAluno();
        telaAluno.menu();
    }
}
