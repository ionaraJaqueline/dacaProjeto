package entities;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TB_PEDIDO")

public class Pedido implements Identificavel {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	private Float valorUnitario;
	private Float valorTotal;
	@Temporal(TemporalType.DATE)
	private Date dataDoPedido;

	@Column(name = "pedido_tipoDePagamento")
	@Enumerated(EnumType.STRING)
	private TipoDePagamento tipoDePagamento;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "Codigo_ItemDePedido")
	private List<ItemDePedido> itemDePedido;
	

	public Pedido() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Date getDataDoPedido() {
		return dataDoPedido;
	}

	public void setDataDoPedido(Date dataDoPedido) {
		this.dataDoPedido = dataDoPedido;
	}

	public List<ItemDePedido> getItemDePedido() {
		return itemDePedido;
	}

	public void setItemDePedido(List<ItemDePedido> itemDePedido) {
		this.itemDePedido = itemDePedido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result + ((valorUnitario == null) ? 0 : valorUnitario.hashCode());
		result = prime * result + ((valorTotal == null) ? 0 : valorTotal.hashCode());
		result = prime * result + ((tipoDePagamento == null) ? 0 : tipoDePagamento.hashCode());
		result = prime * result + ((dataDoPedido == null) ? 0 : removeTime(dataDoPedido).hashCode());
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
		Pedido other = (Pedido) obj;
		if (tipoDePagamento != other.tipoDePagamento)
			return false;

		if (valorUnitario == null) {
			if (other.valorUnitario != null)
				return false;
		} else if (!valorUnitario.equals(other.valorUnitario))
			return false;
		if (valorTotal == null) {
			if (other.valorTotal != null)
				return false;
		} else if (!valorTotal.equals(other.valorTotal))
			return false;
		if (dataDoPedido == null) {
			if (other.dataDoPedido != null)
				return false;
		} else if (!dataDoPedido.equals(other.dataDoPedido))
			return false;
		if (itemDePedido == null) {
			if (other.itemDePedido != null)
				return false;
		} else if (!itemDePedido.equals(other.itemDePedido))
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
		return "Pedido [id=" + id + ", valorUnitario=" + valorUnitario + ", valorTotal=" + valorTotal
				+ ", dataDoPedido=" + dataDoPedido + ", tipoDePagamento=" + tipoDePagamento + ", itemDePedido="
				+ itemDePedido + "]";
	}

	@Override
	public Pedido clone() {
		Pedido clone = new Pedido();
		clone.setId(id);
		clone.setValorUnitario(valorUnitario);
		clone.setTipoDePagamento(tipoDePagamento);
		clone.setValorTotal(valorTotal);
		clone.setDataDoPedido(dataDoPedido);
		clone.setItemDePedido(itemDePedido);
		

		return clone;
	}

}
