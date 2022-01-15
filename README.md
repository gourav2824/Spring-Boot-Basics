# Spring Boot Basics

## This project will cover the following concepts :
* How to add http requests and http responses
* How to write unit tests for the controller using mockMvc
* How to write integration tests using RestTemplate

## MySQL using Docker

### Setup
* docker pull mysql
* docker run -p 3306:3306 --name <container-name> -e MYSQL_ROOT_PASSWORD=<password-to-be-set> -d mysql:tag

### Usage
* docker exec -it <container-name> mysql -uroot -p
* Enter the password
