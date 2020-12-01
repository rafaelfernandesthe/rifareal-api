package br.com.rti.rifareal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.rti.rifareal.domain.NumeroRifa;
import br.com.rti.rifareal.domain.enums.StatusNumeroRifa;

public interface NumeroRifaRepository extends JpaRepository<NumeroRifa, Long> {

	@Modifying
	@Query( "UPDATE NumeroRifa SET status = ?2 WHERE id = ?1" )
	public void updateStatus( Long id, StatusNumeroRifa newStatus );

}
