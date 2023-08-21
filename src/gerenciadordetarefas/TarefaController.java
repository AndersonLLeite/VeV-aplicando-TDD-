package gerenciadordetarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TarefaController {
    private Collection<Tarefa> tarefas;

    public TarefaController() {
        this.tarefas = new ArrayList<>();
    }

    public Collection<Tarefa> getTarefas(){
        return tarefas;
    }
    public Tarefa getTarefa(String titulo){
        for (Tarefa tarefa: tarefas) {
            if (tarefa.getTitulo().equals(titulo)){
                return  tarefa;
            }
        }
        return null;
    }

    public void addTarefa(Tarefa tarefa){
        tarefas.add(tarefa);
    }
    public void updateTarefa(String titulo, String descricao, String dataVencimento, int prioridade){
        Tarefa novaTarefa = new Tarefa(titulo, descricao, dataVencimento, prioridade);
        deleteTarefa(titulo);
        tarefas.add(novaTarefa);
    }
    public void deleteTarefa(String titulo){
        Tarefa tarefaFound = null;
        for (Tarefa tarefa: tarefas) {
            if (tarefa.getTitulo().equals(titulo)){
                tarefaFound = tarefa;
            }
        }
        tarefas.remove(tarefaFound);
    }
}
