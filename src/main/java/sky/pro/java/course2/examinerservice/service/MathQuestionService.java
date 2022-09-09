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
        List<Question> questions = new ArrayList<>(mathQuestionRepository.getAll());
        return mathQuestionRepository.find(questions.get(random.nextInt(questions.size())));
    }
}
