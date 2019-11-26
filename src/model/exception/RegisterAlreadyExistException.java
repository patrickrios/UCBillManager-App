package model.exception;

@SuppressWarnings("serial")
public class RegisterAlreadyExistException extends Exception {
	private String register;
	
	public RegisterAlreadyExistException(String code) {
		this.register = code;
	}
	@Override
	public String toString() {
		return this.register+" is already registered";
	}
}
