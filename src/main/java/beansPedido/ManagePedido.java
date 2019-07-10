package beansPedido;

import java.util.List;

import javax.annotation.PostConstruct;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import beans.AbstractBean;
import beans.EnderecoPaginas;
import entities.ItemDePedido;
import entities.Pedido;
import entities.Produto;
import exception.DacRuntimeException;
import filters.PedidoFilter;

import impl.PedidoServiceImpl;
import impl.ProdutoServiceImpl;
import service.PedidoService;
import service.ProdutoService;
import service.ServiceDacException;


@Named
@ViewScoped
public class ManagePedido extends AbstractBean {
	private static final long serialVersionUID = 1L;
	
	
	
	@Inject
	private PedidoService pedidoService = new PedidoServiceImpl();
	
	
	@Inject
	private ProdutoService produtoService = new ProdutoServiceImpl();

	private List<Pedido> pedidos;
	private List<Produto> produtos;
	

	private PedidoFilter pedidoFilter;

	

	public List<Produto> getProdutos() {
		return produtos;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public PedidoFilter getPedidoFilter() {
		return pedidoFilter;
	}

	public void setPedidoFilter(PedidoFilter pedidoFilter) {
		this.pedidoFilter = pedidoFilter;
	}

	@PostConstruct
	public void postConstruct() {
		inicializarProdutos();
		limpar();
		filtrar();
	}


	private void inicializarProdutos() {
		try {
			produtos = produtoService.getAll();
		} catch (ServiceDacException e) {
			throw new DacRuntimeException("Ocorreu algum problema ao tentar recuperar os produtos.", e);
		}
	}

	public String filtrar() {
		try {
			pedidos = pedidoService.findBy(getPedidoFilter());
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		return null;
	}

	public String limpar() {
		this.pedidoFilter = new PedidoFilter();
		return null;
	}

	public String delete(Pedido pedido) {
		try {
			pedidoService.delete(pedido);
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso("Pedido '" + pedido.getItemDePedido() + "' deleted");

		return EnderecoPaginas.PAGINA_PRINCIPAL_PEDIDO;
	}
}