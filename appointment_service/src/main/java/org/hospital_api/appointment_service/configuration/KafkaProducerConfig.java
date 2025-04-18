package org.hospital_api.appointment_service.configuration;


import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.hospital_api.appointment_service.event.AppointmentEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Bean
    public ProducerFactory<String, AppointmentEvent> producerFactory(){
        return new DefaultKafkaProducerFactory<>(Map.of(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class,
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092"
        )
        );
    }

    @Bean
    public KafkaTemplate<String, AppointmentEvent> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
