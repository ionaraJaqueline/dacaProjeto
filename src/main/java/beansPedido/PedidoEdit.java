package beansPedido;


import java.util.List;

import javax.annotation.PostConstruct;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import beans.AbstractBean;
import beans.EnderecoPaginas;

import entities.ItemDePedido;
import entities.Pedido;
import entities.Produto;
import entities.TipoDePagamento;
import exception.DacRuntimeException;

import impl.PedidoServiceImpl;
import impl.ProdutoServiceImpl;
import service.PedidoService;
import service.ProdutoService;
import service.ServiceDacException;

@Named
@ViewScoped
public class PedidoEdit extends AbstractBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7779155604704232174L;

	
	private PedidoService pedidoService = new PedidoServiceImpl();
	
	private ProdutoService produtoService = new ProdutoServiceImpl();
	
	private Pedido pedido;
	
	private Produto selectedProduto;
	
	private List<Produto> produtos;
	
	private List<ItemDePedido> itemDePedidos;

	

	@PostConstruct
	public void postConstruct() {
		inicializarProdutos();
	}

	private void inicializarProdutos() {
		try {
			produtos = produtoService.getAll();
		} catch (ServiceDacException e) {
			throw new DacRuntimeException("Ocorreu algum problema ao tentar recuperar os produtos.", e);
		}
	}

	public void init() {
		try {
			if (pedido == null) {
				// Criando
				pedido = new Pedido();
			} else {
				// Editando
				if (isEdicaoPedido()) {
					pedidoService.update(pedido);
				} else {
					pedidoService.save(pedido);
				}
			}
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}
	public String savePedido() {
		try {
			if (isEdicaoPedido()) {
				pedidoService.update(pedido);
			} else {
				pedidoService.save(pedido);
			}
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso("Pedido '" + pedido.getItemDePedido() + "' saved");

		return EnderecoPaginas.PAGINA_PRINCIPAL_PEDIDO;
	}
	
	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	public boolean isEdicaoPedido() {
		return pedido != null && pedido.getItemDePedido() != null;
	}

	public boolean isDinheiro() {
		return pedido != null && pedido.getTipoDePagamento() == TipoDePagamento.DINHEIRO;
	}

	public boolean isCartaoDebito() {
		return pedido != null && pedido.getTipoDePagamento() == TipoDePagamento.CARTAO_DEBITO;
	}

	public boolean isCartaoCredito() {
		return pedido != null && pedido.getTipoDePagamento() == TipoDePagamento.CARTAO_CREDITO;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<ItemDePedido> getItemDePedidos() {
		return itemDePedidos;
	}

	public void setItemDePedidos(List<ItemDePedido> itemDePedidos) {
		this.itemDePedidos = itemDePedidos;
	}
	public boolean isProdutoSelected() {
		return selectedProduto != null;
	}
}
