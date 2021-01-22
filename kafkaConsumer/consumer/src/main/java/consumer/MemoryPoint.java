package consumer;

import java.time.Instant;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

@Measurement(name = "memory")
public class MemoryPoint {

    @Column(name = "time")
    public Instant time;

    @Column(name = "name")
    public String name;

    @Column(name = "free")
    public Long free;

    @Column(name = "used")
    public Long used;

    @Column(name = "words")
    public Long words;
}