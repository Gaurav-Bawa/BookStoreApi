# BookStoreApi
This sample project is created using Spring Boot + H2 DB. This project will perform below operation - 
- CRUD operations on Books
- Checkout operation for single or multiple books which will return the total payable amount
- Checkout method will take list of books as parameters plus optional promotion code and return total price after discount
- Promotion/Discounts is variant according to book type/classification, ex: fiction books may have 10% discount while comic books have 0% discount.

Deliverables
- Sample Postman collection to access all the services https://www.getpostman.com/collections/13cfa774a1e856dd70a8
- Once the application is started, you can view the OpenApi Spec from http://localhost:8080/swagger-ui.html
- Once the application is started, you can access the H2 DB from http://localhost:8080/h2-ui/
- Docker Pull command docker pull gauravbawa061090/book-api:book-store-api
