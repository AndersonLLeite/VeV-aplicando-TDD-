package gerenciadordetarefas;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TarefaController {
    private Map<String, Tarefa> tarefas;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public TarefaController() {
        this.tarefas = new HashMap<String, Tarefa>();
    }

    public Map<String, Tarefa> getTarefas(){
        return tarefas;
    }
    public Tarefa getTarefa(String titulo){
        return  tarefas.get(titulo);
    }

    public void addTarefa(Tarefa tarefa){
        tarefas.put(tarefa.getTitulo(), tarefa);
    }
    public void updateTarefa(String titulo, String descricao, String dataVencimento, Prioridade prioridade){
        Tarefa novaTarefa = new Tarefa(titulo, descricao, LocalDate.parse(dataVencimento, formatter), prioridade);
        tarefas.put(titulo, novaTarefa);
    }
    public void deleteTarefa(String titulo){
        tarefas.remove(titulo);
    }
    public List<Tarefa> listarTarefas(){
        List<Tarefa> tarefasList =  new ArrayList<>(tarefas.values());
        Collections.sort(tarefasList);
        return tarefasList;
    }
}
