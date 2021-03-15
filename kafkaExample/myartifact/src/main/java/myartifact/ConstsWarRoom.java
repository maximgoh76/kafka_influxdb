package myartifact;

public class ConstsWarRoom {
	public static String CALL_WR_MESSAGE = "{\r\n"
			+ "	\"detectorName\":\"Dummy163\",\r\n"
			+ "	\"type\":\"forensics\",\r\n"
			+ "	\"forensics\": {\r\n"
			+ "        \"collectionInterval\": 10,\r\n"
			+ "        \"template\": [\r\n"
			+ "            {\r\n"
			+ "                \"name\" : \"DST_ADDR\",\r\n"
			+ "                \"num_values\" : 5\r\n"
			+ "            },\r\n"
			+ "            {\r\n"
			+ "                \"name\" : \"DST_ADDR_V6\",\r\n"
			+ "                \"num_values\" : 5\r\n"
			+ "            },\r\n"
			+ "            {\r\n"
			+ "                \"name\" : \"L4_SRC_PORT\",\r\n"
			+ "                \"num_values\" : 5\r\n"
			+ "            },\r\n"
			+ "            {\r\n"
			+ "                \"name\" : \"L4_DST_PORT\",\r\n"
			+ "                \"num_values\" : 5\r\n"
			+ "            },\r\n"
			+ "            {\r\n"
			+ "                \"name\" : \"PROTOCOL\",\r\n"
			+ "                \"num_values\" : 5,\r\n"
			+ "                \"ignore_values\": [\"ICMP\"]\r\n"
			+ "            },\r\n"
			+ "            {\r\n"
			+ "                \"name\" : \"TCP_FLAGS\",\r\n"
			+ "                \"num_values\" : 5,\r\n"
			+ "                \"ignore_values\": [\"ACK\"]\r\n"
			+ "            },\r\n"
			+ "            {\r\n"
			+ "                \"name\" : \"TTL\",\r\n"
			+ "                \"num_values\" : 5\r\n"
			+ "            },\r\n"
			+ "            {\r\n"
			+ "                \"name\" : \"IPV4_IDENT\",\r\n"
			+ "                \"num_values\" : 5\r\n"
			+ "            },\r\n"
			+ "            {\r\n"
			+ "                \"name\" : \"FRAG_FLAGS\",\r\n"
			+ "                \"num_values\" : 5\r\n"
			+ "            },\r\n"
			+ "            {\r\n"
			+ "                \"name\" : \"L4_CHKSUM\",\r\n"
			+ "                \"num_values\" : 5\r\n"
			+ "            },\r\n"
			+ "            {\r\n"
			+ "                \"name\" : \"L4_SEQ\",\r\n"
			+ "                \"num_values\" : 5\r\n"
			+ "            }\r\n"
			+ "        ]\r\n"
			+ "    }\r\n"
			+ "}";
}