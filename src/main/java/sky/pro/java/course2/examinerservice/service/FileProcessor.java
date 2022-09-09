package sky.pro.java.course2.examinerservice.service;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class FileProcessor {
    private final List<QuestionService> questionServices;

    public FileProcessor(List<QuestionService> storeServices) {
        this.questionServices = storeServices;
    }

    @PostConstruct
    public void listImplementations() {
//        questionServices
//                .forEach(storageService -> System.out.println(storageService.getClass().getSimpleName()));
        System.out.println(questionServices.get(1));
    }

}
