package entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TB_PRODUTO")
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	private String nome;
	private String descricao;
	private Integer quantidade;
	@Temporal(TemporalType.DATE)
	private Date dataDeValidade;

	@Temporal(TemporalType.DATE)
	private Date dataEntrada;
	@Temporal(TemporalType.DATE)
	private Date dataSaida;
	
	@Column(name = "produto_tipoDeProduto")
	@Enumerated(EnumType.STRING)
	private TipoDeProduto tipoDeProduto;
	
	ArrayList<ItemDePedido> itemDePedido;

	public Produto() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataDeValidade() {
		return dataDeValidade;
	}

	public void setDataDeValidade(Date dataDeValidade) {
		this.dataDeValidade = dataDeValidade;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoDeProduto getTipoDeProduto() {
		return tipoDeProduto;
	}

	public void setTipoDeProduto(TipoDeProduto tipoDeProduto) {
		this.tipoDeProduto = tipoDeProduto;
	}
	

	public ArrayList<ItemDePedido> getItemDePedido() {
		return itemDePedido;
	}

	public void setItemDePedido(ArrayList<ItemDePedido> itemDePedido) {
		this.itemDePedido = itemDePedido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataDeValidade == null) ? 0 : removeTime(dataDeValidade).hashCode());
		result = prime * result + ((dataEntrada == null) ? 0 : removeTime(dataEntrada).hashCode());
		result = prime * result + ((dataSaida == null) ? 0 : removeTime(dataSaida).hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((tipoDeProduto == null) ? 0 : tipoDeProduto.hashCode());
		result = prime * result + ((itemDePedido == null) ? 0 : itemDePedido.hashCode());
		
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (dataDeValidade == null) {
			if (other.dataDeValidade != null)
				return false;
		} else if (!removeTime(dataDeValidade).equals(removeTime(other.dataDeValidade)))
			return false;
		if (dataEntrada == null) {
			if (other.dataEntrada != null)
				return false;
		} else if (!removeTime(dataEntrada).equals(removeTime(other.dataEntrada)))
			return false;
		if (dataSaida == null) {
			if (other.dataSaida != null)
				return false;
		} else if (!removeTime(dataSaida).equals(removeTime(other.dataSaida)))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (tipoDeProduto != other.tipoDeProduto)
			return false;
		if (itemDePedido != other.itemDePedido)
			return false;
		return true;
	}

	private Date removeTime(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", quantidade=" + quantidade
				+ ", dataDeValidade=" + dataDeValidade + ", dataEntrada=" + dataEntrada + ", dataSaida=" + dataSaida
				+ ", tipoDeProduto=" + tipoDeProduto + ", itemDePedido=" + itemDePedido + "]";
	}

	@Override
	public Produto clone() {
		Produto clone = new Produto();
		clone.setId(id);
		clone.setNome(nome);
		clone.setDescricao(descricao);
		clone.setQuantidade(quantidade);
		clone.setDataDeValidade(dataDeValidade);
		clone.setDataEntrada(dataEntrada);
		clone.setDataSaida(dataSaida);
		clone.setTipoDeProduto(tipoDeProduto);
		clone.setItemDePedido(itemDePedido);

		return clone;
	}
}
