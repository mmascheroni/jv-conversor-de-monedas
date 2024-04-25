package com.conversor;

import com.conversor.exceptions.ErrorMonedaBaseNoExiste;
import com.conversor.exceptions.ErrorMonedaBuscadaNoExiste;
import com.conversor.models.Moneda;
import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

@SpringBootApplication
public class ConversorApplication {

	public static void main(String[] args) throws IOException, InterruptedException {

		try {
			Scanner lectura = new Scanner(System.in);
			System.out.println("Ingrese la moneda base: ");
			String busqueda = lectura.nextLine();

			String url = "https://v6.exchangerate-api.com/v6/d65727d9adf1de01c9b6a10e/latest/" + busqueda;

			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create(url))
					.build();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

			String json = response.body();

			if (response.statusCode() != 200) {
				throw new ErrorMonedaBaseNoExiste("El codigo de pais de Moneda Base ingresado no existe");
			} else {
				Gson gson = new Gson();

				Moneda moneda = gson.fromJson(json, Moneda.class);

				System.out.println("Ingrese la moneda buscada: ");
				String busquedaMoneda = lectura.nextLine();

				moneda.setConversionMonedaBuscada(busquedaMoneda);

				if ( moneda.obtenerConversion(busquedaMoneda) == null ) {
					throw new ErrorMonedaBuscadaNoExiste("El codigo de pais de Moneda buscada para conversion no existe");
				} else {
					moneda.setConversion(moneda.obtenerConversion(busquedaMoneda));

					System.out.println(moneda);
					System.out.println("Moneda Base con el código de moneda del pais: " + moneda.getMonedaBase());
					System.out.println("Moneda Buscada con el código de moneda del pais: " + moneda.getConversionMonedaBuscada());
					System.out.println("Moneda Buscada Conversion: $" + moneda.getConversion());
				}
			}

		} catch (ErrorMonedaBaseNoExiste e) {
			System.out.println(e.getMensaje());
		} catch (ErrorMonedaBuscadaNoExiste e) {
			System.out.println(e.getMensaje());
		}





//		SpringApplication.run(ConversorApplication.class, args);
	}

}
