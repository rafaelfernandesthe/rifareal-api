package br.com.rti.rifareal.controllers.site;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rti.rifareal.domain.AssinanteNovidade;
import br.com.rti.rifareal.repositories.AssinanteNovidadeRepository;

@RequestMapping( "/site/assinar-noticias" )
@RestController
public class NoticiasController {

	private static Logger logger = LoggerFactory.getLogger( NumeroRifaController.class );

	@Autowired
	private AssinanteNovidadeRepository assinanteNovidadeRepository;

	@PostMapping
	public void assinarNoticias( @RequestBody String email ) {
		logger.info( "assinarNoticias {}", email );
		if ( email == null ) {
			return;
		}

		email = email.trim().toUpperCase();
		Optional<AssinanteNovidade> objDb = assinanteNovidadeRepository.findOneByEmail( email );
		if ( objDb.isPresent() ) {
			return;
		}

		AssinanteNovidade newAssinante = new AssinanteNovidade();
		newAssinante.setEmail( email );
		assinanteNovidadeRepository.save( newAssinante );
	}

}
