# Minesweeper – Console Game (Java)

A simple command-line version of the classic Minesweeper game implemented in Java.


## Requirements

- Java 17 or newer
- Maven 3.6+

## Quick Start

## Build & Test Commands
# Run all unit tests
mvn clean test

# Run single test class
mvn test -Dtest=GameBoardTest

# Run single test method
mvn test -Dtest=GameBoardTest#reveal_sameCellTwice_returnsFalseSecondTime

# Create JAR file
mvn clean install

### Run the game on localhost 

```bash

# build JAR and run
mvn clean install
java -jar target/brian-0.0.1-SNAPSHOT.jar


### Run the game on server
mvn clean install
nohup java -jar target/brian-0.0.1-SNAPSHOT.jar &

### Check if service running : ps aux| grep java
