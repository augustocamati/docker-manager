package camati.docker_manager.dto;

import lombok.Data;

@Data
public class ImageDto {
  private String id;
  private String[] repoTags;
  private Long size;

  public ImageDto(String id, String[] repoTags, Long size) {
    this.id = id;
    this.repoTags = repoTags;
    this.size = size;
  }

}
