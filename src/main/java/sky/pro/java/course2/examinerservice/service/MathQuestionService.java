package sky.pro.java.course2.examinerservice.service;

import org.springframework.stereotype.Service;
import sky.pro.java.course2.examinerservice.domain.Question;
import sky.pro.java.course2.examinerservice.exceptions.QuestionAlreadyAddedException;
import sky.pro.java.course2.examinerservice.exceptions.QuestionAnswerSameException;
import sky.pro.java.course2.examinerservice.exceptions.QuestionNotFoundException;
import sky.pro.java.course2.examinerservice.repository.MathQuestionRepository;

import java.util.*;

@Service("mathQuestion")
public class MathQuestionService implements QuestionService {
    private final MathQuestionRepository mathQuestionRepository;
    private final Random random = new Random();

    public MathQuestionService(MathQuestionRepository mathQuestionRepository) {
        this.mathQuestionRepository = mathQuestionRepository;
    }

    @Override
    public Question add(String question, String answer) {
        Question question1 = new Question(question, answer);
        if (question.equals(answer)) {
            throw new QuestionAnswerSameException();
        }
        if (mathQuestionRepository.getAll().contains(question1)) {
            throw new QuestionAlreadyAddedException();
        }

        return mathQuestionRepository.add(question1);
    }

    @Override
    public Question find(Question question) {
        if (!mathQuestionRepository.getAll().contains(question)) {
            throw new QuestionNotFoundException();
        }
        return mathQuestionRepository.find(question);
    }

    @Override
    public Question remove(Question question) {
        if (!mathQuestionRepository.getAll().contains(question)) {
            throw new QuestionNotFoundException();
        }
        return mathQuestionRepository.remove(question);
    }

    @Override
    public Collection<Question> getAll() {
        return mathQuestionRepository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        List<Question> questions = new ArrayList<>(List.of(
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
                        "Корнем уравнения является любое число."),
                new Question(
                        "Найти общее решение линейного дифференциального уравнения первого порядка",
                        "xy′+x²+xy−y=0"),
                new Question(
                        "Написать разложение вектора x по векторам (a,b,c)",
                        "x=(−4;4;4), a=(3;1;0), b=(−1;0;6), c=(−1;2;0)"),
                new Question(
                        "Решить уравнение log₂x = -3",
                        "1/8"),
                new Question(
                        "Решить уравнение log₂(x² + 2x - 7) = log₂(x - 1)",
                        "2"),
                new Question(
                        "Решить уравнение log₅(x - 3) + log₅(x + 1) = 1",
                        "4")
        ));
        return mathQuestionRepository.find(questions.get(random.nextInt(questions.size())));
    }
}
