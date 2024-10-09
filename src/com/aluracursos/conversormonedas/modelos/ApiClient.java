package com.aluracursos.conversormonedas.modelos;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ApiClient {
    private static final String API_KEY = "bf038383ba6aec4726987afb";  // Clave de API para acceso
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";

    // Cache para almacenar tasas de conversión
    private static Map<String, Map<String, Double>> cache = new HashMap<>();

    // Metodo para obtener la tasa de conversión entre dos monedas
    public static double obtenerTasaConversion(String base, String objetivo) throws Exception {
        // Verificar si ya está en el caché
        if (cache.containsKey(base) && cache.get(base).containsKey(objetivo)) {
            return cache.get(base).get(objetivo);
        }

        // Si no está en caché, solicitar la tasa de conversión a la API
        String urlStr = BASE_URL + base;
        HttpURLConnection conn = (HttpURLConnection) new URL(urlStr).openConnection();
        conn.setRequestMethod("GET");

        // Leer la respuesta de la API
        StringBuilder content = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
        }
        conn.disconnect();

        // Parsear la respuesta JSON
        JSONObject json = new JSONObject(content.toString());
        JSONObject tasasConversion = json.getJSONObject("conversion_rates");

        // Almacenar en caché
        Map<String, Double> conversiones = new HashMap<>();
        for (String key : tasasConversion.keySet()) {
            conversiones.put(key, tasasConversion.getDouble(key));
        }
        cache.put(base, conversiones);

        // Retornar la tasa de conversión solicitada
        return conversiones.getOrDefault(objetivo, 0.0);
    }
}
