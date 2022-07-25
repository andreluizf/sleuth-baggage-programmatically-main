package withyuu.tutorial.a;

import brave.baggage.BaggageField;
import brave.baggage.BaggagePropagationConfig.SingleBaggageField;
import brave.baggage.BaggagePropagationCustomizer;
import brave.baggage.CorrelationScopeConfig.SingleCorrelationField;
import brave.baggage.CorrelationScopeCustomizer;
import brave.context.slf4j.MDCScopeDecorator;
import brave.propagation.CurrentTraceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AConfiguration {

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

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
  BaggageField idField() {
    return BaggageField.create("id");
  }

  @Bean
  public BaggagePropagationCustomizer baggagePropagationCustomizerid() {
    return (factoryBuilder) -> {
      factoryBuilder.add(
              SingleBaggageField.remote(BaggageField.create("id")));
    };
  }

  @Bean
  public CorrelationScopeCustomizer correlationScopeCustomizerid() {
    return builder -> {
      builder.add(
              SingleCorrelationField.newBuilder(BaggageField.create("id"))
                      .flushOnUpdate()
                      .build());
    };
  }

}
