package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class DbServiceTestSuite {
    @Autowired
    DbService dbService;
    @Test
    void testGetAllTasks(){
        //Given
        Task task = new Task("Title", "Content");

        //When
        dbService.saveTask(task);
        List<Task> tasks = dbService.getAllTasks();
        Long id = task.getId();

        //Then
        assertEquals(4, tasks.size());

        //Cleanup
        dbService.deleteTask(id);
    }

    @Test
    void testSaveTask() throws TaskNotFoundException {
        //Given
        Task task = new Task("Test Title", "Test Content");

        //When
        dbService.saveTask(task);
        Long id = task.getId();

        //Then
        assertNotNull(dbService.getTask(id));

        //Cleanup
        dbService.deleteTask(id);
    }
}