package siga;

import java.util.List;

/**
 * Código INICIAL da atividade — camada de serviço incompleta.
 *
 * DESLIZE 3 — validação duplicada e divergente (etapa 3):
 * a regra da média aparece aqui E na camada de apresentação (Main), com
 * LIMITES DIFERENTES: aqui aceita até 10, lá aceita até 100. Quando a mesma
 * regra mora em dois lugares, elas divergem com o tempo — e ninguém sabe qual
 * é a verdadeira. A correção é centralizar a regra do domínio no serviço.
 *
 * PENDENTE (etapa 2): as operações de consulta, alteração e exclusão ainda
 * não foram implementadas, e a validação não está extraída em um método
 * privado reutilizável.
 *
 * Tarefa:
 *   - Etapa 2: implementar as operações do serviço e extrair validar(...);
 *   - Etapa 3: eliminar a duplicação da regra entre serviço e apresentação.
 */
public class ServicoAluno {

    private final AlunoDAO dao;

    public ServicoAluno(AlunoDAO dao) {   // injeção de dependência (DIP)
        this.dao = dao;
    }

    public void cadastrar(Aluno aluno) {
        validar(aluno);
        dao.inserir(aluno);
    }

    private void validar(Aluno aluno) {
        if (aluno == null) {
            throw new IllegalArgumentException("Aluno é obrigatório.");
        }
        if (aluno.getNome() == null || aluno.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome é obrigatório.");
        }
        if (aluno.getMedia() < 0 || aluno.getMedia() > 10) {
            throw new IllegalArgumentException("Média deve estar entre 0 e 10.");
        }
    }

    public List<Aluno> listar() {
        return dao.listarTodos();
    }

    public Aluno consultar(String matricula) {
        Aluno aluno = dao.buscarPorMatricula(matricula);
        if (aluno == null) {
            throw new IllegalArgumentException("Matrícula " + matricula + " não encontrada.");
        }
        return aluno;
    }

    public void alterar(Aluno aluno) {
        validar(aluno);
        consultar(aluno.getMatricula());
        dao.atualizar(aluno);
    }

    public void excluir(String matricula) {
        consultar(matricula);
        dao.remover(matricula);
    }
}
