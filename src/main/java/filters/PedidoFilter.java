package filters;

import java.util.Date;

import entities.Produto;
import entities.TipoDePagamento;
import service.ServiceDacException;

public class PedidoFilter {
	private static final long serialVersionUID = 1L;

	
	private Date inicioDataDoPedido;
	private Date fimDataDoPedido;
	private Float valorUnitario;
	private Float valorTotal;
	private TipoDePagamento tipoDePagamento;
	private Produto produto;

	public PedidoFilter() {

	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Float getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Float valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Float getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Float valorTotal) {
		this.valorTotal = valorTotal;
	}

	public TipoDePagamento getTipoDePagamento() {
		return tipoDePagamento;
	}

	public void setTipoDePagamento(TipoDePagamento tipoDePagamento) {
		this.tipoDePagamento = tipoDePagamento;
	}

	public Date getInicioDataDoPedido() {
		return inicioDataDoPedido;
	}

	public void setInicioDataDoPedido(Date inicioDataDoPedido) {
		this.inicioDataDoPedido = inicioDataDoPedido;
	}

	public Date getFimDataDoPedido() {
		return fimDataDoPedido;
	}

	public void setFimDataDoPedido(Date fimDataDoPedido) {
		this.fimDataDoPedido = fimDataDoPedido;
	}

	
	public boolean isEmpty() {

		
		if (this.inicioDataDoPedido != null) {
			return false;
		}
		if (this.fimDataDoPedido != null) {
			return false;
		}
		if (this.tipoDePagamento != null) {
			return false;
		}
		if (this.valorTotal != null) {
			return false;
		}
		if (this.valorUnitario != null) {
			return false;
		}
		if (this.produto != null) {
			return false;
		}

		return true;
	}



	@Override
	public String toString() {
		return "PedidoFilter [inicioDataDoPedido=" + inicioDataDoPedido + ", fimDataDoPedido=" + fimDataDoPedido
				+ ", valorUnitario=" + valorUnitario + ", valorTotal=" + valorTotal + ", tipoDePagamento="
				+ tipoDePagamento + ", produto=" + produto + "]";
	}

	public void validate() throws ServiceDacException {
		if (this.inicioDataDoPedido != null && this.fimDataDoPedido != null) {
			if (this.inicioDataDoPedido.after(this.fimDataDoPedido)) {
				throw new ServiceDacException("'\r\n"
						+ "'The start of the order interval \"must be before\" the end of the requested period'!");
			}
		}
	}

}
