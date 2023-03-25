package com.betterjavacode.elasticsearchdemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.List;

import com.betterjavacode.elasticsearchdemo.ElasticsearchdemoApplication;
// import org.junit.After;
// import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = ElasticsearchdemoApplication.class)
@AutoConfigureMockMvc 
class ElasticsearchdemoApplicationTests {

	// @Test
	// void contextLoads() {
	// }
	@Autowired
    private MockMvc mvc;

	@Test
	public void givenEmployees_whenGetEmployees_thenReturnJsonArray() throws Exception {
		
		// Employee alex = new Employee("alex");

		// List<Employee> allEmployees = Arrays.asList(alex);

		// given(service.getAllEmployees()).willReturn(allEmployees);

		// mvc.perform(get("/api/employees")
		// .contentType(MediaType.APPLICATION_JSON))
		// .andExpect(status().isOk())
		// .andExpect(jsonPath("$", hasSize(1)))
		// .andExpect(jsonPath("$[0].name", is(alex.getName())));

		mvc.perform(get("/v1/betterjavacode/logdata/search?term=this&orderby=date&sort=asc")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content()
		.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$[0].name", is("bob")));
	}
}
