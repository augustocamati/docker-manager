package camati.docker_manager.ports;

import java.util.List;

import com.github.dockerjava.api.model.Container;

public interface ContainerServicePort {
  List<Container> listContainers(boolean showAll);
    void startContainer(String id);
    void stopContainer(String id);
    void deleteContainer(String id);
}
