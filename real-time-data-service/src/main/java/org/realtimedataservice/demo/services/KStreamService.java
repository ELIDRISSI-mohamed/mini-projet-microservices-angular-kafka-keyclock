package org.realtimedataservice.demo.services;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class KStreamService {
    StreamsBuilder builder = new StreamsBuilder();
    //Serializers/deserializers (serde) for string and Long types
    final Serde<String> stringSerde = Serdes.String();
    final Serde<Long> longSerde = Serdes.Long();

    KStream<String, String> textLines = builder.stream("FACTURATION", Consumed.with(stringSerde, stringSerde));

}
