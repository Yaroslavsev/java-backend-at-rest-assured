import dto.PostImageResponse;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static steps.CommonRequest.uploadCommonImage;

@DisplayName("Негативное тестирование метода DELETE")
@Feature("Тестирование метода DELETE")
public class DeleteImageNegativeTests extends BaseTest {

    private static String uploadedImageId;

    static ResponseSpecification postImageDeleteNegativeResSpec;

    @BeforeEach
    void setUp() {
        PostImageResponse response = uploadCommonImage();

        uploadedImageId = response.getData().getId();

        given()
                .spec(reqSpec)
                .when()
                .delete(uploadedImageId)
                .prettyPeek()
                .then()
                .statusCode(200);

        postImageDeleteNegativeResSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .expectBody("success", is(true))
                .build();
    }

    @DisplayName("Удаление только что удаленного изображения")
    @Story("Удаление несуществующего изображения")
    @Test
    void DeleteJustDeletedImageTest() {
        given()
                .spec(reqSpec)
                .when()
                .delete(uploadedImageId)
                .prettyPeek()
                .then()
                .spec(postImageDeleteNegativeResSpec);
    }

    @DisplayName("Удаление изображения с несуществующим id")
    @Story("Удаление несуществующего изображения")
    @Test
    void DeleteNotExistingImageTest() {
        given()
                .spec(reqSpec)
                .when()
                .delete(prop.getProperty("notExistingDeleteId"))
                .prettyPeek()
                .then()
                .spec(postImageDeleteNegativeResSpec);
    }

}
