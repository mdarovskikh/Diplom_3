package api;

import com.google.gson.Gson;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * Клиент для работы с API пользователей
 * Используется в тестах для:
 *  - создания пользователя до теста
 *  - удаления пользователя после теста (очистка данных)
 */
public class UserApiClient {
    private static final String BASE_URL = "https://stellarburgers.education-services.ru";
    private final Gson gson = new Gson();

    /**
     * Создаёт пользователя через API.
     *
     * @return accessToken созданного пользователя (нужен для удаления)
     *         или null, если регистрация не удалась
     */
    public String createUser(String email, String password, String name) {
        Map<String, String> body = new HashMap<>();
        body.put("email", email);
        body.put("password", password);
        body.put("name", name);

        Response response = given()
                .baseUri(BASE_URL)
                .contentType("application/json")
                .body(gson.toJson(body))
                .when()
                .post("/api/auth/register");

        if (response.statusCode() == 200) {
            return response.then().extract().path("accessToken");
        }
        return null;
    }
    /**
     * Удаляет пользователя по accessToken.
     */
    public void deleteUser(String accessToken) {
        if (accessToken == null) {
            return;
        }
        given()
                .baseUri(BASE_URL)
                .contentType("application/json")
                .header("Authorization", accessToken)
                .when()
                .delete("/api/auth/user");
    }
}
