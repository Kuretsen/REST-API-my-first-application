package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/tasks")
public class TaskController {

    @GetMapping
    public List<TaskDto> getTasks() {
        return new ArrayList<>() ;
    }

    @GetMapping
    public TaskDto getTask() {
        return new TaskDto(1L, "test title", "test content");
    }

    @DeleteMapping
    public void deleteTask(){

    }

    @PutMapping
    public TaskDto updateTask() {
        return new TaskDto(1L, "Edited test title", "Test content");
    }

    @PostMapping
    public void createTask() {

    }
}
