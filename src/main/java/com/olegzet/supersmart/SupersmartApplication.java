package com.olegzet.supersmart;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info =
@Info(
        title = "Validation of Transaction API",
        version = "1.0",
        description = "Validation of Transaction API provides with validating 3 types of items in Transaction.",
        license = @License(name = "Apache 2.0", url = "http://foo.bar"),
        contact = @Contact(url = "http://somesecretresourse.com", name = "Oleg Zorin", email = "olegzet.ua@gmail.com")
))
public class SupersmartApplication {

    public static void main(String[] args) {
        SpringApplication.run(SupersmartApplication.class, args);
    }

}
