package model.exception;

import model.entity.Register;

public class RegisterAlreadyExistException extends Exception {
	private Register register;
	
	public RegisterAlreadyExistException(Register register) {
		this.register = register;
	}
	@Override
	public String toString() {
		return this.register.getCode()+" is already registered";
	}
}
