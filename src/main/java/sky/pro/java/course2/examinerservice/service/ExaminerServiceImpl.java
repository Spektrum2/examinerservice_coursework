package sky.pro.java.course2.examinerservice.service;

import org.springframework.stereotype.Service;
import sky.pro.java.course2.examinerservice.domain.Question;
import sky.pro.java.course2.examinerservice.exceptions.QuestionInvalidAmountException;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final JavaQuestionService javaQuestionService;
    private final MathQuestionService mathQuestionService;
    private final Random random = new Random();

    public ExaminerServiceImpl(JavaQuestionService javaQuestionService, MathQuestionService mathQuestionService) {
        this.javaQuestionService = javaQuestionService;
        this.mathQuestionService = mathQuestionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        List<Question> questions = new ArrayList<>();
        int countJava;
        int countMath = 0;
        if (amount < 1 || amount > 10) {
            throw new QuestionInvalidAmountException();
        }
        if (amount == 1) {
            countJava = random.nextInt(2);
            if (countJava == 0) {
                countMath = 1;
            }
        } else {
            countJava = random.nextInt(amount - 1) + 1;
            countMath = amount - countJava;
        }
        int counter = 0;
        while (counter != countJava) {
            Question question = javaQuestionService.getRandomQuestion();
            if (!questions.contains(question)) {
                questions.add(question);
                counter++;
            }
        }
        counter = 0;
        while (counter != countMath) {
            Question question = mathQuestionService.getRandomQuestion();
            if (!questions.contains(question)) {
                questions.add(question);
                counter++;
            }
        }
        return questions;
    }
}
