# Hotel-Price-Calculator

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

There are several ways to run the Spring Boot application on your local machine. One way is to execute the `main` method in the `weisoon.hotels.HotelsApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```
$ mvn spring-boot:run
```

## Usage

After running the application locally, go to `localhost:8080/hotels/price` and append the following key-value parameters to the URL in their proper formats:

- destination_id: `int`
- check_in: `String` (YYYY-MM-DD)
- check_out: `String` (YYYY-MM-DD)
- guests: `int`
- tenant_id: `String` (A/B/C)

The output of the hotel pricing API should look something like this:

```
    [
      {
        "hotel_id": "iJhz",
        "price": 256.30
      },
      {
        "hotel_id": "f8c9",
        "price": 100.90
      },
      {
        "hotel_id": "SjyX",
        "price": 1044.20
      },
    ]
```
