package com.geocat.harvesterclient.process;

import com.geocat.harvesterclient.response.ApiResponse;
import com.geocat.harvesterclient.response.HarvesterStatusResponse;
import com.google.gson.Gson;
import org.apache.logging.log4j.Logger;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HarvesterProcess {
    public void execute(HttpClient client, String apiEndpoint, String harvesterUrl, String harvesterName, Logger logger) throws Exception {
        String harvestData = "{\"url\":\"" + harvesterUrl + "\",\"longTermTag\":\"" + harvesterName + "\",\"lookForNestedDiscoveryService\":false}";

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(apiEndpoint + "/startHarvest"))
                .setHeader("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(harvestData))
                .build();

        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        ApiResponse harvesterResponse = new Gson().fromJson(response.body(), ApiResponse.class);

        httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(apiEndpoint + "/getstatus/" + harvesterResponse.processID))
                .setHeader("Content-Type", "application/json")
                .GET()
                .build();

        boolean harvesterFinished = false;
        while (!harvesterFinished) {
            Thread.sleep(1500);

            response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            HarvesterStatusResponse harvesterStatusResponse = new Gson().fromJson(response.body(), HarvesterStatusResponse.class);

            logger.info(harvesterStatusResponse.toString());
            logger.debug(response.body());

            harvesterFinished = (harvesterStatusResponse.state.equals("RECORDS_RECEIVED"));
        }

    }
}
