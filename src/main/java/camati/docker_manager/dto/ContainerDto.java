package camati.docker_manager.dto;

import lombok.Data;

@Data
public class ContainerDto {
  private String id;
  private String image;
  private String status;
  private String[] names;

  public ContainerDto(String id, String image, String[] names,String status) {
    this.id = id;
    this.image = image;
    this.status = status;
    this.names = names;
  }



}