package sky.pro.java.course2.examinerservice.repository;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import sky.pro.java.course2.examinerservice.domain.Question;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class MathQuestionRepositoryTest {
    private final MathQuestionRepository mathQuestionRepository = new MathQuestionRepository();


    @Test
    void initTest() {
        Set<Question> expected = new HashSet<>(Set.of(
                new Question(
                        "Найти значение выражения a²(b+c), если a = 4, b = -7, c = 2",
                        "–80"),
                new Question(
                        "Найти нули функции y = x² - 8x + 12",
                        "x = 2; x = 6"),
                new Question(
                        "Решить уравнение |5x - 3(x + 2) + 3| = 3",
                        "x = 3; x = 0"),
                new Question(
                        "Решить уравнение 5(2x - 1) = 4x - 23",
                        "-3"),
                new Question(
                        "Решить уравнение 3x - 2(x - 1) = x + 2",
                        "Корнем уравнения является любое число.")
        ));
        mathQuestionRepository.init();
        assertThat(mathQuestionRepository.getAll()).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTests")
    void add(Question question) {
        AssertionsForClassTypes.assertThat(mathQuestionRepository.add(question)).isEqualTo(question);
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTests")
    void find(Question question) {
        mathQuestionRepository.add(question);
        AssertionsForClassTypes.assertThat(mathQuestionRepository.find(question)).isEqualTo(question);
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTests")
    void remove(Question question) {
        mathQuestionRepository.add(question);
        AssertionsForClassTypes.assertThat(mathQuestionRepository.remove(question)).isEqualTo(question);
        assertThat(mathQuestionRepository.getAll()).isEmpty();
    }

    public static Stream<Arguments> provideParamsForTests () {
        return Stream.of(
                Arguments.of(new Question("Как найти необходимый символ в строке?", "str.indexOf(char ch) или lastIndexOf(char c) — вернет индекс первого и последнего вхождения символа")),
                Arguments.of(new Question("Дайте определение понятию класс", "Класс – это шаблон, описывающий общие свойства группы объектов")),
                Arguments.of(new Question("Что такое поле/атрибут класса?", "Поле (атрибут) класса — это характеристика объекта"))
        );
    }

}