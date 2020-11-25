package br.com.rti.rifareal.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.rti.rifareal.domain.enums.StatusNumeroRifa;

@Entity
public class NumeroRifa {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "pk_id_numero_rifa" )
	private Long id;

	@JoinColumn( name = "fk_id_rifa" )
	@ManyToOne( fetch = FetchType.LAZY, optional = false )
	private Rifa rifa;

	private Integer valor;

	@Enumerated( EnumType.ORDINAL )
	private StatusNumeroRifa status;

	@JoinColumn( name = "fk_id_ordem_de_compra" )
	@ManyToOne( fetch = FetchType.LAZY, optional = false )
	private OrdemDeCompra ordemDeCompra;

	public NumeroRifa() {}

	public Long getId() {
		return id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public Rifa getRifa() {
		return rifa;
	}

	public void setRifa( Rifa rifa ) {
		this.rifa = rifa;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor( Integer valor ) {
		this.valor = valor;
	}

	public StatusNumeroRifa getStatus() {
		return status;
	}

	public void setStatus( StatusNumeroRifa status ) {
		this.status = status;
	}

	public OrdemDeCompra getOrdemDeCompra() {
		return ordemDeCompra;
	}

	public void setOrdemDeCompra( OrdemDeCompra ordemDeCompra ) {
		this.ordemDeCompra = ordemDeCompra;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
		result = prime * result + ( ( ordemDeCompra == null ) ? 0 : ordemDeCompra.hashCode() );
		result = prime * result + ( ( rifa == null ) ? 0 : rifa.hashCode() );
		result = prime * result + ( ( status == null ) ? 0 : status.hashCode() );
		result = prime * result + ( ( valor == null ) ? 0 : valor.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		NumeroRifa other = (NumeroRifa) obj;
		if ( id == null ) {
			if ( other.id != null )
				return false;
		} else if ( !id.equals( other.id ) )
			return false;
		if ( ordemDeCompra == null ) {
			if ( other.ordemDeCompra != null )
				return false;
		} else if ( !ordemDeCompra.equals( other.ordemDeCompra ) )
			return false;
		if ( rifa == null ) {
			if ( other.rifa != null )
				return false;
		} else if ( !rifa.equals( other.rifa ) )
			return false;
		if ( status != other.status )
			return false;
		if ( valor == null ) {
			if ( other.valor != null )
				return false;
		} else if ( !valor.equals( other.valor ) )
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "NumeroRifa [id=" + id + ", rifa=" + rifa + ", valor=" + valor + ", status=" + status + ", ordemDeCompra=" + ordemDeCompra + "]";
	}

}
