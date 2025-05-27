package com.danielfdez.prices;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PricesApplicationTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void test1_requestAt10On14th() throws Exception {
		mockMvc.perform(get("/api/prices")
						.param("date", "2020-06-14T10:00:00")
						.param("productId", "35455")
						.param("brandId", "1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.price").value(35.50));
	}

	@Test
	void test2_requestAt16On14th() throws Exception {
		mockMvc.perform(get("/api/prices")
						.param("date", "2020-06-14T16:00:00")
						.param("productId", "35455")
						.param("brandId", "1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.price").value(25.45));
	}

	@Test
	void test3_requestAt21On14th() throws Exception {
		mockMvc.perform(get("/api/prices")
						.param("date", "2020-06-14T21:00:00")
						.param("productId", "35455")
						.param("brandId", "1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.price").value(35.50));
	}

	@Test
	void test4_requestAt10On15th() throws Exception {
		mockMvc.perform(get("/api/prices")
						.param("date", "2020-06-15T10:00:00")
						.param("productId", "35455")
						.param("brandId", "1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.price").value(30.50));
	}

	@Test
	void test5_requestAt21On16th() throws Exception {
		mockMvc.perform(get("/api/prices")
						.param("date", "2020-06-16T21:00:00")
						.param("productId", "35455")
						.param("brandId", "1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.price").value(38.95));
	}

	@Test
	void test_noApplicablePrice() throws Exception {
		mockMvc.perform(get("/api/prices")
						.param("date", "2020-01-01T00:00:00") // fecha fuera de rango
						.param("productId", "35455")
						.param("brandId", "1"))
				.andExpect(status().isNotFound()); // o is4xxClientError dependiendo de tu diseño
	}

	@Test
	void test_missingParameter_productId() throws Exception {
		mockMvc.perform(get("/api/prices")
						.param("date", "2020-06-14T10:00:00")
						.param("brandId", "1"))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.error").value("Missing required parameter: productId"));
	}

	@Test
	void test_invalidParameterFormat_brandId() throws Exception {
		mockMvc.perform(get("/api/prices")
						.param("date", "2020-06-14T10:00:00")
						.param("productId", "35455")
						.param("brandId", "invalid"))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.error").value("Invalid request parameters. Please check your input."));
	}

	@Test
	void test_invalidDateFormat() throws Exception {
		mockMvc.perform(get("/api/prices")
						.param("date", "14-06-2020 10:00")  // formato incorrecto
						.param("productId", "35455")
						.param("brandId", "1"))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.error").value("Invalid request parameters. Please check your input."));
	}


}

