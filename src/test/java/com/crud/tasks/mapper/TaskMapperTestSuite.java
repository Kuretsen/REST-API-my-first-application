package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TaskMapperTestSuite {
    @Autowired
    TaskMapper taskMapper;

    @Test
    void testMapToTask(){
        //Given
        TaskDto taskDto = new TaskDto(1L, "Task1", "Test content");

        //When
        Task task = taskMapper.mapToTask(taskDto);

        //Then
        assertEquals(task.getId(), taskDto.getId());
        assertEquals(task.getTitle(), taskDto.getTitle());
        assertEquals(task.getContent(), taskDto.getContent());
    }

    @Test
    void testMapToTaskDto(){
        //Given
        Task task = new Task(1L, "Task1", "Test content");

        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        //Then
        assertEquals(taskDto.getId(), task.getId());
        assertEquals(taskDto.getTitle(), task.getTitle());
        assertEquals(taskDto.getContent(), task.getContent());
    }

    @Test
    void testMapToTaskDtoList(){
        //Given
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1L, "Task1", "Test content"));
        tasks.add(new Task(2L, "Task2", "Test content 2"));

        //When
        List<TaskDto> taskDtos = taskMapper.mapToTaskDtoList(tasks);

        //Then
        assertEquals(taskDtos.size(), tasks.size());
    }
}