package com.crud.tasks.trello.validator;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloList;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TrelloValidatorTestSuite {

    @Test
    void testValidateCard(){
        //Given
        Logger LOGGER = (Logger) LoggerFactory.getLogger(TrelloValidator.class);
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        LOGGER.addAppender(listAppender);

        //When
        TrelloCard trelloCard = new TrelloCard("test", "testDesc", "testPos", "testListId");
        TrelloValidator trelloValidator = new TrelloValidator();

        trelloValidator.validateCard(trelloCard);
        String loggerInfo = String.valueOf(listAppender.list.get(0));

        //Then
        assertEquals("[INFO] Someone is testing my application!", loggerInfo);
    }

    @Test
    void testValidateTrelloBoards(){
        //Given
        List<TrelloList> lists = new ArrayList<>();
        TrelloBoard trelloBoard1 = new TrelloBoard("1", "test", lists);
        TrelloBoard trelloBoard2= new TrelloBoard("2", "board", lists);
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard1);
        trelloBoards.add(trelloBoard2);

        //When
        TrelloValidator trelloValidator = new TrelloValidator();
        List<TrelloBoard> validatedList = trelloValidator.validateTrelloBoards(trelloBoards);

        //Then
        assertEquals(1, validatedList.size());
    }
}