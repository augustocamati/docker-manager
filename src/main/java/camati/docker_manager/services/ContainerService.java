package camati.docker_manager.services;

import java.util.List;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Container;

import camati.docker_manager.ports.ContainerServicePort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ContainerService implements ContainerServicePort {
  private final DockerClient dockerClient;

  @Override
  public void createContainer(String imageName) {
    try (var cmd = dockerClient.createContainerCmd(imageName)) {
      cmd.exec();
    }
  }

  @Override
  public List<Container> listContainers(boolean showAll) {
    return dockerClient.listContainersCmd()
        .withShowAll(showAll)
        .exec();
  }

  @Override
  public void startContainer(String id) {
    dockerClient.startContainerCmd(id).exec();
  }

  @Override
  public void stopContainer(String id) {
    dockerClient.startContainerCmd(id).exec();
  }

  @Override
  public void deleteContainer(String id) {
    dockerClient.removeContainerCmd(id).exec();
  }
  
}
