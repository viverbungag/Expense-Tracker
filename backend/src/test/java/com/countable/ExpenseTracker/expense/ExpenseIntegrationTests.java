package com.countable.ExpenseTracker.expense;


import com.countable.ExpenseTracker.expense.dto.CreateExpenseDto;
import com.countable.ExpenseTracker.expense.dto.TotalPerWeekExpenseDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = {"/cleanup.sql", "/test-data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class ExpenseIntegrationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private HttpHeaders headers = new HttpHeaders();
    private String endpointUrl;

    @BeforeEach
    public void setup() {
        headers = new HttpHeaders();
        endpointUrl = "http://localhost:" + port + "/expenses";
    }

    @Test
    public void display_all_expenses() {
        HttpEntity request = new HttpEntity<>(headers);
        ResponseEntity<List> response = restTemplate.exchange(endpointUrl, HttpMethod.GET, request, List.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        List<Expense> expenses = response.getBody();
        assertThat(expenses.size()).isEqualTo(3);
    }

    @Test
    public void create_expense() {
        CreateExpenseDto createExpenseDto = new CreateExpenseDto("New Expense", LocalDate.parse("2024-06-14"), 100F);
        HttpEntity<CreateExpenseDto> request = new HttpEntity<>(createExpenseDto, headers);
        ResponseEntity<Void> response = restTemplate.exchange(endpointUrl, HttpMethod.POST, request, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        // Verify that the new expense is added
        HttpEntity requestGet = new HttpEntity<>(headers);
        ResponseEntity<List> responseGet = restTemplate.exchange(endpointUrl, HttpMethod.GET, requestGet, List.class);
        List<Expense> expenses = responseGet.getBody();
        assertThat(expenses.size()).isEqualTo(4);
    }

    @Test
    public void display_all_expenses_with_description_search_filter() {
        HttpEntity request = new HttpEntity<>(headers);
        ResponseEntity<List> response = restTemplate.exchange(endpointUrl + "?searchDescription=cof", HttpMethod.GET, request, List.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        List<Expense> expenses = response.getBody();
        assertThat(expenses.size()).isEqualTo(2);
    }

    @Test
    public void display_total_amount_per_week() throws ClassNotFoundException {
        HttpEntity request = new HttpEntity<>(headers);
        ResponseEntity<List> response = restTemplate.exchange(endpointUrl + "/totalAmountPerWeek/2024", HttpMethod.GET, request, List.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        List<Object> totalPerWeekExpenseDtos = response.getBody();
        assertThat(totalPerWeekExpenseDtos.size()).isEqualTo(1);
    }
}
