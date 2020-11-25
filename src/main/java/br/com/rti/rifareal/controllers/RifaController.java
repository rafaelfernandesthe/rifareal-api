package br.com.rti.rifareal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.rti.rifareal.domain.Rifa;
import br.com.rti.rifareal.repositories.RifaRepository;

@RequestMapping( "/site/rifa" )
@RestController( )
public class RifaController {

	@Autowired
	private RifaRepository rifaRepository;

	@GetMapping( "/byId" )
	public Rifa loadById( @RequestParam( "id" ) Long id ) {
		return rifaRepository.findById( id ).orElse( null );
	}

	@GetMapping( "/byCode" )
	public Rifa loadByCodigo( @RequestParam( "code" ) String code ) {
		return rifaRepository.findOneByCodigo( code ).orElse( null );
	}

	@GetMapping( "/list" )
	public List<Rifa> listar() {
		return rifaRepository.findAll( Sort.by( Direction.DESC, "dataInclusao" ) );
	}

	@GetMapping( "/list3" )
	public List<Rifa> listar3() {
		Page<Rifa> res = rifaRepository.findAll( PageRequest.of( 1, 3, Sort.by( Direction.DESC, "dataInclusao" ) ) );
		return res.getContent();
	}

}
