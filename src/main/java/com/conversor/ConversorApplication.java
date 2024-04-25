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
			System.out.println("Ingrese el código de pais de la moneda base: ");
			String busqueda = lectura.nextLine();

			String url = "https://v6.exchangerate-api.com/v6/d65727d9adf1de01c9b6a10e/latest/" + busqueda;

			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create(url))
					.build();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

			String json = response.body();

			if (response.statusCode() != 200) {
				throw new ErrorMonedaBaseNoExiste("El código de pais de la moneda base no existe!");
			} else {
				Gson gson = new Gson();

				Moneda moneda = gson.fromJson(json, Moneda.class);

				System.out.println("Ingrese el código de pais de la moneda a cual se quiere convertir: ");
				String busquedaMoneda = lectura.nextLine();

				moneda.setConversionMonedaBuscada(busquedaMoneda);

				if ( moneda.obtenerConversion(busquedaMoneda) == null ) {
					throw new ErrorMonedaBuscadaNoExiste("El código de pais de moneda a cual se quiere convertir no existe");
				} else {
					moneda.setConversion(moneda.obtenerConversion(busquedaMoneda));

					System.out.println(moneda);
					System.out.println("Código de Pais de la Moneda base: " + moneda.getMonedaBase());
					System.out.println("Código de Pais de la Moneda a cual se quiere convertir: " + moneda.getConversionMonedaBuscada());
					System.out.println("Conversion: $" + moneda.getConversion());

					System.out.println("Ingrese el importe que desea convertir");
					Float importeAConversion = lectura.nextFloat();

					System.out.println("La conversión de $" + importeAConversion + " " + moneda.getMonedaBase() + " es igual a $" + importeAConversion * moneda.getConversion() + " " + moneda.getConversionMonedaBuscada());
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
