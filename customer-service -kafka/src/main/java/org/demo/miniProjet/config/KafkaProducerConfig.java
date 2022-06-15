package org.demo.miniProjet.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.demo.miniProjet.dto.CustomerResDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
public class KafkaProducerConfig {

    @Value("${kafka.producer.bootstrap-servers}")
    private String server;
    @Value("${kafka.producer.client-id}")
    private String clientId;
    @Value("${kafka.producer.key-serializer}")
    private String keySerializerClass;
    @Value("${kafka.producer.value-serializer}")
    private String valueSerializerClass;

    @Bean
    public ProducerFactory<String, CustomerResDTO> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,server);
        configProps.put(ProducerConfig.CLIENT_ID_CONFIG, clientId);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,keySerializerClass);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,valueSerializerClass);

        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, CustomerResDTO> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
