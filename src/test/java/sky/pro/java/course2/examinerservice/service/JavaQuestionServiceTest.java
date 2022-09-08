package sky.pro.java.course2.examinerservice.service;

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
import sky.pro.java.course2.examinerservice.repository.JavaQuestionRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;


import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JavaQuestionServiceTest {
    private final Set<Question> test = new HashSet<>();

    @Mock
    private JavaQuestionRepository javaQuestionRepository;
    @InjectMocks
    private JavaQuestionService out;

    @ParameterizedTest
    @MethodSource("provideParamsForAddTest")
    void addTest(String question, String answer) {
        Question expected = new Question(question, answer);
        when(javaQuestionRepository.add(any())).thenReturn(expected);
        assertThat(out.add(question, answer)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTests")
    void findTest(Question question) {
        test.add(question);
        when(javaQuestionRepository.add(any())).thenReturn(question);
        out.add(question.getQuestion(), question.getAnswer());
        when(javaQuestionRepository.getAll()).thenReturn(test);
        when(javaQuestionRepository.find(any())).thenReturn(question);
        assertThat(out.find(question)).isEqualTo(question);
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTests")
    void removeTest(Question question) {
        test.add(question);
        when(javaQuestionRepository.add(any())).thenReturn(question);
        out.add(question.getQuestion(), question.getAnswer());
        when(javaQuestionRepository.getAll()).thenReturn(test);
        when(javaQuestionRepository.remove(any())).thenReturn(question);
        assertThat(out.remove(question)).isEqualTo(question);
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTests")
    void getAllTest(Question question) {
        test.add(question);
        when(javaQuestionRepository.add(any())).thenReturn(question);
        out.add(question.getQuestion(), question.getAnswer());
        when(javaQuestionRepository.getAll()).thenReturn(test);
        assertThat(out.getAll()).isEqualTo(test);
    }

        @Test
        void getRandomQuestion () {
            List<Question> expected = new ArrayList<>(List.of(
                    new Question(
                            "Как правильно организовать доступ к полям класса?",
                            "Модификатор доступа — private. Доступ через методы get, set."
                    ),
                    new Question(
                            "Дайте определение понятию метод",
                            "Метод — это последовательность команд, которые вызываются по определенному имени."
                    ),
                    new Question(
                            "Что такое итерация цикла?",
                            "Это одноразовое выполнение кода, размещенного в теле цикла"),
                    new Question(
                            "Какие циклы вы знаете?",
                            "for, while, do-while, for-each"
                    ),
                    new Question(
                            "Какой оператор используется для немедленной остановки цикла?",
                            "break;"
                    ),
                    new Question(
                            "Какой оператор используется для перехода к следующей итерации цикла?",
                            "continue;"
                    ),
                    new Question(
                            "Какие данные могут хранить коллекции?",
                            "Коллекции могут хранить любые ссылочные типы данных."
                    ),
                    new Question(
                            "Что будет, если в Map положить два значения с одинаковым ключом?",
                            "Последнее значение перезапишет предыдущее."
                    ),
                    new Question(
                            "Как получить часть строки?",
                            "Метод substring(int beginIndex, int lastIndex) — возвращает часть строки по указанным индексам."
                    ),
                    new Question(
                            "Как узнать значение конкретного символа строки, зная его порядковый номер в строке?",
                            "str.charAt(int i) вернет символ по индексу"
                    )
            ));
            assertThat(out.getRandomQuestion()).isIn(expected);
        }

        @ParameterizedTest
        @MethodSource("provideParamsForTests")
        void shouldThrowExceptionsForAdd (Question question){
            test.add(question);
            when(javaQuestionRepository.add(any())).thenReturn(question);
            out.add(question.getQuestion(), question.getAnswer());
            when(javaQuestionRepository.getAll()).thenReturn(test);
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