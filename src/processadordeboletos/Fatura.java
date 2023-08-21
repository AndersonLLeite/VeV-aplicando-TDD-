package processadordeboletos;

import java.util.Date;

public class Fatura {
    private Date data;
    private double valorTotal;
    private String nomeCliente;
    private boolean paga;

    public Fatura(Date data, double valorTotal, String nomeCliente) {
        this.data = data;
        this.valorTotal = valorTotal;
        this.nomeCliente = nomeCliente;
        this.paga = false;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public boolean estaPaga() {
        return paga;
    }

    public void marcarComoPaga() {
        paga = true;
    }
}
