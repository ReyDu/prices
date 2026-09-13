package com.jjimenez.prices.infrastructure.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.jjimenez.prices.domain.model.Brand;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerIT {

  @Autowired
  private MockMvc mockMvc;

  @ParameterizedTest(name = "Test price: Fecha {0} -> Esperado priceList {1} con precio {2}")
  @CsvSource({
      "2020-06-14T10:00:00, 1, 35.50", // Test 1: Aplica tarifa 1 (prioridad 0)
      "2020-06-14T16:00:00, 2, 25.45", // Test 2: Aplica tarifa 2 (prioridad 1 desempata)
      "2020-06-14T21:00:00, 1, 35.50", // Test 3: Aplica tarifa 1 (la 2 ya ha caducado)
      "2020-06-15T10:00:00, 3, 30.50", // Test 4: Aplica tarifa 3 (prioridad 1 desempata)
      "2020-06-16T21:00:00, 4, 38.95"  // Test 5: Aplica tarifa 4 (prioridad 1 desempata)
  })
  void whenRequestedAtDifferentDatesThenReturnCorrectPrice(
      String applicationDate,
      int expectedPriceList,
      double expectedPrice) throws Exception {

    // Arrange
    int brandId = Brand.ZARA.getId(); // ZARA
    long productId = 35455;

    // Act & Assert
    mockMvc.perform(get("/brands/{brandId}/products/{productId}/prices", brandId, productId)
            .param("applicationDate", applicationDate)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.productId").value(productId))
        .andExpect(jsonPath("$.brandId").value(brandId))
        .andExpect(jsonPath("$.priceList").value(expectedPriceList))
        .andExpect(jsonPath("$.price").value(expectedPrice));
  }
}