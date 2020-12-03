package br.com.rti.rifareal.controllers.admin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.rti.rifareal.domain.NumeroRifa;
import br.com.rti.rifareal.domain.Rifa;
import br.com.rti.rifareal.domain.dto.RifaDTO;
import br.com.rti.rifareal.domain.enums.StatusRifa;
import br.com.rti.rifareal.repositories.RifaRepository;

@RequestMapping( "/admin/rifa" )
@RestController
public class RifaAdminController {

	@Autowired
	private RifaRepository rifaRepository;

	@Value( "${diretorio-imagem-produto}" )
	private String diretorioImagem;

	@PostMapping( "/anexo" )
	public void uploadAnexo( @RequestParam MultipartFile anexo ) throws IOException {
		FileUtils.copyInputStreamToFile( anexo.getInputStream(), new File( diretorioImagem + anexo.getOriginalFilename() ) );
	}

	@PostMapping( "/saveNew" )
	public RifaDTO save( @RequestBody RifaDTO reqObj ) {
		Rifa entity = reqObj.toEntity();
		entity.setStatus( StatusRifa.ABERTA );
		entity.setRifasRestantes( entity.getRifasTotal() );
		List<NumeroRifa> numeros = new ArrayList<NumeroRifa>();
		for ( int i = 0; i < reqObj.getRifasTotal(); i++ ) {
			numeros.add( new NumeroRifa( i, entity ) );
		}
		entity.setNumeros( numeros );

		return new RifaDTO( rifaRepository.save( entity ), true );
	}

}
