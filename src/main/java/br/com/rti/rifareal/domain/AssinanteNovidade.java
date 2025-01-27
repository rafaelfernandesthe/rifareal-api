package br.com.rti.rifareal.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AssinanteNovidade implements Serializable {

	private static final long serialVersionUID = -1551813739043436434L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "pk_id_assinante_novidade" )
	private Long id;

	private String email;

	private String nome;

	private String telefone;

	public AssinanteNovidade() {}

	public Long getId() {
		return id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail( String email ) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome( String nome ) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone( String telefone ) {
		this.telefone = telefone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( email == null ) ? 0 : email.hashCode() );
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
		result = prime * result + ( ( nome == null ) ? 0 : nome.hashCode() );
		result = prime * result + ( ( telefone == null ) ? 0 : telefone.hashCode() );
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
		AssinanteNovidade other = (AssinanteNovidade) obj;
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
		if ( telefone == null ) {
			if ( other.telefone != null )
				return false;
		} else if ( !telefone.equals( other.telefone ) )
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AssinanteNovidade [id=" + id + ", email=" + email + ", nome=" + nome + ", telefone=" + telefone + "]";
	}

}
