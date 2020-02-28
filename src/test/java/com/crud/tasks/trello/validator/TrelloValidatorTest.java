package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TrelloValidatorTest {

    @InjectMocks
    private TrelloValidator trelloValidator;

    @Test
    public void validateTrelloBoardsNameDoesntExistTest() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1", "name", false));
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("1", "name", trelloLists));

        //When
        List<TrelloBoard> boards = trelloValidator.validateTrelloBoards(trelloBoards);

        //Then
        assertNotEquals("test", boards.get(0).getName());
    }
    @Test
    public void validateTrelloBoardsNameExistsEmptyListTest() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1", "name", false));
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("1", "test", trelloLists));

        //When
        List<TrelloBoard> boards = trelloValidator.validateTrelloBoards(trelloBoards);

        //Then
        assertTrue(boards.isEmpty());
    }
    @Test
    public void validateTrelloBoardsNameExistsTest() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1", "name", false));
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("1", "test", trelloLists));
        trelloBoards.add(new TrelloBoard("2", "test2", trelloLists));

        //When
        List<TrelloBoard> boards = trelloValidator.validateTrelloBoards(trelloBoards);

        //Then
        assertNotEquals("test", boards.get(0).getName());
    }
}