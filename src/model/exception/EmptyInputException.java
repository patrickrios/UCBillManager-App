package model.exception;

public class EmptyInputException extends Exception {
	private String inputId;
	
	public EmptyInputException(String inputId) {
		this.inputId = inputId;
	}
	
	@Override
	public String toString() {
		return this.inputId+" is empty";
	}
}
