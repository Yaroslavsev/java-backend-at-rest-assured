import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.EnumSource;
import utils.ListOfIdsForGetTests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

@DisplayName("Тестирование метода GET")
@Feature("Тестирование метода GET")
public class GetImageNegativeTests extends BaseTest{

    @DisplayName("Пустой запрос на получение изображения")
    @Story("Получение несуществующего изображения")
    @Test
    @EnumSource(ListOfIdsForGetTests.class)
    void getEmptyRequestTest() {
        given()
                .spec(reqSpec)
                .when()
                .get(ListOfIdsForGetTests.EMPTY_ID.imageId)
                .prettyPeek()
                .then()
                .statusCode(400)
                .body("success", is(false))
                .body("data.error", containsString("An image ID is required for a GET request to /image"));
    }

    @DisplayName("Получение изображения c несуществующим id")
    @Story("Получение несуществующего изображения")
    @Test
    @EnumSource(ListOfIdsForGetTests.class)
    void getNotExistingImageTest() {
        given()
                .spec(reqSpec)
                .when()
                .get(ListOfIdsForGetTests.NOT_EXISTING_ID.imageId)
                .prettyPeek()
                .then()
                .statusCode(404)
                .body(containsString("imgur: the simple 404 page"));
    }

}
