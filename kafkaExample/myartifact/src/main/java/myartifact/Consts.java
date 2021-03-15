package myartifact;

public class Consts {
	
	public static String msgConfig = "{\r\n"
			+ "	\"detector_id\": \"d99faf2b-d89f-40e2-8041-b68ed69424d9\",\r\n"
			+ "	\"transaction_id\": \"135\",\r\n"
			+"	\"name\": \"detector1\",\r\n"
			+ "	\"event\": {\r\n"
			+ "		\"event_type\": \"config_start\",\r\n"
			+ "		\"event_id\": \"0\",\r\n"
			+ "		\"event_start_ts\": 1613916292726\r\n"
			+ "	},\r\n"
			+ "	\"main_config\": {\r\n"
			+ "		\"_id\": \"602d57aa86b8916014740d1a\",\r\n"
			+ "		\"version\": \"1\",\r\n"
			+ "		\"name\": \"standard\",\r\n"
			+ "		\"modelName\": \"NCS540\",\r\n"
			+ "		\"description\": \"Cisco NCS540 standard config template\",\r\n"
			+ "		\"default\": true,\r\n"
			+ "		\"managementAccessInfoGrpc\": {\r\n"
			+ "			\"ipAddress\": \"10.19.0.150\",\r\n"
			+ "			\"tcpPort\": 10000\r\n"
			+ "		},\r\n"
			+ "		\"netconfAccessInfo\": {\r\n"
			+ "			\"serverIpAddress\": \"10.19.0.150\",\r\n"
			+ "			\"serverTcpPort\": 830,\r\n"
			+ "			\"serverUser\": \"radware\",\r\n"
			+ "			\"serverPassword\": \"radware\"\r\n"
			+ "		},\r\n"
			+ "		\"reportingInfo\": {\r\n"
			+ "			\"reportIntervalSec\": 3.0\r\n"
			+ "		},\r\n"
			+ "		\"telemetryInfo\": {\r\n"
			+ "			\"protocol\": \"GPB\",\r\n"
			+ "			\"samplingRate\": 10000.0,\r\n"
			+ "			\"exporterIpAddress\": \"10.19.0.150\",\r\n"
			+ "			\"exporterPort\": 5005,\r\n"
			+ "			\"interfaces\": [\r\n"
			+ "				{\r\n"
			+ "					\"name\": \"TenGigE0/0/0/1\",\r\n"
			+ "					\"mode\": \"rx\"\r\n"
			+ "				}\r\n"
			+ "			]\r\n"
			+ "		},\r\n"
			+ "		\"baseTemplateId\": \"602d57aa86b8916014740d1a\"\r\n"
			+ "	},\r\n"
			+ "	\"sec_config\": {\r\n"
			+ "		\"_id\": \"602d585586b8916014740d1c\",\r\n"
			+ "		\"version\": \"1\",\r\n"
			+ "		\"modelName\": \"NCS540\",\r\n"
			+ "		\"name\": \"standard\",\r\n"
			+ "		\"description\": \"Cisco NCS540 standard security template\",\r\n"
			+ "		\"default\": false,\r\n"
			+ "		\"detectionAlgorithms\": [\r\n"
			+ "			{\r\n"
			+ "				\"id\": \"msd2\",\r\n"
			+ "				\"name\": \"Behavioral TEID\",\r\n"
			+ "				\"parameters\": [\r\n"
			+ "					{\r\n"
			+ "						\"type\": \"int\",\r\n"
			+ "						\"id\": \"numberOfQuantiles\",\r\n"
			+ "						\"name\": \"Number Of UE buckets\",\r\n"
			+ "						\"value\": 100.0\r\n"
			+ "					},\r\n"
			+ "					{\r\n"
			+ "						\"type\": \"int\",\r\n"
			+ "						\"id\": \"minSamplesForLearning\",\r\n"
			+ "						\"name\": \"Minimal flows per UE during learnig\",\r\n"
			+ "						\"value\": 5.0\r\n"
			+ "					},\r\n"
			+ "					{\r\n"
			+ "						\"type\": \"int\",\r\n"
			+ "						\"id\": \"minFps\",\r\n"
			+ "						\"name\": \"Min Fps Threshold\",\r\n"
			+ "						\"value\": 1000.0\r\n"
			+ "					},\r\n"
			+ "					{\r\n"
			+ "						\"type\": \"int\",\r\n"
			+ "						\"id\": \"minBw\",\r\n"
			+ "						\"name\": \"Min Bw Detectable Attack (GBs)\",\r\n"
			+ "						\"value\": 0.001\r\n"
			+ "					},\r\n"
			+ "					{\r\n"
			+ "						\"type\": \"int\",\r\n"
			+ "						\"id\": \"minBwBps\",\r\n"
			+ "						\"name\": \"Min Bw Bps Threshold (GBs)\",\r\n"
			+ "						\"value\": 0.01\r\n"
			+ "					},\r\n"
			+ "					{\r\n"
			+ "						\"type\": \"int\",\r\n"
			+ "						\"id\": \"attackStartThreshold\",\r\n"
			+ "						\"name\": \"Attack Threshold (%)\",\r\n"
			+ "						\"value\": 2.0\r\n"
			+ "					},\r\n"
			+ "					{\r\n"
			+ "						\"type\": \"float\",\r\n"
			+ "						\"id\": \"attackEndThreshold\",\r\n"
			+ "						\"name\": \"Attack End Threshold (%)\",\r\n"
			+ "						\"value\": 1.5\r\n"
			+ "					},\r\n"
			+ "					{\r\n"
			+ "						\"type\": \"boolean\",\r\n"
			+ "						\"id\": \"swapKey\",\r\n"
			+ "						\"name\": \"Swap flow key\",\r\n"
			+ "						\"value\": true\r\n"
			+ "					},\r\n"
			+ "					{\r\n"
			+ "						\"type\": \"int\",\r\n"
			+ "						\"id\": \"quietTime\",\r\n"
			+ "						\"name\": \"Period -of quiet time to restart learning (s)\",\r\n"
			+ "						\"value\": 10.0\r\n"
			+ "					},\r\n"
			+ "					{\r\n"
			+ "						\"type\": \"int\",\r\n"
			+ "						\"id\": \"attackDetectionInterval\",\r\n"
			+ "						\"name\": \"Interval of continous traffic for attack start (s)\",\r\n"
			+ "						\"value\": 2.0\r\n"
			+ "					},\r\n"
			+ "					{\r\n"
			+ "						\"type\": \"float\",\r\n"
			+ "						\"id\": \"singleAttackerMinBw\",\r\n"
			+ "						\"name\": \"Minimal BW for flagging for mitigation (MBs)\",\r\n"
			+ "						\"value\": 0.001\r\n"
			+ "					}\r\n"
			+ "				]\r\n"
			+ "			},\r\n"
			+ "			{\r\n"
			+ "				\"id\": \"thdsrc\",\r\n"
			+ "				\"name\": \"Threshold TEID\",\r\n"
			+ "				\"parameters\": [\r\n"
			+ "					{\r\n"
			+ "						\"type\": \"float\",\r\n"
			+ "						\"id\": \"attackHighWater1\",\r\n"
			+ "						\"name\": \"Attack High Water Mark (GBs)\",\r\n"
			+ "						\"value\": 0.5\r\n"
			+ "					},\r\n"
			+ "					{\r\n"
			+ "						\"type\": \"float\",\r\n"
			+ "						\"id\": \"attackLowWater2\",\r\n"
			+ "						\"name\": \"Attack Low Water Mark (GBs)\",\r\n"
			+ "						\"value\": 0.4\r\n"
			+ "					},\r\n"
			+ "					{\r\n"
			+ "						\"type\": \"int\",\r\n"
			+ "						\"id\": \"aboveHighIntervals\",\r\n"
			+ "						\"name\": \"Above High Interval (s)\",\r\n"
			+ "						\"value\": 5.0\r\n"
			+ "					},\r\n"
			+ "					{\r\n"
			+ "						\"type\": \"int\",\r\n"
			+ "						\"id\": \"belowLowIntervals\",\r\n"
			+ "						\"name\": \"Below Low Interval (s)\",\r\n"
			+ "						\"value\": 5.0\r\n"
			+ "					}\r\n"
			+ "				]\r\n"
			+ "			}"
			+ "		],\r\n"
			+ "		\"attackEndDetection\": {\r\n"
			+ "			\"attackEndGraceSec\": 1000.0,\r\n"
			+ "			\"attackEndTelemetry\": \"peeping\",\r\n"
			+ "			\"peepingRemovalIntervalSec\": 1000.0\r\n"
			+ "		},\r\n"
			+ "		\"attackCharacterization\": {\r\n"
			+ "			\"enabled\": \"true\",\r\n"
			+ "			\"characterizationIntervalSec\": 3.0,\r\n"
			+ "			\"characterizationAlgorithm\": \"byTuple\"\r\n"
			+ "		},\r\n"
			+ "		\"mitigation\": {\r\n"
			+ "			\"method\": \"teid\",\r\n"
			+ "			\"maxBlockedTEID\": 500.0\r\n"
			+ "		},\r\n"
			+ "		\"baseTemplateId\": \"602d585586b8916014740d1c\"\r\n"
			+ "	},\r\n"
			+ "	\"hardware\": {\r\n"
			+ "		\"_id\": {\r\n"
			+ "			\"$oid\": \"602d580a86b8916014740d1b\"\r\n"
			+ "		},\r\n"
			+ "		\"version\": \"1\",\r\n"
			+ "		\"modelName\": \"NCS540\",\r\n"
			+ "		\"description\": \"Cisco NCS540 Aggregation Router\",\r\n"
			+ "		\"cores\": 8.0,\r\n"
			+ "		\"memoryInMB\": 8096.0,\r\n"
			+ "		\"allocatedCores\": 2.0,\r\n"
			+ "		\"allocatedMemoryInMB\": 1024.0,\r\n"
			+ "		\"warninglMemoryPercentage\": 50.0,\r\n"
			+ "		\"criticalMemoryPercentage\": 80.0,\r\n"
			+ "		\"warninglCpuPercentage\": 50.0,\r\n"
			+ "		\"criticalCpuPercentage\": 80.0,\r\n"
			+ "		\"detectorMaxFPS\": 120000.0,\r\n"
			+ "		\"deploySupport\": \"true\",\r\n"
			+ "		\"interfaces\": [\r\n"
			+ "			\"TenGigE0/0/0/0\",\r\n"
			+ "			\"TenGigE0/0/0/1\",\r\n"
			+ "			\"TenGigE0/0/0/2\",\r\n"
			+ "			\"TenGigE0/0/0/4\",\r\n"
			+ "			\"TenGigE0/0/0/5\",\r\n"
			+ "			\"TenGigE0/0/0/6\",\r\n"
			+ "			\"TenGigE0/0/0/7\",\r\n"
			+ "			\"TenGigE0/0/0/8\",\r\n"
			+ "			\"TenGigE0/0/0/9\",\r\n"
			+ "			\"TenGigE0/0/0/10\",\r\n"
			+ "			\"TenGigE0/0/0/11\",\r\n"
			+ "			\"TenGigE0/0/0/12\",\r\n"
			+ "			\"TenGigE0/0/0/13\",\r\n"
			+ "			\"TenGigE0/0/0/14\",\r\n"
			+ "			\"TenGigE0/0/0/15\",\r\n"
			+ "			\"TenGigE0/0/0/16\",\r\n"
			+ "			\"TenGigE0/0/0/17\",\r\n"
			+ "			\"TenGigE0/0/0/18\",\r\n"
			+ "			\"TenGigE0/0/0/19\",\r\n"
			+ "			\"TenGigE0/0/0/20\",\r\n"
			+ "			\"TenGigE0/0/0/21\",\r\n"
			+ "			\"TenGigE0/0/0/22\",\r\n"
			+ "			\"TenGigE0/0/0/23\",\r\n"
			+ "			\"HundredGigE0/0/1/0\",\r\n"
			+ "			\"HundredGigE0/0/1/1\",\r\n"
			+ "			\"GigabitEthernet0/0/0/3\",\r\n"
			+ "			\"TwentyFiveGigE0/0/0/24\",\r\n"
			+ "			\"TwentyFiveGigE0/0/0/25\",\r\n"
			+ "			\"TwentyFiveGigE0/0/0/26\",\r\n"
			+ "			\"TwentyFiveGigE0/0/0/27\",\r\n"
			+ "			\"TwentyFiveGigE0/0/0/28\",\r\n"
			+ "			\"TwentyFiveGigE0/0/0/29\",\r\n"
			+ "			\"TwentyFiveGigE0/0/0/30\",\r\n"
			+ "			\"TwentyFiveGigE0/0/0/31\"\r\n"
			+ "		]\r\n"
			+ "	}\r\n"
			+ "}";
	
	
	
	
	
	public static String msgConfigReal = "{\r\n"
			+ "	\"detector_id\": \"d99faf2b-d89f-40e2-8041-b68ed69424d9\",\r\n"
			+ "	\"transaction_id\": \"135\",\r\n"
			+"	\"name\": \"detector1\",\r\n"
			+ "	\"event\": {\r\n"
			+ "		\"event_type\": \"config_start\",\r\n"
			+ "		\"event_id\": \"0\",\r\n"
			+ "		\"event_start_ts\": 1613916292726\r\n"
			+ "	},\r\n"
			+ "	\"main_config\": {\r\n"
			+ "		\"_id\": \"602d57aa86b8916014740d1a\",\r\n"
			+ "		\"version\": \"1\",\r\n"
			+ "		\"name\": \"standard\",\r\n"
			+ "		\"modelName\": \"NCS540\",\r\n"
			+ "		\"description\": \"Cisco NCS540 standard config template\",\r\n"
			+ "		\"default\": true,\r\n"
			+ "		\"managementAccessInfoGrpc\": {\r\n"
			+ "			\"ipAddress\": \"10.19.0.20\",\r\n"
			+ "			\"tcpPort\": 10000\r\n"
			+ "		},\r\n"
			+ "		\"netconfAccessInfo\": {\r\n"
			+ "			\"serverIpAddress\": \"17.1.1.2\",\r\n"
			+ "			\"serverTcpPort\": 830,\r\n"
			+ "			\"serverUser\": \"radware\",\r\n"
			+ "			\"serverPassword\": \"radware\"\r\n"
			+ "		},\r\n"
			+ "		\"reportingInfo\": {\r\n"
			+ "			\"reportIntervalSec\": 3.0\r\n"
			+ "		},\r\n"
			+ "		\"telemetryInfo\": {\r\n"
			+ "			\"protocol\": \"GPB\",\r\n"
			+ "			\"samplingRate\": 10000.0,\r\n"
			+ "			\"exporterIpAddress\": \"15.1.1.2\",\r\n"
			+ "			\"exporterPort\": 5005,\r\n"
			+ "			\"interfaces\": [\r\n"
			+ "				{\r\n"
			+ "					\"name\": \"TenGigE0/0/0/1\",\r\n"
			+ "					\"mode\": \"rx\"\r\n"
			+ "				}\r\n"
			+ "			]\r\n"
			+ "		},\r\n"
			+ "		\"baseTemplateId\": \"602d57aa86b8916014740d1a\"\r\n"
			+ "	},\r\n"
			+ "	\"sec_config\": {\r\n"
			+ "		\"_id\": \"602d585586b8916014740d1c\",\r\n"
			+ "		\"version\": \"1\",\r\n"
			+ "		\"modelName\": \"NCS540\",\r\n"
			+ "		\"name\": \"standard\",\r\n"
			+ "		\"description\": \"Cisco NCS540 standard security template\",\r\n"
			+ "		\"default\": false,\r\n"
			+ "		\"detectionAlgorithms\": [\r\n"
			+ "			{\r\n"
			+ "				\"id\": \"msd2\",\r\n"
			+ "				\"name\": \"Behavioral TEID\",\r\n"
			+ "				\"parameters\": [\r\n"
			+ "					{\r\n"
			+ "						\"type\": \"int\",\r\n"
			+ "						\"id\": \"numberOfQuantiles\",\r\n"
			+ "						\"name\": \"Number Of UE buckets\",\r\n"
			+ "						\"value\": 100.0\r\n"
			+ "					},\r\n"
			+ "					{\r\n"
			+ "						\"type\": \"int\",\r\n"
			+ "						\"id\": \"minSamplesForLearning\",\r\n"
			+ "						\"name\": \"Minimal flows per UE during learnig\",\r\n"
			+ "						\"value\": 5.0\r\n"
			+ "					},\r\n"
			+ "					{\r\n"
			+ "						\"type\": \"int\",\r\n"
			+ "						\"id\": \"minFps\",\r\n"
			+ "						\"name\": \"Min Fps Threshold\",\r\n"
			+ "						\"value\": 1000.0\r\n"
			+ "					},\r\n"
			+ "					{\r\n"
			+ "						\"type\": \"int\",\r\n"
			+ "						\"id\": \"minBw\",\r\n"
			+ "						\"name\": \"Min Bw Detectable Attack (GBs)\",\r\n"
			+ "						\"value\": 0.001\r\n"
			+ "					},\r\n"
			+ "					{\r\n"
			+ "						\"type\": \"int\",\r\n"
			+ "						\"id\": \"minBwBps\",\r\n"
			+ "						\"name\": \"Min Bw Bps Threshold (GBs)\",\r\n"
			+ "						\"value\": 0.01\r\n"
			+ "					},\r\n"
			+ "					{\r\n"
			+ "						\"type\": \"int\",\r\n"
			+ "						\"id\": \"attackStartThreshold\",\r\n"
			+ "						\"name\": \"Attack Threshold (%)\",\r\n"
			+ "						\"value\": 2.0\r\n"
			+ "					},\r\n"
			+ "					{\r\n"
			+ "						\"type\": \"float\",\r\n"
			+ "						\"id\": \"attackEndThreshold\",\r\n"
			+ "						\"name\": \"Attack End Threshold (%)\",\r\n"
			+ "						\"value\": 1.5\r\n"
			+ "					},\r\n"
			+ "					{\r\n"
			+ "						\"type\": \"boolean\",\r\n"
			+ "						\"id\": \"swapKey\",\r\n"
			+ "						\"name\": \"Swap flow key\",\r\n"
			+ "						\"value\": true\r\n"
			+ "					},\r\n"
			+ "					{\r\n"
			+ "						\"type\": \"int\",\r\n"
			+ "						\"id\": \"quietTime\",\r\n"
			+ "						\"name\": \"Period -of quiet time to restart learning (s)\",\r\n"
			+ "						\"value\": 10.0\r\n"
			+ "					},\r\n"
			+ "					{\r\n"
			+ "						\"type\": \"int\",\r\n"
			+ "						\"id\": \"attackDetectionInterval\",\r\n"
			+ "						\"name\": \"Interval of continous traffic for attack start (s)\",\r\n"
			+ "						\"value\": 2.0\r\n"
			+ "					},\r\n"
			+ "					{\r\n"
			+ "						\"type\": \"float\",\r\n"
			+ "						\"id\": \"singleAttackerMinBw\",\r\n"
			+ "						\"name\": \"Minimal BW for flagging for mitigation (MBs)\",\r\n"
			+ "						\"value\": 0.001\r\n"
			+ "					}\r\n"
			+ "				]\r\n"
			+ "			},\r\n"
			+ "			{\r\n"
			+ "				\"id\": \"thdsrc\",\r\n"
			+ "				\"name\": \"Threshold TEID\",\r\n"
			+ "				\"parameters\": [\r\n"
			+ "					{\r\n"
			+ "						\"type\": \"float\",\r\n"
			+ "						\"id\": \"attackHighWater1\",\r\n"
			+ "						\"name\": \"Attack High Water Mark (GBs)\",\r\n"
			+ "						\"value\": 0.5\r\n"
			+ "					},\r\n"
			+ "					{\r\n"
			+ "						\"type\": \"float\",\r\n"
			+ "						\"id\": \"attackLowWater2\",\r\n"
			+ "						\"name\": \"Attack Low Water Mark (GBs)\",\r\n"
			+ "						\"value\": 0.4\r\n"
			+ "					},\r\n"
			+ "					{\r\n"
			+ "						\"type\": \"int\",\r\n"
			+ "						\"id\": \"aboveHighIntervals\",\r\n"
			+ "						\"name\": \"Above High Interval (s)\",\r\n"
			+ "						\"value\": 5.0\r\n"
			+ "					},\r\n"
			+ "					{\r\n"
			+ "						\"type\": \"int\",\r\n"
			+ "						\"id\": \"belowLowIntervals\",\r\n"
			+ "						\"name\": \"Below Low Interval (s)\",\r\n"
			+ "						\"value\": 5.0\r\n"
			+ "					}\r\n"
			+ "				]\r\n"
			+ "			}"
			+ "		],\r\n"
			+ "		\"attackEndDetection\": {\r\n"
			+ "			\"attackEndGraceSec\": 1000.0,\r\n"
			+ "			\"attackEndTelemetry\": \"peeping\",\r\n"
			+ "			\"peepingRemovalIntervalSec\": 1000.0\r\n"
			+ "		},\r\n"
			+ "		\"attackCharacterization\": {\r\n"
			+ "			\"enabled\": \"true\",\r\n"
			+ "			\"characterizationIntervalSec\": 3.0,\r\n"
			+ "			\"characterizationAlgorithm\": \"byTuple\"\r\n"
			+ "		},\r\n"
			+ "		\"mitigation\": {\r\n"
			+ "			\"method\": \"teid\",\r\n"
			+ "			\"maxBlockedTEID\": 500.0\r\n"
			+ "		},\r\n"
			+ "		\"baseTemplateId\": \"602d585586b8916014740d1c\"\r\n"
			+ "	},\r\n"
			+ "	\"hardware\": {\r\n"
			+ "		\"_id\": {\r\n"
			+ "			\"$oid\": \"602d580a86b8916014740d1b\"\r\n"
			+ "		},\r\n"
			+ "		\"version\": \"1\",\r\n"
			+ "		\"modelName\": \"NCS540\",\r\n"
			+ "		\"description\": \"Cisco NCS540 Aggregation Router\",\r\n"
			+ "		\"cores\": 8.0,\r\n"
			+ "		\"memoryInMB\": 8096.0,\r\n"
			+ "		\"allocatedCores\": 2.0,\r\n"
			+ "		\"allocatedMemoryInMB\": 1024.0,\r\n"
			+ "		\"warninglMemoryPercentage\": 50.0,\r\n"
			+ "		\"criticalMemoryPercentage\": 80.0,\r\n"
			+ "		\"warninglCpuPercentage\": 50.0,\r\n"
			+ "		\"criticalCpuPercentage\": 80.0,\r\n"
			+ "		\"detectorMaxFPS\": 120000.0,\r\n"
			+ "		\"deploySupport\": \"true\",\r\n"
			+ "		\"interfaces\": [\r\n"
			+ "			\"TenGigE0/0/0/0\",\r\n"
			+ "			\"TenGigE0/0/0/1\",\r\n"
			+ "			\"TenGigE0/0/0/2\",\r\n"
			+ "			\"TenGigE0/0/0/4\",\r\n"
			+ "			\"TenGigE0/0/0/5\",\r\n"
			+ "			\"TenGigE0/0/0/6\",\r\n"
			+ "			\"TenGigE0/0/0/7\",\r\n"
			+ "			\"TenGigE0/0/0/8\",\r\n"
			+ "			\"TenGigE0/0/0/9\",\r\n"
			+ "			\"TenGigE0/0/0/10\",\r\n"
			+ "			\"TenGigE0/0/0/11\",\r\n"
			+ "			\"TenGigE0/0/0/12\",\r\n"
			+ "			\"TenGigE0/0/0/13\",\r\n"
			+ "			\"TenGigE0/0/0/14\",\r\n"
			+ "			\"TenGigE0/0/0/15\",\r\n"
			+ "			\"TenGigE0/0/0/16\",\r\n"
			+ "			\"TenGigE0/0/0/17\",\r\n"
			+ "			\"TenGigE0/0/0/18\",\r\n"
			+ "			\"TenGigE0/0/0/19\",\r\n"
			+ "			\"TenGigE0/0/0/20\",\r\n"
			+ "			\"TenGigE0/0/0/21\",\r\n"
			+ "			\"TenGigE0/0/0/22\",\r\n"
			+ "			\"TenGigE0/0/0/23\",\r\n"
			+ "			\"HundredGigE0/0/1/0\",\r\n"
			+ "			\"HundredGigE0/0/1/1\",\r\n"
			+ "			\"GigabitEthernet0/0/0/3\",\r\n"
			+ "			\"TwentyFiveGigE0/0/0/24\",\r\n"
			+ "			\"TwentyFiveGigE0/0/0/25\",\r\n"
			+ "			\"TwentyFiveGigE0/0/0/26\",\r\n"
			+ "			\"TwentyFiveGigE0/0/0/27\",\r\n"
			+ "			\"TwentyFiveGigE0/0/0/28\",\r\n"
			+ "			\"TwentyFiveGigE0/0/0/29\",\r\n"
			+ "			\"TwentyFiveGigE0/0/0/30\",\r\n"
			+ "			\"TwentyFiveGigE0/0/0/31\"\r\n"
			+ "		]\r\n"
			+ "	}\r\n"
			+ "}";
	
	
	
	
	
}
//GBP,NFV9