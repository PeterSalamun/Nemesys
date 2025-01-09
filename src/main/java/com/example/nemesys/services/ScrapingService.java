package com.example.nemesys.services;


import com.example.nemesys.entity.NematodeGenus;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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
        NematodeGenus nematodeGenus = new NematodeGenus();
        Document document = Jsoup.parse(responseBody);

        String genusName = document.select("table#DetailsView2 tr:contains(Genus) td:nth-of-type(2) b").first().text();
        System.out.println("GENUS NAME: " + genusName);
        nematodeGenus.setGenusName(genusName);
        Integer cpValue = getIntegerFromDom(document.select("table#DetailsView2 tr:contains(cp_value) td:nth-of-type(2) b").first().text());
        nematodeGenus.setCp(cpValue);
        Integer feeding = getIntegerFromDom(document.select("table#DetailsView2 tr:contains(feeding) td:nth-of-type(2) b").first().text());
        nematodeGenus.setFeeding(feeding);
        Double basal =getDoubleFromDom(document.select("table#DetailsView2 tr:contains(Basal_Wtg) td:nth-of-type(2) b").first().text());
        nematodeGenus.setBasal(basal);
        Double enrich = getDoubleFromDom(document.select("table#DetailsView2 tr:contains(Enrich_Wtg) td:nth-of-type(2) b").first().text());
        nematodeGenus.setEnrichment(enrich);
        Double structure = getDoubleFromDom(document.select("table#DetailsView2 tr:contains(Structure_Wtg) td:nth-of-type(2) b").first().text());
        nematodeGenus.setStructure(structure);

        //  Functional Guild Average Parameters
        Double GenavgMass = getDoubleFromDom(document.select("table#DetailsView1 tr:contains(GenavgMass) td:nth-of-type(2) b").first().text());
        nematodeGenus.setGenavgMass(GenavgMass);
        Double GenavgCPr = getDoubleFromDom(document.select("table#DetailsView1 tr:contains(GenavgCPr) td:nth-of-type(2) b").first().text());
        nematodeGenus.setGenavgCPr(GenavgCPr);
        Double GenavgCRs = getDoubleFromDom(document.select("table#DetailsView1 tr:contains(GenavgCRs) td:nth-of-type(2) b").first().text());
        nematodeGenus.setGenavgCRs(GenavgCRs);
        Double GenavgMFP = getDoubleFromDom(document.select("table#DetailsView1 tr:contains(GenavgMFP) td:nth-of-type(2) b").first().text());
        nematodeGenus.setGenavgMFP(GenavgMFP);
        Double GenavgEFP = getDoubleFromDom(document.select("table#DetailsView1 tr:contains(GenavgEFP) td:nth-of-type(2) b").first().text());
        nematodeGenus.setGenavgEFP(GenavgEFP);
        Double GenavgSFP = getDoubleFromDom(document.select("table#DetailsView1 tr:contains(GenavgSFP) td:nth-of-type(2) b").first().text());
        nematodeGenus.setGenavgSFP(GenavgSFP);
        Double GenavgHFP = getDoubleFromDom(document.select("table#DetailsView1 tr:contains(GenavgHFP) td:nth-of-type(2) b").first().text());
        nematodeGenus.setGenavgHFP(GenavgHFP);
        Double GenavgFFP = getDoubleFromDom(document.select("table#DetailsView1 tr:contains(GenavgFFP) td:nth-of-type(2) b").first().text());
        nematodeGenus.setGenavgFFP(GenavgFFP);
        Double GenavgBFP = getDoubleFromDom(document.select("table#DetailsView1 tr:contains(GenavgBFP) td:nth-of-type(2) b").first().text());
        nematodeGenus.setGenavgBFP(GenavgBFP);
        Double GenavgPFP = getDoubleFromDom(document.select("table#DetailsView1 tr:contains(GenavgPFP) td:nth-of-type(2) b").first().text());
        nematodeGenus.setGenavgPFP(GenavgPFP);

        //  Functional Guild Standard Errors
        Double StderrMass = getDoubleFromDom(document.select("table#DetailsView3 tr:contains(StderrMass) td:nth-of-type(2) b").first().text());
        nematodeGenus.setStderrMass(StderrMass);
        Double StderrCPr = getDoubleFromDom(document.select("table#DetailsView3 tr:contains(StderrCPr) td:nth-of-type(2) b").first().text());
        nematodeGenus.setStderrCPr(StderrCPr);
        Double StderrCRs = getDoubleFromDom(document.select("table#DetailsView3 tr:contains(StderrCRs) td:nth-of-type(2) b").first().text());
        nematodeGenus.setStderrCRs(StderrCRs);
        Double StderrMFP = getDoubleFromDom(document.select("table#DetailsView3 tr:contains(StderrMFP) td:nth-of-type(2) b").first().text());
        nematodeGenus.setStderrMFP(StderrMFP);
        Double StderrEFP = getDoubleFromDom(document.select("table#DetailsView3 tr:contains(StderrEFP) td:nth-of-type(2) b").first().text());
        nematodeGenus.setStderrEFP(StderrEFP);
        Double StderrSFP = getDoubleFromDom(document.select("table#DetailsView3 tr:contains(StderrSFP) td:nth-of-type(2) b").first().text());
        nematodeGenus.setStderrSFP(StderrSFP);
        Double StderrHFP = getDoubleFromDom(document.select("table#DetailsView3 tr:contains(StderrHFP) td:nth-of-type(2) b").first().text());
        nematodeGenus.setStderrHFP(StderrHFP);
        Double StderrFFP = getDoubleFromDom(document.select("table#DetailsView3 tr:contains(StderrFFP) td:nth-of-type(2) b").first().text());
        nematodeGenus.setStderrFFP(StderrFFP);
        Double StderrBFP = getDoubleFromDom(document.select("table#DetailsView3 tr:contains(StderrBFP) td:nth-of-type(2) b").first().text());
        nematodeGenus.setStderrBFP(StderrBFP);
        Double StderrPFP = getDoubleFromDom(document.select("table#DetailsView3 tr:contains(StderrPFP) td:nth-of-type(2) b").first().text());
        nematodeGenus.setStderrPFP(StderrPFP);

        return nematodeGenus;
    }
    private Integer getIntegerFromDom(String element){
        try {
            return Integer.valueOf(element);
        } catch (Exception e) {
            System.out.println("Exception for converting dom element to integer.");
            return 0;
        }
    }

    private Double getDoubleFromDom(String element){
        try {
            return Double.valueOf(element);
        } catch (Exception e) {
            System.out.println("Exception for converting dom element to integer.");
            return 0.0;
        }
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
