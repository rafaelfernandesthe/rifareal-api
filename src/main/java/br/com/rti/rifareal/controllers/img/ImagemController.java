package br.com.rti.rifareal.controllers.img;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping( "/site/product-images" )
@Controller
public class ImagemController {

	@Value( "${diretorio-imagem-produto}" )
	public String diretorioImagem;

	@GetMapping( value = "/{nomeImagem}" )
	public @ResponseBody byte[] getImage( @PathVariable( "nomeImagem" ) String nomeImagem ) {
		try {
			InputStream in = new FileInputStream( diretorioImagem + nomeImagem );
			return IOUtils.toByteArray( in );
		} catch ( Exception e ) {
			return null;
		}
	}

}
