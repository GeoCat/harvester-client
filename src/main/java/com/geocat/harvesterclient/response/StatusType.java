package com.geocat.harvesterclient.response;

public class StatusType {
    public String statusType;
    public int nDocuments;

    @Override
    public String toString() {
        return "  '" + statusType + "\': " +
                "nDocuments=" + nDocuments;
    }
}
