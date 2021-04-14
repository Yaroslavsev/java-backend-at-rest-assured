import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.EnumSource;
import utils.ListOfIdsForGetTests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@DisplayName("Тестирование метода GET")
@Feature("Тестирование метода GET")
public class GetImagePositiveTests extends BaseTest{

    @DisplayName("Получение существующего изображения")
    @Story("Получение изображения")
    @Test
    @EnumSource(ListOfIdsForGetTests.class)
    void getImageTest() {
        given()
                .spec(reqSpec)
                .when()
                .get(ListOfIdsForGetTests.VALID_ID.imageId)
                .prettyPeek()
                .then()
                .body("data.id", is(ListOfIdsForGetTests.VALID_ID.imageId))
                .statusCode(200)
                .body("success", is(true));
    }

}
