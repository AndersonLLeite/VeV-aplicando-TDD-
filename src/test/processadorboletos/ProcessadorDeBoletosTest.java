package test.processadorboletos;

import org.junit.Test;
import processadordeboletos.Boleto;
import processadordeboletos.Fatura;
import processadordeboletos.ProcessadorDeBoletos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ProcessadorDeBoletosTest {
    @Test
    public void testProcessarBoletosResultandoEmFaturaPaga() {
        List<Boleto> boletos = new ArrayList<>();
        boletos.add(new Boleto("001", new Date(), 500.00));
        boletos.add(new Boleto("002", new Date(), 400.00));
        boletos.add(new Boleto("003", new Date(), 600.00));

        Fatura fatura = new Fatura(new Date(), 1500.00, "Cliente Teste");

        ProcessadorDeBoletos.processarBoletos(boletos, fatura);

        assertTrue(fatura.estaPaga());
    }

    @Test
    public void testProcessarBoletosResultandoEmFaturaNaoPaga() {
        List<Boleto> boletos = new ArrayList<>();
        boletos.add(new Boleto("001", new Date(), 500.00));
        boletos.add(new Boleto("002", new Date(), 100.00));
        boletos.add(new Boleto("003", new Date(), 600.00));

        Fatura fatura = new Fatura(new Date(), 1500.00, "Cliente Teste");

        ProcessadorDeBoletos.processarBoletos(boletos, fatura);

        assertFalse(fatura.estaPaga());
    }

    @Test
    public void testProcessarBoletosResultandoTem3PagamentosDoTipoBoleto() {
        List<Boleto> boletos = new ArrayList<>();
        boletos.add(new Boleto("001", new Date(), 500.00));
        boletos.add(new Boleto("002", new Date(), 100.00));
        boletos.add(new Boleto("003", new Date(), 600.00));

        Fatura fatura = new Fatura(new Date(), 1500.00, "Cliente Teste");

        ProcessadorDeBoletos.processarBoletos(boletos, fatura);

        assertTrue(fatura.getPagamentosDoTipoBoleto() == 3);

    }
}
