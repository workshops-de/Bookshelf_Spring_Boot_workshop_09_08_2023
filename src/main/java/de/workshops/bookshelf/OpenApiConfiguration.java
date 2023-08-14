package de.workshops.bookshelf;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI openAPI(BookshelfProperties properties) {
        return new OpenAPI()
                .info(
                        new Info()
                                .title(properties.getTitle())
                                .version(properties.getVersion())
                                .description(properties.getDescription() + " with a capacity of %d book".formatted(properties.getCapacity()))
                                .license(new License()
                                        .name("MIT License")
                                        .url("https://opensource.org/licenses/MIT")
                                )
                );
    }
}
