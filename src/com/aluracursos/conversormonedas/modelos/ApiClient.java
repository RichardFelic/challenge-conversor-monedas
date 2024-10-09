package com.aluracursos.conversormonedas.modelos;

import com.google.gson.JsonObject;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ApiClient {
    private static final String API_KEY = "bf038383ba6aec4726987afb";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY
            + "/latest/";

    // Cache para almacenar tasas de conversion
    private static Map<String, Map<String, Double>> cache = new HashMap<>();

    // Metodo para obtener las tasas de conversion
    public static double obtenerTasaConversion(String base, String objetivo) throws Exception {
        // Para verificar si ya esta en el cache
        if (cache.containsKey(base) && cache.get(base).containsKey(objetivo)){
            return cache.get(base).get(objetivo);
        }

        // Si no esta en cache entonces que solicite el ISO
        String urlStr = BASE_URL + base;
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        conn.disconnect();

        // Parsear la respuesta Json
        JSONObject json = new JSONObject(content.toString());
        JSONObject tasasConversion = json.getJSONObject("conversion_rates");

        //Almacenar cache
        Map<String, Double> conversiones = new HashMap<>();
        for (String key : tasasConversion.keySet()){
            conversiones.put(key, tasasConversion.getDouble(key));
        }
        cache.put(base, conversiones);

        // retorna la tasa de conversion solicitada
        return conversiones.getOrDefault(objetivo, 0.0);
    }
}
