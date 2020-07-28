package pl.java.converters;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateConverter {
	private static final String DAY_MONTH_YEAR_PATTERN = "dd/MM/yyyy";
	
	public static LocalDate convertDayMonthYearStringToLocalDate(String dayMonthYearString) {
		DateTimeFormatter formatter = createDateTimeFormatter(DAY_MONTH_YEAR_PATTERN);
		LocalDate localDate = LocalDate.parse(dayMonthYearString, formatter);
		return localDate;
	}
	
	private static DateTimeFormatter createDateTimeFormatter(String pattern) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		return formatter;
	}
		
}
