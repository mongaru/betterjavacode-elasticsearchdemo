# A Spring Boot REST application with Elasticsearch integration

## Important Notice
This is a fork of the blog https://betterjavacode.com

## How to setup



Please check the endpoints provided by the service and find those responsible for:
1 - Indexing new documents into ElasticSearch.

To create new document we can use the endpoint: "POST /v1/betterjavacode/logdata"

2 - Searching for documents into the index:

We can use two endpoints to search for documents:
- "GET /v1/betterjavacode/logdata?host="
- "GET /v1/betterjavacode/logdata/search?term="

3 - What is the curl command needed to index the documents above using the SpringBoot service? 

'''
curl -v --header "Content-type: application/json" \
--data '[{"id":"121","host":"abc.com","date":"2021-08-21","message":"This is a test message for elasticsearch","size":30,"status":"Success"},{"id":"122","host":"abc.com","date":"2021-08-15","message":"This is a test message for elasticsearch","size":31,"status":"Success"},{"id":"123","host":"abc.com","date":"2021-08-22","message":"This is a test message for elasticsearch","size":32,"status":"Success"},{"id":"124","host":"google.com","date":"2021-08-23","message":"This is a google message for elasticsearch","size":33,"status":"Success"},{"id":"125","host":"lycos.com","date":"2021-08-24","message":"This is a lycos message for elasticsearch","size":34,"status":"Success"},{"id":"127","host":"search.yahoo.com","date":"2021-08-26","message":"This is a yahoo message for elasticsearch","size":35,"status":"Success"}]' \
--request POST "http://localhost:8080/v1/betterjavacode/logdata/createInBulk"
'''

4 - What is the curl command needed to search for documents?

curl -v "http://localhost:8080/v1/betterjavacode/logdata/search?term=this&orderby=date&sort=asc"

5 - What are the controller actions triggered by the REST calls above (filename, method)?

- For the endpoint "/v1/betterjavacode/logdata/createInBulk", the file is "LogDataController.java" and method "addLogDataInBulk".
- For the endpoint "/v1/betterjavacode/logdata/search", the file is "LogDataController.java" and method "searchLogDataByTerm".

6 - Please share the diff file with the changes needed in order to provide this new feature. Optional: share unit tests validating the new feature.

- The modifications to add the sorting feature include:
    - Include the parameters to the method "searchLogDataByTerm" to be able to receive them from the URL.
    - Modify the "LogDataService" class to add the parameters to the method "findBySearchTerm".
    - Modify the "LogDataRepository" interface to make sure the SpringBoot magic methods allow the usage of the Sort object.
    - Add the logic to manage the asc/desc type of sorting in the Service class "LogDataService".

- Added a unit test to ensure the filtering works:
    - ElasticsearchdemoApplicationTests

Notes
- I included more entries to data to test different filters.
- The Elasticsearch service does not like to use the text and id properties to sort the results, the error is: "ElasticsearchException: Elasticsearch exception [type=illegal_argument_exception, reason=Fielddata is disabled on [status] in [logdataindex]. Text fields are not optimised for operations that require per-document field data like aggregations and sorting, so these operations are disabled by default."

