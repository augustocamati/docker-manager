package camati.docker_manager.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import camati.docker_manager.dto.ImageDto;
import camati.docker_manager.services.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/images")
@Tag(name = "Imagens", description = "Gerenciamento de imagens Docker")
public class ImagesController {
  private final ImageService imageService;


@Operation(summary = "Lista imagens", description = "Retorna todas as imagens disponíveis no host Docker.")
  @GetMapping
  List<ImageDto> listImages() {
    return imageService.listImages()
        .stream()
        .map(image -> new ImageDto(
            image.getId(),
            image.getRepoTags(),
            image.getSize()))
        .toList();
  }
  @Operation(summary = "Remove imagem", description = "Remove permanentemente uma imagem do host Docker.")
  @DeleteMapping("/{id}")
  void deleteImage(@Parameter(description = "ID da imagem") @PathVariable String id) {
    imageService.deleteImage(id);
  }

  @Operation(summary = "Filtra imagens", description = "Retorna imagens que correspondem ao nome ou parte do nome fornecido.")
  @GetMapping("/filter")
  List<ImageDto> filterImages(
      @Parameter(description = "Nome ou parte do nome da imagem") 
      @RequestParam(required = true) String filterName) {
    return imageService.filterImages(filterName)
        .stream()
        .map(image -> new ImageDto(
            image.getId(),
            image.getRepoTags(),
            image.getSize()))
        .toList();
  }
}
