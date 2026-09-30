package siga;

import java.util.List;

public interface AlunoDAO {

    void inserir(Aluno aluno);                    // Create
    Aluno buscarPorMatricula(String matricula);   // Read (um)
    List<Aluno> listarTodos();                    // Read (vários)
    void atualizar(Aluno aluno);                  // Update
    void remover(String matricula);               // Delete
}
