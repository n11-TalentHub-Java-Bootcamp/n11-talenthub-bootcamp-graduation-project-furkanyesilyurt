<div id="top"></div>
<div align="center">
  <h2 align="center"> n11 TalentHub Java Bootcamp</h2>
<h3 align="center">Graduate Homework</h3>
</div>



<!-- About The Project -->
## About The Project
This project is a small e-banking system.
* Existing customers can be listed, updated and deleted.
* A new customer can be registered to the system. Customer informations can be updated and deleted.
* A customer can apply for a loan with an identification number.
* A customer can see the approved loan application with his identification number and birthday information.
* You can find detailed information about the rules of the project [here](https://github.com/n11-TalentHub-Java-Bootcamp/n11-talenthub-bootcamp-graduation-project-furkanyesilyurt/blob/main/credit-application-system/2022.01.18_n11_TalentHub_Java_Spring_Bootcamp_Bitirme_Projesi_1.pdf).
___________________________________________________________________
<!-- Built With -->
## Built With
* [Java](https://www.java.com/tr/)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Hibernate](https://hibernate.org/)
* [JPA](https://docs.oracle.com/javaee/6/tutorial/doc/bnbpz.html)
* [PostgreSQL](https://www.postgresql.org/)
* [Lombok](https://projectlombok.org/)
* [Mapstruct](https://mapstruct.org/)
___________________________________________________________________
<!-- Getting Started -->
## Getting Started
### Prerequisites
* PostgreSql
* Java 11 or newer
### Installation
1. Clone the repo
 ```sh
   git clone https://github.com/n11-TalentHub-Java-Bootcamp/n11-talenthub-bootcamp-graduation-project-furkanyesilyurt.git
   ```
2. Configure application.settings files for PostgreSQL connection.
3. Run the project then try it out https://localhost:8080/swagger-ui.html
4. Send request to endpoints (The project is running on the 8080 port by defalt).
___________________________________________________________________
<!-- Swagger Documentation -->
## Swagger Documentation
![Swagger](https://github.com/n11-TalentHub-Java-Bootcamp/n11-talenthub-bootcamp-graduation-project-furkanyesilyurt/blob/main/credit-application-system/images/swagger.PNG)
![Swagger2](https://github.com/n11-TalentHub-Java-Bootcamp/n11-talenthub-bootcamp-graduation-project-furkanyesilyurt/blob/main/credit-application-system/images/swagger2.PNG)

You can reach the Swagger Api-doc.json at [here]()https://github.com/n11-TalentHub-Java-Bootcamp/n11-talenthub-bootcamp-graduation-project-furkanyesilyurt/blob/main/credit-application-system/src/main/resources/api-doc.json.
___________________________________________________________________
<!-- EndPoints -->
## EndPoints
[Customer Controller](https://github.com/n11-TalentHub-Java-Bootcamp/n11-talenthub-bootcamp-graduation-project-furkanyesilyurt/blob/main/credit-application-system/src/main/java/com/furkanyesilyurt/creditapplicationsystem/controller/CustomerController.java)
| Method | URI               | Request Body                                                 | Description       |
| ------ | ----------------- | ------------------------------------------------------------ | ----------------- |
| GET    | /api/v1/customers |                                                              | Get all customers |
| POST   | /api/v1/customers | {<br/>  "identityNo": "10735264974",<br/>  "firstname": "furkan",<br/>  "lastname": "yesilyurt",<br/>  "monthlyIncome": 4500,<br/>  "phone": "5386057030",<br/>  "birthday": "1998-11-25T01:34:38.657Z",<br/>  "guarantee": 10000<br/>} | Save a customer   |
| PUT    | /api/v1/customers | {<br/>  "identityNo": "10735264974",<br/>  "firstname": "furkan",<br/>  "lastname": "yesilyurt",<br/>  "monthlyIncome": 4500,<br/>  "phone": "5386057030",<br/>  "birthday": "1998-11-25T01:34:38.657Z",<br/>  "guarantee": 10000<br/>} | Update a customer |
| DELETE | /api/v1/customers |                                                              | Delete a customer |

[Loan Controller](https://github.com/n11-TalentHub-Java-Bootcamp/n11-talenthub-bootcamp-graduation-project-furkanyesilyurt/blob/main/credit-application-system/src/main/java/com/furkanyesilyurt/creditapplicationsystem/controller/LoanController.java)
| Method | URI                           | Description                       |
| ------ | ----------------------------- | --------------------------------- |
| GET    | /api/v1/loans                 | Apply for a loan with identity no |
| GET    | /api/v1/loans/approved-credit | See aprovved loan application     |

___________________________________________________________________
<!-- License -->
## License
Distributed under the MIT License. See LICENSE.txt for more information.
___________________________________________________________________
<!-- Contact -->
## Contact
Furkan YEŞİLYURT
* Mail Address: f.yesilyurt@outlook.com
* LinkedIn: [https://www.linkedin.com/in/furkanyesilyurt/](https://www.linkedin.com/in/furkanyesilyurt/)
* Project link: [https://github.com/n11-TalentHub-Java-Bootcamp/n11-talenthub-bootcamp-graduation-project-furkanyesilyurt](https://github.com/n11-TalentHub-Java-Bootcamp/n11-talenthub-bootcamp-graduation-project-furkanyesilyurt)

You can reach my other projects at [here](https://github.com/furkanyesilyurt).
___________________________________________________________________


