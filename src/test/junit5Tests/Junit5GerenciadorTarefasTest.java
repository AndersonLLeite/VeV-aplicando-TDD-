package test.junit5Tests;

import gerenciadordetarefas.Prioridade;
import gerenciadordetarefas.Tarefa;
import gerenciadordetarefas.TarefaController;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Junit5GerenciadorTarefasTest {
    TarefaController tarefaController;
    private static DateTimeFormatter formatter;

    @BeforeAll
    static void setupTestEnvironment(){
        formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    }
    @BeforeEach
    public void setupController(){
        tarefaController = new TarefaController();
    }
    // Testes TDD - Tarefa
    @Test
    @Tag("TDD")
    public void testCriarTarefaValida1(){
        String titulo = "Tarefa1";
        String descricao = "Uma tarefa valida";
        LocalDate dataVencimento = LocalDate.parse( "21/08/2023", formatter);
        Prioridade prioridade = Prioridade.ALTA;

        Tarefa tarefa = new Tarefa(titulo, descricao, dataVencimento, prioridade);

        assertAll(
                () -> assertEquals(titulo, tarefa.getTitulo()),
                () -> assertEquals(descricao, tarefa.getDescricao()),
                () -> assertEquals(dataVencimento, tarefa.getDataVencimento()),
                () -> assertEquals(prioridade, tarefa.getPrioridade())
        );
    }
    @Test
    @Tag("TDD")
    public void testCriarTarefaValida2(){
        String titulo = "Tarefa2";
        String descricao = "Outra tarefa valida";
        LocalDate dataVencimento = LocalDate.parse("30/09/2024", formatter);
        Prioridade prioridade = Prioridade.MEDIA;

        Tarefa tarefa = new Tarefa(titulo, descricao, dataVencimento, prioridade);

        assertAll(
                () -> assertEquals(titulo, tarefa.getTitulo()),
                () -> assertEquals(descricao, tarefa.getDescricao()),
                () -> assertEquals(dataVencimento, tarefa.getDataVencimento()),
                () -> assertEquals(prioridade, tarefa.getPrioridade())
        );
    }
    // Testes TDD - TarefaController
    @Test
    @Tag("TDD")
    public void testGetTarefasCollectionVazio(){
        assertEquals(0, tarefaController.getTarefas().size());
    }
    @Test
    @Tag("TDD")
    public void testGetTarefaInexistente(){
        assertNull(tarefaController.getTarefa("Tarefa"));
    }
    @Test
    @Tag("TDD")
    public void testAddTarefa(){
        Tarefa novaTarefa = new Tarefa("NovaTarefa", "Uma nova tarefa", LocalDate.parse("27/09/2022", formatter), Prioridade.MEDIA);
        tarefaController.addTarefa(novaTarefa);
        assertEquals(1, tarefaController.getTarefas().size());
    }
    @Test
    @Tag("TDD")
    void testAddTarefaMultiplas(){
        Tarefa tarefa1 = new Tarefa("Tarefa1", "Primeira tarefa", LocalDate.parse("19/07/2023", formatter), Prioridade.ALTA);
        Tarefa tarefa2 = new Tarefa("Tarefa2", "Segunda tarefa", LocalDate.parse("21/08/2023", formatter), Prioridade.MEDIA);

        tarefaController.addTarefa(tarefa1);
        tarefaController.addTarefa(tarefa2);

        assertEquals(2, tarefaController.getTarefas().size());
    }
    @Test
    @Tag("TDD")
    public void testAtualizarTarefa(){
        Tarefa tarefa = new Tarefa("Tarefa", "Uma nova tarefa", LocalDate.parse("27/09/2022", formatter), Prioridade.MEDIA);
        tarefaController.addTarefa(tarefa);

        String novaDescricao = "Uma tarefa atualizada";
        tarefaController.updateTarefa(tarefa.getTitulo(), novaDescricao, tarefa.getDataVencimento().format(formatter), tarefa.getPrioridade());

        assertEquals(novaDescricao, tarefaController.getTarefa("Tarefa").getDescricao());
    }
    @Test
    @Tag("TDD")
    public void testDeletarTarefa(){
        Tarefa tarefa = new Tarefa("Tarefa", "Uma nova tarefa", LocalDate.parse("27/09/2022", formatter), Prioridade.MEDIA);
        tarefaController.addTarefa(tarefa);
        tarefaController.deleteTarefa(tarefa.getTitulo());
        assertNull(tarefaController.getTarefa("Tarefa"));
    }
    @Test
    @Tag("TDD")
    public void testListarTarefasVazio(){
        assertEquals(0, tarefaController.listarTarefas().size());
    }
    @Test
    @Tag("TDD")
    public void testListarTarefas3Prioridades(){
        Tarefa tarefa1 = new Tarefa("Tarefa1", "Primeira tarefa", LocalDate.parse("30/09/2024", formatter), Prioridade.ALTA);
        Tarefa tarefa2 = new Tarefa("Tarefa2", "Segunda tarefa", LocalDate.parse("30/09/2024", formatter), Prioridade.MEDIA);
        Tarefa tarefa3 = new Tarefa("Tarefa3", "Terceira tarefa", LocalDate.parse("30/09/2024", formatter), Prioridade.BAIXA);

        tarefaController.addTarefa(tarefa1);
        tarefaController.addTarefa(tarefa2);
        tarefaController.addTarefa(tarefa3);

        List<Tarefa> listaCorreta = Arrays.asList(tarefa1, tarefa2, tarefa3);

        assertEquals(listaCorreta, tarefaController.listarTarefas());
    }

    // Testes Funcionais
    @Test
    @Tag("Functional")
    @Tag("ExceptionHandling")
    public void testCriarTarefaDataFormatoInvalido(){
        assertThrows(DateTimeParseException.class, () -> {
            Tarefa novaTarefa = new Tarefa("T1", "tarefa 1", LocalDate.parse("20.04.2024", formatter), Prioridade.MEDIA);
            tarefaController.addTarefa(novaTarefa);
        });
    }
    @Test
    @Tag("Functional")
    @Tag("ExceptionHandling")
    public void testCriarTarefaDataInvalida(){
        Tarefa novaTarefa = new Tarefa("T1", "tarefa 1", LocalDate.parse("31/02/2024", formatter), Prioridade.MEDIA);
        tarefaController.addTarefa(novaTarefa);
        assertThrows(IllegalArgumentException.class, () -> tarefaController.getTarefa("T1"));
    }
    @Test
    @Tag("Functional")
    @Tag("ExceptionHandling")
    public void testCriarTarefaDataJaSePassou(){
        Tarefa novaTarefa = new Tarefa("T1", "tarefa 1", LocalDate.parse("02/03/2019", formatter), Prioridade.MEDIA);
        tarefaController.addTarefa(novaTarefa);

        assertThrows(IllegalArgumentException.class, () -> tarefaController.getTarefa("T1"));
    }
    @Test
    @Tag("Functional")
    public void testCriarTarefaDataValida(){
        Tarefa novaTarefa = new Tarefa("T1", "tarefa 1", LocalDate.parse("20/05/2024", formatter), Prioridade.MEDIA);
        tarefaController.addTarefa(novaTarefa);

        assertEquals(novaTarefa, tarefaController.getTarefa("T1"));
    }
    @Test
    @Tag("Functional")
    @Tag("ExceptionHandling")
    public void testCriarTarefaTituloVazio(){
        Tarefa novaTarefa = new Tarefa("", "", LocalDate.parse("20/05/2024", formatter), Prioridade.MEDIA);
        tarefaController.addTarefa(novaTarefa);

        assertThrows(IllegalArgumentException.class, () -> tarefaController.getTarefa(""));
    }
    @Test
    @Tag("Functional")
    public void testCriarTarefaTituloCaracterEspecial(){
        Tarefa novaTarefa = new Tarefa("@Ação", "Ações válidas", LocalDate.parse("20/05/2024", formatter), Prioridade.MEDIA);
        tarefaController.addTarefa(novaTarefa);

        assertEquals(novaTarefa, tarefaController.getTarefa("@Ação"));
    }
    @Test
    @Tag("Functional")
    public void testCriarTarefaTituloValidoSimples(){
        Tarefa novaTarefa = new Tarefa("T1", "tarefa 1", LocalDate.parse("20/05/2024", formatter), Prioridade.MEDIA);
        tarefaController.addTarefa(novaTarefa);

        assertEquals(novaTarefa, tarefaController.getTarefa("T1"));
    }
    @Test
    @Tag("Functional")
    public void testListarTarefa1NaoExiste(){
        Tarefa tarefa2 = new Tarefa("T2", "tarefa 2", LocalDate.parse("20/05/2024", formatter), Prioridade.MEDIA);
        tarefaController.addTarefa(tarefa2);

        List<Tarefa> expected = List.of(tarefa2);
        assertEquals(expected, tarefaController.listarTarefas());
    }
    @Test
    @Tag("Functional")
    public void testListarTarefa2NaoExiste(){
        Tarefa tarefa1 = new Tarefa("T1", "tarefa 1", LocalDate.parse("20/05/2024", formatter), Prioridade.MEDIA);
        tarefaController.addTarefa(tarefa1);

        List<Tarefa> expected = List.of(tarefa1);
        assertEquals(expected, tarefaController.listarTarefas());
    }
    @Test
    @Tag("Functional")
    public void testListarTarefa1e2NaoExistem(){
        List<Tarefa> expected = new ArrayList<>();
        assertEquals(expected, tarefaController.listarTarefas());
    }
    @Test
    @Tag("Functional")
    public void testListarData1MaiorPrioridadeIgual(){
        Tarefa tarefa1 = new Tarefa("T1", "tarefa 1", LocalDate.parse("21/05/2025", formatter), Prioridade.MEDIA);
        Tarefa tarefa2 = new Tarefa("T2", "tarefa 2", LocalDate.parse("20/05/2024", formatter), Prioridade.MEDIA);

        tarefaController.addTarefa(tarefa1);
        tarefaController.addTarefa(tarefa2);

        List<Tarefa> expected = Arrays.asList(tarefa2, tarefa1);
        assertEquals(expected, tarefaController.listarTarefas());
    }
    @Test
    @Tag("Functional")
    public void testListarData1MenorPrioridadeIgual(){
        Tarefa tarefa1 = new Tarefa("T1", "tarefa 1", LocalDate.parse("20/05/2024", formatter), Prioridade.MEDIA);
        Tarefa tarefa2 = new Tarefa("T2", "tarefa 2", LocalDate.parse("21/05/2025", formatter), Prioridade.MEDIA);

        tarefaController.addTarefa(tarefa1);
        tarefaController.addTarefa(tarefa2);

        List<Tarefa> expected =Arrays.asList(tarefa1, tarefa2);
        assertEquals(expected, tarefaController.listarTarefas());
    }

    @Test
    @Tag("Functional")
    public void testListarDatasIguaisPrioridade1Maior(){
        Tarefa tarefa1 = new Tarefa("T1", "tarefa 1", LocalDate.parse("20/05/2024", formatter), Prioridade.ALTA);
        Tarefa tarefa2 = new Tarefa("T2", "tarefa 2", LocalDate.parse("20/05/2024", formatter), Prioridade.MEDIA);

        tarefaController.addTarefa(tarefa1);
        tarefaController.addTarefa(tarefa2);

        List<Tarefa> expected =Arrays.asList(tarefa1, tarefa2);
        assertEquals(expected, tarefaController.listarTarefas());
    }
    @Test
    @Tag("Functional")
    public void testListarDatasIguaisPrioridade1Menor(){
        Tarefa tarefa1 = new Tarefa("T1", "tarefa 1", LocalDate.parse("20/05/2024", formatter), Prioridade.BAIXA);
        Tarefa tarefa2 = new Tarefa("T2", "tarefa 2", LocalDate.parse("20/05/2024", formatter), Prioridade.MEDIA);

        tarefaController.addTarefa(tarefa1);
        tarefaController.addTarefa(tarefa2);

        List<Tarefa> expected =Arrays.asList(tarefa2, tarefa1);
        assertEquals(expected, tarefaController.listarTarefas());
    }
    @Test
    @Tag("Functional")
    public void testListarDatasIguaisPrioridadesIguais(){
        Tarefa tarefa1 = new Tarefa("T1", "tarefa 1", LocalDate.parse("20/05/2024", formatter), Prioridade.MEDIA);
        Tarefa tarefa2 = new Tarefa("T2", "tarefa 2", LocalDate.parse("20/05/2024", formatter), Prioridade.MEDIA);

        tarefaController.addTarefa(tarefa1);
        tarefaController.addTarefa(tarefa2);

        List<Tarefa> expected =Arrays.asList(tarefa1, tarefa2);
        assertEquals(expected, tarefaController.listarTarefas());
    }
    // Novos Testes
    @Test
    @Tag("NewTest")
    @Tag("ExceptionHandling")
    void testCriarTarefaNula(){
        Tarefa tarefa1 = null;
        assertThrows(NullPointerException.class, () -> tarefaController.addTarefa(tarefa1));
    }
    @Test
    @Tag("NewTest")
    @Tag("ExceptionHandling")
    void testCriarTarefaSobrescrita(){
        Tarefa tarefa1 = new Tarefa("T1", "tarefa 1", LocalDate.parse("20/05/2024", formatter), Prioridade.MEDIA);
        Tarefa repetida = new Tarefa("T1", "tarefa repetida", LocalDate.parse("20/05/2024", formatter), Prioridade.BAIXA);
        tarefaController.addTarefa(tarefa1);

        assertThrows(IllegalArgumentException.class, () -> tarefaController.addTarefa(repetida));
    }
    @Test
    @Tag("NewTest")
    @Tag("ExceptionHandling")
    void testDeletarTarefaInexistente(){
        assertThrows(IllegalArgumentException.class, () -> tarefaController.deleteTarefa("T99"));
    }
    @Test
    @Tag("NewTest")
    @Tag("ExceptionHandling")
    void testAtualizarTarefaInexistente(){
        assertThrows(IllegalArgumentException.class, () -> tarefaController.updateTarefa("T99", "descricao", "20/05/2024", Prioridade.BAIXA));
    }
}
