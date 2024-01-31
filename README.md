# Customer Management System

This is a simple Customer Management System with CRUD functionalities, built using Java Spring Boot for the backend, MySQL for the database, and HTML/CSS/JS for the frontend.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) installed
- MySQL Database Server installed
- Git installed
- Maven installed

### Clone and Run

1. Clone the repository:
```
   git clone https://github.com/yourusername/customer-management-system.git
```
2. Navigate to the project directory:
```
cd customer-management-system
```
3. Update application.properties with your MySQL database configuration:

- Set spring.datasource.username and spring.datasource.password to your MySQL username and password.   
- Set spring.datasource.url to your MySQL database URL.

4. Build and run the application:
```
    mvn clean install
    java -jar target/customer-management-system-1.0.0.jar

    Access the application in your browser:
    http://localhost:8080
```
### Functionalities
Login
- Access the application and log in with your credentials.  
        - Username: admin  
        - Password: Your-Encoded-Password (replace with the actual encoded password)

Customer List
- View a list of customers with basic details.
- Edit or delete existing customers.
- Add a new customer.

### Search
- Search for customers based on their fields and names.

### Sync with Remote API
- Click the "Sync" button on the customer list screen.
- Authenticate with the provided credentials to get a JWT token.
- Fetch customer data from the remote API.
- Update or insert customers in the local database.

### Important Note
- Ensure that you update the application.properties file with your actual MySQL database credentials.
- Provide your own login user and password for authentication with the remote API.

That's it! You are now ready to use the Customer Management System. Feel free to explore and enhance the system as needed.