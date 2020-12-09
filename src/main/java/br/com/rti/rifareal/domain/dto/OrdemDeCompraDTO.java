package br.com.rti.rifareal.domain.dto;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.rti.rifareal.domain.NumeroRifa;
import br.com.rti.rifareal.domain.OrdemDeCompra;
import br.com.rti.rifareal.domain.Rifa;
import br.com.rti.rifareal.domain.enums.StatusNumeroRifa;

@JsonInclude( Include.NON_EMPTY )
public class OrdemDeCompraDTO implements Serializable {

	private static final long serialVersionUID = -343898193755078979L;

	private Long id;

	private String nome;
	private String cpf;
	private String telefone;
	private String email;
	private Boolean termos;

	private Long idRifa;

	private List<Long> idNumeros;

	private Integer valorTotal;

	private String status;

	private StatusNumeroRifa novoStatus;

	public OrdemDeCompraDTO() {}

	public OrdemDeCompraDTO( OrdemDeCompra ordemDeCompra ) {
		super();
		BeanUtils.copyProperties( ordemDeCompra, this );
		this.setIdRifa( ordemDeCompra.getRifa().getId() );
		this.setIdNumeros( ordemDeCompra.getNumeros().stream().map( n -> n.getId() ).collect( Collectors.toList() ) );
		this.setStatus( ordemDeCompra.getNumeros().get( 0 ).getStatus().name() );
	}

	public OrdemDeCompra toEntity() {
		OrdemDeCompra entity = new OrdemDeCompra();
		BeanUtils.copyProperties( this, entity );
		entity.setRifa( new Rifa( this.getIdRifa() ) );
		entity.setNumeros( this.getIdNumeros().stream().map( n -> new NumeroRifa( n ) ).collect( Collectors.toList() ) );
		return entity;
	}

	public Long getId() {
		return id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome( String nome ) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf( String cpf ) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone( String telefone ) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail( String email ) {
		this.email = email;
	}

	public Boolean getTermos() {
		return termos;
	}

	public void setTermos( Boolean termos ) {
		this.termos = termos;
	}

	public Integer getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal( Integer valorTotal ) {
		this.valorTotal = valorTotal;
	}

	public Long getIdRifa() {
		return idRifa;
	}

	public void setIdRifa( Long idRifa ) {
		this.idRifa = idRifa;
	}

	public List<Long> getIdNumeros() {
		return idNumeros;
	}

	public void setIdNumeros( List<Long> idNumeros ) {
		this.idNumeros = idNumeros;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus( String status ) {
		this.status = status;
	}

	public StatusNumeroRifa getNovoStatus() {
		return novoStatus;
	}

	public void setNovoStatus( StatusNumeroRifa novoStatus ) {
		this.novoStatus = novoStatus;
	}

	@Override
	public String toString() {
		return "OrdemDeCompraDTO [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", telefone=" + telefone + ", email=" + email + ", termos=" + termos + ", idRifa=" + idRifa + ", idNumeros=" + idNumeros + ", valorTotal=" + valorTotal + "]";
	}

}
