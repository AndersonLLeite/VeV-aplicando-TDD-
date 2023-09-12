package test.functionalTests;

import gerenciadordetarefas.Prioridade;
import gerenciadordetarefas.Tarefa;
import gerenciadordetarefas.TarefaController;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FunctionalGerenciadorTarefasTest {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // testes de Particoes de Equivalencia
    @Test
    public void testCriarTarefaDataFormatoInvalido(){

        TarefaController tarefaController = new TarefaController();

        assertThrows(DateTimeParseException.class, () -> {
            Tarefa novaTarefa = new Tarefa("T1", "tarefa 1", LocalDate.parse("20.04.2024", formatter), Prioridade.MEDIA);
            tarefaController.addTarefa(novaTarefa);
        });
    }
    @Test
    public void testCriarTarefaDataInvalida(){
        Tarefa novaTarefa = new Tarefa("T1", "tarefa 1", LocalDate.parse("31/02/2024", formatter), Prioridade.MEDIA);
        TarefaController tarefaController = new TarefaController();
        tarefaController.addTarefa(novaTarefa);

        assertThrows(IllegalArgumentException.class, () -> {tarefaController.getTarefa("T1");});
    }
    @Test
    public void testCriarTarefaDataJaSePassou(){
        Tarefa novaTarefa = new Tarefa("T1", "tarefa 1", LocalDate.parse("02/03/2019", formatter), Prioridade.MEDIA);
        TarefaController tarefaController = new TarefaController();
        tarefaController.addTarefa(novaTarefa);

        assertThrows(IllegalArgumentException.class, () -> {tarefaController.getTarefa("T1");});
    }
    @Test
    public void testCriarTarefaDataValida(){
        Tarefa novaTarefa = new Tarefa("T1", "tarefa 1", LocalDate.parse("20/05/2024", formatter), Prioridade.MEDIA);
        TarefaController tarefaController = new TarefaController();
        tarefaController.addTarefa(novaTarefa);

        assertEquals(novaTarefa, tarefaController.getTarefa("T1"));
    }
    @Test
    public void testCriarTarefaTituloVazio(){
        Tarefa novaTarefa = new Tarefa("", "", LocalDate.parse("20/05/2024", formatter), Prioridade.MEDIA);
        TarefaController tarefaController = new TarefaController();
        tarefaController.addTarefa(novaTarefa);

        assertThrows(IllegalArgumentException.class, () -> {tarefaController.getTarefa("");});
    }
    @Test
    public void testCriarTarefaTituloCaracterEspecial(){
        Tarefa novaTarefa = new Tarefa("@Ação", "Ações válidas", LocalDate.parse("20/05/2024", formatter), Prioridade.MEDIA);
        TarefaController tarefaController = new TarefaController();
        tarefaController.addTarefa(novaTarefa);

        assertEquals(novaTarefa, tarefaController.getTarefa("@Ação"));
    }    @Test
    public void testCriarTarefaTituloValidoSimples(){
        Tarefa novaTarefa = new Tarefa("T1", "tarefa 1", LocalDate.parse("20/05/2024", formatter), Prioridade.MEDIA);
        TarefaController tarefaController = new TarefaController();
        tarefaController.addTarefa(novaTarefa);

        assertEquals(novaTarefa, tarefaController.getTarefa("T1"));
    }

    // Testes de Tabela de Decisão
}
