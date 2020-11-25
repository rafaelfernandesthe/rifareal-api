package br.com.rti.rifareal.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.rti.rifareal.domain.enums.StatusRifa;

@Entity
public class Rifa {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "pk_id_rifa" )
	private Long id;

	@Enumerated( EnumType.ORDINAL )
	private StatusRifa status;

	private String codigo;
	private String descricao;
	private Integer valor;
	private Integer diasTotal;
	private Integer diasRestantes;
	private Integer rifasTotal;
	private Integer rifasRestantes;

	@Temporal( TemporalType.TIMESTAMP )
	private Date dataInclusao;

	@Temporal( TemporalType.TIMESTAMP )
	private Date dataInicio;

	@Temporal( TemporalType.TIMESTAMP )
	private Date dataFim;

	private String imagem;

	@JoinColumn( name = "fk_id_rifa" )
	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true )
	private List<NumeroRifa> numeros;

	public Rifa() {}

	public Long getId() {
		return id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public StatusRifa getStatus() {
		return status;
	}

	public void setStatus( StatusRifa status ) {
		this.status = status;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo( String codigo ) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao( String descricao ) {
		this.descricao = descricao;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor( Integer valor ) {
		this.valor = valor;
	}

	public Integer getDiasTotal() {
		return diasTotal;
	}

	public void setDiasTotal( Integer diasTotal ) {
		this.diasTotal = diasTotal;
	}

	public Integer getDiasRestantes() {
		return diasRestantes;
	}

	public void setDiasRestantes( Integer diasRestantes ) {
		this.diasRestantes = diasRestantes;
	}

	public Integer getRifasTotal() {
		return rifasTotal;
	}

	public void setRifasTotal( Integer rifasTotal ) {
		this.rifasTotal = rifasTotal;
	}

	public Integer getRifasRestantes() {
		return rifasRestantes;
	}

	public void setRifasRestantes( Integer rifasRestantes ) {
		this.rifasRestantes = rifasRestantes;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim( Date dataFim ) {
		this.dataFim = dataFim;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem( String imagem ) {
		this.imagem = imagem;
	}

	public List<NumeroRifa> getNumeros() {
		if ( numeros == null ) {
			numeros = new ArrayList<NumeroRifa>();
		}
		return numeros;
	}

	public void setNumeros( List<NumeroRifa> numeros ) {
		this.numeros = numeros;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao( Date dataInclusao ) {
		this.dataInclusao = dataInclusao;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio( Date dataInicio ) {
		this.dataInicio = dataInicio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( codigo == null ) ? 0 : codigo.hashCode() );
		result = prime * result + ( ( dataFim == null ) ? 0 : dataFim.hashCode() );
		result = prime * result + ( ( dataInclusao == null ) ? 0 : dataInclusao.hashCode() );
		result = prime * result + ( ( dataInicio == null ) ? 0 : dataInicio.hashCode() );
		result = prime * result + ( ( descricao == null ) ? 0 : descricao.hashCode() );
		result = prime * result + ( ( diasRestantes == null ) ? 0 : diasRestantes.hashCode() );
		result = prime * result + ( ( diasTotal == null ) ? 0 : diasTotal.hashCode() );
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
		result = prime * result + ( ( imagem == null ) ? 0 : imagem.hashCode() );
		result = prime * result + ( ( numeros == null ) ? 0 : numeros.hashCode() );
		result = prime * result + ( ( rifasRestantes == null ) ? 0 : rifasRestantes.hashCode() );
		result = prime * result + ( ( rifasTotal == null ) ? 0 : rifasTotal.hashCode() );
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
		Rifa other = (Rifa) obj;
		if ( codigo == null ) {
			if ( other.codigo != null )
				return false;
		} else if ( !codigo.equals( other.codigo ) )
			return false;
		if ( dataFim == null ) {
			if ( other.dataFim != null )
				return false;
		} else if ( !dataFim.equals( other.dataFim ) )
			return false;
		if ( dataInclusao == null ) {
			if ( other.dataInclusao != null )
				return false;
		} else if ( !dataInclusao.equals( other.dataInclusao ) )
			return false;
		if ( dataInicio == null ) {
			if ( other.dataInicio != null )
				return false;
		} else if ( !dataInicio.equals( other.dataInicio ) )
			return false;
		if ( descricao == null ) {
			if ( other.descricao != null )
				return false;
		} else if ( !descricao.equals( other.descricao ) )
			return false;
		if ( diasRestantes == null ) {
			if ( other.diasRestantes != null )
				return false;
		} else if ( !diasRestantes.equals( other.diasRestantes ) )
			return false;
		if ( diasTotal == null ) {
			if ( other.diasTotal != null )
				return false;
		} else if ( !diasTotal.equals( other.diasTotal ) )
			return false;
		if ( id == null ) {
			if ( other.id != null )
				return false;
		} else if ( !id.equals( other.id ) )
			return false;
		if ( imagem == null ) {
			if ( other.imagem != null )
				return false;
		} else if ( !imagem.equals( other.imagem ) )
			return false;
		if ( numeros == null ) {
			if ( other.numeros != null )
				return false;
		} else if ( !numeros.equals( other.numeros ) )
			return false;
		if ( rifasRestantes == null ) {
			if ( other.rifasRestantes != null )
				return false;
		} else if ( !rifasRestantes.equals( other.rifasRestantes ) )
			return false;
		if ( rifasTotal == null ) {
			if ( other.rifasTotal != null )
				return false;
		} else if ( !rifasTotal.equals( other.rifasTotal ) )
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
		return "Rifa [id=" + id + ", status=" + status + ", codigo=" + codigo + ", descricao=" + descricao + ", valor=" + valor + ", diasTotal=" + diasTotal + ", diasRestantes=" + diasRestantes + ", rifasTotal=" + rifasTotal + ", rifasRestantes=" + rifasRestantes + ", dataInclusao=" + dataInclusao + ", dataInicio=" + dataInicio + ", dataFim=" + dataFim + ", imagem=" + imagem + ", numeros=" + numeros + "]";
	}

}
