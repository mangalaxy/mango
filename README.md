# mango

[![CircleCI](https://circleci.com/gh/mangalaxy/mango/tree/develop.svg?style=svg)](https://circleci.com/gh/mangalaxy/mango/tree/develop)


Mango is a recruitment platform for talents and employers

## Getting Started
By following these instructions you will understand how to run a project in development mode.

### Prerequisites
Before running the project on the local machine, make sure that the necessary software is installed on it:
* JDK 1.8
* Intellij IDEA 2019.1 or above
* PostgreSQL 10.7

## Local Development
### Running frontend locally
In the ``frontend`` directory, you can run:
```
npm start
```
Runs the app in the development mode.
Open http://localhost:3000 to view it in the browser.

The page will reload if you make edits.
You will also see any lint errors in the console.
```
npm test
```
Launches the test runner in the interactive watch mode.
See the section about running tests for more information.
```
npm run build
```
Builds the app for production to the build folder.
It correctly bundles React in production mode and optimizes the build for the best performance.

The build is minified and the filenames include the hashes.
Your app is ready to be deployed!

### Running backend locally
To build and run Spring Boot locally
```
mvn spring-boot:run
```
You can now access the app on http://localhost:9000

### Build deployable package
To build a deployable artifact
```
mvn package
```
The self contained jar will be created in ``${project.basedir}/target``

To run the jar
```
java -jar target/mango-1.0.0-SNAPSHOT.jar
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

### License
This project is licensed under the MIT License - see the ``LICENSE.md`` file for details
