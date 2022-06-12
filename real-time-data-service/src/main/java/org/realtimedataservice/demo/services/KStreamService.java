package org.realtimedataservice.demo.services;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import org.realtimedataservice.demo.entity.Bill;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Properties;
import java.util.function.Function;

@Slf4j
@Service
public class KStreamService {
    @Bean
    public Function<KStream<String, Bill>, KStream<String,Long>> KStreamFunction(){
        log.info("Main class");
        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "COUNT-FACTURATION");
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "localhost:9092");
        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.Long().getClass());

        StreamsBuilder builder = new StreamsBuilder();

        KStream<String, Bill> kStream = builder.stream("FACTURATION");
        return (KStream)->
                kStream
                .map((k,v)-> new KeyValue<>(v.getCustomer(),0L))
                .groupBy((k,v)->k,Grouped.with(Serdes.String(), Serdes.Long()))
                .windowedBy(TimeWindows.of(Duration.ofMillis(5000)))
                .count(Materialized.as("customer-count"))
                .toStream()
                .map((k,v)->new KeyValue<>("=>"+k.window().startTime()+k.window().endTime()+k.key(), v));
    }
}
