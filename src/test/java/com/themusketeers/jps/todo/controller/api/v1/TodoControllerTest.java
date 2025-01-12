/*----------------------------------------------------------------------------*/
/* Source File:   TODOCONTROLLERTEST.JAVA                                     */
/* Copyright (c), 2024 The Musketeers                                         */
/*----------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------
 History
 Jun.24/2024  COQ  File created.
 -----------------------------------------------------------------------------*/
package com.themusketeers.jps.todo.controller.api.v1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.themusketeers.jps.common.config.JsonPlaceholderServiceAutoConfiguration;
import com.themusketeers.jps.todo.model.Todo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(TodoController.class)
@Import({WebClientAutoConfiguration.class, JsonPlaceholderServiceAutoConfiguration.class})
class TodoControllerTest {
    private static final String TODO_CONTROLLER_BASE_PATH = "/api/v1/todos";
    private static final String TODO_CONTROLLER_BASE_PATH_ID = "/api/v1/todos/{id}";
    private static final String TODO_TITLE = "ipsam aperiam voluptates qui";

    private static final int TODO_LIST_EXPECTED_SIZE = 200;
    private static final int TODO_ID = 200;
    private static final int TODO_USER_ID = 10;

    @Autowired
    private WebTestClient client;

    @Test
    @DisplayName("Should Retrieve TODO List")
    void shouldRetrieveTODOList() {
        client.get()
            .uri(TODO_CONTROLLER_BASE_PATH)
            .accept(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE)
            .exchange()
            .expectStatus().isOk()
            .expectBodyList(Todo.class)
            .consumeWith(response -> {
                var resBody = response.getResponseBody();

                assertThat(resBody)
                    .isNotNull()
                    .isNotEmpty()
                    .hasSize(TODO_LIST_EXPECTED_SIZE);
            });
    }

    @Test
    @DisplayName("Should Retrieve a TODO by ID")
    void shouldRetrieveTODOById() {
        var expectedTodo = buildTodo();

        client.get()
            .uri(TODO_CONTROLLER_BASE_PATH_ID, TODO_ID)
            .accept(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE)
            .exchange()
            .expectStatus().isOk()
            .expectBody(Todo.class)
            .consumeWith(response -> {
                var resBody = response.getResponseBody();

                assertThat(resBody)
                    .isNotNull()
                    .isEqualTo(expectedTodo);
            });
    }

    private Todo buildTodo() {
        return new Todo(TODO_USER_ID, TODO_ID, TODO_TITLE, false);
    }
}
