package com.geocat.harvesterclient.response;

import java.util.List;

public class HarvesterStatusResponse {
    public String processID;
    public String state;

    public List<Endpoint> endpoints;

    @Override
    public String toString() {

        if ((endpoints != null) && (endpoints.size() == 1)) {
            return "harvestJobstate='" + state + '\'' + "\n" +
                    "  expectedNumberOfRecords=" + endpoints.get(0).expectedNumberOfRecords +  "\n" +
                    "  numberOfRecordsReceived=" + endpoints.get(0).numberOfRecordsReceived;
        } else {
            return "HarvesterStatusResponse{" +
                    "state='" + state + '\'' +
                    ", endpoints=" + endpoints +
                    '}';

        }
    }
}
