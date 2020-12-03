package br.com.rti.rifareal.domain.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.rti.rifareal.domain.Rifa;
import br.com.rti.rifareal.domain.enums.StatusRifa;

@JsonInclude( Include.NON_EMPTY )
public class RifaDTO implements Serializable {

	private static final long serialVersionUID = 2638688190822943559L;

	private Long id;
	private StatusRifa status;
	private String codigo;
	private String descricao;
	private Integer valor;
	private Integer rifasTotal;
	private Integer rifasRestantes;
	@JsonFormat( pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS" )
	private Date dataInclusao;
	@JsonFormat( pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS" )
	private Date dataInicio;
	@JsonFormat( pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS" )
	private Date dataSorteio;
	private String dataSorteioStr;
	private String imagem;
	private boolean telaPrincipal;
	private List<NumeroRifaDTO> numeros;
	private long diasRestantes;

	public RifaDTO() {}

	public RifaDTO( Rifa rifa, boolean lazy ) {
		if ( rifa == null )
			return;
		BeanUtils.copyProperties( rifa, this, "numeros" );
		if ( !lazy ) {
			this.setNumeros( rifa.getNumeros().stream().map( r -> new NumeroRifaDTO( r ) ).collect( Collectors.toList() ) );
		}

		long diffInMillies = Math.abs( new Date().getTime() - this.getDataSorteio().getTime() );
		this.setDiasRestantes( TimeUnit.DAYS.convert( diffInMillies, TimeUnit.MILLISECONDS ) );
	}

	public Rifa toEntity() {
		Rifa entity = new Rifa();
		BeanUtils.copyProperties( this, entity );
		entity.setNumeros( this.getNumeros().stream().map( n -> n.toEntity() ).collect( Collectors.toList() ) );
		return entity;
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

	public String getDataSorteioStr() {
		if ( dataSorteio != null ) {
			dataSorteioStr = new SimpleDateFormat( "dd/MM/yyyy" ).format( dataSorteio );
		}
		return dataSorteioStr;
	}

	public void setDataSorteioStr( String dataSorteioStr ) {
		this.dataSorteioStr = dataSorteioStr;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem( String imagem ) {
		this.imagem = imagem;
	}

	public boolean isTelaPrincipal() {
		return telaPrincipal;
	}

	public void setTelaPrincipal( boolean telaPrincipal ) {
		this.telaPrincipal = telaPrincipal;
	}

	public List<NumeroRifaDTO> getNumeros() {
		if ( numeros == null ) {
			numeros = new ArrayList<NumeroRifaDTO>();
		}
		return numeros;
	}

	public void setNumeros( List<NumeroRifaDTO> numeros ) {
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

	public long getDiasRestantes() {
		return diasRestantes;
	}

	public void setDiasRestantes( long diasRestantes ) {
		this.diasRestantes = diasRestantes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( codigo == null ) ? 0 : codigo.hashCode() );
		result = prime * result + ( ( dataInclusao == null ) ? 0 : dataInclusao.hashCode() );
		result = prime * result + ( ( dataInicio == null ) ? 0 : dataInicio.hashCode() );
		result = prime * result + ( ( dataSorteio == null ) ? 0 : dataSorteio.hashCode() );
		result = prime * result + ( ( dataSorteioStr == null ) ? 0 : dataSorteioStr.hashCode() );
		result = prime * result + ( ( descricao == null ) ? 0 : descricao.hashCode() );
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
		result = prime * result + ( ( imagem == null ) ? 0 : imagem.hashCode() );
		result = prime * result + ( ( numeros == null ) ? 0 : numeros.hashCode() );
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
		RifaDTO other = (RifaDTO) obj;
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
		if ( dataSorteioStr == null ) {
			if ( other.dataSorteioStr != null )
				return false;
		} else if ( !dataSorteioStr.equals( other.dataSorteioStr ) )
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
		return "RifaDTO [id=" + id + ", status=" + status + ", codigo=" + codigo + ", descricao=" + descricao + ", valor=" + valor + ", rifasTotal=" + rifasTotal + ", rifasRestantes=" + rifasRestantes + ", dataInclusao=" + dataInclusao + ", dataInicio=" + dataInicio + ", dataSorteio=" + dataSorteio + ", dataSorteioStr=" + dataSorteioStr + ", imagem=" + imagem + ", telaPrincipal=" + telaPrincipal + ", numeros=" + numeros + "]";
	}

}
