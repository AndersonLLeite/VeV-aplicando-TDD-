package processadordeboletos;

import java.util.List;

public class ProcessadorDeBoletos {
    public static void processarBoletos(List<Boleto> boletos, Fatura fatura) {
        double totalPago = 0;

        for (Boleto boleto : boletos) {
            totalPago += boleto.getValorPago();
            Pagamento pagamento = new Pagamento(boleto.getValorPago(), boleto.getData(), "BOLETO");
        }
        if (totalPago >= fatura.getValorTotal()) {
            fatura.marcarComoPaga();
        }
    }
}
