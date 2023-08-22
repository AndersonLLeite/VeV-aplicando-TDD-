package test.gerenciadortarefas;

import gerenciadordetarefas.Tarefa;
import gerenciadordetarefas.TarefaController;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TarefaControllerTest {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
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
        Tarefa novaTarefa = new Tarefa("NovaTarefa", "Uma nova tarefa", LocalDate.parse("27/09/2022", formatter), 2);
        TarefaController tarefaController = new TarefaController();
        tarefaController.addTarefa(novaTarefa);

        assertEquals(1, tarefaController.getTarefas().size());
    }
    @Test void testAddTarefaMultiplas(){

        Tarefa tarefa1 = new Tarefa("Tarefa1", "Primeira tarefa", LocalDate.parse("19/07/2023", formatter), 3);
        Tarefa tarefa2 = new Tarefa("Tarefa2", "Segunda tarefa", LocalDate.parse("21/08/2023", formatter), 2);
        TarefaController tarefaController = new TarefaController();
        tarefaController.addTarefa(tarefa1);
        tarefaController.addTarefa(tarefa2);

        assertEquals(2, tarefaController.getTarefas().size());
    }
    @Test
    public void testGetNovaTarefa(){
        Tarefa novaTarefa = new Tarefa("NovaTarefa", "Uma nova tarefa", LocalDate.parse("27/09/2022", formatter), 2);
        TarefaController tarefaController = new TarefaController();
        tarefaController.addTarefa(novaTarefa);

        assertEquals(novaTarefa, tarefaController.getTarefa("NovaTarefa"));
    }
    @Test
    public void testAtualizarTarefa(){
        Tarefa tarefa = new Tarefa("Tarefa", "Uma nova tarefa", LocalDate.parse("27/09/2022", formatter), 2);
        TarefaController tarefaController = new TarefaController();
        tarefaController.addTarefa(tarefa);

        String novaDescricao = "Uma tarefa atualizada";

        tarefaController.updateTarefa(tarefa.getTitulo(), novaDescricao, tarefa.getDataVencimento().format(formatter), tarefa.getPrioridade());

        assertEquals(novaDescricao, tarefaController.getTarefa("Tarefa").getDescricao());
    }
    @Test
    public void testDeletarTarefa(){
        Tarefa tarefa = new Tarefa("Tarefa", "Uma nova tarefa", LocalDate.parse("27/09/2022", formatter), 2);
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
        Tarefa tarefa = new Tarefa("Tarefa", "Uma nova tarefa", LocalDate.parse("27/09/2022", formatter), 2);
        TarefaController tarefaController = new TarefaController();
        tarefaController.addTarefa(tarefa);

        assertEquals(tarefa, tarefaController.listarTarefas().get(0));
    }
    @Test
    public void testListarTarefasMultiplas(){
        Tarefa tarefa1 = new Tarefa("Tarefa1", "Primeira tarefa", LocalDate.parse("19/07/2023", formatter), 3);
        Tarefa tarefa2 = new Tarefa("Tarefa2", "Segunda tarefa", LocalDate.parse("21/08/2023", formatter), 2);
        Tarefa tarefa3 = new Tarefa("Tarefa3", "Terceira tarefa", LocalDate.parse("30/09/2024", formatter), 1);

        TarefaController tarefaController = new TarefaController();
        tarefaController.addTarefa(tarefa1);
        tarefaController.addTarefa(tarefa2);
        tarefaController.addTarefa(tarefa3);

        List<Tarefa> listaCorreta = Arrays.asList(tarefa1, tarefa2, tarefa3);

        assertEquals(listaCorreta, tarefaController.listarTarefas());
    }
}
