package test.functionalTests;

import org.junit.Test;
import processadordeboletos.Boleto;
import processadordeboletos.Fatura;
import processadordeboletos.ProcessadorDeBoletos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class FunctionalProcessadorBoletosTest {
    //testes de Analise de valores limite
    @Test
    public void testLimiteNaturalAntesDoNegativo(){
        List<Boleto> boletos = new ArrayList<>();
        Fatura fatura = new Fatura(new Date(), 0.00, "Cliente Teste");
        ProcessadorDeBoletos.processarBoletos(boletos, fatura);
        assertTrue(fatura.estaPaga());
    }

    @Test
    public void testSomaBoletosIgualValorFatura(){
        List<Boleto> boletos = new ArrayList<>();
        boletos.add(new Boleto("001", new Date(), 1000.00));
        Fatura fatura = new Fatura(new Date(), 1000.00, "Cliente Teste");
        ProcessadorDeBoletos.processarBoletos(boletos, fatura);
        assertTrue(fatura.estaPaga());
    }

    @Test
    public void testSomaBoletos1UnidadeAbaxioValorFatura(){
        List<Boleto> boletos = new ArrayList<>();
        boletos.add(new Boleto("001", new Date(), 999.99));
        Fatura fatura = new Fatura(new Date(), 1000.00, "Cliente Teste");
        ProcessadorDeBoletos.processarBoletos(boletos, fatura);
        assertFalse(fatura.estaPaga());
    }

    @Test
    public void testSomaBoletos1UnidadeAcimaValorFatura(){
        List<Boleto> boletos = new ArrayList<>();
        boletos.add(new Boleto("001", new Date(), 500.00));
        boletos.add(new Boleto("002", new Date(), 500.01));
        Fatura fatura = new Fatura(new Date(), 1000.00, "Cliente Teste");
        ProcessadorDeBoletos.processarBoletos(boletos, fatura);
        assertTrue(fatura.estaPaga());
    }

    //testes de partição de equivalência

    @Test
    public void testParticao1ValorFaturaMaiorQueSomaDeBoletos(){
        List<Boleto> boletos = new ArrayList<>();
        boletos.add(new Boleto("001", new Date(), 500.00));

        Fatura fatura = new Fatura(new Date(), 1000.00, "Cliente Teste");
        ProcessadorDeBoletos.processarBoletos(boletos, fatura);
        assertFalse(fatura.estaPaga());
    }

    @Test
    public void testParticao2ValorFaturaMenorQueSomaDeBoletos(){
        List<Boleto> boletos = new ArrayList<>();
        boletos.add(new Boleto("001", new Date(), 1500.00));

        Fatura fatura = new Fatura(new Date(), 1000.00, "Cliente Teste");
        ProcessadorDeBoletos.processarBoletos(boletos, fatura);
        assertTrue(fatura.estaPaga());
    }



}
