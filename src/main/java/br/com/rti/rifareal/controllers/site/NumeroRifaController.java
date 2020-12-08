package br.com.rti.rifareal.controllers.site;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rti.rifareal.domain.NumeroRifa;
import br.com.rti.rifareal.domain.dto.NumeroRifaDTO;
import br.com.rti.rifareal.repositories.NumeroRifaRepository;

@RequestMapping( "/site/numero-rifa" )
@RestController
public class NumeroRifaController {

	private static Logger logger = LoggerFactory.getLogger( NumeroRifaController.class );

	@Autowired
	private NumeroRifaRepository numeroRifaRespository;

	@GetMapping( "/byId/{id}" )
	public ResponseEntity<NumeroRifaDTO> loadById( @PathVariable( "id" ) Long id ) {
		logger.info( "loadById({})", id );
		NumeroRifa loaded = numeroRifaRespository.findById( id ).orElse( null );

		if ( loaded == null )
			return new ResponseEntity<>( HttpStatus.NOT_FOUND );

		return new ResponseEntity<NumeroRifaDTO>( new NumeroRifaDTO( loaded ), HttpStatus.OK );
	}

}
