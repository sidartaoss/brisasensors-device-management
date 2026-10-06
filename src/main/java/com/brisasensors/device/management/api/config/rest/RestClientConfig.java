package com.brisasensors.device.management.api.config.rest;

import com.brisasensors.device.management.api.client.RestClientFactory;
import com.brisasensors.device.management.api.client.DeviceMonitoringClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.JacksonJsonHttpMessageConverter;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import tools.jackson.databind.json.JsonMapper;

@Configuration
public class RestClientConfig {

    @Bean
    public DeviceMonitoringClient deviceMonitoringClient(RestClientFactory restClientFactory) {
        RestClient restClient = restClientFactory.createRestClient();
        RestClientAdapter restClientAdapter = RestClientAdapter.create(restClient);

        HttpServiceProxyFactory proxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();

        return proxyFactory.createClient(DeviceMonitoringClient.class);
    }

    @Bean
    public RestClient.Builder restClientBuilder(JsonMapper jsonMapper) {
        return RestClient.builder()
                .configureMessageConverters(builder ->
                        builder.withJsonConverter(new JacksonJsonHttpMessageConverter(jsonMapper))
                );
    }
}
