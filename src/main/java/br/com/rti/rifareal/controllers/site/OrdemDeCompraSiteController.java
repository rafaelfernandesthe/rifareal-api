package br.com.rti.rifareal.controllers.site;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rti.rifareal.domain.NumeroRifa;
import br.com.rti.rifareal.domain.OrdemDeCompra;
import br.com.rti.rifareal.domain.Rifa;
import br.com.rti.rifareal.domain.dto.OrdemDeCompraDTO;
import br.com.rti.rifareal.domain.enums.StatusNumeroRifa;
import br.com.rti.rifareal.domain.enums.StatusRifa;
import br.com.rti.rifareal.repositories.NumeroRifaRepository;
import br.com.rti.rifareal.repositories.OrdemDeCompraRepository;
import br.com.rti.rifareal.repositories.RifaRepository;

@RequestMapping( "/site/ordem-de-compra" )
@RestController
public class OrdemDeCompraSiteController {

	private static Logger logger = LoggerFactory.getLogger( OrdemDeCompraSiteController.class );

	@Autowired
	private OrdemDeCompraRepository ordemDeCompraRepository;

	@Autowired
	private NumeroRifaRepository numeroRifaRespository;

	@Autowired
	private RifaRepository rifaRepository;

	@PostMapping( "/saveNew" )
	public OrdemDeCompraDTO save( @RequestBody OrdemDeCompraDTO reqObj ) {

		logger.info( "/saveNew save() \n{}", reqObj.toString() );

		boolean rifaValida = true;
		Optional<Rifa> loadedRifa = rifaRepository.findById( reqObj.getIdRifa() );
		if ( !loadedRifa.isPresent() || !StatusRifa.ABERTA.equals( loadedRifa.get().getStatus() ) ) {
			rifaValida = false;
		}

		List<NumeroRifa> numerosInvalidos = new ArrayList<NumeroRifa>();
		List<NumeroRifa> loadedNumeros = new ArrayList<NumeroRifa>();
		reqObj.getIdNumeros().forEach( i -> {
			Optional<NumeroRifa> loaded = numeroRifaRespository.findById( i );
			if ( !loaded.isPresent() || !StatusNumeroRifa.DISPONIVEL.equals( loaded.get().getStatus() ) ) {
				numerosInvalidos.add( loaded.orElseGet( null ) );
			} else {
				loaded.get().setStatus( StatusNumeroRifa.RESERVADO );
				loadedNumeros.add( loaded.get() );
			}
		} );

		if ( rifaValida && numerosInvalidos.isEmpty() ) {
			OrdemDeCompra persistenceObj = reqObj.toEntity();
			loadedNumeros.forEach( n -> n.setOrdemDeCompra( persistenceObj ) );
			persistenceObj.setNumeros( loadedNumeros );
			persistenceObj.setValorTotal( reqObj.getIdNumeros().size() * loadedRifa.get().getValor() );

			OrdemDeCompra objSalvo = ordemDeCompraRepository.save( persistenceObj );

			loadedRifa.get().setRifasRestantes( loadedRifa.get().getRifasRestantes() - reqObj.getIdNumeros().size() );
			rifaRepository.save( loadedRifa.get() );

			return new OrdemDeCompraDTO( objSalvo );
		}

		return null;
	}

	@GetMapping( "/byId/{id}" )
	public ResponseEntity<OrdemDeCompraDTO> loadById( @PathVariable( "id" ) Long id ) {
		OrdemDeCompra loaded = ordemDeCompraRepository.findById( id ).orElse( null );

		if ( loaded == null )
			return new ResponseEntity<>( HttpStatus.NOT_FOUND );

		return new ResponseEntity<OrdemDeCompraDTO>( new OrdemDeCompraDTO( loaded ), HttpStatus.OK );
	}

	@GetMapping( "/list" )
	public List<OrdemDeCompraDTO> listarTodas() {
		return ordemDeCompraRepository.findAll( Sort.by( Direction.DESC, "dataInclusao" ) ).stream().map( r -> new OrdemDeCompraDTO( r ) ).collect( Collectors.toList() );
	}

}
