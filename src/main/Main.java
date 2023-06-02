package main;

import ejercicio1.CachedClima;
import ejercicio1.HttpClima;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		var tiempo = new CachedClima(
				new HttpClima("https://api.openweathermap.org/data/2.5/weather?q=viedma,032&lang=sp&APPID=",
						"15d720e6be9936c8e9f24977a2bdcf42"));

		long start = System.currentTimeMillis();
		var climaActual = tiempo.clima();
		long end = System.currentTimeMillis();
		System.out.println(climaActual + "\n" + (end - start) / 1000f);

		long start1 = System.currentTimeMillis();
		var climaActual1 = tiempo.clima();
		long end1 = System.currentTimeMillis();
		System.out.println((end - start) / 1000f);

		long start2 = System.currentTimeMillis();
		var climaActual2 = tiempo.clima();
		long end2 = System.currentTimeMillis();
		System.out.println((end - start) / 1000f);
	}

}
