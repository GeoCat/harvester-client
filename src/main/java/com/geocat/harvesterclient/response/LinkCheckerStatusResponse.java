package com.geocat.harvesterclient.response;

public class LinkCheckerStatusResponse {
    public String processID;
    public String linkCheckJobState;
    public ServiceRecordStatus serviceRecordStatus;
    public DatasetRecordStatus datasetRecordStatus;

    @Override
    public String toString() {


        return "linkCheckJobState='" + linkCheckJobState + '\'' + "\n" +
                "serviceRecordStatus:\n" + serviceRecordStatus + "\n" +
                "datasetRecordStatus:\n" + datasetRecordStatus;
    }
}
