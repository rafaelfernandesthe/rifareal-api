package br.com.rti.rifareal.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rti.rifareal.domain.Rifa;

public interface RifaRepository extends JpaRepository<Rifa, Long> {

	public Optional<Rifa> findOneByCodigo( String codigo );

}
