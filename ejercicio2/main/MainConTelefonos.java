package main;

import modelo.Persona;
import modelo.PersonaDao;
import modelo.Telefono;

public class MainConTelefonos {

	public static void main(String[] args) throws Exception {
		PersonaDao dao = new PersonaDao("jdbc:mysql://localhost:3306/oo2proxy");

		Persona p = dao.personaPorId(1);

		System.out.println(p.nombre());

		for (Telefono telefono : p.telefonos()) {
			System.out.println(telefono);
		}

	}

}