Translation Service
Описание
Приложение перевода слов, которое использует Spring Boot для обработки запросов на перевод. Приложение принимает строку, состоящую из набора слов, исходный язык и целевой язык, а затем возвращает переведенную строку. Каждый запрос выполняется в многопоточном режиме с использованием ThreadPool.
Требования
- Java 17
- Maven
- PostgreSQL (или другая реляционная база данных)
Установка и запуск
1. Клонируйте репозиторий
bash
git clone https://github.com/olya807/com.translation.service.git
cd translation_service
2. Настройте базу данных
Создайте базу данных и таблицу для хранения запросов перевода. Пример SQL-запроса для создания таблицы:
sql
Копировать код
CREATE TABLE translation_requests (
    id SERIAL PRIMARY KEY,
    ip_address VARCHAR(255) NOT NULL,
    input_string TEXT NOT NULL,
    translated_string TEXT,
    request_time TIMESTAMP NOT NULL
);
3. Настройте приложение
Создайте файл application.properties в каталоге src/main/resources и добавьте настройки для подключения к вашей базе данных:
properties
Копировать код
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
4. Соберите проект
Используйте Maven для сборки проекта:
bash
Копировать код
mvn clean install
5. Запустите приложение
Запустите приложение с помощью Maven:
bash
Копировать код
mvn spring-boot:run
6. Использование
После запуска приложения, вы можете отправлять POST-запросы на конечную точку /api/translate для перевода текста. Вот пример использования c curl:
bash
Копировать код
curl -X POST "http://localhost:8080/api/translate" \
    -d "inputString=Hello world, this is my first program" \
    -d "sourceLang=en" \
    -d "targetLang=ru"
