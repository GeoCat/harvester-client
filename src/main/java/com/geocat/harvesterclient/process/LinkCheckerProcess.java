package com.geocat.harvesterclient.process;

import com.geocat.harvesterclient.response.ApiResponse;
import com.geocat.harvesterclient.response.LinkCheckerStatusResponse;
import com.google.gson.Gson;
import org.apache.logging.log4j.Logger;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class LinkCheckerProcess {
    public void execute(HttpClient client, String apiEndpoint, String harvesterName, Logger logger) throws Exception {
        String linkCheckerData = "{\"longTermTag\":\"" + harvesterName + "\"}";

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(apiEndpoint + "/startLinkCheck"))
                .setHeader("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(linkCheckerData))
                .build();

        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        ApiResponse linkCheckerResponse = new Gson().fromJson(response.body(), ApiResponse.class);

        httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(apiEndpoint + "/getstatus/" + linkCheckerResponse.processID))
                .setHeader("Content-Type", "application/json")
                .GET()
                .build();

        boolean linkCheckerFinished = false;
        while (!linkCheckerFinished) {
            Thread.sleep(1500);

            response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            LinkCheckerStatusResponse linkCheckerStatusResponse = new Gson().fromJson(response.body(), LinkCheckerStatusResponse.class);

            logger.info(linkCheckerStatusResponse.toString());
            logger.debug(response.body());

            linkCheckerFinished = (linkCheckerStatusResponse.linkCheckJobState.equals("COMPLETE"));
        }
    }
}
