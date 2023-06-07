package main;

import modelo.Persona;
import modelo.PersonaDao;

public class MainSinTelefonos {

	public static void main(String[] args) throws Exception {
		PersonaDao dao = new PersonaDao("jdbc:mysql://localhost:3306/oo2proxy");

		Persona p = dao.personaPorId(2);

		System.out.println(p.nombre());

	}

}