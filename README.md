# Проект автоматизации тестирования [Imgur.com](https://imgur.com) с использованием REST-assured

## Сводка
- Требования
- Скачивание и запуск проекта
- Информация о проекте
  - Информация по тестам
  - Информация по отчету Allure
- Автор

## Используемые фреймворки и бибилиотеки
  - [Maven](https://maven.apache.org/)
  - [JUnit](https://junit.org/junit5/)
  - [REST-assured](https://rest-assured.io/)
  - [Allure](http://allure.qatools.ru/)
  - [AspectJ](https://www.eclipse.org/aspectj/)
  - [Lombok](https://projectlombok.org/)
  - [Jackson](https://github.com/FasterXML/jackson)
  - [Faker](https://github.com/DiUS/java-faker)

## Сборка проекта и полуение отчёта
Удаление всех созданных в процессе сборки артефактов: .class, .jar и др. файлов.
В простейшем случае результат — просто удаление каталога target:

    mvn clean

Тестирование с помощью JUnit тестов:

    mvn test
    
Вывод отчёта через фреймворк Allure:

    allure:serve

В случае последовательного использования команд:
    
    mvn clean test allure:serve

## Автор

  - **Ярославцев Антон** - *Автор проекта*
