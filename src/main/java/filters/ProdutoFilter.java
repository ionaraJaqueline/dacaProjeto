package filters;

import java.util.Date;

import entities.TipoDeProduto;
import service.ServiceDacException;

public class ProdutoFilter {
	private static final long serialVersionUID = 1L;
	private String nome;
	private String descricao;
	private Date inicioDataDeValidade;
	private Date fimDataDeValidade;
	private Date inicioDataEntrada;
	private Date fimDataEntrada;
	private Date inicioDataSaida;
	private Date fimDataSaida;
	private TipoDeProduto tipoDeProduto;

	public ProdutoFilter() {

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getInicioDataDeValidade() {
		return inicioDataDeValidade;
	}

	public void setInicioDataDeValidade(Date inicioDataDeValidade) {
		this.inicioDataDeValidade = inicioDataDeValidade;
	}

	public Date getFimDataDeValidade() {
		return fimDataDeValidade;
	}

	public void setFimDataDeValidade(Date fimDataDeValidade) {
		this.fimDataDeValidade = fimDataDeValidade;
	}

	public Date getInicioDataEntrada() {
		return inicioDataEntrada;
	}

	public void setInicioDataEntrada(Date inicioDataEntrada) {
		this.inicioDataEntrada = inicioDataEntrada;
	}

	public Date getFimDataEntrada() {
		return fimDataEntrada;
	}

	public void setFimDataEntrada(Date fimDataEntrada) {
		this.fimDataEntrada = fimDataEntrada;
	}

	public Date getInicioDataSaida() {
		return inicioDataSaida;
	}

	public void setInicioDataSaida(Date inicioDataSaida) {
		this.inicioDataSaida = inicioDataSaida;
	}

	public Date getFimDataSaida() {
		return fimDataSaida;
	}

	public void setFimDataSaida(Date fimDataSaida) {
		this.fimDataSaida = fimDataSaida;
	}

	public TipoDeProduto getTipoDeProduto() {
		return tipoDeProduto;
	}

	public void setTipoDeProduto(TipoDeProduto tipoDeProduto) {
		this.tipoDeProduto = tipoDeProduto;
	}

	public boolean isEmpty() {
		if (this.nome != null && !this.nome.trim().isEmpty()) {
			return false;
		}
		if (this.descricao != null && !this.descricao.trim().isEmpty()) {
			return false;
		}
		if (this.inicioDataDeValidade != null) {
			return false;
		}
		if (this.fimDataDeValidade != null) {
			return false;
		}
		if (this.inicioDataEntrada != null) {
			return false;
		}
		if (this.fimDataEntrada != null) {
			return false;
		}
		if (this.inicioDataSaida != null) {
			return false;
		}
		if (this.fimDataSaida != null) {
			return false;
		}
		if (this.tipoDeProduto != null) {
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		return "ProdutoFilter [nome=" + nome + ", descricao=" + descricao + ", inicioDataDeValidade="
				+ inicioDataDeValidade + ", fimDataDeValidade=" + fimDataDeValidade + ", inicioDataEntrada="
				+ inicioDataEntrada + ", fimDataEntrada=" + fimDataEntrada + ", inicioDataSaida=" + inicioDataSaida
				+ ", fimDataSaida=" + fimDataSaida + ", tipoDeProduto=" + tipoDeProduto + "]";
	}

	public void validate() throws ServiceDacException {
		if (this.inicioDataDeValidade != null && this.fimDataDeValidade != null) {
			if (this.inicioDataDeValidade.after(this.fimDataDeValidade)) {
				throw new ServiceDacException("'\r\n"
						+ "O início do intervalo da  Validade \"deve ser antes\" do final do período da Validade'!");
			}
			if (this.inicioDataEntrada != null && this.fimDataEntrada != null) {
				if (this.inicioDataEntrada.after(this.fimDataEntrada)) {
					throw new ServiceDacException("'\r\n"
							+ "O início do intervalo de entrada \"deve ser antes\" do final do período de entrada'!");
				}
				if (this.inicioDataSaida != null && this.fimDataSaida != null) {
					if (this.inicioDataSaida.after(this.fimDataSaida)) {
						throw new ServiceDacException("'\r\n"
								+ "O início do intervalo de saida \"deve ser antes\" do final do período de saida'!");
					}
				}
			}

		}
	}
}
