package model.types;

public class TypeRegister
{
	private int code;
	private String name;
	public static int DESPESA = 1;
	public static int RECEITA = 2;
	
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
	
	public static String getTypeName(int type)
	{
		String name = "";
		if(type==1)
			name="Despesa";
		else if(type==2)
			name="Receita";
		return name;
	}

}
