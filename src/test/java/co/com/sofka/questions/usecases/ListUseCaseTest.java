package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.repositories.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;


import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ListUseCaseTest {

    @Mock
    private QuestionRepository repository;
    ListUseCase listUseCase;


    @BeforeEach
    public void setup(){
        MapperUtils mapperUtils = new MapperUtils();
        listUseCase = new ListUseCase(mapperUtils, repository);
    }

    @Test
     void listQuestionTest(){
        var question =  new Question();
        question.setUserId("xxxx-xxxx");
        question.setType("tech");
        question.setCategory("software");
        question.setQuestion("¿Que es java?");
        when(repository.findAll()).thenReturn(Flux.just(question ));

        StepVerifier.create(listUseCase.get())
                .expectNextMatches(questionDTO -> {
                    assert questionDTO.getUserId().equals("xxxx-xxxx");
                    assert questionDTO.getCategory().equals("software");
                    assert questionDTO.getQuestion().equals("¿Que es java?");
                    assert questionDTO.getType().equals("tech");
                    return true;
                })
                .verifyComplete();

        verify(repository).findAll();
    }
}