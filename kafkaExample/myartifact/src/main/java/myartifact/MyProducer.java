package myartifact;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
//import util.properties packages
import java.util.Properties;
import java.util.Random;
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import redis.clients.jedis.Jedis;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;

import java.io.BufferedWriter;
import java.io.File;
//import kafka.admin.AdminUtils;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.concurrent.Executors;

 
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
	        props.putIfAbsent(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "10.19.0.234:9092");
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
		try {
			String path = null;
			if(args.length > 0){
				path = args[0];
			}else {
				Path currentRelativePath = Paths.get("");
				path = currentRelativePath.toAbsolutePath().toString();
			}
			
			path = "C:\\Projects\\warroom\\application\\src\\test\\resources\\pofilters_agg\\hostsNew";
			
			System.out.println("Currrent path:" + path);
			
			final File folder = new File(path);
			
			for (final File fileEntry : folder.listFiles()) {
				formatFile(fileEntry);
			      //convertFileToNewStructure(fileEntry); 
			}
			
		} catch (Exception e) {
			System.out.println("ERROR:" + e.getMessage());
			e.printStackTrace();
		}
	
		
	}
	
	private static void formatFile(File fileEntry) throws JsonSyntaxException, IOException, URISyntaxException {
		
		JsonObject oldJson = JsonParser
				.parseString(new String(Files.readAllBytes(fileEntry.toPath())))
				.getAsJsonObject();
		
		
		JsonObject newJson = new JsonObject();
		JsonArray posArray = null;
		newJson.add("pos", posArray);
		
		boolean isFirstPO = true;
		
		
		File newfile = new File(fileEntry.getAbsolutePath().replace(".json", "NEW.json"));
		Files.writeString( newfile.toPath(), "{\n \"pos\":[" ,StandardCharsets.UTF_8,StandardOpenOption.CREATE);
		
		
		posArray = oldJson.get("pos").getAsJsonArray();
		
		for (JsonElement poObj: posArray) {
			
			JsonObject po = poObj.getAsJsonObject();
			boolean isFirstFilter = true;
			
			JsonArray filtersArray =  po.get("filters").getAsJsonArray();
			
			String  poId = po.get("po").getAsString();
			
			if (!isFirstPO) {
				writeLine(",",newfile);
			}
			isFirstPO = false;
			
			writeLine( "\n\t{\"po\":\"" + poId + "\",", newfile);
			writeLine( "\"filters\":[", newfile);
			for (JsonElement filter:filtersArray) {
				
				if (!isFirstFilter) {
					writeLine(",",newfile);
				}
				isFirstFilter = false;
				
				writeLine(filter, newfile);
			}
			
			writeLine( "\n\t]}", newfile);
		}
		
		
		Files.writeString( newfile.toPath(), "\n]}" ,StandardCharsets.UTF_8,StandardOpenOption.APPEND);
		
		
		
		//gson.toJson(newJson)
		

		

	}
	
	private static Gson gson = new GsonBuilder()
			  //.setPrettyPrinting()
			  .create();
	
	private static void writeLine (Object line,File newfile ) throws IOException {
		
		
		String txt = null;
		if (line instanceof String) {
			txt = (String)line;
		}else {
			txt = gson.toJson(line);
			txt = "\n\t\t\t" + txt.replace("\\u003d", "=");
		}
		
		Files.writeString( newfile.toPath(), txt ,StandardCharsets.UTF_8,StandardOpenOption.APPEND);
	}
	
	
	private static void convertFileToNewStructure(File fileEntry) throws JsonSyntaxException, IOException, URISyntaxException {
		
		JsonObject olsJson = JsonParser
				.parseString(new String(Files.readAllBytes(fileEntry.toPath())))
				.getAsJsonObject();
		
		
		JsonObject newJson = new JsonObject();
		JsonArray posArray = new JsonArray();
		newJson.add("pos", posArray);
		
		for (Map.Entry<String, JsonElement> entry: olsJson.entrySet()) {
			String poId = entry.getKey();
			JsonArray filtersArray =  entry.getValue().getAsJsonArray();
			
			JsonObject  newPoObject = new JsonObject();
			newPoObject.addProperty("po", poId);
			
			JsonArray newFiltersArray = new JsonArray();
			newPoObject.add("filters", newFiltersArray);
			posArray.add(newPoObject);
			
			for (JsonElement oldFilterElement:filtersArray) {
				JsonObject  oldFilterObject = oldFilterElement.getAsJsonObject();
			
				JsonObject  newFilterObject = findFilter(newFiltersArray,oldFilterObject.get("filter").getAsString(),oldFilterElement.getAsJsonObject());
				
				newFiltersArray.add(newFilterObject);
			}
		}
		
		File newfile = new File(fileEntry.getAbsolutePath().replace(".json", "NEW.json"));
		
		
		Gson gson = new GsonBuilder()
				  //.setPrettyPrinting()
				  .create();
		
		//gson.toJson(newJson)
		
		String txt =  gson.toJson(newJson);
		txt = txt.replace("\\u003d", "=");
		
		Files.writeString( newfile.toPath(), txt ,StandardCharsets.UTF_8,StandardOpenOption.CREATE);

	}


	private static JsonObject findFilter(JsonArray newFiltersArray,String curretnFilter,JsonObject oldFilterElement) {
		
		JsonObject newFilterJsonObject = null;
		
		for (JsonElement filter:newFiltersArray) {
			if (curretnFilter.equals(filter.getAsJsonObject().get("filter").getAsString())) {
				newFilterJsonObject = filter.getAsJsonObject();
				break;
			}
		}
		
		if (newFilterJsonObject==null) {
			newFilterJsonObject = new JsonObject();
			newFilterJsonObject.addProperty("filter", curretnFilter);
		}	
		
		String host = null;
		
		if (oldFilterElement.get("dst_ip")!=null) {
			host = oldFilterElement.get("dst_ip").getAsString();
		}
		

		if (host==null) {
			newFilterJsonObject.addProperty("pps",  oldFilterElement.get("pps").getAsLong());
			newFilterJsonObject.addProperty("bps",  oldFilterElement.get("bps").getAsLong());
			convertSources(newFilterJsonObject,oldFilterElement);
			
		}else {
			JsonObject hostObj = new JsonObject();
			
			
			hostObj.addProperty("host", host);
			hostObj.addProperty("pps",  oldFilterElement.get("pps").getAsLong());
			hostObj.addProperty("bps",  oldFilterElement.get("bps").getAsLong());
			convertSources(hostObj,oldFilterElement);
			
			JsonArray newhostsArray = null;
			
			if (newFilterJsonObject.get("hosts")==null) {
				newhostsArray = new JsonArray();
				newFilterJsonObject.add("hosts", newhostsArray);			
			}else {
				newhostsArray = newFilterJsonObject.get("hosts").getAsJsonArray();
			}
			
			newhostsArray.add(hostObj);
		}
		

		return newFilterJsonObject;
	}


	private static void convertSources(JsonObject newFilterJsonObject, JsonObject oldFilterElement) {
		
		if (oldFilterElement.get("Sources")==null) return;
		
		JsonArray sourcesArr = new JsonArray();
		
		for (Map.Entry<String,JsonElement> source:oldFilterElement.get("Sources").getAsJsonObject().entrySet()) {
			JsonObject sourceObj = new JsonObject();
			sourceObj.addProperty("router", source.getKey());
			
			sourceObj.addProperty("pps", source.getValue().getAsJsonObject().get("pps").getAsLong());
			sourceObj.addProperty("bps", source.getValue().getAsJsonObject().get("bps").getAsLong());
			
			sourcesArr.add(sourceObj);
		}
		
		newFilterJsonObject.add("sources", sourcesArr);
	}


	public static void mainOld(String[] args) {
		
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
		      //sendLog(producer);
		      //sendWRStartForensics(producer);
		      //sendWarAlert(producer,"attack_start");
		      //sendWarAlert(producer,"attack_signature");
		      //sendMitigatoinBlock(producer);
		      //sendAlertEndBlock(producer);
		      //sendUIMitigation(producer);
		      //sendUIMitigationRelease(producer);
		      
		      //System.out.println("true & true = " + (true & true));
		      //System.out.println("true & false = " + (true & false));
		      //System.out.println("false & false = " + (false & false));
		      
		      producer.close();
		      //createPOJson();
		      //createFiltersJson();
		      //AclCalc2 aclCalc2 = new AclCalc2();
		      //aclCalc2.threadsTest();
			//System.in.read();
		     
		      //loadRedisPoFilters();
		      
		      add2000Pos();
		      
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	private static void loadRedisPoFilters() {
		Jedis jedis = new Jedis("localhost",6379); 
		
		try {
			
			System.out.println("REDIS Connection to server sucessfully"); 
	      
			int i = 0;
			
			while (i<500) {
				
				long sleep = 3000;
				double ttl = 30; //sec
				long now = System.currentTimeMillis(); 
				
				long ppsFor3Sec = 3500;
				long bpsFor3Sec = 40000;
				
				if (i%2 ==0) {
					ppsFor3Sec = 3500;
					bpsFor3Sec = 20000;
				}
				
				String key = "grpc:pofilters:Cisco-Ufi-10:6229dde7a2519e8f77536709";
				String report = "{\r\n"
						+ "	\"Updated\": " + now +",\r\n"
						+ "	\"PoFilters\": [\r\n"
						+ "		{\r\n"
						+ "			\"filter\": \"Total\",\r\n"
						+ "			\"pps\": 3000,\r\n"
						+ "			\"bps\": 3333\r\n"
						+ "		},\r\n"
						+ "		{\r\n"
						+ "			\"filter\": \"PROTOCOL=UDP,L4_SRC_PORT=19\",\r\n"
						+ "			\"pps\": " +ppsFor3Sec +",\r\n"
						+ "			\"bps\": " + bpsFor3Sec +"\r\n"
						+ "		}\r\n"
						+ "	],\r\n"
						+ "	\"Seconds\": 3\r\n"
						+ "}";
				
				
				
				//6225b449733a8fa057b64789  = Max PO
				
				
				
				jedis.zadd(key, now, report);
				jedis.zremrangeByScore(key, 0 , (double)(now - ttl * 3000 ));
				jedis.expire(key, (long) (ttl));
				
				Thread.sleep(sleep);
				
				
				i++;

			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
		}
		finally {
			jedis.close();
			System.out.println("REDIS Connection Closed"); 
		}
		
		
	

	}



	private static void sendUIMitigation(Producer<String, String> producer) throws InterruptedException, ExecutionException {
		String topicName = "attack_forensics";
		  //String topicName = "rpc-tasks-status";\"Dummy - 163\"
		  RecordMetadata meta = producer.send(new ProducerRecord<String, String>(topicName, 
				  "NCS_Real_30","{\r\n"
							+ "	\"attackId\":\""+ "60a9f4842e6e8c2ea7194b84" + "\",\r\n"
							+ "	\"action\":\"mitigate_teid\",\r\n"
							+ "	\"detectorName\":\"NCS_Real_30\",\r\n"
							+ "	\"teids\":[\"0x00000008\"],\r\n"
							+ "	\"msgType\":\"UI_ACTION\"\r\n"
							+ "}" )) .get(); //
	}
	
	

	private static void sendUIMitigationRelease(Producer<String, String> producer) throws InterruptedException, ExecutionException {
		String topicName = "attack_forensics";
		  //String topicName = "rpc-tasks-status";\"Dummy - 163\"
		  RecordMetadata meta = producer.send(new ProducerRecord<String, String>(topicName, 
				  "NCS_Real_30","{\r\n"
							+ "	\"attackId\":\""+ "60a9f4842e6e8c2ea7194b84" + "\",\r\n"
							+ "	\"action\":\"release_teid\",\r\n"
							+ "	\"detectorName\":\"NCS_Real_30\",\r\n"
							+ "	\"teids\":[\"0x00000008\"],\r\n"
							+ "	\"msgType\":\"UI_ACTION\"\r\n"
							+ "}" )) .get(); //
	}
	
	
	
	private static void createFiltersJson() {
		JsonObject filters = new JsonObject();
		filters.addProperty("router_name", "router1");
		JsonArray array = new JsonArray();
		
		for (int i=0;i<2000;i++) {
			
			for (int j=0;j<30;j ++) {
				String nodeStr = "{\r\n"
						+ "      \"po\": \"po" + i+ "\",\r\n"
						+ "      \"filter\": \"ROTOCOL=TCP" + j+"\",\r\n"
						+ "      \"pps\": 100,\r\n"
						+ "      \"bps\": 100\r\n"
						+ "    }";
				JsonObject node = JsonParser.parseString(nodeStr).getAsJsonObject();
				array.add(node);
			}
		}
		
		
		filters.add("filters", array);
		
		try {
			Files.writeString(Path.of("C:\\Users\\maximg\\Downloads\\filters.txt"), filters.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private static void add2000Pos() throws IOException {
		//JsonObject filters = new JsonObject();
		//filters.addProperty("router_name", "router1");
		
		
		String filePath = "C:\\Users\\maximg\\Downloads\\Protected-Objects-List-05-24-22-1023_1400.json";
		String filePathOutput = "C:\\Users\\maximg\\Downloads\\Protected-Objects-List-3000.json";

		String fileStr =  Files.readAllLines(Paths.get(filePath), Charset.forName("UTF-8")).stream().collect(Collectors.joining(""));
		
		Gson gson = new GsonBuilder()
		        .setLenient()
		        .create();
		JsonObject jsonObject = gson.fromJson(fileStr, JsonObject.class);
		JsonArray posArray = jsonObject.get("json").getAsJsonObject().get("protectedObjects").getAsJsonArray();
		
		for (int i=1;i<100;i++) {
			for (int j=1;j<27;j++) {
				String nodeStr = "{\r\n"
						+ "        \"sources\": [],\r\n"
						+ "        \"destinations\": [\r\n"
						+ "          \"16.1"+ i      + "."+ j   +".0/24\"\r\n"
						+ "        ],\r\n"
						+ "        \"routers\": [],\r\n"
						+ "        \"name\": \"MAX"+i +"_"+j+"\",\r\n"
						+ "        \"type\": \"dst\",\r\n"
						+ "        \"mode\": \"user_confirmation\",\r\n"
						+ "        \"bpsHighSeverity\": 50,\r\n"
						+ "        \"bpsMediumSeverity\": 10,\r\n"
						+ "        \"ppsHighSeverity\": 500,\r\n"
						+ "        \"ppsMediumSeverity\": 100,\r\n"
						+ "        \"firewall\": {\r\n"
						+ "          \"allow\": [],\r\n"
						+ "          \"block\": [\r\n"
						+ "            {\r\n"
						+ "              \"disabled\": true,\r\n"
						+ "              \"name\": \"default\",\r\n"
						+ "              \"isDefault\": true\r\n"
						+ "            }\r\n"
						+ "          ]\r\n"
						+ "        },\r\n"
						+ "        \"thresholdsTemplate\": \"standard\",\r\n"
						+ "        \"mitigationGroup\": \"#All\",\r\n"
						+ "        \"isThresholdsActive\": true\r\n"
						+ "      }";
				JsonObject node = JsonParser.parseString(nodeStr).getAsJsonObject();
				posArray.add(node);
			}
		}
		
		
		
		
		try {
			Files.writeString(Path.of(filePathOutput), jsonObject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private static void createPOJson() {
	
		JsonArray array = new JsonArray();
		
		for (int i=0;i<4000;i++) {
			String nodeStr = "{\r\n"
					+ "    \"name\": \"\",\r\n"
					+ "    \"type\": \"dst\",\r\n"
					+ "    \"sources\": [],\r\n"
					+ "    \"destinations\": [],\r\n"   
					+ "    \"routers\": [],\r\n"
					+ "    \"mode\": \"user_confirmation\",\r\n"
					+ "    \"bpsHighSeverity\": 50,\r\n"
					+ "    \"bpsMediumSeverity\": 10,\r\n"
					+ "    \"ppsHighSeverity\": 500,\r\n"
					+ "    \"ppsMediumSeverity\": 100\r\n"
					+ "  }\r\n";
			JsonObject node = JsonParser.parseString(nodeStr).getAsJsonObject();
			node.addProperty("name", "ll" + i);
			
			
			Random r = new Random();
			String ip = r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256);
			node.get("destinations").getAsJsonArray().add(ip+ "/32");
			ip = r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256);
			node.get("destinations").getAsJsonArray().add(ip + "/32");
			
			array.add(node);

		}
		
		try {
			Files.writeString(Path.of("C:\\Users\\maximg\\Downloads\\max.txt"), array.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private static void sendWarAlert(Producer<String, String> producer,String attackType) throws InterruptedException, ExecutionException, JsonSyntaxException, IOException, URISyntaxException {
		String topicName = "alerts";
		  //String topicName = "rpc-tasks-status";\"Dummy - 163\"
		
		JsonArray elemArr = JsonParser.parseString(new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemClassLoader().getResource("alerts_examples.json").toURI())))).getAsJsonArray();

		for (JsonElement item : elemArr) {
			if (item.getAsJsonObject().get("event_type").getAsString().equals(attackType)) {
			 RecordMetadata meta = producer.send(new ProducerRecord<String, String>(topicName, 
					  "sender1", item.toString() )) .get(); //
			}
		}
		
		
		
	}
	

	private static void sendMitigatoinBlock(Producer<String, String> producer) throws InterruptedException, ExecutionException {
		String topicName = "attack_forensics";
		  //String topicName = "rpc-tasks-status";\"Dummy - 163\"
		  RecordMetadata meta = producer.send(new ProducerRecord<String, String>(topicName, 
				  "Real-NCS540", "{\r\n"
				  		+ "  \"detectorName\": \"Real-NCS540\",\r\n"
				  		+ "  \"type\": \"mitigation\",\r\n"
				  		+ "  \"attackId\": \"606dece32829a24290cb0b8b\",\r\n"
				  		+ "  \"poId\": \"606b2f6517725c780b8d9a6e\",\r\n"
				  		+ "  \"free\": [\r\n"
				  		+ "    \"0x0000000A\"\r\n"
				  		+ "  ],\r\n"
				  		+ "  \"alertIds\": [\r\n"
				  		+ "    \"host:0_RP0-Apr  7 17:31:21-25\"\r\n"
				  		+ "  ]\r\n"
				  		+ "}"
				  		+ "" )) .get(); //
	}
	
	private static void sendAlertEndBlock(Producer<String, String> producer) throws InterruptedException, ExecutionException {
		String topicName = "wr2config";
		  //String topicName = "rpc-tasks-status";\"Dummy - 163\"
		  RecordMetadata meta = producer.send(new ProducerRecord<String, String>(topicName, 
				  "Real-NCS540", "{\r\n"
				  		+ "  \"detectorName\": \"Real-NCS540\",\r\n"
				  		+ "  \"type\": \"alert_end\",\r\n"
				  		+ "  \"attackId\": \"606dece32829a24290cb0b8b\",\r\n"
				  		+ "  \"poId\": \"606b2f6517725c780b8d9a6e\",\r\n"
				  		+ "  \"free\": [\r\n"
				  		+ "    \"0x0000000A\"\r\n"
				  		+ "  ],\r\n"
				  		+ "  \"alertIds\": [\r\n"
				  		+ "    \"host:0_RP0-Apr  7 17:31:21-25\"\r\n"
				  		+ "  ]\r\n"
				  		+ "}"
				  		+ "" )) .get(); //
	}
	
	private static void sendLog(Producer<String, String> producer) throws InterruptedException, ExecutionException {
		String topicName = "logs";
		  //String topicName = "rpc-tasks-status";\"Dummy - 163\"
		  RecordMetadata meta = producer.send(new ProducerRecord<String, String>(topicName, 
				  "sender1", "{\r\n"
					  		+ "                \"detectorName\": null,\r\n"
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
				  "609ce064d3da784f07a11b4e", Consts.msgConfig )) .get(); //
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

