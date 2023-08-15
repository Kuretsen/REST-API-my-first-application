package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TrelloServiceTestSuite {
    @InjectMocks
    TrelloService trelloService;
    @Mock
    TrelloClient trelloClient;
    @Mock
    SimpleEmailService emailService;
    @Mock
    AdminConfig adminConfig;

    @Test
    void testFetchTrelloBoards(){
        //Given
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("1", "Test", new ArrayList<>());
        List<TrelloBoardDto> fetchList = List.of(trelloBoardDto);
        when(trelloService.fetchTrelloBoards()).thenReturn(fetchList);

        //When
        int fetchedBoards = trelloService.fetchTrelloBoards().size();

        //Then
        assertEquals(1, fetchedBoards);
    }

    @Test
    void testCreateTrelloCard(){
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto();
        trelloCardDto.setName("Test Card");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto();
        when(trelloClient.createNewCard(any(TrelloCardDto.class))).thenReturn(createdTrelloCardDto);

        //When
        TrelloService trelloService1 = new TrelloService(trelloClient,emailService,adminConfig);

        CreatedTrelloCardDto result = trelloService1.createTrelloCard(trelloCardDto);

        //Then
        assertEquals(createdTrelloCardDto, result);
        verify(emailService, times(1)).send(any(Mail.class));
    }
}