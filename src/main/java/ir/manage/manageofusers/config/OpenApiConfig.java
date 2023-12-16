package ir.manage.manageofusers.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.*;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author F_Babaei
 * @Date 12/13/2023
 */
@SecurityScheme(
        name = "security_auth", type = SecuritySchemeType.OAUTH2,
        flows = @OAuthFlows(
                password = @OAuthFlow(
                        authorizationUrl = "${springdoc.oAuthFlow.authorizationUrl}",
                        tokenUrl = "${springdoc.oAuthFlow.tokenUrl}"


                )))
@Configuration
@OpenAPIDefinition(info = @Info(title = "Apply Default Global SecurityScheme in springdoc-openapi", version = "1.0.0"), security = { @SecurityRequirement(name = "security_auth") })
public class OpenApiConfig {

    @Bean
    public OpenAPI getOpenApiDocumentation() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info().title("apiTitle")
                        .description("apiDescription")
                        .version("apiVersion")
                        .contact(new Contact()
                                .name("apiContactName")
                                .url("apiContactUrl")
                                .email("apiContactEmail"))
                        .termsOfService("apiTermsOfService")
                        .license(new License()
                                .name("apiLicense")
                                .url("apiLicenseUrl")))
                .externalDocs(new ExternalDocumentation()
                        .description("apiExternalDocDesc")
                        .url("apiExternalDocUrl"));
    }

}
