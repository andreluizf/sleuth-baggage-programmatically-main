package withyuu.tutorial.b;

import brave.baggage.BaggageField;
import brave.baggage.BaggagePropagationConfig.SingleBaggageField;
import brave.baggage.BaggagePropagationCustomizer;
import brave.baggage.CorrelationScopeConfig.SingleCorrelationField;
import brave.baggage.CorrelationScopeCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BConfiguration {

  @Bean
  public BaggagePropagationCustomizer baggagePropagationCustomizer() {
    return (factoryBuilder) -> {
      factoryBuilder.add(
          SingleBaggageField.remote(BaggageField.create("Correlation-Id")));
    };
  }

  @Bean
  public CorrelationScopeCustomizer correlationScopeCustomizer() {
    return builder -> {
      builder.add(
          SingleCorrelationField.newBuilder(BaggageField.create("Correlation-Id"))
              .flushOnUpdate()
              .build());
    };
  }

  @Bean
  public BaggagePropagationCustomizer baggagePropagationCustomizerID() {
    return (factoryBuilder) -> {
      factoryBuilder.add(
              SingleBaggageField.remote(BaggageField.create("id")));
    };
  }

  @Bean
  public CorrelationScopeCustomizer correlationScopeCustomizerId() {
    return builder -> {
      builder.add(
              SingleCorrelationField.newBuilder(BaggageField.create("id"))
                      .flushOnUpdate()
                      .build());
    };
  }

}
