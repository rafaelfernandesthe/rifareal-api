package br.com.rti.rifareal.domain.dto;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.rti.rifareal.domain.NumeroRifa;
import br.com.rti.rifareal.domain.enums.StatusNumeroRifa;

@JsonInclude( Include.NON_EMPTY )
public class NumeroRifaDTO implements Serializable {

	private static final long serialVersionUID = 6711494175574524964L;

	private Long id;

	private Integer valor;

	private StatusNumeroRifa status;

	private Integer statusNum;

	private String nomeComprador;

	private String cpfComprador;

	private String telefoneComprador;

	public NumeroRifaDTO() {}

	public NumeroRifaDTO( NumeroRifa numero ) {
		BeanUtils.copyProperties( numero, this );
		if ( !ObjectUtils.isEmpty( numero.getOrdemDeCompra() ) ) {
			this.setNomeComprador( getNomeVisao( numero.getOrdemDeCompra().getNome() ) );
			this.setCpfComprador( getCpfVisao( numero.getOrdemDeCompra().getCpf() ) );
			this.setTelefoneComprador( getTelefoneVisao( numero.getOrdemDeCompra().getTelefone() ) );
		}
	}

	private String getNomeVisao( String nomeOrdemDeCompra ) {
		String nome = "";
		String[] nomeSplited = nomeOrdemDeCompra.split( " " );
		nome = nomeSplited[0];
		if ( nomeSplited.length > 1 ) {
			nome += " " + String.join( " ", nomeSplited[1] ).replaceAll( "[\\w\\d]", "*" );
		}
		if ( nomeSplited.length > 2 ) {
			nome += " " + String.join( " ", nomeSplited[2] ).replaceAll( "[\\w\\d]", "*" );
		}
		if ( nomeSplited.length > 3 ) {
			nome += " " + nomeSplited[3];
		}
		return nome;
	}

	private String getCpfVisao( String cpfOrdemDeCompra ) {
		if ( cpfOrdemDeCompra.length() == 11 ) {
			return "***." + cpfOrdemDeCompra.substring( 3, 6 ) + ".***-" + cpfOrdemDeCompra.substring( 9, 11 );
		} else {
			return cpfOrdemDeCompra;
		}
	}

	private String getTelefoneVisao( String telefoneOrdemDeCompra ) {
		if ( telefoneOrdemDeCompra.length() == 11 ) {
			return "(" + telefoneOrdemDeCompra.substring( 0, 2 ) + ")*****-" + telefoneOrdemDeCompra.substring( 7, 11 );
		} else {
			return telefoneOrdemDeCompra;
		}
	}

	public NumeroRifa toEntity() {
		NumeroRifa entity = new NumeroRifa();
		BeanUtils.copyProperties( this, entity );
		return entity;
	}

	public Long getId() {
		return id;
	}

	public void setId( Long id ) {
		this.id = id;
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

	public String getNomeComprador() {
		return nomeComprador;
	}

	public void setNomeComprador( String nomeComprador ) {
		this.nomeComprador = nomeComprador;
	}

	public String getCpfComprador() {
		return cpfComprador;
	}

	public void setCpfComprador( String cpfComprador ) {
		this.cpfComprador = cpfComprador;
	}

	public String getTelefoneComprador() {
		return telefoneComprador;
	}

	public void setTelefoneComprador( String telefoneComprador ) {
		this.telefoneComprador = telefoneComprador;
	}

	public Integer getStatusNum() {
		statusNum = this.status.ordinal() + 1;
		return statusNum;
	}

	@Override
	public String toString() {
		return "NumeroRifaDTO [id=" + id + ", valor=" + valor + ", status=" + status + ", nomeComprador=" + nomeComprador + ", cpfComprador=" + cpfComprador + ", telefoneComprador=" + telefoneComprador + "]";
	}

}
