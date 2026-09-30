package siga;

public class Aluno {

    private final String nome;
    private final String matricula;
    private final double media;

    public Aluno(String nome, String matricula, double media) {
        this.nome = nome;
        this.matricula = matricula;
        this.media = media;
    }

    public String getNome()      { return nome; }
    public String getMatricula() { return matricula; }
    public double getMedia()     { return media; }

    @Override
    public String toString() {
        return matricula + " - " + nome + " (média " + media + ")";
    }
}
