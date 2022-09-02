package sky.pro.java.course2.examinerservice.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sky.pro.java.course2.examinerservice.domain.Question;
import sky.pro.java.course2.examinerservice.exceptions.QuestionInvalidAmountException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    @Mock
    private QuestionService questionService;
    @InjectMocks
    private ExaminerServiceImpl out;

    @Test
    void getQuestionsTest() {
        List<Question> expected = new ArrayList<>(List.of(new Question("Чем известен Герострат?", "Сжёг храм Артемиды Эфесской")));
        Question question = new Question("Чем известен Герострат?", "Сжёг храм Артемиды Эфесской");
        when(questionService.getRandomQuestion()).thenReturn(question);
        assertThat(out.getQuestions(1)).isEqualTo(expected);
    }

    @Test
    void shouldThrowExceptions() {
        assertThatExceptionOfType(QuestionInvalidAmountException.class).isThrownBy(() -> out.getQuestions(-1));
        assertThatExceptionOfType(QuestionInvalidAmountException.class).isThrownBy(() -> out.getQuestions(11));
    }
}