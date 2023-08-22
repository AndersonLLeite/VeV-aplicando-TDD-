package processadordeboletos;

import java.util.Date;

public class Pagamento {
    private double valorPago;
    private Date data;
    private String tipo;

    public Pagamento(double valorPago, Date data, String tipo) {
        this.valorPago = valorPago;
        this.data = data;
        this.tipo = tipo;
    }
}
