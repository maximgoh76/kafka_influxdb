package consumer;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;
import org.influxdb.dto.Pong;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;

public class InfluxDbService {
	InfluxDB influxDB = null;
	public boolean openConnection() {
		
		
	influxDB = InfluxDBFactory.connect("http://localhost:8086");//(databaseURL, userName, password);
	Pong response = this.influxDB.ping();
	if (response.getVersion().equalsIgnoreCase("unknown")) {
	    System.out.println("Error pinging server.");
	    return false;
	} 
	//influxDB.createDatabase("baeldung");
	//influxDB.createRetentionPolicy(
	//  "defaultPolicy", "baeldung", "30d", 1, true);
	influxDB.setLogLevel(InfluxDB.LogLevel.BASIC);
	influxDB.enableBatch(100, 200, TimeUnit.MILLISECONDS);
	
	//influxDB.setRetentionPolicy("defaultPolicy");
	influxDB.setDatabase("testDb");
	
	return true;
	
	}
	
	public void writePoint(String key,int count) {
		Point point1 = Point.measurement("sample")
				  .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
				  .addField("name", "server1") 
				  .addField("words", count)
				  .build();
		influxDB.write(point1);
	}
	
	public void close() {
		this.influxDB.close();
	}
	
	public void queryData() {
		Query query = new Query("Select * from memory");
		QueryResult queryResult = influxDB.query(query);

		InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
		List<MemoryPoint> memoryPointList = resultMapper
				  .toPOJO(queryResult, MemoryPoint.class);
		for (MemoryPoint memoryPoint:memoryPointList) {
			System.out.println(memoryPoint.name + " with words = "+memoryPoint.words);
		}

		
	}
}
