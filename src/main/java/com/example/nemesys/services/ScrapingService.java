package com.example.nemesys.services;


import com.example.nemesys.entity.NematodeGenus;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ScrapingService {

    private final HttpClient httpClient;
    private final String url = "http://nemaplex.ucdavis.edu/Ecology/EcophysiologyParms/GenusParmsResult.aspx";

    public ScrapingService() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public List<NematodeGenus> updateNematodesList() {
        try {
            List<String> nematodes = getResourceFileAsString();
            List<NematodeGenus> nematodeGenusList = new ArrayList<>();

            for (String nematode : nematodes) {
                String responseBody = fetchWebResponse(nematode);
                nematodeGenusList.add(parseResponseBody(responseBody));
            }

            return nematodeGenusList;

        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch web response from " + url, e);
        }
    }

    private NematodeGenus parseResponseBody(String responseBody){


        return null;
    }

    private String fetchWebResponse(String nematodeName) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .method("POST", HttpRequest.BodyPublishers.ofString(String.format("DropDownList3=%s", nematodeName)))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();

        }catch (Exception e) {
            throw new RuntimeException("Failed to fetch web response from " + url, e);
        }
    }

    private List<String> getResourceFileAsString() {
        try (InputStream inputStream = getClass().getResourceAsStream("/data/Nematode.txt")) {
            String nematodes = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            List<String> namesList = Arrays.stream(nematodes.split("\n")).toList();

            return namesList;
        } catch (NullPointerException e) {
            throw new RuntimeException("NullPointerException", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
