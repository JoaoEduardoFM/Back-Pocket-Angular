package br.com;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;

import br.com.model.entity.Usuario;
import br.com.model.response.ResponseRest;
import br.com.model.response.ResponseRest.messageType;
import br.com.service.UsuarioService;
import ch.qos.logback.core.subst.Token.Type;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class Application {
	

	static UsuarioService usuarioService;

	@Bean
	public ModelMapper modelMapper(){
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setSkipNullEnabled(true);
		return modelMapper;
	}

	public static void main(String[] args) throws IOException, URISyntaxException {
		String swaggerUrl = "http://localhost:8080/swagger-ui.html#/";

		if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
			SpringApplication.run(Application.class, args);
			Desktop.getDesktop().browse(new URI(swaggerUrl));
			log.info("Aplicação iniciada.");
		}
		
		ResponseEntity<?> find = usuarioService.buscaPorLoginSenha("admin", "admin");
		
		if(find == null) {
			ResponseRest resp = new ResponseRest();
			Usuario user = new Usuario();
			user.setCpf("administrador");
			user.setLogin("admin");
			user.setSenha("admin");
			user.setNome("administrador");
			resp.setMessage("Cadastro realizado com sucesso.");
			resp.setType(messageType.SUCESSO);
			usuarioService.salvaRegistro(user, resp);
		}
		
	}

}

