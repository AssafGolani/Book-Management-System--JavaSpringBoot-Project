# Book Management System

This is a Book Management System built using Java, Spring Boot, and PostgreSQL. The application allows you to manage books and authors, including the relationships between them. It also integrates with the Open Library API to search for books and save them to the local database.

## Features

- Manage Books and Authors
- CRUD operations for Books and Authors
- Many-to-Many relationship between Books and Authors
- Integration with Open Library API to search and save books
- Exception handling for resource not found and other errors

## Technologies Used

- Java 17
- Spring Boot 3.3.1
- PostgreSQL
- Maven
- Lombok
- MapStruct
- Open Library API

## Getting Started

### Prerequisites

- Java 17
- PostgreSQL
- Maven

### Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/your-usernameAssafGolani/book-management-system.git
   cd book-management-system

2. Configure the database:

Update the application.properties file with your PostgreSQL database connection details:

```spring.datasource.url=jdbc:postgresql://localhost:5432/bookdb
spring.datasource.username=yourUsername
spring.datasource.password=yourPassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

3. Build the project:
```
./mvnw clean install
```

4. Run the application:
```
./mvnw spring-boot:run
```


### Usage
#### API Endpoints

#### API Endpoints

##### Books

GET /api/books: Get all books
GET /api/books/{id}: Get a book by ID
POST /api/books: Add a new book
PUT /api/books/{id}: Update a book by ID
DELETE /api/books/{id}: Delete a book by ID
GET /api/books/search?title={title}: Search for books by title
GET /api/books/searchAndSave?title={title}: Search for books by title and save them to the database
Authors
GET /api/authors: Get all authors
GET /api/authors/{id}: Get an author by ID
POST /api/authors: Add a new author

#### Example Requests

##### Adding an Author

Request Type: POST
URL: http://localhost:8080/api/authors
Headers:
Content-Type: application/json
Body:
```
{
    "name": "J. R. R. Tolkien"
}
```

Adding a Book with Authors
Request Type: POST
URL: http://localhost:8080/api/books
Headers:
Content-Type: application/json
Body:
```
{
    "title": "The Lord of the Rings",
    "isbn": "9780544003415",
    "publisher": "Houghton Mifflin Harcourt",
    "year": 1954,
    "authors": [
        {
            "id": 1,
            "name": "J. R. R. Tolkien"
        }
    ]
}
```
Updating a Book
Request Type: PUT
URL: http://localhost:8080/api/books/1
Headers:
Content-Type: application/json
Body:
```
{
    "title": "The Lord of the Rings",
    "isbn": "9780544003415",
    "publisher": "Houghton Mifflin Harcourt",
    "year": 1954,
    "authors": [
        {
            "id": 1,
            "name": "J. R. R. Tolkien"
        }
    ]
}
```

### Example: Searching and Saving Books from External API
Request Type: GET
URL: http://localhost:8080/api/books/searchAndSave?title=the+lord+of+the+rings

### Exception Handling
The application uses a global exception handler to manage errors such as resource not found. Custom exceptions and error details are returned to the client with appropriate HTTP status codes.

### Contact
For any inquiries or issues, please open an issue on GitHub or contact the repository owner.



This README file provides essential information to get started with the project, including installation steps, API endpoints, example requests, and other relevant details. You can customize it further based on your specific needs and preferences.
