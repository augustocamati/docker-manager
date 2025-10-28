package camati.docker_manager.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Image", description = "Representa uma imagem Docker no sistema")
public class ImageDto {
  @Schema(description = "Identificador único da imagem (ID do Docker)", example = "sha256:9c4b12345d1f2a3b4c5d6e7f8g9h0i1j2k3l4m5n6o7p8q9r0s1t2u3v4w5x6y7")
  private String id;

  @Schema(description = "Tags do repositório da imagem", example = "[\"nginx:latest\"]")
  private String[] repoTags;

  @Schema(description = "Tamanho da imagem em bytes", example = "123456789")
  private Long size;

  public ImageDto(String id, String[] repoTags, Long size) {
    this.id = id;
    this.repoTags = repoTags;
    this.size = size;
  }

}
