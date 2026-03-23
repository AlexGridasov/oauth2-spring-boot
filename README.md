# OAuth 2.0 in Spring Boot Applications

Learn to secure Spring Boot applications with the OAuth 2.0 Stack in Spring Security 5

## Topics covered

* Perform each OAuth 2 authorization flow,
  * Authorization Code,
  * PKCE-enhanced authorization code,
  * Client credentials,
  * Password credentials.
* Startup and configure the Keycloak server,
* Configure OAuth 2 Resource Server,
* Startup multiple Resource Servers on random port numbers,
* Configure Spring Cloud API Gateway,
* Configure and use Eureka Registry and Discovery Service,
* Build a simple Spring MVC Web Application that fetches data from a protected Resource Server running behind Spring Cloud API Gateway.
* Implement a simple JavaScript application that uses PKCE-Enhanced authorization code to acquire JWT access tokens and communicate with protected Resource Server,
* Refresh an expired JWT Access token,
* Implement Scope-base access control,
* Implement Role-based access control,
* OAuth social login with Facebook, Google, and Okta accounts,
* Implement Keycloak Remote User Authentication(User Storage SPI)
* Spring Authorization Server


# Local Environment

http://localhost:8070 - Keycloak Admin Console
http://localhost:8081 - Resource Server


# How to run

1. Start Keycloak server in development mode using `kc.bat start-dev` command.
2. Get `code` from Keycloak server by accessing the following URL in browser and login with `alex.developer` user and password `123qwe` - `{{KEYCLOAK_SERVER}}/realms/appsdeveloper/protocol/openid-connect/auth?client_id=...&response_type=code&scope=openid profile&redirect_uri=http://localhost:8083/callback&state=...` (`Get Code` API in Postman)
3. Exchange the `code` for `access_token` and `refresh_token` by accessing the following URL in Postman - `{{KEYCLOAK_SERVER}}/realms/appsdeveloper/protocol/openid-connect/token` (`Get Access Token` API in Postman)
4. Run the Resource Server application using `mvn spring-boot:run` command
5. Use the `access_token` (Authorization - Bearer Token) to access Resource Server API - `{{RESOURCE_SERVER}}/users/status/check` (`Users Status Check` API in Postman)


# Important Links

- Spring Boot - https://spring.io/projects/spring-boot
- Create SpringBoot project - https://start.spring.io
- Keycloak website - https://www.keycloak.org
- JWT Debugger - https://www.jwt.io


## Maven Commands

|     Maven Command       |     Description          |
| ------------- | ------------- |
| `mvn clean install -Dmaven.test.skip=true` | To generate a jar inside target folder |
| `mvn spring-boot:run` | To start a springboot maven project |


# Commands

`kc.bat start-dev` - To start Keycloak server in development mode
