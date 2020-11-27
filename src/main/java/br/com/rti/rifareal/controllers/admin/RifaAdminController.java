package br.com.rti.rifareal.controllers.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rti.rifareal.domain.NumeroRifa;
import br.com.rti.rifareal.domain.Rifa;
import br.com.rti.rifareal.domain.dto.RifaDTO;
import br.com.rti.rifareal.repositories.RifaRepository;

@RequestMapping( "/admin/rifa" )
@RestController
public class RifaAdminController {

	@Autowired
	private RifaRepository rifaRepository;

	@PostMapping( "/saveNew" )
	public RifaDTO save( @RequestBody RifaDTO reqObj ) {
		Rifa entity = reqObj.toEntity();
		entity.setRifasRestantes( entity.getRifasTotal() );
		entity.setDiasRestantes( entity.getDiasTotal() );
		List<NumeroRifa> numeros = new ArrayList<NumeroRifa>();
		for ( int i = 0; i < reqObj.getRifasTotal(); i++ ) {
			numeros.add( new NumeroRifa( i, entity ) );
		}
		entity.setNumeros( numeros );

		return new RifaDTO( rifaRepository.save( entity ), true );
	}

}
