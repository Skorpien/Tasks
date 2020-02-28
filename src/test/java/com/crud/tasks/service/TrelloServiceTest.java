package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {

    @InjectMocks
    private TrelloService trelloService;
    @Mock
    private TrelloClient trelloClient;
    @Mock
    private SimpleEmailService emailService;
    @Mock
    private AdminConfig adminConfig;

    @Test
    public void shouldfetchTrelloBoards() {
        //Given
        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloListDto("1", "test_list", false));
        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoardDto("1", "test", trelloLists));

        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoards);

        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloService.fetchTrelloBoards();

        //Then
        assertEquals(trelloBoards.get(0).getId(), trelloBoardDtos.get(0).getId());
        assertEquals(trelloBoards.get(0).getName(), trelloBoardDtos.get(0).getName());
        assertEquals(trelloBoards.get(0).getLists(), trelloBoardDtos.get(0).getLists());
    }
    @Test
    public void shouldCreateCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("nameDto", "descDto", "posDto", "listIdDto");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("id", "mame", "shortUrl", null);

        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdTrelloCardDto);

        //When
        CreatedTrelloCardDto trelloCardDtos = trelloService.createTrelloCard(trelloCardDto);

        //Then
        assertNotNull(trelloCardDtos);
        assertEquals(createdTrelloCardDto.getId(), trelloCardDtos.getId());
        assertEquals(createdTrelloCardDto.getName(), trelloCardDtos.getName());
        assertEquals(createdTrelloCardDto.getShortUrl(), trelloCardDtos.getShortUrl());
    }
}