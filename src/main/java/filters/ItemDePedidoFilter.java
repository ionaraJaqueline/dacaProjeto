package filters;

import entities.Pedido;
import entities.Produto;

public class ItemDePedidoFilter {
	private static final long serialVersionUID = 1L;
	private Integer quantidade;
	private Produto produto;
	private Pedido pedido;

	public ItemDePedidoFilter() {

	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public boolean isEmpty() {
		if (this.quantidade != null) {
			return false;
		}
		if (this.produto != null) {
			return false;
		}
		if (this.pedido != null) {
			return false;
		}

		return true;
	}
}
