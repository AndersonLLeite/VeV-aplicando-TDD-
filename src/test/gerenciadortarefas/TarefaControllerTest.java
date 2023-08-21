package test.gerenciadortarefas;

import gerenciadordetarefas.Tarefa;
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

        assertNull(tarefaController.getTarefa());
    }
    @Test
    public void testAddTarefa(){
        Tarefa novaTarefa = new Tarefa("NovaTarefa", "Uma nova tarefa", "27/09/2022", 2)
        TarefaController tarefaController = new TarefaController();
        tarefaController.addTarefa(novaTarefa);

        assertEquals(1, tarefaController.getTarefas().size());
    }
    @Test
    public void testGetNovaTarefa(){
        Tarefa novaTarefa = new Tarefa("NovaTarefa", "Uma nova tarefa", "27/09/2022", 2)
        TarefaController tarefaController = new TarefaController();
        tarefaController.addTarefa(novaTarefa);

        assertEquals(tarefaController.getTarefa("NovaTarefa"));
    }
    @Test
    public void testAtualizarTarefa(){
        Tarefa tarefa = new Tarefa("Tarefa", "Uma nova tarefa", "27/09/2022", 2)
        TarefaController tarefaController = new TarefaController();
        tarefaController.addTarefa(novaTarefa);

        String novaDescricao = "Uma tarefa atualizada";

        tarefaController.updateTarefa(tarefa.getTitulo(), novaDescricao, tarefa.getDataVencimento(), tarefa.getPrioridade());

        assertEquals(tarefaController.getTarefa("Tarefa").getDescricao().equals(novaDescricao));
    }
    @Test
    public void testDeletarTarefa(){
        Tarefa tarefa = new Tarefa("Tarefa", "Uma nova tarefa", "27/09/2022", 2)
        TarefaController tarefaController = new TarefaController();
        tarefaController.addTarefa(novaTarefa);

        tarefaController.deleteTarefa(tarefa.getTitulo());

        assertNull(tarefaController.getTarefa());
    }
}
