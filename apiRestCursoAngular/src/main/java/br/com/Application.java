package br.com;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.model.entity.Usuario;
import br.com.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;    

@Slf4j
@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioService;

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
    }

    @Override
    public void run(String... args) throws Exception {
        List<Usuario> find = usuarioService.findByLoginAndSenha("admin", "admin");
        
        if (find == null || find.isEmpty()) {
            Usuario user = new Usuario();
            user.setCpf("administrador");
            user.setLogin("admin");
            user.setSenha("admin");
            user.setNome("administrador");
            log.info("Adm cadastrado!");
            usuarioService.save(user);
        }
    }
}
