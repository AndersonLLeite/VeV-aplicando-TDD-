package test.junit5Tests;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;
        import processadordeboletos.Boleto;
        import processadordeboletos.Fatura;
        import processadordeboletos.ProcessadorDeBoletos;

        import java.util.ArrayList;
        import java.util.Date;
        import java.util.List;

        import static org.junit.jupiter.api.Assertions.*;

public class junit5GerenciadorTarefasTest {
    private List<Boleto> boletos;
    private Fatura fatura;

    @BeforeEach
    public void setUp() {
        boletos = new ArrayList<>();
        fatura = new Fatura(new Date(), 1000.00, "Cliente Teste");
    }

    // Testes de Análise de Valores Limite

    @Test
    public void testLimiteNaturalAntesDoNegativo() {
        fatura = new Fatura(new Date(), 0.00, "Cliente Teste");
        ProcessadorDeBoletos.processarBoletos(boletos, fatura);
        assertTrue(fatura.estaPaga());
    }

    @Test
    public void testSomaBoletosIgualValorFatura() {
        boletos.add(new Boleto("001", new Date(), 1000.00));
        ProcessadorDeBoletos.processarBoletos(boletos, fatura);
        assertTrue(fatura.estaPaga());
    }

    @Test
    public void testSomaBoletos1UnidadeAbaixoValorFatura() {
        boletos.add(new Boleto("001", new Date(), 999.99));
        ProcessadorDeBoletos.processarBoletos(boletos, fatura);
        assertFalse(fatura.estaPaga());
    }

    @Test
    public void testSomaBoletos1UnidadeAcimaValorFatura() {
        boletos.add(new Boleto("001", new Date(), 500.00));
        boletos.add(new Boleto("002", new Date(), 500.01));
        ProcessadorDeBoletos.processarBoletos(boletos, fatura);
        assertTrue(fatura.estaPaga());
    }

    // Testes de Partição de Equivalência

    @Test
    public void testParticao1ValorFaturaMaiorQueSomaDeBoletos() {
        boletos.add(new Boleto("001", new Date(), 500.00));
        ProcessadorDeBoletos.processarBoletos(boletos, fatura);
        assertFalse(fatura.estaPaga());
    }

    @Test
    public void testParticao2ValorFaturaMenorQueSomaDeBoletos() {
        boletos.add(new Boleto("001", new Date(), 1500.00));
        ProcessadorDeBoletos.processarBoletos(boletos, fatura);
        assertTrue(fatura.estaPaga());
    }

    @Test
    public void testValorBoletoNegativo() {
        boletos.add(new Boleto("001", new Date(), -500.00));

        assertThrows(IllegalArgumentException.class, () -> {
            ProcessadorDeBoletos.processarBoletos(boletos, fatura);
        });
    }

    @Test
    public void testValorFaturaNegativo() {
        fatura = new Fatura(new Date(), -1000.00, "Cliente Teste");

        assertThrows(IllegalArgumentException.class, () -> {
            ProcessadorDeBoletos.processarBoletos(boletos, fatura);
        });
    }

    @Test
    public void testAmbosValoresNegativos() {
        boletos.add(new Boleto("001", new Date(), -500.00));
        fatura = new Fatura(new Date(), -1000.00, "Cliente Teste");

        assertThrows(IllegalArgumentException.class, () -> {
            ProcessadorDeBoletos.processarBoletos(boletos, fatura);
        });
    }
}
