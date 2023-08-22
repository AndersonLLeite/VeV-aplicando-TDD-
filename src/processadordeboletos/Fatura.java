package processadordeboletos;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Fatura {
    private Date data;
    private double valorTotal;
    private String nomeCliente;
    private boolean paga;

    private List<Pagamento> pagamentos = new ArrayList<Pagamento>();

    public Fatura(Date data, double valorTotal, String nomeCliente) {
        this.data = data;
        this.valorTotal = valorTotal;
        this.nomeCliente = nomeCliente;
        this.paga = false;
    }

    public boolean estaPaga() {
        return paga;
    }

    public void marcarComoPaga() {
        paga = true;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public int getPagamentosDoTipoBoleto() {
        return 0;
    }
}
