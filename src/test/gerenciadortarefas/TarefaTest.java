package test.gerenciadortarefas;

import gerenciadordetarefas.Tarefa;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class TarefaTest {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    @Test
    public void testCriarTarefa(){
        String titulo = "Tarefa1";
        String descricao = "Uma tarefa valida";
        LocalDate dataVencimento = LocalDate.parse( "21/08/2023", formatter);
        int prioridade = 3;

        Tarefa tarefa = new Tarefa(titulo, descricao, dataVencimento, prioridade);

        assertTrue(tarefa.getTitulo().equals(titulo) && tarefa.getDescricao().equals(descricao) && tarefa.getDataVencimento().equals(dataVencimento) && tarefa.getPrioridade() == prioridade);
    }
    @Test
    public void testCriarTarefa2(){
        String titulo = "Tarefa2";
        String descricao = "Outra tarefa valida";
        LocalDate dataVencimento = LocalDate.parse("30/09/2024", formatter);
        int prioridade = 2;

        Tarefa tarefa = new Tarefa(titulo, descricao, dataVencimento, prioridade);

        assertTrue(tarefa.getTitulo().equals(titulo) && tarefa.getDescricao().equals(descricao) && tarefa.getDataVencimento().equals(dataVencimento) && tarefa.getPrioridade() == prioridade);
    }
}