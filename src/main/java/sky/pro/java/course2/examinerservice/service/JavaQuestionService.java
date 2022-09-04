package sky.pro.java.course2.examinerservice.service;

import org.springframework.stereotype.Service;
import sky.pro.java.course2.examinerservice.domain.Question;
import sky.pro.java.course2.examinerservice.exceptions.QuestionAlreadyAddedException;
import sky.pro.java.course2.examinerservice.exceptions.QuestionAnswerSameException;
import sky.pro.java.course2.examinerservice.exceptions.QuestionNotFoundException;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {

    private final Set<Question> questions = new HashSet<>();

    @Override
    public Question add(String question, String answer) {
        Question question1 = new Question(question, answer);
        if (question.equals(answer)) {
            throw new QuestionAnswerSameException();
        }
        if (questions.contains(question1)) {
            throw new QuestionAlreadyAddedException();
        }
        questions.add(question1);
        return question1;
    }

    @Override
    public Question remove(Question question) {
        if (!questions.contains(question)) {
            throw new QuestionNotFoundException();
        }
        questions.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return questions;
    }

    @Override
    public Question getRandomQuestion() {
        Random random = new Random();
        List<Question> questionsRandom = new ArrayList<>(List.of(
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
        int pos = random.nextInt(questionsRandom.size());
        Question question = questionsRandom.get(pos);
        questions.add(question);
        return question;
    }
}
