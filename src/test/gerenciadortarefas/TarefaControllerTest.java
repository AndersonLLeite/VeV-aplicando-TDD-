package test.gerenciadortarefas;

import gerenciadordetarefas.Tarefa;
import gerenciadordetarefas.TarefaController;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    @Test
    public void testListarTarefasVazio(){
        TarefaController tarefaController = new TarefaController();

        assertEquals(0, tarefaController.listarTarefas().size());
    }
    @Test
    public void testListarTarefasUma(){
        Tarefa tarefa = new Tarefa("Tarefa", "Uma nova tarefa", "27/09/2022", 2);
        TarefaController tarefaController = new TarefaController();
        tarefaController.addTarefa(tarefa);

        assertEquals(tarefa, tarefaController.listarTarefas().get(0));
    }
    @Test
    public void testListarTarefasMultiplas(){
        Tarefa tarefa1 = new Tarefa("Tarefa1", "Primeira tarefa", "19/07/2023", 3);
        Tarefa tarefa2 = new Tarefa("Tarefa1", "Segunda tarefa", "21/08/2023", 2);
        Tarefa tarefa3 = new Tarefa("Tarefa1", "Terceira tarefa", "30/09/2024", 1);

        TarefaController tarefaController = new TarefaController();
        tarefaController.addTarefa(tarefa1);
        tarefaController.addTarefa(tarefa2);
        tarefaController.addTarefa(tarefa3);

        List<Tarefa> listaCorreta = Arrays.asList(tarefa1, tarefa2, tarefa3);

        assertEquals(listaCorreta, tarefaController.listarTarefas());
    }
}
