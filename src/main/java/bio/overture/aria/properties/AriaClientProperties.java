package bio.overture.aria.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "ariaclient")
public class AriaClientProperties {
  private String songRootUrl;
  private String scoreRootUrl;
  private String clientId;
  private String clientSecret;
  private String tokenUrl;
  private Integer retryMaxAttempts;
  private Integer retryDelaySec;
}
