package br.com.rti.rifareal.controllers.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

@RequestMapping( "/admin/ordem-de-compra" )
@RestController
public class OrdemDeCompraAdminController {

	private static Logger logger = LoggerFactory.getLogger( OrdemDeCompraAdminController.class );

	@Autowired
	private OrdemDeCompraRepository ordemDeCompraRepository;

	@Autowired
	private NumeroRifaRepository numeroRifaRespository;

	@Autowired
	private RifaRepository rifaRepository;

	@GetMapping( "/findByRifa/{idRifa}" )
	public List<OrdemDeCompraDTO> findByRifa( @PathVariable( "idRifa" ) Long idRifa ) {
		logger.info( "/findByRifa {}", idRifa );

		List<OrdemDeCompra> result = ordemDeCompraRepository.findByRifaId( idRifa );

		return result.stream().map( r -> new OrdemDeCompraDTO( r ) ).collect( Collectors.toList() );
	}

	@PostMapping( "/savePay" )
	public OrdemDeCompraDTO savePay( @RequestBody OrdemDeCompraDTO reqObj ) {

		logger.info( "/savePay savePay() \n{}", reqObj.toString() );

		boolean rifaValida = true;
		Optional<Rifa> loadedRifa = rifaRepository.findById( reqObj.getIdRifa() );
		if ( !loadedRifa.isPresent() || !StatusRifa.ABERTA.equals( loadedRifa.get().getStatus() ) ) {
			rifaValida = false;
		}

		List<NumeroRifa> numerosInvalidos = new ArrayList<NumeroRifa>();
		List<NumeroRifa> loadedNumeros = new ArrayList<NumeroRifa>();
		reqObj.getIdNumeros().forEach( i -> {
			Optional<NumeroRifa> loaded = numeroRifaRespository.findById( i );
			if ( !loaded.isPresent() || ( StatusNumeroRifa.COMPRADO.equals( reqObj.getNovoStatus() ) && !StatusNumeroRifa.RESERVADO.equals( loaded.get().getStatus() ) ) ) {
				numerosInvalidos.add( loaded.orElseGet( null ) );
			} else {
				loaded.get().setStatus( reqObj.getNovoStatus() );
				loadedNumeros.add( loaded.get() );
			}
		} );

		if ( rifaValida && numerosInvalidos.isEmpty() ) {
			OrdemDeCompra persistenceObj = reqObj.toEntity();
			loadedNumeros.forEach( n -> n.setOrdemDeCompra( persistenceObj ) );
			persistenceObj.setNumeros( loadedNumeros );
			persistenceObj.setValorTotal( reqObj.getIdNumeros().size() * loadedRifa.get().getValor() );

			OrdemDeCompra objSalvo = ordemDeCompraRepository.save( persistenceObj );

			return new OrdemDeCompraDTO( objSalvo );
		}

		return null;
	}

}
