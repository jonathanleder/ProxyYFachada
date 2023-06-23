package main;

import java.util.Map;

import modelo.DBDefault;
import modelo.DBFacade;

public class MainE3 {

	public static void main(String[] args) {
		DBFacade bd = new DBDefault("jdbc:mysql://127.0.0.1/oo2proxy", "root", "");

		String sql = "SELECT p.nombre Nombre, t.numero Telefono FROM persona p LEFT JOIN telefono T ON (p.id = t.id_Persona) ORDER BY Nombre ASC, Telefono ASC";

		bd.open();

		var res = bd.queryResultAsAsociation(sql);
		var res2 = bd.queryResultAsArray(sql);

		int i = 0;
		for (Map<String, String> fila : res) {
			System.out.println("FILA " + (i + 1));
			var columnas = fila.keySet();
			columnas.stream().forEach(x -> System.out.println(" ".repeat(3) + x + ": " + fila.get(x)));
			i++;
		}

		System.out.println("\n************************************************************\n");

		for (String[] fila : res2) {
			for (int j = 1; j < fila.length; j++) {
				System.out.print(fila[j] + " ".repeat(2));
			}
			System.out.println();
		}

		bd.close();
	}

}