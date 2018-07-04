package net.geforce.smartwatch.gui.rologia.time;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeManager {
    
	public static String getTime(String format) {
		//DateFormat dateFormat = new SimpleDateFormat("hh:mm aa");
    	//return dateFormat.format(new Date()).toString();
	    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(format);

		LocalDateTime time = LocalDateTime.now();
		return dateFormatter.format(time);
	}

}
