package camati.docker_manager.ports;

import java.util.List;

import com.github.dockerjava.api.model.Image;

public interface ImageServicePort {
  List<Image> listImages();
  void deleteImage(String id);
  List<Image> filterImages(String filterName);
  
}
