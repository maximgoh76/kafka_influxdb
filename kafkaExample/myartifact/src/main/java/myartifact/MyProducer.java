package myartifact;

import java.io.IOException;
//import util.properties packages
import java.util.Properties;
import java.util.Set;
//import simple producer packages
import org.apache.kafka.clients.producer.Producer;
//import KafkaProducer packages
import org.apache.kafka.clients.producer.KafkaProducer;

//import ProducerRecord packages
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
//import kafka.admin.AdminUtils;
import java.io.FileInputStream;
import java.util.Collections;

import java.util.concurrent.TimeUnit;


 
public final class MyProducer {


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

	        props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
	        props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
	        
//	        prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
//	        prop.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
//	        prop.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
	        
	        // setting offset reset to earliest so that we can re-run the demo code with the same pre-loaded data
	        // Note: To re-run the demo, you need to use the offset reset tool:
	        // https://cwiki.apache.org/confluence/display/KAFKA/Kafka+Streams+Application+Reset+Tool
	        props.putIfAbsent(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
	        return props;
	    }
	
	
	
	public static void main(String[] args) {
		
		System.out.println("Hello");
		try {
			//org.apache.kafka.common.serialization.StringSerializer a ;
			
			// Check arguments length value
		    //  if(args.length == 0){
		    //     System.out.println("Enter topic name");
		    //     return;
		    //  }
		      
//		      //Assign topicName to string variable
			
		      String topicName = "streams-plaintext-input";
		      final Properties props = getStreamsConfig(args);
		      createTopic("ips",props);
		 
		      Producer<String, String> producer = new KafkaProducer
		         <String, String>(props);
		      
		      for(int i = 0; i < 2; i++) {
		    	 int  key = i % 3 ;
		    	 
		         producer.send(new ProducerRecord<String, String>(topicName, 
		          "key"+  Integer.toString(key), "word1 word2 word " +  Integer.toString(i)));
		         producer.send(new ProducerRecord<String, String>("ips", 
     		          "key"+  Integer.toString(key), "12.22.12.1" +  Integer.toString(i)));
     		               
		         System.out.println("Message sent successfully");
		      }
			
		      producer.close();

			System.in.read();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void createTopic(String topicName,Properties props) throws Exception {
		AdminClient adminClient = AdminClient.create(props);
		ListTopicsResult listTopics = adminClient.listTopics();
		Set<String> names = listTopics.names().get();
		boolean contains = names.contains(topicName);
		if (!contains) {
			adminClient.createTopics(Collections.singleton(new NewTopic(topicName, 1, (short)1)))
			    .all()
			    .get(30, TimeUnit.SECONDS);
		}
	}
}

