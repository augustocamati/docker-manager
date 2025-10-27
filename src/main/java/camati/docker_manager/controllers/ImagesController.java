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
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/images")
public class ImagesController {
  private final ImageService imageService;

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

  @DeleteMapping("/{id}")
  void deleteImage(@PathVariable  String id) {
    imageService.deleteImage(id);
  }

  @GetMapping("/filter")
  List<ImageDto> filterImages(@RequestParam(required = false) String filterName) {
    return imageService.filterImages(filterName)
        .stream()
        .map(image -> new ImageDto(
            image.getId(),
            image.getRepoTags(),
            image.getSize()))
        .toList();
  }
}
