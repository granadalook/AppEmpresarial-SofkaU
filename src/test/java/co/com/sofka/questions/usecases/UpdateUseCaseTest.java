package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.repositories.QuestionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class UpdateUseCaseTest {

    @Mock
    private QuestionRepository questionRepository;

    UpdateUseCase useCase;

    @BeforeEach
    public void setup(){
        MapperUtils mapperUtils = new MapperUtils();
        useCase = new UpdateUseCase(mapperUtils, questionRepository);
    }

    @Test
    public void updateQuestionTest(){
        var questionData = new Question();
        questionData.setId("010");
        questionData.setUserId("001");
        questionData.setQuestion("¿Cual es la capital de caldas?");
        questionData.setType("Sociales");
        questionData.setCategory("Colombia");

        var questionDTO = new QuestionDTO();
        questionDTO.setId("010");
        questionDTO.setUserId("001");
        questionDTO.setQuestion("¿Cual es la capital de cundinamarca?");
        questionDTO.setType("Sociales");
        questionDTO.setCategory("Colombia");

       Mockito.when(questionRepository.findById("010")).thenReturn(Mono.just(questionData));
       Mono<String> resultado = useCase.apply(questionDTO);

        StepVerifier.create(resultado)
                .expectNextCount(1)
                .verifyComplete();

        Mockito.verify(questionRepository).save(Mockito.any(Question.class));
    }

}