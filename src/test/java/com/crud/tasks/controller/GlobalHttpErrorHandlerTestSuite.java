package com.crud.tasks.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GlobalHttpErrorHandlerTestSuite {
    @Test
    void testHandleTaskNotFoundException() {
        //Given
        GlobalHttpErrorHandler errorHandler = new GlobalHttpErrorHandler();
        TaskNotFoundException taskNotFoundException = new TaskNotFoundException();

        //When
        ResponseEntity<Object> responseEntity = errorHandler.handleTaskNotFoundException(taskNotFoundException);

        //Then
        assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertEquals(responseEntity.getBody(), "Task with given id doesn't exist");
    }
}