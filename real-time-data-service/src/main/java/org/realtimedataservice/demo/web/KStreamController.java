package org.realtimedataservice.demo.web;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Windowed;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyWindowStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.binder.kafka.streams.InteractiveQueryService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class KStreamController {
    @Autowired
    private InteractiveQueryService interactiveQueryService;

    @GetMapping(path = "/analytics",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Map<String,Long>> analytics(){
        log.info("Analytics web");
        return Flux.interval(Duration.ofSeconds(1))
            .map(sequence->{
                Map<String, Long> stringLongMap=new HashMap<>();
                ReadOnlyWindowStore<String, Long> windowStore=interactiveQueryService.getQueryableStore("customer-count", QueryableStoreTypes.windowStore());
                Instant now=Instant.now();
                Instant from=now.minusMillis(5000);
                KeyValueIterator<Windowed<String>, Long> fetchAll=windowStore.fetchAll(from, now);
                while (fetchAll.hasNext()){
                    KeyValue<Windowed<String>, Long> next =fetchAll.next();
                    stringLongMap.put(next.key.key(), next.value);
                }
                return stringLongMap;
            }).share();
    }
}
