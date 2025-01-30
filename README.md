# Домашнее задание №15
_______________________________
## Тема задания:
Есть список ссылок в текстовом файле.
Нужно написать программу, которая при старте будет скачивать эти файлы и складывать в указанную
папку на локальном диске.
Программа должна уметь качать несколько файлов одновременно (в несколько потоков, например, 3
потока) и выдерживать указанное ограничение на скорость загрузки, например, 500 килобайт в
секунду.
Программу можно сделать консольной, можно использовать spring-boot.
Для работы с http, с ограничением скорости и любыми манипуляциямис данными можно брать любую
библиотеку.
При желании, можно любую часть сделать самостоятельно.
Нужно учитывать, что программа может развиваться, из нее могут быть переиспользованы полезные
части.
Поэтому крайне желательно использовать абстракции (ООП, интерфейсы, паттерны, SOLID и т.д.).

_______________________________
## Что было реализованно:

были указаны [**`property`**](https://github.com/Mikhayloves/SpringFraemworkSberHomeworkApplication/blob/main/src/main/resources/application.properties) для Spring.
Что это такое?
Properties в Spring — это параметры, которые позволяют настраивать различные аспекты приложения без изменения исходного кода. 
С их помощью настраивают, например, параметры подключения к базе данных, уровни логирования, порт сервера, настройки безопасности и другое. 

в [**`DownloadCommandLineRunner`**](https://github.com/Mikhayloves/SpringFraemworkSberHomeworkApplication/blob/main/src/main/java/com/example/SpringFraemworkSberHomework/commandLineRunner/DownloadCommandLineRunner.java) мы ссылаемся на эти property.

в классе [**`MultiThreadingResourceDownloadService`**](https://github.com/Mikhayloves/SpringFraemworkSberHomeworkApplication/blob/main/src/main/java/com/example/SpringFraemworkSberHomework/service/MultiThreadingResourceDownloadService.java) создаются ограничения по использованию потоков.

![photo_2025-01-30_20-35-37.jpg](https://github.com/Mikhayloves/SpringFraemworkSberHomeworkApplication/blob/main/photo_2025-01-30_20-35-37.jpg)

Ну и в классе [**`ResourceDownloadTask`**] (https://github.com/Mikhayloves/SpringFraemworkSberHomeworkApplication/blob/main/src/main/java/com/example/SpringFraemworkSberHomework/service/ResourceDownloadTask.java) реализованна основная логика скачивания, установка заглушки по скорости скачивания, и по очереде вывод потоков.

Демонстарация корректной роботоспособности
