package com.geocat.harvesterclient.response;

public class Endpoint {
    public int expectedNumberOfRecords;
    public int numberOfRecordsReceived;

    @Override
    public String toString() {
        return "Endpoint{" +
                "expectedNumberOfRecords=" + expectedNumberOfRecords +
                ", numberOfRecordsReceived=" + numberOfRecordsReceived +
                '}';
    }
}
