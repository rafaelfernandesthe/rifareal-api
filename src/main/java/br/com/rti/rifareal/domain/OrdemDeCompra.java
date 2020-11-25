package br.com.rti.rifareal.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class OrdemDeCompra {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "pk_id_ordem_de_compra" )
	private Long id;

	private String nome;
	private String cpf;
	private String telefone;
	private String email;
	private Boolean termos;

	@ManyToOne( optional = false, fetch = FetchType.EAGER )
	@JoinColumn( name = "fk_id_rifa" )
	private Rifa rifa;

	@JoinColumn( name = "fk_id_ordem_de_compra" )
	@OneToMany( orphanRemoval = true, fetch = FetchType.EAGER )
	private List<NumeroRifa> numeros;

	private Integer valorTotal;

	public OrdemDeCompra() {}

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

	public Rifa getRifa() {
		return rifa;
	}

	public void setRifa( Rifa rifa ) {
		this.rifa = rifa;
	}

	public List<NumeroRifa> getNumeros() {
		return numeros;
	}

	public void setNumeros( List<NumeroRifa> numeros ) {
		this.numeros = numeros;
	}

	public Integer getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal( Integer valorTotal ) {
		this.valorTotal = valorTotal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( cpf == null ) ? 0 : cpf.hashCode() );
		result = prime * result + ( ( email == null ) ? 0 : email.hashCode() );
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
		result = prime * result + ( ( nome == null ) ? 0 : nome.hashCode() );
		result = prime * result + ( ( numeros == null ) ? 0 : numeros.hashCode() );
		result = prime * result + ( ( rifa == null ) ? 0 : rifa.hashCode() );
		result = prime * result + ( ( telefone == null ) ? 0 : telefone.hashCode() );
		result = prime * result + ( ( termos == null ) ? 0 : termos.hashCode() );
		result = prime * result + ( ( valorTotal == null ) ? 0 : valorTotal.hashCode() );
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
		OrdemDeCompra other = (OrdemDeCompra) obj;
		if ( cpf == null ) {
			if ( other.cpf != null )
				return false;
		} else if ( !cpf.equals( other.cpf ) )
			return false;
		if ( email == null ) {
			if ( other.email != null )
				return false;
		} else if ( !email.equals( other.email ) )
			return false;
		if ( id == null ) {
			if ( other.id != null )
				return false;
		} else if ( !id.equals( other.id ) )
			return false;
		if ( nome == null ) {
			if ( other.nome != null )
				return false;
		} else if ( !nome.equals( other.nome ) )
			return false;
		if ( numeros == null ) {
			if ( other.numeros != null )
				return false;
		} else if ( !numeros.equals( other.numeros ) )
			return false;
		if ( rifa == null ) {
			if ( other.rifa != null )
				return false;
		} else if ( !rifa.equals( other.rifa ) )
			return false;
		if ( telefone == null ) {
			if ( other.telefone != null )
				return false;
		} else if ( !telefone.equals( other.telefone ) )
			return false;
		if ( termos == null ) {
			if ( other.termos != null )
				return false;
		} else if ( !termos.equals( other.termos ) )
			return false;
		if ( valorTotal == null ) {
			if ( other.valorTotal != null )
				return false;
		} else if ( !valorTotal.equals( other.valorTotal ) )
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrdemDeCompra [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", telefone=" + telefone + ", email=" + email + ", termos=" + termos + ", rifa=" + rifa + ", numeros=" + numeros + ", valorTotal=" + valorTotal + "]";
	}

}
