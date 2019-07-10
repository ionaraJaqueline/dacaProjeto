package converters;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import entities.Produto;

import impl.ProdutoServiceImpl;

import service.ProdutoService;
import service.ServiceDacException;


@FacesConverter(forClass = Produto.class)
public class ProdutoConverter implements Converter<Produto> {
	private ProdutoService produto = new ProdutoServiceImpl();


	@Override
	public Produto getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.trim().isEmpty()) {
			return null;
		}

		try {
			int id = Integer.parseInt(value);
			return produto.getByID(id);
		} catch (ServiceDacException | NumberFormatException e) {
			String msgErroStr = String.format(
					"Erro de conversão! Não foi possível realizar a conversão da string '%s' para o tipo esperado.",
					value);
			FacesMessage msgErro = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErroStr, msgErroStr);
			throw new ConverterException(msgErro);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Produto value) {
		if (!(value instanceof Produto)) {
			return null;
		}

		Integer id = ((Produto) value).getId();
		return (id != null) ? id.toString() : null;
	}

}
