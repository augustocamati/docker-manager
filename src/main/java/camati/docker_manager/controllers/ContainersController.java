package camati.docker_manager.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import camati.docker_manager.dto.ContainerDto;
import camati.docker_manager.services.ContainerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/containers")
@Tag(name = "Containers", description = "Gerenciamento de containers Docker")
public class ContainersController {
  private final ContainerService containerService;

  @Operation(summary = "Cria um novo container", description = "Cria um container a partir da imagem informada.")
  @PostMapping("/{imageName}/create")
  public void createContainer(
      @Parameter(description = "Nome da imagem Docker usada para criar o container") @PathVariable String imageName) {
    containerService.createContainer(imageName);
  }

  @Operation(summary = "Lista containers", description = "Retorna todos os containers em execução ou todos, dependendo do parâmetro.")
  @GetMapping()
  public List<ContainerDto> listContainers(
      @Parameter(description = "Se verdadeiro, mostra todos os containers, inclusive os parados") @RequestParam(defaultValue = "false") boolean showAll) {

    return containerService.listContainers(
        showAll)
        .stream()
        .map(container -> new ContainerDto(
            container.getId(),
            container.getImage(),
            container.getNames(),
            container.getStatus()))
        .toList();
  }

  @Operation(summary = "Inicia container", description = "Inicia um container existente pelo seu ID.")
  @PostMapping("/{id}/start")
  public void startContainer(@Parameter(description = "ID do container") @PathVariable String id) {
    containerService.startContainer(id);
  }

  @Operation(summary = "Para container", description = "Para a execução de um container em execução.")
  @PostMapping("/{id}/stop")
  public void stopContainer(@Parameter(description = "ID do container") @PathVariable String id) {
    containerService.stopContainer(id);
  }

  @Operation(summary = "Remove container", description = "Remove permanentemente um container do host Docker.")
  @DeleteMapping("/{id}/delete")
  public void deleteContainer(@Parameter(description = "ID do container") @PathVariable String id) {
    containerService.deleteContainer(id);
  }

}
