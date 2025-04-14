package br.com;

import java.awt.Desktop;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.model.entity.Produtos;
import br.com.model.entity.Usuario;
import br.com.repository.ProdutoRepository;
import br.com.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;    

@Slf4j
@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioService;
    
    @Autowired
    private ProdutoRepository produtoRepository;

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
            log.info("Usuário administrador cadastrado com sucesso!");
            usuarioService.save(user);

            // Cadastro de 5 produtos de panificadora com descrições ajustadas
            Produtos produto1 = new Produtos();
            produto1.setNome("Pão Francês");
            produto1.setDescricao("Pão crocante por fora e macio por dentro, ideal para o café da manhã ou lanche. Feito diariamente com ingredientes frescos.");
            produto1.setPreco(BigDecimal.valueOf(0.50)); // Preço por unidade
            produto1.setQuantidade(500L);
            produtoRepository.save(produto1);

            Produtos produto2 = new Produtos();
            produto2.setNome("Bolo de Cenoura com Cobertura de Chocolate");
            produto2.setDescricao("Bolo caseiro de cenoura fofinho com deliciosa cobertura de chocolate. Perfeito para acompanhar o café.");
            produto2.setPreco(BigDecimal.valueOf(15.00)); // Preço por unidade
            produto2.setQuantidade(30L);
            produtoRepository.save(produto2);

            Produtos produto3 = new Produtos();
            produto3.setNome("Pão de Queijo");
            produto3.setDescricao("Tradicional pão de queijo mineiro, crocante por fora e cremoso por dentro. Servido quentinho.");
            produto3.setPreco(BigDecimal.valueOf(1.50)); // Preço por unidade
            produto3.setQuantidade(200L);
            produtoRepository.save(produto3);

            Produtos produto4 = new Produtos();
            produto4.setNome("Torta de Frango");
            produto4.setDescricao("Torta salgada recheada com frango desfiado, catupiry e temperos selecionados. Ideal para refeições rápidas.");
            produto4.setPreco(BigDecimal.valueOf(25.00)); // Preço por unidade
            produto4.setQuantidade(15L);
            produtoRepository.save(produto4);

            Produtos produto5 = new Produtos();
            produto5.setNome("Croissant de Chocolate");
            produto5.setDescricao("Croissant folhado recheado com chocolate meio amargo, assado até ficar dourado. Doce e irresistível.");
            produto5.setPreco(BigDecimal.valueOf(4.00)); // Preço por unidade
            produto5.setQuantidade(100L);
            produtoRepository.save(produto5);

            log.info("5 produtos de panificadora cadastrados com sucesso!");
        }
    }



}
