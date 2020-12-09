package br.com.rti.rifareal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rti.rifareal.domain.OrdemDeCompra;

public interface OrdemDeCompraRepository extends JpaRepository<OrdemDeCompra, Long> {

	List<OrdemDeCompra> findByRifaId( Long idRifa );

}
