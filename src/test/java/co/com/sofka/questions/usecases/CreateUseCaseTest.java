package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.repositories.QuestionRepository;
import org.junit.jupiter.api.Assertions;
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
public class CreateUseCaseTest {

    @Mock
    private QuestionRepository questionRepository;

    private MapperUtils mapper = new MapperUtils();

    CreateUseCase useCase;

    @BeforeEach
    public void setUp(){
        useCase = new CreateUseCase(mapper,questionRepository);
    }

    @Test
    public void createQuestionTest(){
        var questionData = new Question();
        questionData.setId("010");
        questionData.setUserId("001");
        questionData.setQuestion("¿Cual es la capital de caldas?");
        questionData.setType("Sociales");
        questionData.setCategory("Colombia");

        var questionDTO = new QuestionDTO();
        questionDTO.setUserId("001");
        questionDTO.setQuestion("¿Cual es la capital de caldas?");
        questionDTO.setType("Sociales");
        questionDTO.setCategory("Colombia");

        Mockito.when(questionRepository.save(Mockito.any(Question.class))).thenReturn(Mono.just(questionData));

        var resultado = useCase.apply(questionDTO);

        StepVerifier.create(resultado)
                .expectNextCount(1)
                .verifyComplete();

        Mockito.verify(questionRepository).save(Mockito.any(Question.class));
    }
}