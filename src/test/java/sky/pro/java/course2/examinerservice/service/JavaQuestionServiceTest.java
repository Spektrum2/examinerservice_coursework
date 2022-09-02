package sky.pro.java.course2.examinerservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import sky.pro.java.course2.examinerservice.domain.Question;
import sky.pro.java.course2.examinerservice.exceptions.QuestionAlreadyAddedException;
import sky.pro.java.course2.examinerservice.exceptions.QuestionAnswerSameException;
import sky.pro.java.course2.examinerservice.exceptions.QuestionNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class JavaQuestionServiceTest {

    private final JavaQuestionService out = new JavaQuestionService();

    @BeforeEach
    void setUp() {
        out.add("Каким словом называли в древние времена водяные часы?", "Клепсидра");
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTests")
    void addTest(String question, String answer) {
        Question expected = new Question(question, answer);
        assertThat(out.add(question, answer)).isEqualTo(expected);
    }

    @Test
    void removeTest() {
        Question actual = new Question("Каким словом называли в древние времена водяные часы?", "Клепсидра");
        Question expected = new Question("Каким словом называли в древние времена водяные часы?", "Клепсидра");
        assertThat(out.remove(actual)).isEqualTo(expected);
        assertThat(out.getAll()).isEmpty();
    }

    @Test
    void getRandomQuestion() {
        List<Question> expected = new ArrayList<>(List.of(
                new Question("Как называется самая известная смотровая площадка Москвы?", "Воробьёвы горы"),
                new Question("Что было создано благодаря грибам-плесени?", "Пенициллин"),
                new Question("Что в море является ориентиром для моряка? ", "Полярная звезда"),
                new Question("Назовите страну с самой высокой продолжительностью жизни.", "Китай"),
                new Question("Как звали маму Македонского?", "Олимпиада"),
                new Question("Чем известен Герострат?", "Сжёг храм Артемиды Эфесской"),
                new Question("Какую вершину сложно покорить?", "Вершину Эвереста"),
                new Question("Как называется линия, у которой нет концов?", "Луч"),
                new Question("Каким словом называли в древние времена водяные часы?", "Клепсидра"),
                new Question("На какой вопрос не могу получить ответ Гамлет?", "Быть или не быть?")
        ));
        assertThat(out.getRandomQuestion()).isIn(expected);
    }

    @Test
    void shouldThrowExceptions() {
        Question question = new Question("Чем известен Герострат?", "Сжёг храм Артемиды Эфесской");
        assertThatExceptionOfType(QuestionAnswerSameException.class).isThrownBy(() -> out.add("Каким словом называли в древние времена водяные часы?", "Каким словом называли в древние времена водяные часы?"));
        assertThatExceptionOfType(QuestionAlreadyAddedException.class).isThrownBy(() -> out.add("Каким словом называли в древние времена водяные часы?", "Клепсидра"));
        assertThatExceptionOfType(QuestionNotFoundException.class).isThrownBy(() -> out.remove(question));
    }

    public static Stream<Arguments> provideParamsForTests() {
        return Stream.of(
                Arguments.of("Сколько длилась столетняя война?", "116 лет"),
                Arguments.of("Какой фрукт является объектом раздора?", "Яблоко"),
                Arguments.of("В каком месте началась 1-ая мировая война?", "Сараев")
        );
    }
}