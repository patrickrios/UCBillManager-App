package model.entity;

public class TypeRegister
{
	private int code;
	private String name;
	
	public TypeRegister(int code) {
		this.code = code;
		if(code==1)
			this.name="Despesa";
		else if(code==2)
			this.name="Receita";
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	public int getCode() {
		return code;
	}

}
