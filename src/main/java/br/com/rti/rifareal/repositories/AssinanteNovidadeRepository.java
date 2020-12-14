package br.com.rti.rifareal.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rti.rifareal.domain.AssinanteNovidade;

public interface AssinanteNovidadeRepository extends JpaRepository<AssinanteNovidade, Long> {

	Optional<AssinanteNovidade> findOneByEmail( String email );

}
