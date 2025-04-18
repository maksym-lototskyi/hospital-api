package org.hospital_api.appointment_service.configuration;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.hospital_api.appointment_service.event.BillingEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.Map;

@Configuration
public class KafkaConsumerConfig {
    @Bean
    public ConsumerFactory<String, BillingEvent> consumerFactory(){
        return new DefaultKafkaConsumerFactory<>(Map.of(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class,
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092",
                JsonDeserializer.TRUSTED_PACKAGES, "org.hospital_api.billing_service.event",
                JsonDeserializer.TYPE_MAPPINGS, "org.hospital_api.billing_service.event.BillingEvent:org.hospital_api.appointment_service.event.BillingEvent"
        ));
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, BillingEvent>> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, BillingEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());

        return factory;
    }
}
