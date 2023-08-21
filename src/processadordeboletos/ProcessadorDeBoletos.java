package processadordeboletos;

import java.util.List;

public class ProcessadorDeBoletos {
    public static void processarBoletos(List<Boleto> boletos, Fatura fatura) {
        fatura.marcarComoPaga();
    }
}
