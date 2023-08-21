package test.gerenciadortarefas;

import gerenciadordetarefas.Tarefa;
import gerenciadordetarefas.TarefaController;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TarefaControllerTest {
    @Test
    public void testTarefasCollectionVazio(){
        TarefaController tarefaController = new TarefaController();

        assertEquals(0, tarefaController.getTarefas().size());
    }
    @Test
    public void testGetTarefaVazio(){
        TarefaController tarefaController = new TarefaController();

        assertNull(tarefaController.getTarefa("Tarefa"));
    }
    @Test
    public void testAddTarefa(){
        Tarefa novaTarefa = new Tarefa("NovaTarefa", "Uma nova tarefa", "27/09/2022", 2);
        TarefaController tarefaController = new TarefaController();
        tarefaController.addTarefa(novaTarefa);

        assertEquals(1, tarefaController.getTarefas().size());
    }
    @Test
    public void testGetNovaTarefa(){
        Tarefa novaTarefa = new Tarefa("NovaTarefa", "Uma nova tarefa", "27/09/2022", 2);
        TarefaController tarefaController = new TarefaController();
        tarefaController.addTarefa(novaTarefa);

        assertEquals(novaTarefa, tarefaController.getTarefa("NovaTarefa"));
    }
    @Test
    public void testAtualizarTarefa(){
        Tarefa tarefa = new Tarefa("Tarefa", "Uma nova tarefa", "27/09/2022", 2);
        TarefaController tarefaController = new TarefaController();
        tarefaController.addTarefa(tarefa);

        String novaDescricao = "Uma tarefa atualizada";

        tarefaController.updateTarefa(tarefa.getTitulo(), novaDescricao, tarefa.getDataVencimento(), tarefa.getPrioridade());

        assertEquals(novaDescricao, tarefaController.getTarefa("Tarefa").getDescricao());
    }
    @Test
    public void testDeletarTarefa(){
        Tarefa tarefa = new Tarefa("Tarefa", "Uma nova tarefa", "27/09/2022", 2);
        TarefaController tarefaController = new TarefaController();
        tarefaController.addTarefa(tarefa);

        tarefaController.deleteTarefa(tarefa.getTitulo());

        assertNull(tarefaController.getTarefa("Tarefa"));
    }
}
