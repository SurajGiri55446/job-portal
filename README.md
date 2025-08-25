#  my application.properties


spring.application.name=job

# Database config
spring.datasource.url=UserUrl
spring.datasource.username=UserName
spring.datasource.password=UserPassword
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Hibernate / JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# Thymeleaf
spring.thymeleaf.cache=false
spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html
spring.thymeleaf.mode=HTML
spring.thymeleaf.encoding=UTF-8
spring.thymeleaf.enabled=true

#
logging.level.org.springframework.security=DEBUG
logging.level.com.jobportal.job=DEBUG
