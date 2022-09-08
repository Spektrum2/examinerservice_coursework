package sky.pro.java.course2.examinerservice.repository;

import org.springframework.stereotype.Repository;
import sky.pro.java.course2.examinerservice.domain.Question;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Repository
public class JavaQuestionRepository implements QuestionRepository {
    private final Set<Question> questions;

    public JavaQuestionRepository() {
        this.questions = new HashSet<>();
    }

    @Override
    @PostConstruct
    public void init() {
        questions.add(new Question(
                "Дайте определение понятию исключение",
                "Исключение — это проблема(ошибка) возникающая во время выполнения программы."));
        questions.add(new Question(
                "Какова роль и правила написания оператора выбора (switch)?",
                "Оператор switch сравнивает аргумент на равенство с предложенным значением. Обязательно необходимо ставить break; после завершения тело команды, иначе будет продолжаться выполнение ниже по строчкам."));
        questions.add(new Question(
                "Что вы знаете о функции main, какие обязательные условия ее определения?",
                "Метод main() — точка входа в программу. Может быть несколько методов main. Входные параметры — только массив строк. Если этого метода не будет, то компиляция возможна, но при запуске будет Error: Main method not found."));
        questions.add(new Question(
                "Могут ли нестатические методы перегрузить статические?",
                "Да. Это будут просто два разных метода для программы. Статический будет доступен по имени класса."));
        questions.add(new Question(
                "Как получить доступ к переопределенным методам родительского класса?",
                "super.method();"));
    }

    @Override
    public Question add(Question question) {
        questions.add(question);
        return question;
    }

    @Override
    public Question find(Question question) {
        Question question1 = null;
        for (Question question2 : questions) {
            if (question2.equals(question)) {
                question1 = question2;
            }
        }
        return question1;
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
