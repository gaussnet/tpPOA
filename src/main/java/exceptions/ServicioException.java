package exceptions;

public class ServicioException extends Exception{

	public ServicioException() {
		super();
		
	}

	public ServicioException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public ServicioException(String message) {
		super(message);
		
	}

	public ServicioException(Throwable cause) {
		super(cause);
		
	}
}
