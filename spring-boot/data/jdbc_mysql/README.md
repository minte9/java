# Java Spring-Boot Jdbc Mysql

## Schema

Create database `table schema` and add data.

~~~sql
create database db_example;
create user 'springuser'@'%' identified by 'ThePassword';
grant all on db_example.* to 'springuser'@'%';

create table users (
  user_id int unsigned NOT NULL AUTO_INCREMENT,
  email varchar(50) NOT NULL,
  username varchar(50) NOT NULL,
  PRIMARY KEY (user_id)
);

insert into users (email, username) values ('a@a.com', 'aaa');
insert into users (email, username) values ('b@b.com', 'bbb');
select * from users;
~~~

### Dependencies

Start a `Spring Web` project and add dependencies to pom.xml

~~~xml
<dependency>
	<groupId>mysql</groupId>
	<artifactId>mysql-connector-java</artifactId>
	<scope>runtime</scope>
</dependency>
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-jdbc</artifactId>
</dependency>
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-jdbc</artifactId>
</dependency>
~~~

### Config

Add `datasource` configuration properties.

~~~ini
# src/main/resources/application.properties

spring.datasource.url=jdbc:mysql://localhost:3306/db_example
spring.datasource.username=springuser
spring.datasource.password=ThePassword
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
~~~

### Application

Return `rows` from mysql database table.

https://github.com/minte9/spring-boot-pages/blob/85b5599816a027a62f6b0b77067c7b0b8d9a3cb5/jdbc_mysql/src/main/java/com/minte9/jdbc_mysql/App.java#L27-L52

### Run

Test and `run` the application.

~~~
mvn spring-boot:run

http://localhost:8080/users
    // [aaa, bbb]

http://localhost:8080/users_json
    // [{"email":"a@a.com","username":"aaa"}, ... }]
~~~

### Archive

The `.jar` file is handy for deployment because it includes all the dependencies.

~~~
mvn package

cd target/
java -jar jdbc_mysql-0.0.1.jar 

http://localhost:8080/users
    // [aaa, bbb]
~~~


### References

https://www.codejava.net/frameworks/spring-boot/connect-to-mysql-database-examples  
https://spring.io/guides/gs/relational-data-access  
https://www.twilio.com/blog/beginner-mysql-database-java-spring-boot  
https://www.codejava.net/frameworks/spring-boot/connect-to-oracle-database-examples  
https://spring.io/blog/2014/03/07/deploying-spring-boot-applications  
https://minte9.com/java/spring-boot-jdbc-mysql-1450  
