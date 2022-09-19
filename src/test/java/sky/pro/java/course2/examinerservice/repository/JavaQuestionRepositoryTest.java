package sky.pro.java.course2.examinerservice.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import sky.pro.java.course2.examinerservice.domain.Question;

import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


class JavaQuestionRepositoryTest {
    private final JavaQuestionRepository javaQuestionRepository = new JavaQuestionRepository();


    @Test
    void initTest() {
        List<Question> expected = new ArrayList<>(List.of(
                new Question(
                "Дайте определение понятию исключение",
                "Исключение — это проблема(ошибка) возникающая во время выполнения программы."),
                new Question(
                        "Какова роль и правила написания оператора выбора (switch)?",
                        "Оператор switch сравнивает аргумент на равенство с предложенным значением. Обязательно необходимо ставить break; после завершения тело команды, иначе будет продолжаться выполнение ниже по строчкам."),
                new Question(
                        "Что вы знаете о функции main, какие обязательные условия ее определения?",
                        "Метод main() — точка входа в программу. Может быть несколько методов main. Входные параметры — только массив строк. Если этого метода не будет, то компиляция возможна, но при запуске будет Error: Main method not found."),
                new Question(
                        "Могут ли нестатические методы перегрузить статические?",
                        "Да. Это будут просто два разных метода для программы. Статический будет доступен по имени класса."),
                new Question(
                        "Как получить доступ к переопределенным методам родительского класса?",
                        "super.method();"),
                new Question(
                        "Что значит слово инициализация?",
                        "Инициализация — создание, активация, подготовка к работе, определение параметров."),
                new Question(
                        "Какими значениями инициализируются переменные по умолчанию?",
                        "Числа инициализируются 0 или 0.0. Объекты (в том числе String) — null, char — u0000; boolean — false;"),
                new Question(
                        "Что такое таблица истинности?",
                        "Таблица истинности — это таблица, описывающая логическую функцию."),
                new Question(
                        "О чем говорят ключевые слова “this”, “super”, где и как их можно использовать?",
                        "super — используется для обращения к базовому классу, а this к текущему."),
                new Question(
                        "Может ли метод принимать разное количество параметров (аргументы переменной длины)?",
                        "Да. Запись имеет вид method(type … val). Например public void method(String … strings), где strings это массив, т.е. можно записать")
        ));
        javaQuestionRepository.init();
        assertThat(javaQuestionRepository.getAll()).containsExactlyInAnyOrderElementsOf(expected);
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTests")
    void add(Question question) {
        assertThat(javaQuestionRepository.add(question)).isEqualTo(question);
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTests")
    void find(Question question) {
        javaQuestionRepository.add(question);
        Question question1 = new Question("Hello", "World");
        assertThat(javaQuestionRepository.find(question)).isEqualTo(question);
        assertThat(javaQuestionRepository.find(question1)).isEqualTo(null);

    }

    @ParameterizedTest
    @MethodSource("provideParamsForTests")
    void remove(Question question) {
        javaQuestionRepository.add(question);
        assertThat(javaQuestionRepository.remove(question)).isEqualTo(question);
        assertThat(javaQuestionRepository.getAll()).isEmpty();
    }

    public static Stream<Arguments> provideParamsForTests () {
        return Stream.of(
                Arguments.of(new Question("Как найти необходимый символ в строке?", "str.indexOf(char ch) или lastIndexOf(char c) — вернет индекс первого и последнего вхождения символа")),
                Arguments.of(new Question("Дайте определение понятию класс", "Класс – это шаблон, описывающий общие свойства группы объектов")),
                Arguments.of(new Question("Что такое поле/атрибут класса?", "Поле (атрибут) класса — это характеристика объекта"))
        );
    }
}