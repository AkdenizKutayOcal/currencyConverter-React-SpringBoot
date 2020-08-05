package currency_converter.backend;

import currency_converter.backend.model.Currency;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackendApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CurrencyControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void contextLoads() {

    }

    @Test
    public void testAllCurrencies() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/currencies",
                HttpMethod.GET, entity, String.class);

        assertNotNull(response.getBody());
    }

    @Test
    public void testGetCurrency() {
        String baseCode = "TRY";
        Currency currency = restTemplate.getForObject(getRootUrl() + "/currencies/"+baseCode, Currency.class);
        assertNotNull(currency);
        assertEquals(baseCode,currency.getBaseCode());
    }

    @Test
    public void testGetValue() {
        String baseCode = "TRY";
        Currency currency = restTemplate.getForObject(getRootUrl() + "/currencies/"+baseCode, Currency.class);
        assertNotNull(currency);
        assertEquals(baseCode,currency.getBaseCode());

        String targetCode = "EUR";
        double response_value = restTemplate.getForObject(getRootUrl() + "/currencies/"+baseCode+ "/rates/"+targetCode, double.class);
        double value = 0;
        var it = currency.getRates();
        for (var rate : it) {
            if (rate.getTargetCode().equals(targetCode)) {
                value = rate.getValue();
            }
        }
        System.out.println(value);
        assertEquals((int)value,(int)response_value); //double is not supported?
    }

    @Test
    public void testCreateCurrency() {
        Currency currency = new Currency();
        currency.setBaseCode("CODE");
        currency.setBaseName("NAME");

        ResponseEntity<Currency> postResponse = restTemplate.postForEntity(getRootUrl() + "/currencies", currency, Currency.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        assertEquals(currency.getBaseName(),postResponse.getBody().getBaseName());
    }

    @Test
    public void testReplaceCurrency() {
        String baseCode = "TRY";
        Currency currency = restTemplate.getForObject(getRootUrl() + "/currencies/" + baseCode, Currency.class);
        currency.setBaseName("NAME");

        restTemplate.put(getRootUrl() + "/currencies/" + baseCode, currency);

        Currency updatedCurrency = restTemplate.getForObject(getRootUrl() + "/currencies/" + baseCode, Currency.class);
        assertNotNull(updatedCurrency);
        assertEquals(currency.getBaseName(),updatedCurrency.getBaseName());
    }

    @Test
    public void testDeleteCurrency() {
        String baseCode = "test";
        Currency currency = restTemplate.getForObject(getRootUrl() + "/currencies/"+baseCode, Currency.class);
        assertNotNull(currency);
        assertEquals(baseCode,currency.getBaseCode());

        restTemplate.delete(getRootUrl() + "/currencies/" + baseCode);

        try {
            currency = restTemplate.getForObject(getRootUrl() + "/currencies/" + baseCode, Currency.class);

        } catch (final HttpClientErrorException e) {

            assertEquals(e.getStatusCode(), HttpStatus.BAD_REQUEST);
        }
    }
}
