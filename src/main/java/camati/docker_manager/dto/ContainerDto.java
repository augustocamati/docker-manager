package camati.docker_manager.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Container", description = "Representa um container Docker no sistema")
public class ContainerDto {
  @Schema(description = "Identificador único do container (ID do Docker)", example = "9f6c2d3f8e17b5a2e48c5e2a6fdb3a7b2c3e8e91dfe5a7b4a2b5c9d3f8a7b6c2")
  private String id;

  @Schema(description = "Nome da imagem Docker usada pelo container", example = "nginx:latest")
  private String image;

  @Schema(description = "Status atual do container", example = "Up 3 minutes")
  private String status;

  @Schema(description = "Nomes atribuídos ao container", example = "[\"nginx\", \"webserver\"]")
  private String[] names;

  public ContainerDto(String id, String image, String[] names,String status) {
    this.id = id;
    this.image = image;
    this.status = status;
    this.names = names;
  }



}