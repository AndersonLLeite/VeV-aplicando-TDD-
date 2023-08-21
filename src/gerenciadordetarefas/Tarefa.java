package gerenciadordetarefas;

public class Tarefa {
    private String titulo;
    private String descricao;
    private String dataVencimento;
    private int prioridade;

    public Tarefa(String titulo, String descricao, String dataVencimento, int prioridade) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataVencimento = dataVencimento;
        this.prioridade = prioridade;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public String getDescricao() {
        return this.descricao;
    }
    public String getDataVencimento() {
        return this.dataVencimento;
    }
    public int getPrioridade() {
        return this.prioridade;
    }
}
