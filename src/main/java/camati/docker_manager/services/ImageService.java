package camati.docker_manager.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Image;

import camati.docker_manager.ports.ImageServicePort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ImageService implements ImageServicePort {

  private final DockerClient dockerClient;
  @Override
  public List<Image> listImages() {
    return dockerClient.listImagesCmd().exec();
  }

  @Override
  public void deleteImage(String id) {
     dockerClient.removeImageCmd(id).exec();
  }

  @Override
  public List<Image> filterImages(String filterName) {
    return dockerClient.listImagesCmd().withImageNameFilter(filterName).exec();
  }
  
}
