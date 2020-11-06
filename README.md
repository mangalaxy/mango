# mango

[![CircleCI](https://circleci.com/gh/mangalaxy/mango.svg?style=svg)](https://circleci.com/gh/mangalaxy/mango)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=mangalaxy_mango&metric=alert_status)](https://sonarcloud.io/dashboard?id=mangalaxy_mango)
[![codecov](https://codecov.io/gh/mangalaxy/mango/branch/develop/graph/badge.svg)](https://codecov.io/gh/mangalaxy/mango)


Mango is a recruitment platform for talents and employers

## Getting Started
By following these instructions you will understand how to run a project in development mode.

### Prerequisites
Before running the project on the local machine, make sure that the necessary software is installed on it:
* JDK 1.8
* Intellij IDEA 2019.1 or above
* PostgreSQL 10.7

## Local Development

### How to run web server on the local machine
To build and run Spring Boot on the local machine perform this
```
mvn spring-boot:run
```
You can now access the app on http://localhost:9000

### Build deployable package
To build a deployable artifact
```
mvn clean package
```
The self contained jar will be created in ``${project.basedir}/target``

To run the jar
```
java -jar target/mango-1.x.x.jar
```
You can now access the production-ready app on http://localhost:9000

## Build with
* [Spring Boot](https://spring.io/projects/spring-boot) - Starter for IOC container.
* [Maven](https://maven.apache.org/) - Dependency management.
* [PostgreSQL](https://www.postgresql.org/about/) - open source object-relational database system.

### Versioning
We use [Git](https://git-scm.com/about) for versioning.

### Authors
* **Yuri Podolsky** - Initial work - Fullstack Engineer
* **Alex Kopin** - Frontend Developer
* **Nick Blanchuk** - Fullstack Engineer

### License
This project is licensed under the MIT License - see the ``LICENSE.md`` file for details
