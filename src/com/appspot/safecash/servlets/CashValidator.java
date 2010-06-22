package com.appspot.safecash.servlets;

public class  CashValidator {

	private static String valor;
	
	public static String validate(String entrada)
	{
		valor = "";
		int size = entrada.length();
		for(int i=0;i<size;i++)
		{
			if(entrada.charAt(i)==',')
			{
				valor += '.';
			}
			else if(entrada.charAt(i)!='.')
			{
				valor += entrada.charAt(i);
			}
		}
		
		return valor;
	}
}
