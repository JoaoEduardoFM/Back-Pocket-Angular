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

            // Cadastro de 5 produtos reais com descrições ajustadas
            Produtos produto1 = new Produtos();
            produto1.setNome("Apple iPhone 13");
            produto1.setDescricao("Smartphone com tela Super Retina XDR de 6,1\", chip A15 Bionic, e câmeras avançadas. Desempenho rápido e ótima duração de bateria.");
            produto1.setPreco(BigDecimal.valueOf(799.99));
            produto1.setQuantidade(50L);
            produtoRepository.save(produto1);

            Produtos produto2 = new Produtos();
            produto2.setNome("Samsung Galaxy S21 Ultra");
            produto2.setDescricao("Smartphone Android com tela Dynamic AMOLED 2X de 6,8\", câmeras de até 108 MP e suporte para S Pen. Alta qualidade e desempenho.");
            produto2.setPreco(BigDecimal.valueOf(1199.99));
            produto2.setQuantidade(30L);
            produtoRepository.save(produto2);

            Produtos produto3 = new Produtos();
            produto3.setNome("Microsoft Surface Laptop 4");
            produto3.setDescricao("Notebook fino e leve com tela touchscreen de 13,5\" ou 15\", processadores Intel/AMD, até 32GB de RAM, ideal para produtividade.");
            produto3.setPreco(BigDecimal.valueOf(1299.99));
            produto3.setQuantidade(20L);
            produtoRepository.save(produto3);

            Produtos produto4 = new Produtos();
            produto4.setNome("Sony WH-1000XM4");
            produto4.setDescricao("Fone de ouvido com cancelamento de ruído ativo, áudio de alta qualidade, 30h de bateria, e controle por toque. Confortável e imersivo.");
            produto4.setPreco(BigDecimal.valueOf(349.99));
            produto4.setQuantidade(40L);
            produtoRepository.save(produto4);

            Produtos produto5 = new Produtos();
            produto5.setNome("Amazon Echo Dot (4ª Geração)");
            produto5.setDescricao("Smart speaker com Alexa integrada, som nítido, controle de dispositivos inteligentes e streaming de música. Compacto e funcional.");
            produto5.setPreco(BigDecimal.valueOf(49.99));
            produto5.setQuantidade(100L);
            produtoRepository.save(produto5);

            log.info("5 produtos cadastrados com sucesso!");
        }
    }



}
