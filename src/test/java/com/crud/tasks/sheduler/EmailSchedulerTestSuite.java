package com.crud.tasks.sheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class EmailSchedulerTestSuite {
    @InjectMocks
    EmailScheduler emailScheduler;
    @Mock
    SimpleEmailService simpleEmailService;
    @Mock
    TaskRepository taskRepository;
    @Mock
    AdminConfig adminConfig;


    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSendInformationEmail(){
        //Given
        long taskCount = 5;
        when(taskRepository.count()).thenReturn(taskCount);
        when(adminConfig.getAdminMail()).thenReturn("wojciech.nesteruk@gmail.com");

        //When
        emailScheduler.sendInformation();

        //Then
        verify(taskRepository, times(1)).count();
        verify(adminConfig, times(1)).getAdminMail();
        verify(simpleEmailService, times(1)).send(any(Mail.class));
    }
}