ForismaticClient is a command line Forismatic API client.
It is in package com.hlee.sandbox.rest.forismatic;

ForismaticClient does the following:

1. Operates on the command line
2. Accepts a language as an argument (either English or Russian)
3. Fetches results from the forismatic.com quote api (http://forismatic.com/en/api/)
4. Displays to stdout the quote and author

Unit test class: ForismaticClientTest

To build the project from command line: 
- This project uses Java 17
- cd to project root directory
- mvn clean package

To run the main class from command line:
- cd to project root directory
- java -jar target/sandbox-0.0.1-SNAPSHOT.jar English (or Russian)
