# Курсовая работа 2-го курса

В курсовой работе ко второму курсу вам необходимо реализовать приложение, которое будет генерировать вопросы к экзамену.

### Путь пользователя

1. Пользователь обращается к некому эндпоинту по адресу (”/exam/get/{amount}”)
2. Пользователь получает ответ в виде списка случайных вопросов-ответов, количество которых равно amount из прошлого шага
3. Пользователь должен иметь возможность добавить, получить и удалить вопросы из хранилища вопросов (”/exam/java/(add/remove/find)”)

### Реализация приложения

Для упрощения архитектурного понимания, вам будут даны подсказки по организации проекта.

1. Реализовать сущность Question с двумя полями: question и answer. Данная сущность будет использоваться в качестве хранителя данных по вопросу.
    - Архитектура
   
        ![Untitled](https://downloader.disk.yandex.ru/preview/501602f712b8afcf4e03b8c526698dc54e3108b68694a651a8dfd2b557e9acd5/64a423e4/IDaLvcL_rPBrTMPqZ6c9VVPxudwH2uIMRCwd0E60IeyoLW1neVK_gquTtQf-ImdcWWw980Z0toipdL5_g65vug%3D%3D?uid=0&filename=Untitled.png&disposition=inline&hash=&limit=0&content_type=image%2Fpng&owner_uid=0&tknv=v2&size=2048x2048)
        
2. Сделать интерфейс QuestionService, который будет содержать в себе все методы по работе с вопросами определенного предмета.
    - Архитектура
        
        ![diagram-17353247503607978677.png](https://downloader.disk.yandex.ru/preview/d4adcf90ade4ad3b61e73377774794b640d174bd2eb88ce5e8f72a8e1cf357bb/64a424da/1oi8r3TEWm9OtEPcYeKZN1b7_7_2Fo8fH8bSeN238s53Ofy7q0k_7cBovdY__Mml9nca730aUMgxzo7fZ78T7A%3D%3D?uid=0&filename=diagram-17353247503607978677.png&disposition=inline&hash=&limit=0&content_type=image%2Fpng&owner_uid=0&tknv=v2&size=2048x2048)
        
3. Реализовать сервис JavaQuestionService, который будет реализовывать QuestionService и хранить в себе список вопросов по Java, а также осуществлять всю работу с этим списком.
    
    Реализация метода getRandomQuestion осуществляется с помощью класса Random и его метода nextInt, который в качестве параметра принимает максимальное число, а затем возвращает вам результат в виде случайного числа от 0 до максимального числа из параметров (не включительно).
    
    - Архитектура
        
        ![Untitled](https://downloader.disk.yandex.ru/preview/9637235b148722324223c73d281e33d4a485bddc2748a20d6f3a4916be913eb5/64a42ad9/tHqlcpf8PYiMhx85xOHKGlb7_7_2Fo8fH8bSeN238s7fqvmj2ne_zv8O9xp3o79385ceql9uq76cd4lJM-T-IA%3D%3D?uid=0&filename=Untitled%20%281%29.png&disposition=inline&hash=&limit=0&content_type=image%2Fpng&owner_uid=0&tknv=v2&size=2048x2048)
        
4. Реализовать контроллер JavaQuestionController, который будет предоставлять возможность пользователю добавлять, просматривать и удалять вопросы по Java в соответствующем QuestionService.
    
    Контроллер должен иметь три метода: добавить, удалить и получить все вопросы.
    
    Эти методы должны висеть на следующих эндпоинтах:
    
    Добавить: “/exam/java/add?question=QuestionText&answer=QuestionAnswer”
    
    Удалить: “/exam/java/remove?question=QuestionText&answer=QuestionAnswer”
    
    Получить все вопросы: “/exam/java”
    
    - Архитектура
        
        ![Untitled](https://downloader.disk.yandex.ru/preview/f019c180bd53875cadca76130956182b912f834065b9dc8dddf94cec9a659888/64a42b01/QtYJLc54tgABG7PtEo5UZ1b7_7_2Fo8fH8bSeN238s46Ban9DUfI3UKeKiG2h2CB2icif1-Sif4fNytScBJ1ww%3D%3D?uid=0&filename=Untitled%20%282%29.png&disposition=inline&hash=&limit=0&content_type=image%2Fpng&owner_uid=0&tknv=v2&size=2048x2048)
        
5. Сделать интерфейс ExaminerService с одним методом getQuestions.
    
    Этот интерфейс должен содержать один метод, который вернет список вопросов.
    
    - Архитектура
        
        ![Untitled](https://downloader.disk.yandex.ru/preview/d4adcf90ade4ad3b61e73377774794b640d174bd2eb88ce5e8f72a8e1cf357bb/64a424da/1oi8r3TEWm9OtEPcYeKZN1b7_7_2Fo8fH8bSeN238s53Ofy7q0k_7cBovdY__Mml9nca730aUMgxzo7fZ78T7A%3D%3D?uid=0&filename=diagram-17353247503607978677.png&disposition=inline&hash=&limit=0&content_type=image%2Fpng&owner_uid=0&tknv=v2&size=2048x2048)
        
6. Реализовать ExaminerServiceImpl, который является реализацией интерфейса из прошлого шага. Данный сервис должен внутри себя хранить поля типа QuestionService.
    
    Его задача: создать коллекцию и заполнить её с помощью вызова getRandomQuestion у QuestionService случайными вопросами. 
    
    Учтите:
    
    1.  Вопросы должны быть уникальные, следовательно, для получения 5 вопросов может потребоваться более 5 вызовов метода getRandomQuestion сервиса вопросов.
    2. Если запрошено большее количество вопросов, чем хранится в сервисе, нужно выкинуть исключение. Для этого, соответственно, нужно написать свое исключение со статусом BAD_REQUEST.
    - Архитектура
        
        ![Untitled](https://downloader.disk.yandex.ru/preview/9b4564b62a877f752e3c17e6707012d600bda6e31f96756096bf65eb080333ed/64a4266a/HXXK3JGF4-pHH7QlKGowRVb7_7_2Fo8fH8bSeN238s7KaNkg4ddxBZVdCiQ1qLhjqNCbKlONzMg5hKqbA7RA0w%3D%3D?uid=0&filename=Untitled%20%286%29.png&disposition=inline&hash=&limit=0&content_type=image%2Fpng&owner_uid=0&tknv=v2&size=2048x2048)
        
7. Реализовать контроллер ExamController с одним методом getQuestions(int amount).
    
    Контроллер должен обратиться к ExaminerService, получить от сервиса коллекцию вопросов и вернуть пользователю.
    
8. Покрыть юнит-тестами JavaQuestionService и ExaminerServiceImpl (потребуется мок).

- **Критерии оценки курсовой** (только базовый обязательный уровень):
    - Использованы все методы, указанные в архитектуре
    - В написании кода используется правильное форматирование
    - DI создан с использованием конструктора
    - Юнит-тестами покрыты JavaQuestionService и ExaminerServiceImpl
    - Метод getRandomQuestion не обязательно покрыт юнит-тестами
    - В коллекциях нет двух вопросов, у которых вопрос-ответ имеет одинаковое значение (поля q и a имеют одинаковое значение)
    - Будет плюсом: в коллекциях указаны только уникальные вопросы
    

### Повышенная сложность:

1. Реализовать ещё одну реализацию для QuestionService, а именно MathQuestionService.
    
    Данный сервис должен работать по аналогии с JavaQuestionService, но с математическими примерами.
    
2. Реализовать контроллер MathQuestionController, который позволяет добавлять, удалять и получать список математических вопросов. Для получения конкретной реализации интерфейса QuestionService может потребоваться аннотация @Qualifier.
3. Перенести функцию хранение вопросов из сервисов в отдельные сущности — репозитории. Для этого потребуется реализовать интерфейс QuestionRepository с методами add, remove и getAll. А затем написать две реализации для вопросов по Java и по математике.
    
    Эти сущности нужно заинжектить в соответствующие сервисы и в сервисах “дергать” репозиторий в случае необходимости добавления, удаления и получения вопросов.
    
    Допустимо также реализовать @PostConstruct метод init, который заполнит репозиторий данными сразу после его создания Spring. 
    
    - Архитектура
        
        ![Untitled](https://downloader.disk.yandex.ru/preview/9d6e4fff99b17e4a1e9e600397fae54b6498fe86ef200627272d18694c0fd0df/64a4269c/fT7pvTYaudWOrszZCvWXO1b7_7_2Fo8fH8bSeN238s4RsxSZyok3aYsWhnUid8hN-bDSbm4Ch8fDchDlkBOVjg%3D%3D?uid=0&filename=Untitled%20%285%29.png&disposition=inline&hash=&limit=0&content_type=image%2Fpng&owner_uid=0&tknv=v2&size=2048x2048)
        
4. Доработать ExaminerService на получение случайного набора вопросов не только из JavaQuestionService, но и из MathQuestionService. Включать в запрос вопросы не только по джаве, но и по математике. Количество вопросов по каждой из тем выбирать случайно. Для получения конкретной реализации интерфейса QuestionService может потребоваться аннотация @Qualifier.
5. Доработать JavaQuestionController, так как вторая реализация QuestionService сломала корректный инжект по интерфейсу. Может потребоваться аннотация @Qualifier.
6. Покрыть юнит-тестами MathQuestionService (с моком), оба репозитория.
7. Переработать юнит-тесты для JavaQuestionService с учетом ввода репозитория (добавить мок).
8. Переработать юнит-тесты для ExaminerServiceImpl с учетом добавления второго сервиса вопросов.

### Mastermind:

1. Удалить MathQuestionRepository.
2. Теперь на попытки добавить, удалить и получить все вопросы по математике должно выбрасываться исключение со статусом 405 Method Not Allowed
3. Добавить в метод getRandomQuestion сервиса MathQuestionService генерацию вопросов по математике “на лету”. Это возможно с помощью уже упомянутого ранее класса Random.
4. Избавиться от полей для каждого сервиса вопросов в ExaminerServiceImpl. Собрать их в коллекцию. Переработать способ сборки коллекции вопросов с учетом этого изменения.
