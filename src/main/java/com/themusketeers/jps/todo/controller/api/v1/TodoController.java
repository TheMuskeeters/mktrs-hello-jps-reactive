/*----------------------------------------------------------------------------*/
/* Source File:   TODOCONTROLLER.JAVA                                         */
/* Copyright (c), 2024 The Musketeers                                         */
/*----------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------
 History
 Apr.19/2024  COQ  File created.
 -----------------------------------------------------------------------------*/
package com.themusketeers.jps.todo.controller.api.v1;

import com.themusketeers.jps.todo.JPSTodoClient;
import com.themusketeers.jps.todo.model.Todo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Exposes endpoints to consume JSON PlaceHolder service.
 *
 * @author COQ - Carlos Adolfo Ortiz Q.
 */
@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {
    public static final String USER_CONTROLLER_BASE_PATH = "/api/v1/users";

    private final JPSTodoClient jpsTodoClient;

    /**
     * Constructor with parameters.
     *
     * @param jpsTodoClient Reference to the Rest Client API consumer to JsSON PlaceHolder Service.
     */
    public TodoController(JPSTodoClient jpsTodoClient) {
        this.jpsTodoClient = jpsTodoClient;
    }

    /**
     * Retrieves a list of {@link Todo} items.
     * GET /api/v1/todos
     *
     * @return List of items
     */
    @GetMapping
    public Flux<Todo> findAll() {
        return jpsTodoClient.findAll();
    }

    /**
     * Find a {@link Todo} record using an 'id'. If record is not found an HTTP 400 is returned.
     *
     * @param id Indicates the identifier we want to locate.
     * @return The requested information.
     */
    @GetMapping("{id}")
    public Mono<Todo> retrieveById(@PathVariable Integer id) {
        return jpsTodoClient.findById(id);
    }
}
