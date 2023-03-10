/*
Задание:
Напишите приложение, которое будет запрашивать у пользователя следующие данные в произвольном порядке, разделенные пробелом:
Фамилия Имя Отчество датарождения номертелефона пол
Форматы данных:
фамилия, имя, отчество - строки
дата_рождения - строка формата dd.mm.yyyy
номер_телефона - целое беззнаковое число без форматирования
пол - символ латиницей f или m.
- Приложение должно проверить введенные данные по количеству.
- Если количество не совпадает с требуемым, вернуть код ошибки, обработать его и показать пользователю сообщение,
  что он ввел меньше и больше данных, чем требуется.
- Приложение должно попытаться распарсить полученные значения и выделить из них требуемые параметры.
- Если форматы данных не совпадают, нужно бросить исключение, соответствующее типу проблемы.
  Можно использовать встроенные типы java и создать свои.
- Исключение должно быть корректно обработано, пользователю выведено сообщение с информацией, что именно неверно.
- Если всё введено и обработано верно, должен создаться файл с названием, равным фамилии, в него в одну строку
  должны записаться полученные данные, вида
  <Фамилия><Имя><Отчество><датарождения> <номертелефона><пол>
- Однофамильцы должны записаться в один и тот же файл, в отдельные строки.
- Не забудьте закрыть соединение с файлом.
- При возникновении проблемы с чтением-записью в файл, исключение должно быть корректно обработано, пользователь должен
  увидеть стектрейс ошибки.
 */
package com.my;

import controller.Controller;

public class Main {  // точка входа
    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.run();
    }
}

/* Вывод программы:
[2022-12-24 00:47:01] [INFO   ] Начало работы!
[2022-12-24 00:47:01] [INFO   ] Введите фамилию, имя, отчество, дату рождения, номер телефона  и пол, разделенные пробелом.
[2022-12-24 00:47:01] [INFO   ] Форматы данных:
[2022-12-24 00:47:01] [INFO   ] Фамилия, имя и отчество - строки
[2022-12-24 00:47:01] [INFO   ] Дата_рождения - строка формата <dd.mm.yyyy>
[2022-12-24 00:47:01] [INFO   ] Номер_телефона - целое беззнаковое число без форматирования
[2022-12-24 00:47:01] [INFO   ] Пол - символ латиницей f или m
[2022-12-24 00:47:01] [INFO   ] Ввод "quit" или "exit" - выход из программы
[2022-12-24 00:47:01] [INFO   ] Введите данные:
Иванов Иван Иванович 04.05.1986 79061119900 m
[2022-12-24 00:47:09] [INFO   ] Вы ввели: Иванов Иван Иванович 04.05.1986 79061119900 m

[2022-12-24 00:47:09] [INFO   ] Данные введены корректно.
[2022-12-24 00:47:09] [INFO   ] Данные записаны в структуру: Иванов Иван Иванович 04.05.1986 79061119900 m
[2022-12-24 00:47:09] [INFO   ] Будете еще вводить данные? ([Y]/n)

[2022-12-24 00:47:10] [INFO   ] Введите данные:
Петрова Ирина Сергеевна 79228881122 f 26.02.2002
[2022-12-24 00:47:16] [INFO   ] Вы ввели: Петрова Ирина Сергеевна 79228881122 f 26.02.2002

[2022-12-24 00:47:16] [INFO   ] Данные введены корректно.
[2022-12-24 00:47:16] [INFO   ] Данные записаны в структуру: Петрова Ирина Сергеевна 26.02.2002 79228881122 f
[2022-12-24 00:47:16] [INFO   ] Будете еще вводить данные? ([Y]/n)

[2022-12-24 00:47:18] [INFO   ] Введите данные:
m Сергеев 19.12.1985 Сергей 79120001234 Сергеевич
[2022-12-24 00:47:27] [INFO   ] Вы ввели: m Сергеев 19.12.1985 Сергей 79120001234 Сергеевич

[2022-12-24 00:47:27] [INFO   ] Данные введены корректно.
[2022-12-24 00:47:27] [INFO   ] Данные записаны в структуру: Сергеев Сергей Сергеевич 19.12.1985 79120001234 m
[2022-12-24 00:47:27] [INFO   ] Будете еще вводить данные? ([Y]/n)

[2022-12-24 00:47:29] [INFO   ] Введите данные:
79029990099 Иванов Саавва 13.08.1993 Пантелеймонович m
[2022-12-24 00:47:38] [INFO   ] Вы ввели: 79029990099 Иванов Саавва 13.08.1993 Пантелеймонович m

[2022-12-24 00:47:38] [INFO   ] Данные введены корректно.
[2022-12-24 00:47:38] [INFO   ] Данные записаны в структуру: Иванов Саавва Пантелеймонович 13.08.1993 79029990099 m
[2022-12-24 00:47:38] [INFO   ] Будете еще вводить данные? ([Y]/n)

[2022-12-24 00:47:39] [INFO   ] Введите данные:
22.11.2002 79093334455 m Иванов Илья Васильевич
[2022-12-24 00:47:45] [INFO   ] Вы ввели: 22.11.2002 79093334455 m Иванов Илья Васильевич

[2022-12-24 00:47:45] [INFO   ] Данные введены корректно.
[2022-12-24 00:47:45] [INFO   ] Данные записаны в структуру: Иванов Илья Васильевич 22.11.2002 79093334455 m
[2022-12-24 00:47:45] [INFO   ] Будете еще вводить данные? ([Y]/n)

[2022-12-24 00:47:52] [INFO   ] Введите данные:
Иванов Иван Иванович 04.05.1986 -79061119900 m
[2022-12-24 00:48:05] [INFO   ] Вы ввели: Иванов Иван Иванович 04.05.1986 -79061119900 m

[2022-12-24 00:48:05] [WARNING] Поле "Номер телефона" не найдено! Введите данные заново!
[2022-12-24 00:48:05] [INFO   ] Введите данные:
Иванов Иван Иванович 04.05.1986 79061119900 m 79121112233
[2022-12-24 00:48:28] [INFO   ] Вы ввели: Иванов Иван Иванович 04.05.1986 79061119900 m 79121112233

[2022-12-24 00:48:28] [WARNING] Введенных данных слишком много! Введите данные заново!
[2022-12-24 00:48:28] [INFO   ] Введите данные:
Иванов Иван Иванович 04.05.1986
[2022-12-24 00:48:39] [INFO   ] Вы ввели: Иванов Иван Иванович 04.05.1986

[2022-12-24 00:48:39] [WARNING] Введенных данных недостаточно! Введите данные заново!
[2022-12-24 00:48:39] [INFO   ] Введите данные:
Иванов Иван Иванович 04.05.1986 79061119900 z
[2022-12-24 00:48:54] [INFO   ] Вы ввели: Иванов Иван Иванович 04.05.1986 79061119900 z

[2022-12-24 00:48:54] [WARNING] Поле "Пол" не найдено! Введите данные заново!
[2022-12-24 00:48:54] [INFO   ] Введите данные:
m Сергеев 19.13.1985 Сергей 79120001234 Сергеевич
[2022-12-24 00:49:25] [INFO   ] Вы ввели: m Сергеев 19.13.1985 Сергей 79120001234 Сергеевич

[2022-12-24 00:49:25] [WARNING] Поле "День рождения" не найдено! Введите данные заново!
[2022-12-24 00:49:25] [INFO   ] Введите данные:
quit
[2022-12-24 00:49:36] [WARNING] Аварийное завершение программы
[2022-12-24 00:49:36] [INFO   ] Работа завершена.
 */