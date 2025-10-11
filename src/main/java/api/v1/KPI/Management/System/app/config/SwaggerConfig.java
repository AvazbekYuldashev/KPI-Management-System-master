package api.v1.KPI.Management.System.app.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        Info info = new Info()
                .title("KPI Management System API")
                .version("1.0.0")
                .description("KPI tizimi uchun API hujjatlari.")
                .contact(new Contact()
                        .name("Avazbek Yuldashev")
                        .email("bigmangcom@gmail.com")
                        .url("https://t.me/Greed_Coder"))
                .license(new License().name("Avazbek").url("https://t.me/Greed_Coder"));

        SecurityRequirement securityRequirement = new SecurityRequirement().addList("bearerAuth");
        SecurityScheme securityScheme = new SecurityScheme()
                .name("bearerAuth")
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER);

        return new OpenAPI()
                .info(info)
                .addSecurityItem(securityRequirement)
                .components(new Components().addSecuritySchemes("bearerAuth", securityScheme));
    }
}
