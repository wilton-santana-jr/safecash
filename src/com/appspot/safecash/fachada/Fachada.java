package com.appspot.safecash.fachada;

public class Fachada {

	private static Fachada singleton;

	public Fachada(){

	}

	public static Fachada getInstance(){
		if(Fachada.singleton == null){
			Fachada.singleton = new Fachada();
		}
		return Fachada.singleton;
	}
}
