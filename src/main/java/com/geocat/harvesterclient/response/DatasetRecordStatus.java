package com.geocat.harvesterclient.response;

import java.util.List;

public class DatasetRecordStatus {
    public String documentType;
    public int nTotalDocuments;

    public List<StatusType> statusTypes;

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();

        text.append("  nTotalDocuments=" + nTotalDocuments + "\n");

        statusTypes.forEach(s -> {text.append(s + "\n");});

        return text.toString();
    }
}
