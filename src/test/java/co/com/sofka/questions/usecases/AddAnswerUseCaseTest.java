package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.repositories.AnswerRepository;
import co.com.sofka.questions.repositories.QuestionRepository;
import co.com.sofka.questions.service.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddAnswerUseCaseTest {


    @Mock
    private AnswerRepository answerRepository;
    @Mock
    GetUseCase getUseCase;
    @SpyBean
    AddAnswerUseCase addAnswerUseCase;

    @BeforeEach
    public void setup(){
        MapperUtils mapperUtils = new MapperUtils();
        addAnswerUseCase = new AddAnswerUseCase(mapperUtils,getUseCase,answerRepository);
    }
    @Test
    public void addAnswerTest(){
        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.setUserId("001");
        answerDTO.setQuestionId("010");
        answerDTO.setAnswer("Manizales");
        answerDTO.setPosition(2);

        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId("010");
        questionDTO.setUserId("001");
        questionDTO.setQuestion("¿Cual es la capital de caldas?");
        questionDTO.setType("Sociales");
        questionDTO.setCategory("Colombia");
        questionDTO.setAnswers(List.of(answerDTO));

        Answer answerData = new Answer();
        answerData.setId("001");
        answerData.setUserId("001");
        answerData.setQuestionId("010");
        answerData.setAnswer("Manizales");
        answerData.setPosition(2);


        when(getUseCase.apply("010")).thenReturn(Mono.just(questionDTO));
        when(answerRepository.save(answerData).thenReturn(Mono.just(questionDTO)));

        var resultado = addAnswerUseCase.apply(answerDTO);

        StepVerifier.create(resultado)
                .expectNextCount(1)
                .verifyComplete();

        Mockito.verify(answerRepository).save(Mockito.any(Answer.class));
    }
}