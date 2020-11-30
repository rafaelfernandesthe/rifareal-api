package br.com.rti.rifareal.controllers.site;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rti.rifareal.domain.Rifa;
import br.com.rti.rifareal.domain.dto.RifaDTO;
import br.com.rti.rifareal.repositories.RifaRepository;

@RequestMapping( "/site/rifa" )
@RestController
public class RifaSiteController {

	@Autowired
	private RifaRepository rifaRepository;

	@GetMapping( "/byMainPage" )
	public ResponseEntity<RifaDTO> loadByMainPage() {
		Rifa loaded = null;
		try {
			loaded = rifaRepository.findOneByTelaPrincipalTrue().orElse( null );
		} catch ( Exception e ) {
			// nothing
		}

		if ( loaded == null ) {
			loaded = rifaRepository.findAll().get( 0 );
		}
		return new ResponseEntity<RifaDTO>( new RifaDTO( loaded, false ), HttpStatus.OK );
	}

	@GetMapping( "/byId/{id}" )
	public ResponseEntity<RifaDTO> loadById( @PathVariable( "id" ) Long id ) {
		Rifa loaded = rifaRepository.findById( id ).orElse( null );

		if ( loaded == null )
			return new ResponseEntity<>( HttpStatus.NOT_FOUND );

		return new ResponseEntity<RifaDTO>( new RifaDTO( loaded, false ), HttpStatus.OK );
	}

	@GetMapping( "/byCode/{code}" )
	public ResponseEntity<RifaDTO> loadByCodigo( @PathVariable( "code" ) String code ) {
		Rifa loaded = rifaRepository.findOneByCodigo( code ).orElse( null );

		if ( loaded == null )
			return new ResponseEntity<>( HttpStatus.NOT_FOUND );

		return new ResponseEntity<RifaDTO>( new RifaDTO( loaded, false ), HttpStatus.OK );
	}

	@GetMapping( "/list3" )
	public List<RifaDTO> listar3Ultimas() {
		Page<Rifa> res = rifaRepository.findAll( PageRequest.of( 0, 3, Sort.by( Direction.DESC, "dataInclusao" ) ) );
		return res.getContent().stream().map( r -> new RifaDTO( r, true ) ).collect( Collectors.toList() );
	}

	@GetMapping( "/list" )
	public List<RifaDTO> listarTodas() {
		return rifaRepository.findAll( Sort.by( Direction.DESC, "dataInclusao" ) ).stream().map( r -> new RifaDTO( r, true ) ).collect( Collectors.toList() );
	}

}
