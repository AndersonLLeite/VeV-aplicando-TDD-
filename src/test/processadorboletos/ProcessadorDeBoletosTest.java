package test.processadorboletos;

import org.junit.Test;

public class ProcessadorDeBoletosTest {
    @Test
    public void testProcessarBoletos() {
        List<Boleto> boletos = new ArrayList<>();
        boletos.add(new Boleto("001", new Date(), 500.00));
        boletos.add(new Boleto("002", new Date(), 500.00));
        boletos.add(new Boleto("003", new Date(), 600.00));

        Fatura fatura = new Fatura(new Date(), 1500.00, "Cliente Teste");

        ProcessadorDeBoletos.processarBoletos(boletos, fatura);

        assertTrue(fatura.estaPaga());
    }
}
