import com.github.javafaker.Faker;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static steps.CommonRequest.uploadCommonImage;

@DisplayName("Тестирование обновления информации методом POST")
@Feature("Тестирование метода POST")
public class PostImageUpdateTests extends BaseTest {

    private static String uploadedImageId;

    static ResponseSpecification postImageUpdateResSpec;

    Faker faker = new Faker();

    @BeforeEach
    void setUp() {
        uploadedImageId = uploadCommonImage().getData().getId();

        postImageUpdateResSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .expectBody("success", is(true))
                .build();
    }

    @DisplayName("Обновление заголовка и описания изображения")
    @Story("Обновление данных об изображении")
    @Test
    void postImageParamsUpdateTest() {
        given()
                .spec(reqSpec)
                .multiPart("title", faker.rickAndMorty().character())
                .multiPart("description", faker.rickAndMorty().quote())
                .when()
                .post(uploadedImageId)
                .prettyPeek();
    }

    @DisplayName("Добавление изображения в избранное")
    @Story("Обновление данных об изображении")
    @Test
    void postImageFavoriteUpdateTest() {
        given()
                .spec(reqSpec)
                .when()
                .post("{uploadedImageId}/favorite", uploadedImageId)
                .prettyPeek()
                .then()
                .body("data", equalTo("favorited"));
    }

    @AfterEach
    void tearDown() {
        given()
                .spec(reqSpec)
                .when()
                .delete(uploadedImageId)
                .prettyPeek()
                .then()
                .statusCode(200);
    }

}
