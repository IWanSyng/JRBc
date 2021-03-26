# JRBc
A JAVA Bootcamp learning project, that allows teachers to deliver quizzes to students. Implemented using Spring Boot and Thymeleaf and MySQL.

## About the project
Designed for a programming Bootcamp assignment by a team of four people eager to learn and getting one step closer towards becoming software developers.
The project attempts to implements the following features:

  `username / password based authentication` allows differentiating users by their roles. Currently two roles are supported `USER` and `ADMIN`.
  `ADMIN` role is designed for teachers who use the system to create and deliver quizzes to students enrolled in a course they are managing.
  `USER` role is designed for students who enroll in a course and submit their answers to quizzes 
  
  Creation and storage of `Quizzes` containing one or more `Quiz items` that can be delivered to students to test their knowledge. Quiz items contain data
  allowing different presentation of questions and type of answers expected (e.g. single answer, multiple answers). 
  `Quiz item` can be easily extended to accomodate new requirements.
  Created quizzes can be stored and retreived from a database for later use. Quizzes submitted by students are stored as snapshots of the whole Quiz object
  including data specific to user and course during which the quiz was done. A snapshot-like approach ensures that no irreversible loss of detail occurs due to 
  chosing to store processed or incomplete data.
  

## Typical usage scenario
Registered users with `ADMIN` role create new courses and automatically become `Instructors` for the course created. Instructors can create new Quizzes and store them in a database. When an instructor initiates a knowledge check, a quiz is delivered to students enrolled in the course and the submitted answers are stored in a database. Since complete data associated with the student and the quiz they were doing is retained, it allows potentially unlimited flexibility for future data processing and analysis. 
The Quiz data supplied by the `Instructor` can be used as a reference to which data submitted by each individual student can be compared. This approach allows `Instructor` to alter their expected results after a quiz was taken (e.g. due to a typo or error) and the submitted answers can be reevaluated without the need for students to retake the quiz or manual result adjustment.

Registered users with `USER` role choose to enroll in an active course thus becoming `Students` upon which they will automatically receive a `Unique ID` which is unique in the scope of the course they are enrolled in. When notified by their instructor, students can do the quizzes and submit their answers and after the quiz retreive the snapshot for the quiz they took.

## Setup guide
1. Clone the JRBc repository:
`git clone https://github.com/IWanSyng/JRBc.git`

2. Open the project in your preferred IDE (tested on InteliJIDEA 2020.3) or use ``mvn`` to build it.

3. Install MySQL Server 8.0+ and make sure that it is running correctly. Create a database with the name of your choice.
4. Edit the ``application.properties`` file located at ``\src\main\resources`` in your project files:
```
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/<database_name>
spring.datasource.username=<user_name>
spring.datasource.password=<pasword>
```
where `<database_name>` is the name of the database you created, and `<user_name>` and `<password>` are your username and password for connecting to your MySQL database.

5. Run the Spring Boot application once and it will automatically create necessary tables in the database and populate it with required data for proper functioning. When using the system in production environment make sure you set the ``spring.jpa.hibernate.ddl-auto`` property in ``application.properties`` to
```
spring.jpa.hibernate.ddl-auto=none
```
6. A fresh configuration of the system has no Users. You can create an ADMIN user by opening the application in your web browser and clicking on `Register` button on the landing page. After an ADMIN user is created all consequitive registrations will create non-admin users and the ability to add new ADMIN users will be limited to logged in users with ADMIN role. 

Note! You can disable user registration completely by executing the following SQL query:

```UPDATE `database`.`config_params` SET `is_enabled` = b'0' WHERE (`config_param_name` = 'USER_REGISTRATION' AND config_id <> 0);```

where `database` is the name of your mysql database.

7. The system is now ready to use.

