package ejercicio1;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class HttpClima implements Clima {

	private String url;
	private String clima;

	public HttpClima(String url, String apiKey) {
		this.url = url + apiKey;// "https://api.openweathermap.org/data/2.5/weather?q=viedma,032&lang=sp&APPID="
								// apiKey= "15d720e6be9936c8e9f24977a2bdcf42"
		try {
			HttpClient httpClient = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(this.url)).build();
			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			String jsonString = response.body();
			Gson gson = new GsonBuilder().create();
			var jsonObject = gson.fromJson(jsonString, JsonObject.class);
			this.clima = jsonObject.getAsJsonArray("weather").asList().get(0).getAsJsonObject().get("description")
					.toString();
		} catch (Exception e) {

			new RuntimeException("No se pudo conectar con el servicio");
		}
	}

	@Override
	public String clima() {
		return this.clima;
	}

}
