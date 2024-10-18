# Spring Boot Application with Apache Camel and Amazon Corretto

This is a sample Spring Boot application that integrates Apache Camel for routing and processing. The application is built using Amazon Corretto 17 and Docker, and includes Maven for build management.

## Requirements

- [Docker](https://www.docker.com/get-started)
- [Maven](https://maven.apache.org/install.html)

## Getting Started

Follow the steps below to build and run the application in a Docker container using Amazon Corretto 17.

### 1. Clone the Repository

Clone this repository to your local machine:

```bash
git clone https://github.com/your-username/your-repo.git
cd your-repo

2. Build the Project

Use Maven to build the project and package it into a .jar file. This step is necessary before building the Docker image.

mvn clean package -DskipTests

3. Build the Docker Image

Build the Docker image using the provided Dockerfile. This will create a container image that runs the Spring Boot application with Amazon Corretto 17.

docker build -t spring-camel-app .

4. Run the Application

Run the Docker container with the built image. The application will be exposed on port 8080.

docker run -p 8080:8080 spring-camel-app

5. Access the Application

After starting the container, the application will be running on http://localhost:8080.

Project Structure

	•	src/main/java: Contains the Java source code.
	•	src/main/resources: Contains the configuration files and resources (e.g., application.properties).
	•	pom.xml: Maven configuration file with dependencies.
	•	Dockerfile: Docker configuration file for building the image.

Technologies Used

	•	Java 17 (Amazon Corretto 17): Amazon’s free, production-ready distribution of OpenJDK.
	•	Spring Boot: Framework for building Java applications.
	•	Apache Camel: Integration framework for building message routing and mediation rules.
	•	Maven: Build automation tool for Java projects.
	•	Docker: Container platform to package and run the application.

Customizations

Apache Camel Routing

In this project, Apache Camel routes are configured to process incoming requests and handle data transformations. You can modify the routes in the src/main/java package to fit your specific requirements.

Adding New Dependencies

To add new dependencies, modify the pom.xml file and rebuild the project with:

mvn clean package -DskipTests

Troubleshooting

	•	If you encounter issues with Docker, ensure that Docker is running and you have internet connectivity to download the required base images.
	•	If you experience Java-related issues, check that you are using the correct Java version (Amazon Corretto 17).

License

This project is licensed under the MIT License - see the LICENSE file for details.