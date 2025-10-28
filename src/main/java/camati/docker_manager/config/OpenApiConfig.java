package camati.docker_manager.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI dockerManagerOpenAPI() {
    return new OpenAPI()
        .info(new Info()
            .title("Docker Manager API")
            .description("API para gerenciamento e monitoramento de containers Docker")
            .version("1.0.0")
            .contact(new Contact()
                .name("Augusto Camati")
                .email("augusto.camati@example.com")
                .url("https://github.com/AugustoCamati"))
            .license(new License()
                .name("MIT License")
                .url("https://opensource.org/licenses/MIT")))
        .servers(List.of(
            new Server().url("http://localhost:8080").description("Servidor local de desenvolvimento")))
        .externalDocs(new ExternalDocumentation()
            .description("Repositório do projeto no GitHub")
            .url("https://github.com/AugustoCamati/docker-manager"));
  }
}
