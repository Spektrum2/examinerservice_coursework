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
        Map<String, String> questsAndAnswers = new HashMap<>(Map.of(
                "Как называется самая известная смотровая площадка Москвы?", "Воробьёвы горы",
                "Что было создано благодаря грибам-плесени?", "Пенициллин",
                "Что в море является ориентиром для моряка?", "Полярная звезда",
                "Назовите страну с самой высокой продолжительностью жизни.", "Китай",
                "Как звали маму Македонского?", "Олимпиада",
                "Чем известен Герострат?", "Сжёг храм Артемиды Эфесской",
                "Какую вершину сложно покорить?", "Вершину Эвереста",
                "Как называется линия, у которой нет концов?", "Луч",
                "Каким словом называли в древние времена водяные часы?", "Клепсидра",
                "На какой вопрос не могу получить ответ Гамлет?", "Быть или не быть?"
        ));
        String[] questions1 = {
                "Как называется самая известная смотровая площадка Москвы?",
                "Что было создано благодаря грибам-плесени?",
                "Что в море является ориентиром для моряка?",
                "Назовите страну с самой высокой продолжительностью жизни.",
                "Как звали маму Македонского?",
                "Чем известен Герострат?",
                "Какую вершину сложно покорить?",
                "Как называется линия, у которой нет концов?",
                "Каким словом называли в древние времена водяные часы?",
                "На какой вопрос не могу получить ответ Гамлет?",
        };
        int pos = random.nextInt(questions1.length);
        Question question = new Question(questions1[pos], questsAndAnswers.get(questions1[pos]));
        questions.add(question);
        return question;
    }
}
