package com.crud.tasks.service;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class DbServiceTest {

    @InjectMocks
    private DbService dbService;
    @Mock
    private TaskRepository repository;

    @Test
    public void getAllTasksTest() {
        //Given
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1L, "title", "content"));

        //When
        when(repository.findAll()).thenReturn(tasks);

        //Then
        List<Task> taskList = dbService.getAllTasks();

        assertEquals(tasks.get(0).getId(), taskList.get(0).getId());
        assertEquals(tasks.get(0).getTitle(), taskList.get(0).getTitle());
        assertEquals(tasks.get(0).getContent(), taskList.get(0).getContent());
    }

    @Test
    public void saveTaskTest() {
        //Given
        Task task = new Task(1L, "title", "content");

        //When
        when(repository.save(task)).thenReturn(task);

        //Then
        Task taskDb = dbService.saveTask(task);

        assertEquals(task.getId(), taskDb.getId());
        assertEquals(task.getTitle(), taskDb.getTitle());
        assertEquals(task.getContent(), taskDb.getContent());
    }
}
