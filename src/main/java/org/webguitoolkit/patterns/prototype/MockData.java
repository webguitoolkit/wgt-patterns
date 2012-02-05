/*
Copyright 2008 Endress+Hauser Infoserve GmbH&Co KG 
Licensed under the Apache License, Version 2.0 (the "License"); 
you may not use this file except in compliance with the License. 
You may obtain a copy of the License at 

http://www.apache.org/licenses/LICENSE-2.0 

Unless required by applicable law or agreed to in writing, software 
distributed under the License is distributed on an "AS IS" BASIS, 
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or 
implied. See the License for the specific language governing permissions 
and limitations under the License.
*/
package org.webguitoolkit.patterns.prototype;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class MockData {

	public static Map<String, String[]> data = new HashMap<String, String[]>();

	public static String lipsum = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Phasellus rhoncus. Quisque id erat. "
			+ "Morbi in lectus. Ut imperdiet dolor tincidunt libero. Donec sed dolor nec libero pulvinar lacinia. "
			+ "Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Vestibulum "
			+ "id erat. Nulla eget dolor at dolor lobortis nonummy. Aliquam velit erat, tristique eu, laoreet sit "
			+ "amet, adipiscing ac, eros. Donec tempor. Sed non quam. Ut ac leo vel lectus tempus mollis. Aliquam "
			+ "sem elit, consequat hendrerit, dictum pretium, molestie quis, diam. Fusce erat. Cras cursus diam "
			+ "vitae nisi. Proin molestie, sem sit amet condimentum euismod, odio felis molestie magna, a imperdiet "
			+ "lorem ipsum sit amet quam. Quisque quis purus sit amet ante suscipit varius. Sed commodo metus ut "
			+ "sapien. Praesent id nibh rhoncus urna molestie dictum.";

	static {
		data.put("name", new String[] { "Müller", "Meier", "Schulz", "Gates", "Jobs", "da Vinci", "Brecht", "Schiller", "Poe", "Harley",
				"Gross", "Podolski", "Toni", "Klose", "Lahm", "Figo", "Beckham" });
		data.put("fname", new String[] { "Peter", "Paul", "Hans", "Bill", "Steve", "Maria", "Jane", "Jenny", "Luca", "Luis", "David",
				"Silvia", "Claudia", "Petra", "Martin", "Thorsten", "Wolfgang", "Wolfram", "Lars", "Edgar A." });
		data.put("city", new String[] { "Berlin", "Freiburg", "London", "New York", "Boston", "Paris", "Köln", "Hamburg", "Amsterdam",
				"Venedig" });
		data.put("street", new String[] { "Rheinstr. 98", "5ts Ave.", "Via Claudio 2", "Hauptstr. 88", "Bergstr. 4", "123 Main St.",
				"22 Ocean Dr.", "Alsterweg 5", "Via San Marco 56", "Domplatz 1" });
		data.put("root", new String[] { "21265A", "21265", "21265S", "21310", "21561", "23P", "30A", "30D", "30F", "30H", "30X", "31F",
				"31X", "33A", "33D", "33F", "33H", "33X", "35S", "35X" });
		data.put("device", new String[] { "Promag 23P", "Promag 30A", "Promag 30D", "Promag 30F", "Promag 30H", "Promag 31F",
				"Spare part Promag 31X", "Promag 33A", "Promag 33D", "Promag 33F", "Promag 33H", "Spare part Promag 33X", "Promag 35S",
				"Spare part Promag 35X", "Promag 39A", "Promag 39F", "Promag 39H", "Spare part Promag 39X", "PROline Promass 40E" });
		data.put("principle", new String[] { "CA-pH / redox measurement", "CC-chlorine measurement", "CC-oxygen measurement",
				"CD-turbidity measurement", "CF-conductivity measurement", "CH-chlorine measurement", "CI-Liquiline",
				"CK-analysis stations", "CM-wastewater samplers", "CZ-general liquid analysis accessories",
				"DA-electro-magnetic flow measurement", "DB-vortex flow measurement", "DD-coriolis mass flow measurement",
				"DF-flow switch", "DH-thermal mass flow measurement", "DJ-ultrasonic flow measurement",
				"DK-flow measurement open channels", "D-turbidity measurement", "DZ-general flow accessories",
				"FA-capacitive level measurement", "FC-conductivity level measurement", "FE-hydrostatic level measurement",
				"FG-radiometric level measurement", "FH-run time measurement TDR", "FJ-ultrasonic level measurement",
				"FK- Ultrasonic from Outside", "FL-microwave level measurement", "FM- Microwave Barriers", "FN-electro-mechanical systems",
				"FP-vibronic limits switches, bulk solids", "FQ-vibronic limits switches, liquides", "FS-servo-balanced tank gauging",
				"NF- Inventory Gauging SYSTEM & GAUGING", "NS- Inventory Gauging SAKURA", "PA-process pressure",
				"PB- Process Pressure, Switches", "PD-differential pressure transmitter", "PE-Hydrostatic Pressure Measurement",
				"PZ-general pressure accessories", "RA-60mm printers", "RC-100mm recorders", "RF-electronic storage units / data manager",
				"SA- Housing Systems and Accessories", "SC-display units and counters", "SD-transmitters power supplies, contactors",
				"SE-power supplies, overvoltage protection", "SG-communication, hard- and software", "SK-controllers", "SM-Hardware E+H",
				"TA-Thermometers (Resistance)", "TC-Thermometers (Thermocouples)", "TM-Transmitter" });
		data.put("role", new String[] { "Admin", "User", "Customer", "Projectlead" });
		data.put("e-mail", new String[] { "k1213@web.com", "p678@mail.com", "t12354@funmail.com", "a134@spam.com" });
	}

	public static String get(String desc) {
		StringTokenizer st = new StringTokenizer(desc, ":");
		assert st.countTokens() == 3 : "missing token " + desc;
		String name = st.nextToken().toLowerCase();
		String type = st.nextToken().toLowerCase();
		if (type.equals("num"))
			return "" + (int)(Math.random() * 100) % 100;
		if (type.equals("ser"))
			return "" + (int)(Math.random() * 1000000);
		if (type.equals("lip"))
			return lipsum;
		if (type.equals("dat")) {
			long d = System.currentTimeMillis() - ((long)(Math.random()) >> 6);
			return new Date(d).toString();
		}
		String[] values = (String[])data.get(type);
		if (values != null) {
			return values[(int)(Math.random() * 100) % values.length];
		}
		else
			return name + "-" + (int)(Math.random() * 100) % 100;
	}

}
