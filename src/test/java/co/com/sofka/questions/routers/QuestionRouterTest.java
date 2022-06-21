package co.com.sofka.questions.routers;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.usecases.ListUseCase;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;

import static org.junit.jupiter.api.Assertions.*;

@WebFluxTest(QuestionRouter.class)
class QuestionRouterTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ListUseCase listUseCase;


    @MockBean
    private QuestionRouter questionRouter;

    @Test
    void getAll() {
        var questionData1 = new Question();
        questionData1.setId("010");
        questionData1.setUserId("001");
        questionData1.setQuestion("¿Cual es la capital de caldas?");
        questionData1.setType("Sociales");
        questionData1.setCategory("Colombia");
        var questionData2 = new Question();
        questionData2.setId("011");
        questionData2.setUserId("001");
        questionData2.setQuestion("¿Cual es la capital del valle del cauca?");
        questionData2.setType("Sociales");
        questionData2.setCategory("Colombia");
        Flux<Question> listQuestions = Flux.just(questionData1,questionData2);

        Mockito.when(questionRouter.getAll(listUseCase)).thenReturn((RouterFunction<ServerResponse>) listQuestions);

        webTestClient.get().uri("/getAll").exchange()
                .expectStatus().isOk().returnResult(Question.class).getResponseBody();
    }

    @Test
    void getOwnerAll() {
    }

    @Test
    void create() {
    }

    @Test
    void get() {
    }

    @Test
    void addAnswer() {
    }

    @Test
    void delete() {
    }
}