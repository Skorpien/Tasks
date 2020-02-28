package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTest {
    @Autowired
    TaskMapper taskMapper;

    @Test
    public void mapToTaskTest() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "title", "content");

        //When
        Task task = taskMapper.mapToTask(taskDto);

        //Then
        Assert.assertEquals(taskDto.getId(), task.getId());
        Assert.assertEquals(taskDto.getTitle(), task.getTitle());
        Assert.assertEquals(taskDto.getContent(), task.getContent());
    }

    @Test
    public void mapToTaskDtoTest() {
        //Given
        Task task = new Task(1L, "title", "content");

        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        //Then
        Assert.assertEquals(task.getId(), taskDto.getId());
        Assert.assertEquals(task.getTitle(), taskDto.getTitle());
        Assert.assertEquals(task.getContent(), taskDto.getContent());
    }

    @Test
    public void mapToTaskDtoListTest() {
        //Given
        List<Task> taskList  = new ArrayList<>();
        taskList.add(new Task(1L, "title", "content"));

        //When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);

        //Then
        Assert.assertEquals(taskList.get(0).getId(), taskDtoList.get(0).getId());
        Assert.assertEquals(taskList.get(0).getTitle(), taskDtoList.get(0).getTitle());
        Assert.assertEquals(taskList.get(0).getContent(), taskDtoList.get(0).getContent());
    }
}