<div id="top"></div>
<div align="center">
  <h2 align="center"> n11 TalentHub Java Bootcamp</h2>
<h3 align="center">Graduate Homework</h3>
</div>

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li><a href="#about-the-project">About The Project</a></li>
    <li><a href="#built-with">Built With</a></li>
    <li><a href="#getting-started">Getting Started</a></li>
    <li><a href="#swagger-documentation">Swagger Documentation</a></li>
    <li><a href="#endpoints">EndPoints</a></li>
    <li><a href="#client-informations">Client Informations</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>

<!-- About The Project -->
## About The Project
This project is a small e-banking system.
* Existing customers can be listed, updated and deleted.
* A new customer can be registered to the system. Customer informations can be updated and deleted.
* A customer can apply for a loan with an identification number.
* A customer can see the approved loan application with his identification number and birthday information.
* You can find detailed information about the rules of the project [here](https://github.com/n11-TalentHub-Java-Bootcamp/n11-talenthub-bootcamp-graduation-project-furkanyesilyurt/blob/main/credit-application-system/2022.01.18_n11_TalentHub_Java_Spring_Bootcamp_Bitirme_Projesi_1.pdf).


<!-- Built With -->
## Built With
* [Java](https://www.java.com/tr/)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Hibernate](https://hibernate.org/)
* [JPA](https://docs.oracle.com/javaee/6/tutorial/doc/bnbpz.html)
* [PostgreSQL](https://www.postgresql.org/)
* [Lombok](https://projectlombok.org/)
* [Mapstruct](https://mapstruct.org/)


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
2. Configure application.properties files for PostgreSQL connection.
3. Run the project then try it out https://localhost:8080/swagger-ui.html
4. Send request to endpoints (The project is running on the 8080 port by defalt).

You can find the customer registration information [here](https://github.com/n11-TalentHub-Java-Bootcamp/n11-talenthub-bootcamp-graduation-project-furkanyesilyurt/blob/main/credit-application-system/src/main/resources/customer/RecommendedCustomers.txt), where you can test 5 different scenarios in the project.


<!-- Swagger Documentation -->
## Swagger Documentation
![Swagger](https://github.com/n11-TalentHub-Java-Bootcamp/n11-talenthub-bootcamp-graduation-project-furkanyesilyurt/blob/main/credit-application-system/images/swagger.PNG)
![Swagger2](https://github.com/n11-TalentHub-Java-Bootcamp/n11-talenthub-bootcamp-graduation-project-furkanyesilyurt/blob/main/credit-application-system/images/swagger2.PNG)

You can reach the Swagger Api-doc.json at [here](https://github.com/n11-TalentHub-Java-Bootcamp/n11-talenthub-bootcamp-graduation-project-furkanyesilyurt/blob/main/credit-application-system/src/main/resources/api-doc.json).


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


<!-- Client Informations -->
## Client Informations
This page welcomes you when the project is started.
![](https://github.com/n11-TalentHub-Java-Bootcamp/n11-talenthub-bootcamp-graduation-project-furkanyesilyurt/blob/main/credit-application-system-client/images/front1.PNG)

On this page you can register customers.
![](https://github.com/n11-TalentHub-Java-Bootcamp/n11-talenthub-bootcamp-graduation-project-furkanyesilyurt/blob/main/credit-application-system-client/images/front2.PNG)

On this page, you can apply for a loan with customer identification number.
![](https://github.com/n11-TalentHub-Java-Bootcamp/n11-talenthub-bootcamp-graduation-project-furkanyesilyurt/blob/main/credit-application-system-client/images/front3.PNG)

After applying for a loan, you will be greeted with this page informing you of the loan result.
![](https://github.com/n11-TalentHub-Java-Bootcamp/n11-talenthub-bootcamp-graduation-project-furkanyesilyurt/blob/main/credit-application-system-client/images/front4.PNG)

On this page you can see the approved loan application with the identification number and date of birth.
![](https://github.com/n11-TalentHub-Java-Bootcamp/n11-talenthub-bootcamp-graduation-project-furkanyesilyurt/blob/main/credit-application-system-client/images/front5.PNG)

After questioning the approved loan application, you will see this page.
![](https://github.com/n11-TalentHub-Java-Bootcamp/n11-talenthub-bootcamp-graduation-project-furkanyesilyurt/blob/main/credit-application-system-client/images/front6.PNG)


<!-- License -->
## License
Distributed under the MIT License. See LICENSE.txt for more information.


<!-- Contact -->
## Contact
Furkan YEŞİLYURT
* Mail Address: f.yesilyurt@outlook.com
* LinkedIn: [https://www.linkedin.com/in/furkanyesilyurt/](https://www.linkedin.com/in/furkanyesilyurt/)
* Project link: [https://github.com/n11-TalentHub-Java-Bootcamp/n11-talenthub-bootcamp-graduation-project-furkanyesilyurt](https://github.com/n11-TalentHub-Java-Bootcamp/n11-talenthub-bootcamp-graduation-project-furkanyesilyurt)

You can reach my other projects at [here](https://github.com/furkanyesilyurt).


<p align="right">(<a href="#top">back to top</a>)</p>
