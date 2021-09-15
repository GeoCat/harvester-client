package com.geocat.harvesterclient;


import com.geocat.harvesterclient.process.HarvesterProcess;
import com.geocat.harvesterclient.process.IngesterProcess;
import com.geocat.harvesterclient.process.LinkCheckerProcess;
import com.geocat.harvesterclient.response.LinkCheckerStatusResponse;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.http.HttpClient;

public class HarvesterClient {
    private static final Logger logger = LogManager.getLogger(HarvesterClient.class);

    public static void main(String[] args) throws Exception {
        String endPoint = "http://jrc-sandbox.geocat.live";

        if (args.length != 2) {
            System.out.println("Usage: java -jar harvester-client.jar HARVESTERNAME HARVESTERURL");
            return;
        }

        String harvesterName = args[0];
        String harvesterUrl = args[1];

        HttpClient client = HttpClient.newHttpClient();


        logger.info(String.format("Starting harvester process: name (%s), url (%s)", harvesterName, harvesterUrl));

        // Harvest process
        String harvestUrl = endPoint + ":9999/api";
        HarvesterProcess harvesterProcess = new HarvesterProcess();
        harvesterProcess.execute(client, harvestUrl, harvesterUrl, harvesterName, logger);

        logger.info("Finished harvester process");

        // LinkChecker process
        logger.info("Starting link checker process");

        String linkCheckerUrl = endPoint + ":8888/api";
        LinkCheckerProcess linkCheckerProcess = new LinkCheckerProcess();
        linkCheckerProcess.execute(client, linkCheckerUrl, harvesterName, logger);

        logger.info("Finished link checker process");


        // Ingester process
        logger.info("Starting ingester process");

        String ingesterUrl = "http://jrc-sandbox.geocat.live:10000/api";

        IngesterProcess ingesterProcess = new IngesterProcess();
        ingesterProcess.execute(client, ingesterUrl, harvesterName, logger);

        logger.info("Finished ingester process");
    }
}
