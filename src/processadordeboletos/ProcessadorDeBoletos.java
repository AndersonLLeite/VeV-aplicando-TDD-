package processadordeboletos;

import java.util.List;

import java.util.List;

public class ProcessadorDeBoletos {
    public static void processarBoletos(List<Boleto> boletos, Fatura fatura) {
        if (fatura.getValorTotal() < 0) {
            throw new IllegalArgumentException("O valor da fatura não pode ser negativo.");
        }

        double totalPago = 0;

        for (Boleto boleto : boletos) {
            double valorPago = boleto.getValorPago();

            if (valorPago < 0) {
                throw new IllegalArgumentException("O valor do boleto não pode ser negativo.");
            }

            totalPago += valorPago;
            Pagamento pagamento = new Pagamento(valorPago, boleto.getData(), "BOLETO");
            fatura.adicionarPagamento(pagamento);
        }

        if (totalPago >= fatura.getValorTotal()) {
            fatura.marcarComoPaga();
        }
    }
}

