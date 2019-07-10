package entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TB_ITEM_DE_PEDIDO")

public class ItemDePedido implements Identificavel, Cloneable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer Id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pedido_fk", foreignKey = @ForeignKey(name = "fk__tb_itemDePedido__tb_pedido"))
	private Pedido pedido;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "produto_fk", foreignKey = @ForeignKey(name = "fk__tb_itemDePedido__tb_produto"))
	private Produto produto;
	
	private Integer quantidade;
	
	private float subTotal;

	public ItemDePedido() {

	}

	

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public float getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(float subTotal) {
		this.subTotal = subTotal;
	}
	
	

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((quantidade == null) ? 0 : quantidade.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		result = prime * result + ((pedido == null) ? 0 : pedido.hashCode());
		
		return result;
	}
	

	@Override
	public String toString() {
		return "ItemDePedido [Id=" + Id + ", pedido=" + pedido + ", produto=" + produto + ", quantidade=" + quantidade
				+ ", subTotal=" + subTotal + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemDePedido other = (ItemDePedido) obj;
		if (pedido == null) {
			if (other.pedido != null)
				return false;
		} else if (!pedido.equals(other.pedido))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		if (quantidade != other.quantidade)
			return false;
		if (subTotal != other.subTotal)
			return false;
		return true;
	}

	@Override
	public ItemDePedido clone() {
		ItemDePedido clone = new ItemDePedido();

		clone.setProduto(produto);
		clone.setQuantidade(quantidade);
		clone.setSubTotal(subTotal);
		return clone;
	}
}
