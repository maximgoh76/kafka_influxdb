package consumer;

import java.io.IOException;
//import util.properties packages
import java.util.Properties;

//import simple producer packages
import org.apache.kafka.clients.producer.Producer;

//import KafkaProducer packages
import org.apache.kafka.clients.producer.KafkaProducer;

//import ProducerRecord packages
import org.apache.kafka.clients.producer.ProducerRecord;



import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Produced;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;


	public class ConsumerMain {

		//kafka-topics.bat --create --zookeeper localhost:2181 --replacation-factor 1 --topic streams-plaintext-input
		//kafka-topics.bat --create --zookeeper localhost:2181 --replacation-factor 1 --topic streams-wordcount-output
	    public static final String INPUT_TOPIC = "streams-plaintext-input";
	    public static final String OUTPUT_TOPIC = "streams-wordcount-output";
	    public static final InfluxDbService  influxDbService= new InfluxDbService();
	    
	    static Properties getStreamsConfig(final String[] args) throws IOException {
	        final Properties props = new Properties();
	        if (args != null && args.length > 0) {
	            try (final FileInputStream fis = new FileInputStream(args[0])) {
	                props.load(fis);
	            }
	            if (args.length > 1) {
	                System.out.println("Warning: Some command line arguments were ignored. This demo only accepts an optional configuration file.");
	            }
	        }
	        props.putIfAbsent(StreamsConfig.APPLICATION_ID_CONFIG, "streams-wordcount");
	        props.putIfAbsent(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
	        props.putIfAbsent(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, 0);
	        props.putIfAbsent(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
	        props.putIfAbsent(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());

	        // setting offset reset to earliest so that we can re-run the demo code with the same pre-loaded data
	        // Note: To re-run the demo, you need to use the offset reset tool:
	        // https://cwiki.apache.org/confluence/display/KAFKA/Kafka+Streams+Application+Reset+Tool
	        props.putIfAbsent(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
	        return props;
	    }

	    static void createWordCountStream(final StreamsBuilder builder) {
	        final KStream<String, String> source = builder.stream(INPUT_TOPIC);

	        final KStream<String, String> countsStream = source
	        		 .map((key, value) -> {
	        		        int len= value.toLowerCase(Locale.getDefault()).split(" ").length;
	        		        influxDbService.writePoint(key,len);;
	        		        return new KeyValue<>(key,""+len);
	        		    });
//	            .flatMapValues(value -> {
//	            		System.out.println("flatMapValues: value + " + value);
//	            		return Arrays.asList(value.toLowerCase(Locale.getDefault()).split(" "));
//	            	})
//	            .groupBy((key, value) -> {
//	            	System.out.println("groupBy: value + " + value);
//	            	return  value;
//	            	})
//	            .count();

	        // need to override value serde to Long type
	        countsStream.to(OUTPUT_TOPIC, Produced.with(Serdes.String(), Serdes.String()));
	    }

	    public static void main(final String[] args) throws IOException {
	    	influxDbService.openConnection();
	    	try {
		    	final Properties props = getStreamsConfig(args);
		       
		        final StreamsBuilder builder = new StreamsBuilder();
		        createWordCountStream(builder);
		        final KafkaStreams streams = new KafkaStreams(builder.build(), props);
		        final CountDownLatch latch = new CountDownLatch(1);
	
		        // attach shutdown handler to catch control-c
		        Runtime.getRuntime().addShutdownHook(new Thread("streams-wordcount-shutdown-hook") {
		            @Override
		            public void run() {
		                streams.close();
		                latch.countDown();
		            }
		        });
	
		        try {
		            streams.start();
		            latch.await();
		        } catch (final Throwable e) {
		            System.exit(1);
		        }
	        }finally {
	        	 influxDbService.close();
			}
	        System.exit(0);
	    }
	
	public static void mainConsumer(String[] args) {
		// TODO Auto-generated method stub

		try {
			  //Kafka consumer configuration settings
		      String topicName = "TestTopic";
		      Properties props = new Properties();
		      
		      props.put("bootstrap.servers", "localhost:9092");
		      props.put("group.id", "test");
		      props.put("enable.auto.commit", "true");
		      props.put("auto.commit.interval.ms", "1000");
		      props.put("session.timeout.ms", "30000");
		      props.put("key.deserializer", 
		         "org.apache.kafka.common.serialization.StringDeserializer");
		      props.put("value.deserializer", 
		         "org.apache.kafka.common.serialization.StringDeserializer");
		      KafkaConsumer<String, String> consumer = new KafkaConsumer
		         <String, String>(props);
		      
		      //Kafka Consumer subscribes list of topics here.
		      consumer.subscribe(Arrays.asList(topicName));
		      
		      //print the topic name
		      System.out.println("Subscribed to topic " + topicName);
		      int i = 0;
		      
		      while (true) {
		         ConsumerRecords<String, String> records = consumer.poll(100);
		         for (ConsumerRecord<String, String> record : records)
		         
		         // print the offset,key and value for the consumer records.
		         System.out.printf("offset = %d, key = %s, value = %s\n", 
		            record.offset(), record.key(), record.value());
		      }
		      
		} catch (Exception e) {
			e.getStackTrace();
		}
		
	}

}
