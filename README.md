# The Calculator

### How to run backend

Specify how to connect to the postgres DB via command line arguments. (e.g. `-db localhost:5432/se-calculator -u se-calculator-user`)
Use `-db test` to enable in-memory H2 DB instance as a stand-in.

### Backend API

* `/calculate`
    Expects a JSON payload of the following form:
  ```json
    {"expression": "1+1"}
  ```
  Responds with result on success or an error:
  ```json
    {"result": 2.0}
  ```
  ```json
    {"message": "aboba"}
  ```
  
  Sample request:
  `curl 0.0.0.0:8080/calculate -d '{"expression":"1+1"}' -H "Content-Type: application/json" -v`

* `/history`
  Optional query parameters:
  * `limit=integer from 1 to 100`

  Response format:
  ```json
  {"history":[{"stringExpression":"1+1","result":2.0}]}
  ```
    
   Sample request:
   `curl 0.0.0.0:8080/history -v`
    
#### Useful links
[For those who always forget how to create new local postgres db](https://stackoverflow.com/questions/30641512/create-database-from-command-line)