package br.com.erudio.springjavaerudio.integrationtest.swagger;

import br.com.erudio.springjavaerudio.configs.TestConfigs;
import br.com.erudio.springjavaerudio.integrationtests.testcontainers.AbstractIntegrationTest;
import static io.restassured.RestAssured.given;

// import static dispoensando colocar o endereço completo
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


// para testar se o swagger subiu com a referida aplicação dentro de um container
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SwaggerIntegrationTest extends AbstractIntegrationTest {

	@Test
	public void shouldDisplaySwaggerUiPage() {
		var content =
		given()
				.basePath("/swagger-ui/index.html")
				.port(TestConfigs.SERVER_PORT)
				.when()
					.get()
				.then()
					.statusCode(200)
				.extract()
					.body()
						.asString();
		assertTrue(content.contains("Swagger UI"));
	}

}
