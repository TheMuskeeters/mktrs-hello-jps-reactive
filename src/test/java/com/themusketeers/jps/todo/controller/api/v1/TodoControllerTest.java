/*----------------------------------------------------------------------------*/
/* Source File:   TODOCONTROLLER.JAVA                                         */
/* Copyright (c), 2024 The Musketeers                                         */
/*----------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------
 History
 Jun.24/2024  COQ  File created.
 -----------------------------------------------------------------------------*/
package com.themusketeers.jps.todo.controller.api.v1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.themusketeers.jps.todo.model.Todo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(TodoController.class)
class TodoControllerTest {
    public static final String TODO_CONTROLLER_BASE_PATH = "/api/v1/todos";

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

                assertThat(resBody).isNotNull().isNotEmpty();

            });
    }
}
