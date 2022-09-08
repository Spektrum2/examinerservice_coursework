package sky.pro.java.course2.examinerservice.service;

import org.assertj.core.api.AssertionsForInterfaceTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sky.pro.java.course2.examinerservice.domain.Question;
import sky.pro.java.course2.examinerservice.exceptions.QuestionAlreadyAddedException;
import sky.pro.java.course2.examinerservice.exceptions.QuestionAnswerSameException;
import sky.pro.java.course2.examinerservice.exceptions.QuestionNotFoundException;
import sky.pro.java.course2.examinerservice.repository.MathQuestionRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MathQuestionServiceTest {
    private final Set<Question> test = new HashSet<>();

    @Mock
    private MathQuestionRepository mathQuestionRepository;
    @InjectMocks
    private MathQuestionService out;

    @ParameterizedTest
    @MethodSource("provideParamsForAddTest")
    void addTest(String question, String answer) {
        Question expected = new Question(question, answer);
        when(mathQuestionRepository.add(any())).thenReturn(expected);
        assertThat(out.add(question, answer)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTests")
    void findTest(Question question) {
        test.add(question);
        when(mathQuestionRepository.add(any())).thenReturn(question);
        out.add(question.getQuestion(), question.getAnswer());
        when(mathQuestionRepository.getAll()).thenReturn(test);
        when(mathQuestionRepository.find(any())).thenReturn(question);
        assertThat(out.find(question)).isEqualTo(question);
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTests")
    void removeTest(Question question) {
        test.add(question);
        when(mathQuestionRepository.add(any())).thenReturn(question);
        out.add(question.getQuestion(), question.getAnswer());
        when(mathQuestionRepository.getAll()).thenReturn(test);
        when(mathQuestionRepository.remove(any())).thenReturn(question);
        assertThat(out.remove(question)).isEqualTo(question);
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTests")
    void getAllTest(Question question) {
        test.add(question);
        when(mathQuestionRepository.add(any())).thenReturn(question);
        out.add(question.getQuestion(), question.getAnswer());
        when(mathQuestionRepository.getAll()).thenReturn(test);
        AssertionsForInterfaceTypes.assertThat(out.getAll()).isEqualTo(test);
    }

    @Test
    void getRandomQuestion () {
        List<Question> expected = new ArrayList<>(List.of(
                new Question("Найти значение выражения a²(b+c), если a = 4, b = -7, c = 2", "–80"),
                new Question("Найти значение выражения (m + n)² - 3n, если m = 1/6, n = 1/3", "-3/4"),
                new Question("Решить уравнение 2(x - 3) + 3x = 4", "x = 2"),
                new Question("Упростить выражение 7a + 23 + 2(-4a + 1)", "-a + 25"),
                new Question("Вычислить 4 * (-5)³ + 8 * 0.5", "–496"),
                new Question("Выполнить умножение 2a²b(-5b² + 2ab)", "-10a²b³ + 4a³b²"),
                new Question("Упростить выражение 5x(x² + 4x - 2) - 2x²(3x - 1)", "-x³ + 22x² - 10x"),
                new Question("Решить уравнение 2x(2x + 3) - 7 = 4x² - 4", "x = 0.5"),
                new Question("Выполнить умножение (2a + b - 3c) * (-4a)", "-8a² - 4ab + 12ac"),
                new Question("Разложить на множители многочлен -2a²b - 8a²b² + 10ab²", "-2ab(a + 4ab - 5b)")
        ));
        assertThat(out.getRandomQuestion()).isIn(expected);
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTests")
    void shouldThrowExceptionsForAdd (Question question){
        test.add(question);
        when(mathQuestionRepository.add(any())).thenReturn(question);
        out.add(question.getQuestion(), question.getAnswer());
        when(mathQuestionRepository.getAll()).thenReturn(test);
        Question question2 = new Question("Какие виды массивов вы знаете?", "одномерные и многомерные массивы");
        assertThatExceptionOfType(QuestionAnswerSameException.class).isThrownBy(() -> out.add(question.getQuestion(), question.getQuestion()));
        assertThatExceptionOfType(QuestionAlreadyAddedException.class).isThrownBy(() -> out.add(question.getQuestion(), question.getAnswer()));
        assertThatExceptionOfType(QuestionNotFoundException.class).isThrownBy(() -> out.find(question2));
        assertThatExceptionOfType(QuestionNotFoundException.class).isThrownBy(() -> out.remove(question2));

    }
    public static Stream<Arguments> provideParamsForAddTest () {
        return Stream.of(
                Arguments.of("Как найти необходимый символ в строке?", "str.indexOf(char ch) или lastIndexOf(char c) — вернет индекс первого и последнего вхождения символа"),
                Arguments.of("Дайте определение понятию класс", "Класс – это шаблон, описывающий общие свойства группы объектов"),
                Arguments.of("Что такое поле/атрибут класса?", "Поле (атрибут) класса — это характеристика объекта")
        );
    }

    public static Stream<Arguments> provideParamsForTests () {
        return Stream.of(
                Arguments.of(new Question("Как найти необходимый символ в строке?", "str.indexOf(char ch) или lastIndexOf(char c) — вернет индекс первого и последнего вхождения символа")),
                Arguments.of(new Question("Дайте определение понятию класс", "Класс – это шаблон, описывающий общие свойства группы объектов")),
                Arguments.of(new Question("Что такое поле/атрибут класса?", "Поле (атрибут) класса — это характеристика объекта"))
        );
    }

}