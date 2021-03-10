import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@DisplayName("Негативное тестирование метода POST")
@Feature("Тестирование метода POST")
public class PostImageNegativeTests extends BaseTest {

    static ResponseSpecification postImageNegativeResSpec;

    @BeforeEach
    void setUp() {

        postImageNegativeResSpec = new ResponseSpecBuilder()
                .expectStatusCode(400)
                .expectBody("success", is(false))
                .build();
    }

    @DisplayName("Отправка текста вместо изображения")
    @Story("Отправка невалидных данных вместо изображения")
    @Test
    void postTextInsteadImageTest() {
        given()
                .spec(reqSpec)
                .multiPart("image", prop.getProperty("textInsteadImage"))
                .when()
                .post()
                .prettyPeek()
                .then()
                .spec(postImageNegativeResSpec);
    }

    @DisplayName("Отправка пустой строки вместо изображения")
    @Story("Отправка невалидных данных вместо изображения")
    @Test
    void postEmptyContentInsteadImageTest() {
        given()
                .spec(reqSpec)
                .multiPart("image", "")
                .when()
                .post()
                .prettyPeek()
                .then()
                .spec(postImageNegativeResSpec);
    }

    @DisplayName("Отправка URL НЕ изображения")
    @Story("Отправка невалидных данных вместо изображения")
    @Test
    void postFileUrlInsteadImageUrlTest() {
        given()
                .spec(reqSpec)
                .multiPart("image", prop.getProperty("fileUrlInsteadImageUrl"))
                .when()
                .post()
                .prettyPeek()
                .then()
                .spec(postImageNegativeResSpec);
    }

}
