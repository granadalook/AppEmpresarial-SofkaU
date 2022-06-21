package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.repositories.AnswerRepository;
import co.com.sofka.questions.repositories.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class DeleteUseCaseTest {

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private AnswerRepository answerRepository;

    DeleteUseCase useCase;

    @BeforeEach
    public void setUp(){
        useCase = new DeleteUseCase(answerRepository,questionRepository);
    }

    @Test
    public void deleteQuestionTest() {
        var questionDTO = new QuestionDTO();
        questionDTO.setUserId("001");
        questionDTO.setQuestion("¿Cual es la capital de caldas?");
        questionDTO.setType("Sociales");
        questionDTO.setCategory("Colombia");

        var questionData = new Question();
        questionData.setId("010");
        questionData.setUserId("001");
        questionData.setQuestion("¿Cual es la capital de caldas?");
        questionData.setType("Sociales");
        questionData.setCategory("Colombia");

        var answerData = new Answer();
        answerData.setId("001");
        answerData.setUserId("001");
        answerData.setQuestionId("010");
        answerData.setAnswer("Manizales");
        answerData.setPosition(2);

        Mockito.when(questionRepository.deleteById("001")).thenReturn(Mono.empty());

        var resultado = questionRepository.deleteById("001");

        Mockito.verify(questionRepository).deleteById("001");
    }

}