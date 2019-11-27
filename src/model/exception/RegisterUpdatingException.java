package model.exception;

public class RegisterUpdatingException extends Exception {
	private String register;
	
	public RegisterUpdatingException(String register) {
		this.register = register;
	}
	
	@Override
	public String toString() {
		return "Fail trying update register "+this.register;
	}
}
