# Online Learning Platform

This project is an Online Learning Platform designed to offer users a seamless experience for course browsing, registration, and enrollment. It includes features like course display, user authentication, and service integration to manage courses and enrollments efficiently. The system architecture is built using microservices with communication between services handled by OpenFeign. User authentication and authorization are managed using Keycloak, and a custom Keycloak provider integrates an external MongoDB database for user data management.

## Features
- **User Registration & Authentication**: Users can register and authenticate via Keycloak. A custom login page is implemented for a streamlined user experience.
- **Course Display & Enrollment**: Users can view available courses and enroll in them. Enrollments are tracked, and users can view their registered courses.
- **Microservice Architecture**:
  - **course-service**: Handles course-related operations such as listing available courses.
  - **enrollment-service**: Manages user enrollments and tracks user-course relations.
  - **Service Communication**: Implemented via OpenFeign to facilitate communication between services.
- **Keycloak Integration**:
  - Custom **Keycloak provider** for user management with an external MongoDB database.
  - Custom **login page** for enhanced user experience during authentication.
- **Service Discovery**: A Discovery Server (using Netflix Eureka) is implemented to dynamically manage service discovery.
- **Nginx as Reverse Proxy**: Configured Nginx as a reverse proxy for better load balancing and security.
- **Containerized with Docker**: The entire platform is containerized using Docker, ensuring consistent environments and simplified deployment.

## Technologies Used
- **Java** (Backend services)
- **Spring Boot** (Microservices)
- **Thymeleaf** (Frontend for course display and user enrollment)
- **OpenFeign** (Inter-service communication)
- **Keycloak** (Authentication and authorization)
- **MongoDB** (External user database)
- **Nginx** (Reverse proxy)
- **Docker** (Containerization)
- **RESTful APIs** (For communication between frontend and backend services)

## Setup and Installation
1. **Clone the repository**:
```
git clone https://github.com/your-username/online-learning-platform.git
cd online-learning-platform
```
2. **Start Keycloak and configure MongoDB**:
- Set up **Keycloak** and create the custom provider for MongoDB.
- Configure realms, clients, and roles in Keycloak.
3. **Run the application** using Docker:
```
docker-compose up
```
4. **Access the platform**:
- The frontend can be accessed at ```http://localhost:8080```.
- Keycloak's admin console will be available at ```http://localhost:8081```.

## How It Works
1. **User Registration & Login**: Users register and log in through Keycloak.
2. **Course Browsing**: The frontend (powered by Thymeleaf) displays available courses to the user.
3. **User Enrollment**: Users can enroll in courses, which triggers the enrollment-service to record the registration.
4. **Keycloak & MongoDB Integration**: User data is stored and managed in an external MongoDB database, integrated through a custom Keycloak provider.
5. **Nginx Reverse Proxy**: All requests go through Nginx, which acts as a reverse proxy for load balancing and security.
