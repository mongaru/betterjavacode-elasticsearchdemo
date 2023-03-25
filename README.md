# A Spring Boot REST application with Elasticsearch integration

## Important Notice
This is a fork of the blog https://betterjavacode.com

## How to setup

- Setup local [Jdk 11] and [Gradle 8.0.2]
- Download and run [Elasticsearch 8.6.2]
- Modify the setup of Elasticsearch to remove the usage of https.
    - elasticsearch.yml: 
        - xpack.security.http.ssl:
        - enabled: false
- Restart Elasticsearch and test http://localhost:9200/ in Postman.
- Modify the class ElasticsearchClientConfiguration, to include the basic auth configuration.
- Modify the interface LogDataRepository to include the @Repository annotation.
- Modify the main class ElasticsearchdemoApplication to add the @ComponentScan(basePackages = {"com.betterjavacode.elasticsearchdemo"}) annotation.
- Modify ElasticsearchClientConfiguration to include the compatibilty headers: "application/vnd.elasticsearch+json;compatible-with=7".
- In the terminal run ./gradlew bbotrun
- Test the endpoint http://localhost:8080/v1/betterjavacode/logdata?host=abc.com to ensure the REST API and Elasticsearch are talking.

## How to test

- Run command "./gradlew clean test".

## Run with curl

curl -v --header "Content-type: application/json" \
--data '[{"id": "123","host": "abc.com","data": "2021-08-22","message": "This is a test message for elasticsearch","size": 30,"status": "Success"},{"id": "124","host": "google.com","data": "2021-08-23","message": "This is a google message for elasticsearch","size": 30,"status": "Success"}]' \
--request POST "http://localhost:8080/v1/betterjavacode/logdata/createInBulk"

curl -v --header "Content-type: application/json" \
--data '{"id": "122","host": "abc.com","date": "2021-08-15","message": "This is a test message for elasticsearch","size": 30,"status": "Success"}' \
--request POST "http://localhost:8080/v1/betterjavacode/logdata"


curl -v --header "Content-type: application/json" \
--data '[{"id":"121","host":"abc.com","date":"2021-08-21","message":"This is a test message for elasticsearch","size":30,"status":"Success"},{"id":"122","host":"abc.com","date":"2021-08-15","message":"This is a test message for elasticsearch","size":31,"status":"Success"},{"id":"123","host":"abc.com","date":"2021-08-22","message":"This is a test message for elasticsearch","size":32,"status":"Success"},{"id":"124","host":"google.com","date":"2021-08-23","message":"This is a google message for elasticsearch","size":33,"status":"Success"},{"id":"125","host":"lycos.com","date":"2021-08-24","message":"This is a lycos message for elasticsearch","size":34,"status":"Success"},{"id":"127","host":"search.yahoo.com","date":"2021-08-26","message":"This is a yahoo message for elasticsearch","size":35,"status":"Success"}]' \
--request POST "http://localhost:8080/v1/betterjavacode/logdata/createInBulk"


curl -v "http://localhost:8080/v1/betterjavacode/logdata/createInBulk"


http://localhost:9200/logdataindex/_doc/127

