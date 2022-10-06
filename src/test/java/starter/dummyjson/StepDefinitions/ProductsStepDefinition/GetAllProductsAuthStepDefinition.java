package starter.dummyjson.StepDefinitions.ProductsStepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import org.hamcrest.Matchers;
import starter.dummyjson.API.ProductsAPI.GetAllProductsAuthApi;
import starter.dummyjson.DummyjsonResponses.ProductsResponses;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

public class GetAllProductsAuthStepDefinition {
    @Steps
    GetAllProductsAuthApi getAuth;
    @Given("Get all product with token and parameter {string}")
    public void get_all_product_with_token_and_parameter(String parameter) {
        getAuth.getAllProductsAuth(parameter);
    }
    @When("Send request get all product with token")
    public void send_request_get_all_product_with_token() {
        SerenityRest.when().get(GetAllProductsAuthApi.AUTH_PARAMETER);
    }
    @Given("Get all product with invalid token and parameter {string}")
    public void getAllProductWithInvalidTokenAndParameter(String parameter) {
        getAuth.getAllProductsInvalidAuth(parameter);
    }
    @Then("Should return {int} Unauthorized or {int} Bad Servers status code")
    public void shouldReturnUnauthorizedOrBadServersStatusCode(int statusPrime, int statusSecondary) {
        SerenityRest.then().statusCode(anyOf(is(statusPrime), is(statusSecondary)));
    }

    @And("Should return message contain {string} token or signature")
    public void shouldReturnMessageContainTokenSignature(String message) {
        SerenityRest.then().assertThat().body(ProductsResponses.MESSAGE_NOT_FOUND, Matchers.containsString(message));
    }

    @Given("Get all product with expired token and parameter {string}")
    public void getAllProductWithExpiredTokenAndParameter(String parameter) {
        getAuth.getAllProductsExpiredAuth(parameter);
    }
}
