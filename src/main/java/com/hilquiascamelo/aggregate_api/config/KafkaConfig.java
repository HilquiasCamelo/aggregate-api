package com.hilquiascamelo.aggregate_api.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Autowired
    private KafkaProperties properties;

    /**
     * Cria uma fábrica de produtores para o Apache Kafka, configurada para lidar com objetos do tipo ObjectTextModel.
     *
     * @return A fábrica de produtores configurada.
     */
    @Bean
    public ProducerFactory<String, Object> objectTextFactory() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers());
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(configs);
    }

    /**
     * Cria um template Kafka para produzir mensagens de ObjectTextModel.
     *
     * @param objectTextFactory A fábrica de produtores a ser usada pelo template.
     * @return O template Kafka configurado com a fábrica de produtores fornecida.
     */
    @Bean
    public KafkaTemplate<String, Object> objectTextKafkaTemplate(ProducerFactory<String, Object> objectTextFactory) {
        return new KafkaTemplate<>(objectTextFactory);
    }

    /**
     * Cria uma fábrica de produtores para o Apache Kafka, configurada para lidar com mensagens de texto.
     *
     * @return A fábrica de produtores configurada.
     */
    @Bean
    public ProducerFactory<String, String> stringProducerFactory() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers());
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(configs);
    }

    /**
     * Cria um template Kafka para produzir mensagens de texto.
     *
     * @param stringProducerFactory A fábrica de produtores a ser usada pelo template.
     * @return O template Kafka configurado com a fábrica de produtores fornecida.
     */
    @Bean
    public KafkaTemplate<String, String> stringKafkaTemplate(ProducerFactory<String, String> stringProducerFactory) {
        return new KafkaTemplate<>(stringProducerFactory);
    }
}