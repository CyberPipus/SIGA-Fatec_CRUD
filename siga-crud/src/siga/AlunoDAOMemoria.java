package siga;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAOMemoria implements AlunoDAO {

    private final List<Aluno> armazem = new ArrayList<>();

    @Override
    public void inserir(Aluno aluno) {
        if (buscarPorMatricula(aluno.getMatricula()) != null) {
            throw new IllegalStateException("Matrícula " + aluno.getMatricula() + " já cadastrada.");
        }
        armazem.add(aluno);
    }

    @Override
    public Aluno buscarPorMatricula(String matricula) {
        for (Aluno aluno : armazem) {
            if (aluno.getMatricula().equals(matricula)) {
                return aluno;
            }
        }
        return null;   // não encontrado
    }

    @Override
    public List<Aluno> listarTodos() {
        return new ArrayList<>(armazem);
    }

    @Override
    public void atualizar(Aluno aluno) {
        Aluno encontrado = buscarPorMatricula(aluno.getMatricula());
        if (encontrado == null) {
            throw new IllegalStateException("Matrícula " + aluno.getMatricula() + " não encontrada.");
        }
        armazem.set(armazem.indexOf(encontrado), aluno);
    }

    @Override
    public void remover(String matricula) {
        Aluno encontrado = buscarPorMatricula(matricula);
        if (encontrado == null) {
            throw new IllegalStateException("Matrícula " + matricula + " não encontrada.");
        }
        armazem.remove(encontrado);
    }
}
