package camati.docker_manager.config;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.RemoteApiVersion;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;

@Configuration
public class DockerClientConfig {
  private String dockerHost = "tcp://localhost:2375";
       

  private static final Logger log = LoggerFactory.getLogger(DockerClientConfig.class);

  @Bean
  public DockerClient buildDockerClient() {
  
    

    log.info("Usando Docker host: {}", dockerHost);

    DefaultDockerClientConfig dockerClientConfig = DefaultDockerClientConfig.createDefaultConfigBuilder()
        .withDockerHost(dockerHost)
        .withApiVersion(RemoteApiVersion.VERSION_1_41)
        .withDockerTlsVerify(false)
        .build();

    ApacheDockerHttpClient dockerHttpClient = new ApacheDockerHttpClient.Builder()
        .dockerHost(dockerClientConfig.getDockerHost())
        .maxConnections(10)
        .connectionTimeout(Duration.ofSeconds(2))
        .responseTimeout(Duration.ofSeconds(10))
        .build();

    DockerClient client = DockerClientBuilder.getInstance(dockerClientConfig)
        .withDockerHttpClient(dockerHttpClient)
        .build();

    try {
      client.pingCmd().exec();
      log.info("✅ Conexão com Docker estabelecida com sucesso.");
    } catch (Exception e) {
      log.error("❌ Falha ao conectar ao Docker em {}: {}", dockerHost, e.getMessage());
      log.error("Verifique se o Docker está em execução e acessível (porta 2375 ou /var/run/docker.sock)");
      throw new RuntimeException("Não foi possível conectar ao Docker host: " + dockerHost, e);
    }

    return client;
  }
}
