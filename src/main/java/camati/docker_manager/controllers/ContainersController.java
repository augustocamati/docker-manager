package camati.docker_manager.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import camati.docker_manager.dto.ContainerDto;
import camati.docker_manager.services.ContainerService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/containers")
public class ContainersController {
  private final ContainerService containerService;

  @PostMapping("")
  public void createContainer(@RequestBody String imageName) {
    containerService.createContainer(imageName);
  }
  
  @GetMapping
  public List<ContainerDto> listContainers() {
   

    return containerService.listContainers(true)
        .stream()
        .map(container -> new ContainerDto(
            container.getId(),
            container.getImage(),
            container.getNames(),
            container.getStatus()))
        .toList();
  }

  @PostMapping("/{id}/start")
  public void startContainer(@PathVariable String id) {
    containerService.startContainer(id);
  }

  @PostMapping("/{id}/stop")
  public void stopContainer(@PathVariable String id) {
    containerService.stopContainer(id);
  }

  @PostMapping("/{id}/delete")
  public void deleteContainer(@PathVariable String id) {
    containerService.deleteContainer(id);
  }



}
