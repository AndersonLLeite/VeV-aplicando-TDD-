package gerenciadordetarefas;

import java.time.LocalDate;
import java.util.Date;

public class Tarefa implements Comparable<Tarefa>{
    private String titulo;
    private String descricao;
    private LocalDate dataVencimento;
    private int prioridade;

    public Tarefa(String titulo, String descricao, LocalDate dataVencimento, int prioridade) {
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
    public LocalDate getDataVencimento() {
        return this.dataVencimento;
    }
    public int getPrioridade() {
        return this.prioridade;
    }

    @Override
    public int compareTo(Tarefa outraTarefa) {
        return outraTarefa.getPrioridade() - this.prioridade;
    }
}
