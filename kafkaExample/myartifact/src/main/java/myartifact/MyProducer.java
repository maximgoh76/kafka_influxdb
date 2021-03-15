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
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
//import kafka.admin.AdminUtils;
import java.io.FileInputStream;
import java.util.Collections;
import java.util.concurrent.ExecutionException;
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
	        props.putIfAbsent(StreamsConfig.APPLICATION_ID_CONFIG, "provider_app");
	        props.putIfAbsent(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "10.19.0.163:9092");
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
			
		    
		      final Properties props = getStreamsConfig(args);
		      //createTopic("ips",props);
		 
		      Producer<String, String> producer = new KafkaProducer
		         <String, String>(props);
		      
//		      for(int i = 0; i < 2; i++) {
//		    	 int  key = i % 3 ;
//		    	 
//		         producer.send(new ProducerRecord<String, String>(topicName, 
//		          "key"+  Integer.toString(key), "word1 word2 word " +  Integer.toString(i)));
//		         producer.send(new ProducerRecord<String, String>("ips", 
//     		          "key"+  Integer.toString(key), "12.22.12.1" +  Integer.toString(i)));
//     		               
//		         System.out.println("Message sent successfully");
//		      }
		      //sendConfig(producer);
		      //sendCallBackConfig
		      //sendHM(producer);
		      //sendWR(producer);
		      sendLog(producer);
		      //sendWRStartForensics(producer);
		      producer.close();
		
			System.in.read();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void sendLog(Producer<String, String> producer) throws InterruptedException, ExecutionException {
		String topicName = "logs";
		  //String topicName = "rpc-tasks-status";
		  RecordMetadata meta = producer.send(new ProducerRecord<String, String>(topicName, 
				  "sender1", "{\r\n"
					  		+ "                \"detectorName\": \"Dummy163\",\r\n"
					  		+ "                \"detectorId\": \"d51cdc1e-3223-4873-ade2-889e9d193ef5\",\r\n"
					  		+ "                \"debugLevel\": 0,\r\n"
					  		+ "                \"module\": \"config\",\r\n"
					  		+ "                \"message\": null,\r\n"
					  		+ "                \"messageAdditionalParameter\": \"add parameter\",\r\n"
					  		+ "                \"messageId\": 24001,\r\n"
					  		+ "                \"router_ip\": \"10.20.30.40\",\r\n"
					  		+ "                \"type\": \"info\"\r\n"
				  		+ "}\r\n"
				  		+ "" )) .get(); //
	}

	
	private static void sendHM(Producer<String, String> producer) throws InterruptedException, ExecutionException {
		String topicName = "hm_out";
		  //String topicName = "rpc-tasks-status";
		  //RecordMetadata meta = producer.send(new ProducerRecord<String, String>(topicName, 
			//	  "sender", "{\"requestType\":\"get_all_detectors\",\"detectorId\":\"id\"}" )) .get(); //
		  RecordMetadata meta = producer.send(new ProducerRecord<String, String>(topicName, 
				  "sender", "{\"detectorId\":\"970ec080-5cfb-4d8c-9b47-2ea9b4fc1450\", \"requestType\":\"detector_is_down\"}" )) .get(); //
	}
	
	private static void sendWR(Producer<String, String> producer) throws InterruptedException, ExecutionException {
		String topicName = "wr_forensics_out";
		  //String topicName = "rpc-tasks-status";
		  RecordMetadata meta = producer.send(new ProducerRecord<String, String>(topicName, 
				  "sender2", ConstsWarRoom.CALL_WR_MESSAGE )) .get(); //
	}
	private static void sendWRStartForensics(Producer<String, String> producer) throws InterruptedException, ExecutionException {
		String topicName = "wr_forensics_out";
		  //String topicName = "rpc-tasks-status";
		  RecordMetadata meta = producer.send(new ProducerRecord<String, String>(topicName, 
				  "sender1", "{\r\n"
				  		+ "                \"detectorName\": \"Dummy163\",\r\n"
				  		+ "                \"type\": \"forensics_start\"\r\n"
				  		+ "}\r\n"
				  		+ "" )) .get(); //
	}

	private static void sendCallBackConfig(Producer<String, String> producer) throws InterruptedException, ExecutionException {
		String topicName = "inventory-change";
		  //String topicName = "rpc-tasks-status";
		  RecordMetadata meta = producer.send(new ProducerRecord<String, String>(topicName, 
				  "a3fbbb84-c74d-4d95-b1a3-5fa8e6ed6d40", ConstsCallback.CALL_BACK_MSG1 )) .get(); //
	}
	

	private static void sendConfig(Producer<String, String> producer) throws InterruptedException, ExecutionException {
		String topicName = "inventory-change";
		  //String topicName = "rpc-tasks-status";
		  RecordMetadata meta = producer.send(new ProducerRecord<String, String>(topicName, 
				  "8b778aa8-5dee-4d66-b3a7-6758b02b274f", Consts.msgConfig )) .get(); //
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

