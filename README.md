# CSW harvester client

Application to execute a CSW harvest process, check links and ingest the metadata in a GeoNetwork metadata catalogue.

## Build

```
mvn package
```

Creates a file `target/harvester-client-jar-with-dependencies.jar` with the harvester client.


## Execute

```
java -jar harvester-client-jar-with-dependencies.jar HARVESTERNAME HARVESTERURL
```

Where:

- `HARVESTERNAME` is the name of the harvester defined in GeoNetwork metadata catalogue.
- `HARVESTERURL`: url of the CSW end-point.

Example:

```
java -jar harvester-client-jar-with-dependencies.jar MT https://msdi.data.gov.mt/geonetwork/srv/eng/csw
```


