package utilidades;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class FechaUtil {
	
	private static String horasManana[]= {"7", "8", "9", "10", "11", "12"};
	private static String horasTarde[]= {"13", "14", "15", "16", "17", "18", "19"};
	private static String horasNoche[]= {"20", "21", "22"};
	
	
	private static String minutos[]= {"00", "30"};
	
	public static LocalDateTime stringToLocalDateTime(String fecha) {
		LocalDateTime laFecha= LocalDateTime.parse(fecha, DateTimeFormatter.ISO_DATE_TIME);
		
		return laFecha;
	}
	
	public static String localDateTimeToString(LocalDateTime fecha) {
		String laFecha= fecha.format(DateTimeFormatter.ISO_DATE_TIME);				//2015-11-23T15:25:10
		
		return laFecha;
	}
	
	public static String getFechaMostrable(LocalDateTime fecha) {
		 DateTimeFormatter formatoMostrar= DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		 
		 return fecha.format(formatoMostrar);
	}
	
	public static LocalDateTime armarFechaYHora(LocalDateTime fecha, int hora, int minuto) {
		LocalDateTime fechaYHora= LocalDateTime.of(fecha.getYear(), fecha.getMonth(), fecha.getDayOfMonth(), hora, minuto);
		
		return fechaYHora;
	}
	
	public static boolean esDiaHabil(LocalDateTime fecha) {
		
		Instant instant= fecha.atZone(ZoneId.systemDefault()).toInstant();
		Date fechaDate= Date.from(instant);
		
		GregorianCalendar fechaCalendario = new GregorianCalendar();
		 fechaCalendario.setTime(fechaDate);
		 int diaSemana= fechaCalendario.get(Calendar.DAY_OF_WEEK);
		 
		 if (diaSemana == 1) {
			 return false;
		 } else {
			 return true;
		 }
	}
	
	public static LocalDateTime obtenerUltimoDiaSemana() {
		LocalDateTime fecha= LocalDateTime.now();
		Instant instant= fecha.atZone(ZoneId.systemDefault()).toInstant();
		Date fechaDate= Date.from(instant);
		
		GregorianCalendar fechaCalendario = new GregorianCalendar();
		 fechaCalendario.setTime(fechaDate);
		 int diaSemana= fechaCalendario.get(Calendar.DAY_OF_WEEK);
		 
		 if(diaSemana == 7) {
			 return fecha;
		 } else {
			 return fecha.plusDays(7 - diaSemana);
		 }
		 
	}

	public static String[] getHorasManana() {
		return horasManana;
	}

	public static void setHorasManana(String[] horasManana) {
		FechaUtil.horasManana = horasManana;
	}

	public static String[] getHorasTarde() {
		return horasTarde;
	}

	public static void setHorasTarde(String[] horasTarde) {
		FechaUtil.horasTarde = horasTarde;
	}

	public static String[] getHorasNoche() {
		return horasNoche;
	}

	public static void setHorasNoche(String[] horasNoche) {
		FechaUtil.horasNoche = horasNoche;
	}
	

	public static String[] getMinutos() {
		return minutos;
	}

	public void setMinutos(String[] minutos) {
		this.minutos = minutos;
	}
	
	
}
