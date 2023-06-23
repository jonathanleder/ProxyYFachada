package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DBDefault implements DBFacade {
	private String url;
	private String user;
	private String password;
	private Connection conexion;
	private boolean estadoConexion;

	public DBDefault(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
		this.estadoConexion = false;
	}

	@Override
	public void open() {
		try {
			if (conexionAbierta()) {
				throw new RuntimeException("Ya hay una conexion a una Base de Datos abierta");
			}
			this.conexion = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public List<Map<String, String>> queryResultAsAsociation(String sql) {
		var tabla = new ArrayList<Map<String, String>>();
		Map<String, String> fila;

		try (PreparedStatement statement = this.conexion.prepareStatement(sql);) {
			ResultSet result = statement.executeQuery();
			var titulos = result.getMetaData();
			while (result.next()) {
				fila = new HashMap<>();
				for (int i = 1; i <= titulos.getColumnCount(); i++) {
					Object contenidoCelda = result.getObject(i);
					String celda = (contenidoCelda == null) ? "" : contenidoCelda.toString();
					fila.put(titulos.getColumnName(i), celda);
				}
				tabla.add(fila);
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return tabla;
	}

	@Override
	public List<String[]> queryResultAsArray(String sql) {
		List<String[]> tabla = new LinkedList<>();
		String[] fila;

		try (PreparedStatement statement = this.conexion.prepareStatement(sql);) {

			var result = statement.executeQuery();
			var cantidadColumnas = result.getMetaData().getColumnCount();

			while (result.next()) {

				fila = new String[cantidadColumnas + 1];

				for (int i = 1; i <= cantidadColumnas; i++) {
					Object contenidoCelda = result.getObject(i);
					String celda = (contenidoCelda == null) ? "" : contenidoCelda.toString();
					fila[i] = celda;
				}

				tabla.add(fila);

			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

		return tabla;
	}

	@Override
	public void close() {
		try {
			if (conexionAbierta()) {
				throw new RuntimeException("La conexion a la base de datos se encuentra cerrada");
			}

			this.conexion.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private boolean conexionAbierta() {
		if (!this.estadoConexion) {
			return false;
		}

		return true;
	}

}
