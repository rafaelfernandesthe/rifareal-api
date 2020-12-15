package br.com.rti.rifareal.domain;

import java.io.Serializable;
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
public class Rifa implements Serializable {

	private static final long serialVersionUID = 1716807558437260933L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "pk_id_rifa" )
	private Long id;

	@Enumerated( EnumType.ORDINAL )
	private StatusRifa status;

	private String codigo;
	private String descricao;
	private Integer premioEmDinheiro;
	private Integer valor;
	private Integer rifasTotal;
	private Integer rifasRestantes;

	@Temporal( TemporalType.TIMESTAMP )
	private Date dataInclusao;

	@Temporal( TemporalType.TIMESTAMP )
	private Date dataInicio;

	@Temporal( TemporalType.TIMESTAMP )
	private Date dataSorteio;

	private String imagem;

	private boolean telaPrincipal;

	@JoinColumn( name = "fk_id_rifa" )
	@OneToMany( cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, orphanRemoval = true )
	private List<NumeroRifa> numeros;

	public Rifa() {}

	public Rifa( Long id ) {
		this.id = id;
	}

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

	public Date getDataSorteio() {
		return dataSorteio;
	}

	public void setDataSorteio( Date dataSorteio ) {
		this.dataSorteio = dataSorteio;
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

	public boolean isTelaPrincipal() {
		return telaPrincipal;
	}

	public void setTelaPrincipal( boolean telaPrincipal ) {
		this.telaPrincipal = telaPrincipal;
	}

	public void setDataInicio( Date dataInicio ) {
		this.dataInicio = dataInicio;
	}

	public Integer getPremioEmDinheiro() {
		return premioEmDinheiro;
	}

	public void setPremioEmDinheiro( Integer premioEmDinheiro ) {
		this.premioEmDinheiro = premioEmDinheiro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( codigo == null ) ? 0 : codigo.hashCode() );
		result = prime * result + ( ( dataInclusao == null ) ? 0 : dataInclusao.hashCode() );
		result = prime * result + ( ( dataInicio == null ) ? 0 : dataInicio.hashCode() );
		result = prime * result + ( ( dataSorteio == null ) ? 0 : dataSorteio.hashCode() );
		result = prime * result + ( ( descricao == null ) ? 0 : descricao.hashCode() );
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
		result = prime * result + ( ( imagem == null ) ? 0 : imagem.hashCode() );
		result = prime * result + ( ( numeros == null ) ? 0 : numeros.hashCode() );
		result = prime * result + ( ( premioEmDinheiro == null ) ? 0 : premioEmDinheiro.hashCode() );
		result = prime * result + ( ( rifasRestantes == null ) ? 0 : rifasRestantes.hashCode() );
		result = prime * result + ( ( rifasTotal == null ) ? 0 : rifasTotal.hashCode() );
		result = prime * result + ( ( status == null ) ? 0 : status.hashCode() );
		result = prime * result + ( telaPrincipal ? 1231 : 1237 );
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
		if ( dataSorteio == null ) {
			if ( other.dataSorteio != null )
				return false;
		} else if ( !dataSorteio.equals( other.dataSorteio ) )
			return false;
		if ( descricao == null ) {
			if ( other.descricao != null )
				return false;
		} else if ( !descricao.equals( other.descricao ) )
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
		if ( premioEmDinheiro == null ) {
			if ( other.premioEmDinheiro != null )
				return false;
		} else if ( !premioEmDinheiro.equals( other.premioEmDinheiro ) )
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
		if ( telaPrincipal != other.telaPrincipal )
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
		return "Rifa [id=" + id + ", status=" + status + ", codigo=" + codigo + ", descricao=" + descricao + ", premioEmDinheiro=" + premioEmDinheiro + ", valor=" + valor + ", rifasTotal=" + rifasTotal + ", rifasRestantes=" + rifasRestantes + ", dataInclusao=" + dataInclusao + ", dataInicio=" + dataInicio + ", dataSorteio=" + dataSorteio + ", imagem=" + imagem + ", telaPrincipal=" + telaPrincipal + ", numeros=" + numeros + "]";
	}

}
