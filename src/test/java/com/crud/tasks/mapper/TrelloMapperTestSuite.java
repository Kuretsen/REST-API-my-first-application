package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TrelloMapperTestSuite {

    @Autowired
    TrelloMapper trelloMapper;

    @Test
    void testMapToBoards(){
        //Given
        List<TrelloListDto> trelloListDto = new ArrayList<>();
        trelloListDto.add(new TrelloListDto("1", "List", false));
        List<TrelloBoardDto> trelloBoardDto = new ArrayList<>();
        trelloBoardDto.add(new TrelloBoardDto("1", "board",trelloListDto));

        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardDto);

        //Then
        assertEquals(trelloBoards.get(0).getId(), trelloBoardDto.get(0).getId());
        assertEquals(trelloBoards.get(0).getName(), trelloBoardDto.get(0).getName());
        assertEquals(trelloBoards.size(), trelloBoardDto.size());
    }

    @Test
    void testMapToBoardDto(){
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1", "List1", false));
        trelloLists.add(new TrelloList("2", "List2", false));
        trelloLists.add(new TrelloList("3", "List3", true));
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("2", "Board1", trelloLists));

        //When
        List<TrelloBoardDto> trelloBoardDto = trelloMapper.mapToBoardDto(trelloBoards);

        //Then
        assertEquals(trelloBoardDto.get(0).getId(), trelloBoards.get(0).getId());
        assertEquals(trelloBoardDto.get(0).getName(), trelloBoards.get(0).getName());
        assertEquals(trelloBoardDto.size(), trelloBoards.size());
    }

    @Test
    void mapToList(){
        //Given
        List<TrelloListDto> trelloListDto = new ArrayList<>();
        trelloListDto.add(new TrelloListDto("1", "List", false));

        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDto);

        //Then
        assertEquals(trelloLists.get(0).getId(), trelloListDto.get(0).getId());
        assertEquals(trelloLists.get(0).getName(), trelloListDto.get(0).getName());
        assertEquals(trelloLists.get(0).isClosed(), trelloListDto.get(0).isClosed());
    }

    @Test
    void mapToListDto(){
        //Given
        List<TrelloList> trelloList = new ArrayList<>();
        trelloList.add(new TrelloList("1", "List", true));

        //When
        List<TrelloListDto> trelloListDto = trelloMapper.mapToListDto(trelloList);

        //Then
        assertEquals(trelloListDto.get(0).getId(), trelloList.get(0).getId());
        assertEquals(trelloListDto.get(0).getName(), trelloList.get(0).getName());
        assertEquals(trelloListDto.get(0).isClosed(), trelloList.get(0).isClosed());
    }

    @Test
    void testMapToCard(){
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("CardTrelloDto", "TestCardDto", "TestPosDto", "1");

        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertEquals(trelloCard.getName(),trelloCardDto.getName());
        assertEquals(trelloCard.getDescription(),trelloCardDto.getDescription());
        assertEquals(trelloCard.getPos(),trelloCardDto.getPos());
        assertEquals(trelloCard.getListId(),trelloCardDto.getListId());
    }

    @Test
    void testMapToCardDto(){
        //Given
        TrelloCard trelloCard = new TrelloCard("CardTrello", "TestCard", "TestPos", "2");

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals(trelloCardDto.getName(),trelloCard.getName());
        assertEquals(trelloCardDto.getDescription(),trelloCard.getDescription());
        assertEquals(trelloCardDto.getPos(),trelloCard.getPos());
        assertEquals(trelloCardDto.getListId(),trelloCard.getListId());
    }
}