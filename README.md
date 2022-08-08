# Quarkus-couchbase-travel-sample

This project implements a CRUD API that handles airline documents from the travel-sample bucket provided by Couchbase.

## API

<table>
<tr>
<td>

**Method**
</td> 
<td> 

**URN**
</td> 
<td> 

**Request Body**
</td> 
<td>

**Output** 
</td> 
<td>

**Description** 
</td>
</tr>
<tr>
<td>POST</td><td>/airlines</td>
<td>

```json
{
  "id": 1234,
  "type": "airline",
  "name": "American Airlines",
  "iata": "AA",
  "icao": "AAL",
  "callsign": "AMERICAN",
  "country": "United States"
}
```
</td>
<td>

```json
{
  "id": 1234,
  "type": "airline",
  "name": "American Airlines",
  "iata": "AA",
  "icao": "AAL",
  "callsign": "AMERICAN",
  "country": "United States"
}
```
</td>
<td>Create an airline</td>
</tr>

<tr>
<td>GET</td><td>/airlines</td><td>-</td>
<td>

```json
[
  {
    "id": 1234,
    "type": "airline",
    "name": "American Airlines",
    "iata": "AA",
    "icao": "AAL",
    "callsign": "AMERICAN",
    "country": "United States"
  }
]
```
</td>
<td>Get all airlines</td>
</tr>

<tr>
<td>GET</td><td>/airlines/:id</td><td>-</td>
<td>

```json
{
  "id": 1234,
  "type": "airline",
  "name": "American Airlines",
  "iata": "AA",
  "icao": "AAL",
  "callsign": "AMERICAN",
  "country": "United States"
}
```
</td>
<td>Get an airline by id</td>
</tr>

<tr>
<td>PUT</td><td>/airlines/:id</td>
<td>

```json
{
  "id": 1234,
  "type": "airline",
  "name": "American Airlines",
  "iata": "AA",
  "icao": "AAL",
  "callsign": "AMERICAN",
  "country": "USA"
}
```
</td>
<td>

```json
{
  "id": 1234,
  "type": "airline",
  "name": "American Airlines",
  "iata": "AA",
  "icao": "AAL",
  "callsign": "AMERICAN",
  "country": "USA"
}
```
</td>
<td>Update an airline by id</td>
</tr>

<tr>
<td>DELETE</td><td>/airlines/:id</td><td>-</td><td>-</td>
<td>Delete an airline by id</td>
</tr>
</table>


## Licence

Code released under Apache License 2.0

## Requirements

* Install Couchbase Server ([docs](https://docs.couchbase.com/server/current/getting-started/do-a-quick-install.html#initialize-cluster-web-console))
* Install travel sample bucket ([docs](https://docs.couchbase.com/server/current/manage/manage-settings/install-sample-buckets.html#install-sample-buckets-with-the-ui))
* Set a configuration file called application.yaml with :
```yaml
couchbase:
  host: <COUCHBASE_SERVER_HOST>
  username: <COUCHBASE_SERVER_USERNAME>
  password: <COUCHBASE_SERVER_PASSWORD>
```

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -Dquarkus.config.locations=<PATH_TO_CONFIG_FILE> -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _uber-jar_, is now runnable using `java -Dquarkus.config.locations=<PATH_TO_CONFIG_FILE> -jar target/*-runner.jar`.