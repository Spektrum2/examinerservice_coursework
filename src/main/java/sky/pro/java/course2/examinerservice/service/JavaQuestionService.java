package sky.pro.java.course2.examinerservice.service;

import org.springframework.stereotype.Service;
import sky.pro.java.course2.examinerservice.domain.Question;
import sky.pro.java.course2.examinerservice.exceptions.QuestionAlreadyAddedException;
import sky.pro.java.course2.examinerservice.exceptions.QuestionAnswerSameException;
import sky.pro.java.course2.examinerservice.exceptions.QuestionNotFoundException;
import sky.pro.java.course2.examinerservice.repository.JavaQuestionRepository;

import java.util.*;

@Service("javaQuestion")
public class JavaQuestionService implements QuestionService {

    private final JavaQuestionRepository javaQuestionRepository;

    private final Random random = new Random();

    public JavaQuestionService(JavaQuestionRepository javaQuestionRepository) {
        this.javaQuestionRepository = javaQuestionRepository;
    }

    @Override
    public Question add(String question, String answer) {
        Question question1 = new Question(question, answer);
        if (question.equals(answer)) {
            throw new QuestionAnswerSameException();
        }
        if (javaQuestionRepository.getAll().contains(question1)) {
            throw new QuestionAlreadyAddedException();
        }

        return javaQuestionRepository.add(question1);
    }

    @Override
    public Question find(Question question) {
        if (!javaQuestionRepository.getAll().contains(question)) {
            throw new QuestionNotFoundException();
        }
        return javaQuestionRepository.find(question);
    }

    @Override
    public Question remove(Question question) {
        if (!javaQuestionRepository.getAll().contains(question)) {
            throw new QuestionNotFoundException();
        }
        return javaQuestionRepository.remove(question);
    }

    @Override
    public Collection<Question> getAll() {
        return javaQuestionRepository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        List<Question> questions = new ArrayList<>(List.of(
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
        return javaQuestionRepository.find(questions.get(random.nextInt(questions.size())));
    }
}
