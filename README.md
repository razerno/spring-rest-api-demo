# Spring REST API Demo [![Build Status](https://travis-ci.org/razerno/spring-rest-api-demo.svg?branch=master)](https://travis-ci.org/razerno/spring-rest-api-demo)
A simple Java application built using Spring Boot serving a RESTful API with which to add and maintain offers. The build is managed using Gradle and includes a pre-built wrapper.

The API provides an /offers endpoint to modify offers. The following methods are accepted:

- `GET /offers` - Retrieves a list of all offers
- `GET /offers/{id}` - Retrieves the offer with the specified id
- `POST /offers` - Adds a new offer
- `PUT /offers/{id}` - Updates the offer with the specified id
- `DELETE /offers/{id}` - Deletes the offer with the specified id

POST and PUT requests are called with the offer information in the request body as JSON. For example:
```
{
  "description": "A simple offer",
  "price": 50,
  "currency": "GBP"
}
```
IDs are assigned to the offers dynamically by the controller in sequential order, allowing for multiple identical offers to be created.

As a simplification, offers are held in an ArrayList housed in the service layer. In practice, this could be abstracted to a repository a further layer down to detach the service logic from the data.
