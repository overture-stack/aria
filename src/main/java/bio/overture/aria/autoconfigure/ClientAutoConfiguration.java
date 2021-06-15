package bio.overture.aria.autoconfigure;

import bio.overture.aria.client.AriaClient;
import bio.overture.aria.properties.AriaClientProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public AriaClientProperties ariaClientProperties() {
    return new AriaClientProperties();
  }

  @Bean
  @Autowired
  @ConditionalOnMissingBean
  public AriaClient ariaClient(AriaClientProperties properties) {
    return new AriaClient(properties);
  }
}
