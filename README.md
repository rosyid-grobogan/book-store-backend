# Book Store Project - Backend


## pom.xml
```
<!-- https://mvnrepository.com/artifact/com.google.code.gson/gson-->
<dependency>
<groupId>com.google.code.gson</groupId>
<artifactId>gson</artifactId>
</dependency>

<!-- https://mvnrepository.com/artifact/io.springfox/springfox-swagger2 -->
<dependency>
<groupId>io.springfox</groupId>
<artifactId>springfox-swagger2</artifactId>
<version>2.9.2</version>
</dependency>

<!-- https://mvnrepository.com/artifact/io.springfox/springfox-swagger-ui -->
<dependency>
<groupId>io.springfox</groupId>
<artifactId>springfox-swagger-ui</artifactId>
<version>2.9.2</version>
</dependency>

<!--https://mvnrepository.com/artifact/org.apache.commons/commons-lang3 -->
<dependency>
<groupId>org.apache.commons</groupId>
<artifactId>commons-lang3</artifactId>
</dependency>
```

## application.properties
```
# Database Config
spring.datasource.url=jdbc:postgresql://localhost:5432/project_book_store 
spring.datasource.username=postgres
spring.datasource.password=postgres

# Hibernate Config
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.default_schema=public
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.enable_lazy_load_no_trans=true
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

```dbn-sql
    create table public.categories (
       id  serial not null,
        created_by varchar(50),
        created_time timestamp,
        status varchar(50),
        updated_by varchar(50),
        updated_time timestamp,
        code varchar(50),
        name varchar(100),
        primary key (id)
    )
```