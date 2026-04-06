[![CodeQL](https://github.com/eggboy/springcloud-azurefunction/actions/workflows/codeql.yml/badge.svg)](https://github.com/eggboy/springcloud-azurefunction/actions/workflows/codeql.yml)
# springcloud-azurefunction
Azure Function built with Spring Cloud Function in Java 21

## Prerequisites

- JDK 21
- Maven
- [Azure Functions Core Tools](https://learn.microsoft.com/en-us/azure/azure-functions/functions-run-local) v4+

## Build

```shell
mvn clean package
```

## Run Locally

```shell
mvn azure-functions:run
```

Then use `curl` to interact with the REST API:

```shell
# Create a user
curl -X POST -H 'Content-Type:application/json' http://localhost:7072/api/AzureWebAdapter/user -d '{"userId":"1","lastName":"Doe","firstName":"John","email":"john@example.com"}'

# Get a user
curl -X GET http://localhost:7072/api/AzureWebAdapter/user/1
```

## Deploy to Azure

```shell
az login
mvn azure-functions:deploy -Pdeploy
```

You can override the default Azure resource settings with `-D` flags:

```shell
mvn azure-functions:deploy -Pdeploy \
  -DfunctionAppName=my-app \
  -DfunctionAppRegion=westus \
  -DfunctionResourceGroup=my-resource-group
```
