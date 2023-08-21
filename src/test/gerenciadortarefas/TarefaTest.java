package test.gerenciadortarefas;

import gerenciadordetarefas.Tarefa;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TarefaTest {
    @Test
    public void testCriarTarefa(){
        String titulo = "Tarefa1";
        String descricao = "Uma tarefa valida";
        String dataVencimento = "21/08/2023";
        int prioridade = 3;

        Tarefa tarefa = new Tarefa();

        assertTrue(tarefa.getTitulo().equals(titulo) && tarefa.getDescricao().equals(descricao) && tarefa.getDataVencimento().equals(dataVencimento) && tarefa.getPrioridade() == prioridade);
    }
}