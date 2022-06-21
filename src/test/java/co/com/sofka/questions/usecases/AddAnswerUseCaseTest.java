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
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddAnswerUseCaseTest {

    @Mock
    private AnswerRepository answerRepository;

    GetUseCase getUseCase;

    AddAnswerUseCase addAnswerUseCase;

    @BeforeEach
    public void setup(){
        MapperUtils mapperUtils = new MapperUtils();
        addAnswerUseCase = new AddAnswerUseCase(mapperUtils,getUseCase,answerRepository);
    }

    @Test
    public void applyTest(){
        /*var questionData = new Question();
        questionData.setId("010");
        questionData.setUserId("001");
        questionData.setQuestion("¿Cual es la capital de caldas?");
        questionData.setType("Sociales");
        questionData.setCategory("Colombia");

        var questionDTO = new QuestionDTO();
        questionDTO.setId("010");
        questionDTO.setUserId("001");
        questionDTO.setQuestion("¿Cual es la capital de caldas?");
        questionDTO.setType("Sociales");
        questionDTO.setCategory("Colombia");

        var answerData = new Answer();
        answerData.setId("001");
        answerData.setUserId("001");
        answerData.setQuestionId("010");
        answerData.setAnswer("Manizales");
        answerData.setPosition(2);

        var answerDTO = new AnswerDTO();
        answerDTO.setUserId("001");
        answerDTO.setQuestionId("010");
        answerDTO.setAnswer("Manizales");
        answerDTO.setPosition(2);

        when(answerRepository.save(answerData)).thenReturn(Mono.just(answerData));

        //var pregunta = createUseCase.apply(questionDTO);
        var resultado = getUseCase.apply(answerDTO.getQuestionId()).flatMap(question ->
            answerRepository.save(mapper.mapperToAnswer().apply(answerDTO))
                    .map(answer -> {
                        question.getAnswers().add(answerDTO);
                        return question;
                    })
        );

        StepVerifier.create(resultado)
                .expectNextCount(1)
                .verifyComplete();

        Mockito.verify(answerRepository).save(Mockito.any(Answer.class));*/
    }


}