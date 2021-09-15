package com.geocat.harvesterclient.process;

import com.geocat.harvesterclient.response.ApiResponse;
import com.geocat.harvesterclient.response.IngesterStatusResponse;
import com.google.gson.Gson;
import org.apache.logging.log4j.Logger;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class IngesterProcess {
    public void execute(HttpClient client, String apiEndpoint, String harvesterName, Logger logger) throws Exception {
        String ingesterData = "{\"longTermTag\":\"" + harvesterName + "\"}";

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(apiEndpoint + "/startIngest"))
                .setHeader("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(ingesterData))
                .build();

        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        ApiResponse ingesterResponse = new Gson().fromJson(response.body(), ApiResponse.class);

        httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(apiEndpoint + "/getstatus/" + ingesterResponse.processID))
                .setHeader("Content-Type", "application/json")
                .GET()
                .build();

        boolean ingesterFinished = false;
        while (!ingesterFinished) {
            Thread.sleep(1500);

            response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            IngesterStatusResponse ingesterStatusResponse = new Gson().fromJson(response.body(), IngesterStatusResponse.class);

            logger.info(ingesterStatusResponse.toString());
            logger.debug(response.body());

            ingesterFinished = (ingesterStatusResponse.state.equals("RECORDS_PROCESSED"));
        }

    }
}
