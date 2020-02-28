package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
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
public class TrelloMapperTest {

    @Autowired
    TrelloMapper trelloMapper;

    @Test
    public void mapToBoardsTest() {
        //Given
        List<TrelloListDto> trelloListDto = new ArrayList<>();
        trelloListDto.add(new TrelloListDto("1", "list", false));
        List<TrelloBoardDto> trelloBoardDto = new ArrayList<>();
        trelloBoardDto.add(new TrelloBoardDto("1", "boardDto", trelloListDto));

        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardDto);

        //Then
        Assert.assertEquals("1", trelloBoards.get(0).getId());
        Assert.assertEquals("boardDto", trelloBoards.get(0).getName());
        Assert.assertEquals("1", trelloBoards.get(0).getLists().get(0).getId());
        Assert.assertEquals("list", trelloBoards.get(0).getLists().get(0).getName());
        Assert.assertEquals(false, trelloBoards.get(0).getLists().get(0).isClosed());
    }

    @Test
    public void mapToBoardsDtoTest() {
        //Given
        List<TrelloList> trelloList = new ArrayList<>();
        trelloList.add(new TrelloList("1", "list", false));
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("1", "board", trelloList));

        //When
        List<TrelloBoardDto> trelloBoardsDto = trelloMapper.mapToBoardsDto(trelloBoards);
        System.out.println(trelloBoardsDto.get(0).getId());

        //Then
        Assert.assertEquals("1", trelloBoardsDto.get(0).getId());
        Assert.assertEquals("board", trelloBoardsDto.get(0).getName());
        Assert.assertEquals("1", trelloBoardsDto.get(0).getLists().get(0).getId());
        Assert.assertEquals("list", trelloBoardsDto.get(0).getLists().get(0).getName());
        Assert.assertEquals(false, trelloBoardsDto.get(0).getLists().get(0).isClosed());
    }

    @Test
    public void mapToListTest() {
        //Given
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(new TrelloListDto("1", "ListDto", false));

        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtos);

        //Then
        Assert.assertEquals("1", trelloLists.get(0).getId());
        Assert.assertEquals("ListDto", trelloLists.get(0).getName());
        Assert.assertEquals(false, trelloLists.get(0).isClosed());
    }

    @Test
    public void mapToListDtoTest() {
        //Given
        List<TrelloList> trelloList = new ArrayList<>();
        trelloList.add(new TrelloList("1", "List", false));

        //When
        List<TrelloListDto> trelloLists = trelloMapper.mapToListDto(trelloList);

        //Then
        Assert.assertEquals("1", trelloLists.get(0).getId());
        Assert.assertEquals("List", trelloLists.get(0).getName());
        Assert.assertEquals(false, trelloLists.get(0).isClosed());
    }

    @Test
    public void maptToCardDtoTest() {
        //Given
        TrelloCard trelloCard = new TrelloCard("testCard", "testCardDesc", "testCardPos", "1");

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        Assert.assertEquals("testCard", trelloCardDto.getName());
        Assert.assertEquals("testCardDesc", trelloCardDto.getDescription());
        Assert.assertEquals("testCardPos", trelloCardDto.getPos());
        Assert.assertEquals("1", trelloCardDto.getListId());
    }

    @Test
    public void mapToCardTest() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("card", "desc", "pos", "1");

        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        Assert.assertEquals("card", trelloCard.getName());
        Assert.assertEquals("desc", trelloCard.getDescription());
        Assert.assertEquals("pos", trelloCard.getPos());
        Assert.assertEquals("1", trelloCard.getListId());
    }
}