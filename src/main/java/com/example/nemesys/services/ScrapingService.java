package com.example.nemesys.services;


import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class ScrapingService {

    private final HttpClient httpClient;
    private final String url = "http://nemaplex.ucdavis.edu/Ecology/EcophysiologyParms/GenusParmsResult.aspx";

    public ScrapingService(){
        this.httpClient = HttpClient.newHttpClient();
    }

    public String fetchWebReponse(){
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .method("POST", HttpRequest.BodyPublishers.ofString("DropDownList3=Dorylaimus"))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            return response.body();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch web response from " + url, e);
        }
    }
}
