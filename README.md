# BTCservice

Simple REST-based btc service

+ [Prerequisites](#Prerequisites)
+ [Instruction](#Instruction)
+ [Structure](#Structure)
+ [API](#API)
+ [Extras](#Extras)


## Prerequisites

+ Java 11 preinstalled
+ IntelijIDEA
+ Docker Toolbox or fimiliar docker platform
+ WEB browser or REST client (Postman, Insomnia, etc.)

## Instruction

1. Pull git project or download and unzip project btcwallet.zip
2. Launch Intelij in project folder
3. Launch docker on local machine
4. Launch docker command ```docker-compose -f postgre.yml up -d``` - Your local postgre instance. postgre.yml is in the project
5. Another docker command in the same shell ```docker-machine ls``` - Your local docker-machine IP in URL column
6. In project application.properties file change  192.168.99.100 to Your local docker-machine IP from p.5
7. Launch Spring Boot project
8. Enjoy

## Structure

Standard Spring Boot based REST service:

1 Controller for 2 operations - MainController.java
Global Exception Handler for any exception stuff - GlobalExceptionHandler.java
Custom Exception for date period validation - InvalidDateFrameException.java

Standard Repositories and Services for models.


## API

+ Create record
URL: http://localhost:8080/record
Method: POST
Example:
Request
```
{
    "dateTime": "2060-01-01T01:01:05+04:00",
    "amount": 25
}
```

Response
```
{
    "transaction_id": 38
}
```
+ Get Balance History
URL: http://localhost:8080/records
Method: POST
Example:
Request
```
{
    "startDatetime": "2020-06-17T10:48:01+00:00",
     "endDatetime": "2020-07-19T18:48:02+00:00"
}
```

Response
```
[
    {
        "dateTime": "2020-07-19T10:00:00+02:00",
        "amount": 141.02
    },
    {
        "dateTime": "2020-07-19T03:00:00+02:00",
        "amount": 146.02
    },
    {
        "dateTime": "2020-07-01T23:00:00+02:00",
        "amount": 122.01
    },
    {
        "dateTime": "2020-06-30T23:00:00+02:00",
        "amount": 122.0
    },
    {
        "dateTime": "2020-06-19T23:00:00+02:00",
        "amount": 80.0
    },
    {
        "dateTime": "2020-06-17T23:00:00+02:00",
        "amount": 0.0
    }
]
```

http://localhost:8080/swagger-ui.html - Swagger Description of REST methods

## Extras
+ application.properties has config to log to file or change logging format
    ```
    # output to a file
    #logging.file.name=app.log

    # temp folder example
    #logging.file=${java.io.tmpdir}/app.log

    #logging.pattern.file=%d %p %c{1.} [%t] %m%n
    #logging.pattern.console=%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n
    ```
    By default it's just console logging

+ application.properties has timezone overconfiguration if docker ignores yaml config for db
+ Record model reused for "/records" answer. That's why I used custom getter with Json annnotations.
+ "/records" implemented with Pageable interface for future extensibility
+ Paging metadata removed for clean response 
    ```
    Page<Record> temp = historyRepository.findByDataFrame(dataframe.getStartDatetime(),dataframe.getEndDatetime(), pageable);
    return temp.getContent();
    ```
