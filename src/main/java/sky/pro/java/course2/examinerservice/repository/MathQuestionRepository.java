package sky.pro.java.course2.examinerservice.repository;

import org.springframework.stereotype.Repository;
import sky.pro.java.course2.examinerservice.domain.Question;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Repository
public class MathQuestionRepository implements QuestionRepository {


    private final Set<Question> questions;

    public MathQuestionRepository() {
        this.questions = new HashSet<>();
    }

    @Override
    @PostConstruct
    public void init() {
        questions.add(new Question(
                "Найти значение выражения a²(b+c), если a = 4, b = -7, c = 2",
                "–80"));
        questions.add(new Question(
                "Найти нули функции y = x² - 8x + 12",
                "x = 2; x = 6"));
        questions.add(new Question(
                "Решить уравнение |5x - 3(x + 2) + 3| = 3",
                "x = 3; x = 0"));
        questions.add(new Question(
                "Решить уравнение 5(2x - 1) = 4x - 23",
                "-3"));
        questions.add(new Question(
                "Решить уравнение 3x - 2(x - 1) = x + 2",
                "Корнем уравнения является любое число."));

    }

    @Override
    public Question add(Question question) {
        questions.add(question);
        return question;
    }

    @Override
    public Question find(Question question) {
        if (!questions.contains(question)) {
            return null;
        }
        return question;
    }

    @Override
    public Question remove(Question question) {
        questions.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return questions;
    }
}
