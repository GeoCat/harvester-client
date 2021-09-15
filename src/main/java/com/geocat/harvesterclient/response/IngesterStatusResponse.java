package com.geocat.harvesterclient.response;

public class IngesterStatusResponse {
    public String processID;
    public String state;

    public int totalRecords;
    public int numberOfRecordsIngested;
    public int numberOfRecordsIndexed;

    @Override
    public String toString() {
        return "ingesterJobstate='" + state + '\'' + "\n" +
                "  totalRecords=" + totalRecords + "\n" +
                "  numberOfRecordsIngested=" + numberOfRecordsIngested + "\n" +
                "  numberOfRecordsIndexed=" + numberOfRecordsIndexed;
    }
}
