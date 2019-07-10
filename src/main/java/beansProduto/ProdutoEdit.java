package beansProduto;


import javax.faces.view.ViewScoped;
import javax.inject.Named;

import beans.AbstractBean;
import beans.EnderecoPaginas;

import entities.Produto;
import entities.TipoDeProduto;

import impl.ProdutoServiceImpl;
import service.ProdutoService;
import service.ServiceDacException;



@Named
@ViewScoped
public class ProdutoEdit extends AbstractBean  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7779155604704232174L;

	private ProdutoService produtoService = new ProdutoServiceImpl();

	private Produto produto;
	
	public void init() {
		try {
			if (produto == null) {
				// Criando novo produto
				produto = new Produto();
			} else {
				// Editando um produto já existente
				if (isEdicaoDeProduto()) {
					produtoService.update(produto);
				} else {
					produtoService.save(produto);
				}
			}
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}
	public String saveProduto() {
		try {
			if (isEdicaoDeProduto()) {
				produtoService.update(produto);
			} else {
				produtoService.save(produto);
			}
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso("Produto '" + produto.getNome() + "' saved");

		return EnderecoPaginas.PAGINA_PRINCIPAL_PRODUTO;
	}

	public boolean isEdicaoDeProduto() {
		return produto != null && produto.getId() != null;
	}

	public boolean isBebida() {
		return produto != null && produto.getTipoDeProduto() == TipoDeProduto.BEBIDA;
	}

	public boolean isSobreMesa() {
		return produto != null && produto.getTipoDeProduto() == TipoDeProduto.SOBREMESA;
	}

	public boolean isLanche() {
		return produto != null && produto.getTipoDeProduto() == TipoDeProduto.LANCHE;
	}

	public ProdutoService getProdutoService() {
		return produtoService;
	}

	public void setProdutoService(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}


}
