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
        List<Question> questionsRandom = new ArrayList<>(List.of(
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
        int pos = random.nextInt(questionsRandom.size());
        Question question = questionsRandom.get(pos);
        javaQuestionRepository.add(question);
        return question;
    }
}
