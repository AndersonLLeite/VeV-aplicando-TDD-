package test.functionalTests;

import gerenciadordetarefas.Prioridade;
import gerenciadordetarefas.Tarefa;
import gerenciadordetarefas.TarefaController;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    @Test
    public void testListarTarefa1NaoExiste(){
        Tarefa tarefa2 = new Tarefa("T2", "tarefa 2", LocalDate.parse("20/05/2024", formatter), Prioridade.MEDIA);
        TarefaController tarefaController = new TarefaController();
        tarefaController.addTarefa(tarefa2);

        List<Tarefa> expected = List.of(tarefa2);
        assertEquals(expected, tarefaController.listarTarefas());
    }
    @Test
    public void testListarTarefa2NaoExiste(){
        Tarefa tarefa1 = new Tarefa("T1", "tarefa 1", LocalDate.parse("20/05/2024", formatter), Prioridade.MEDIA);
        TarefaController tarefaController = new TarefaController();
        tarefaController.addTarefa(tarefa1);

        List<Tarefa> expected = List.of(tarefa1);
        assertEquals(expected, tarefaController.listarTarefas());
    }
    @Test
    public void testListarTarefa1e2NaoExistem(){
        TarefaController tarefaController = new TarefaController();

        List<Tarefa> expected = new ArrayList<>();
        assertEquals(expected, tarefaController.listarTarefas());
    }
    @Test
    public void testListarData1MaiorPrioridadeIgual(){
        Tarefa tarefa1 = new Tarefa("T1", "tarefa 1", LocalDate.parse("21/05/2025", formatter), Prioridade.MEDIA);
        Tarefa tarefa2 = new Tarefa("T2", "tarefa 2", LocalDate.parse("20/05/2024", formatter), Prioridade.MEDIA);
        TarefaController tarefaController = new TarefaController();
        tarefaController.addTarefa(tarefa1);
        tarefaController.addTarefa(tarefa2);

        List<Tarefa> expected =Arrays.asList(tarefa2, tarefa1);
        assertEquals(expected, tarefaController.listarTarefas());
    }
    @Test
    public void testListarData1MenorPrioridadeIgual(){
        Tarefa tarefa1 = new Tarefa("T1", "tarefa 1", LocalDate.parse("20/05/2024", formatter), Prioridade.MEDIA);
        Tarefa tarefa2 = new Tarefa("T2", "tarefa 2", LocalDate.parse("21/05/2025", formatter), Prioridade.MEDIA);
        TarefaController tarefaController = new TarefaController();
        tarefaController.addTarefa(tarefa1);
        tarefaController.addTarefa(tarefa2);

        List<Tarefa> expected =Arrays.asList(tarefa1, tarefa2);
        assertEquals(expected, tarefaController.listarTarefas());
    }

    @Test
    public void testListarDatasIguaisPrioridade1Maior(){
        Tarefa tarefa1 = new Tarefa("T1", "tarefa 1", LocalDate.parse("20/05/2024", formatter), Prioridade.ALTA);
        Tarefa tarefa2 = new Tarefa("T2", "tarefa 2", LocalDate.parse("20/05/2024", formatter), Prioridade.MEDIA);
        TarefaController tarefaController = new TarefaController();
        tarefaController.addTarefa(tarefa1);
        tarefaController.addTarefa(tarefa2);

        List<Tarefa> expected =Arrays.asList(tarefa1, tarefa2);
        assertEquals(expected, tarefaController.listarTarefas());
    }
    @Test
    public void testListarDatasIguaisPrioridade1Menor(){
        Tarefa tarefa1 = new Tarefa("T1", "tarefa 1", LocalDate.parse("20/05/2024", formatter), Prioridade.BAIXA);
        Tarefa tarefa2 = new Tarefa("T2", "tarefa 2", LocalDate.parse("20/05/2024", formatter), Prioridade.MEDIA);
        TarefaController tarefaController = new TarefaController();
        tarefaController.addTarefa(tarefa1);
        tarefaController.addTarefa(tarefa2);

        List<Tarefa> expected =Arrays.asList(tarefa2, tarefa1);
        assertEquals(expected, tarefaController.listarTarefas());
    }
    @Test
    public void testListarDatasIguaisPrioridadesIguais(){
        Tarefa tarefa1 = new Tarefa("T1", "tarefa 1", LocalDate.parse("20/05/2024", formatter), Prioridade.MEDIA);
        Tarefa tarefa2 = new Tarefa("T2", "tarefa 2", LocalDate.parse("20/05/2024", formatter), Prioridade.MEDIA);
        TarefaController tarefaController = new TarefaController();
        tarefaController.addTarefa(tarefa1);
        tarefaController.addTarefa(tarefa2);

        List<Tarefa> expected =Arrays.asList(tarefa1, tarefa2);
        assertEquals(expected, tarefaController.listarTarefas());
    }
}


