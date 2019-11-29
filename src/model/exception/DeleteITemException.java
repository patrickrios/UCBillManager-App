package model.exception;

public class DeleteITemException extends Exception{
	private String itemName;
	public DeleteITemException(String name) {
		this.itemName = name;
	}
	
	@Override
	public String toString() {
		return this.itemName+" cannot be deleted.";
	}

}
