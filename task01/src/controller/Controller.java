package controller;
import view.View;
import model.Model;
import myexception.*;

import java.io.IOException;
import java.util.Scanner;

public class Controller {  // Класс - контроллер - тут все и происходит
    View viewer = new View();  // вьювер

    public Controller() {
    }

    public void run() {
        this.viewer.printInfo("Начало работы!");
        this.viewer.printInfo("Введите фамилию, имя, отчество, дату рождения, номер телефона  и пол, разделенные пробелом.");
        this.viewer.printInfo("Форматы данных:");
        this.viewer.printInfo("Фамилия, имя и отчество - строки");
        this.viewer.printInfo("Дата_рождения - строка формата <dd.mm.yyyy>");
        this.viewer.printInfo("Номер_телефона - целое беззнаковое число без форматирования");
        this.viewer.printInfo("Пол - символ латиницей f или m");
        this.viewer.printInfo("Ввод \"quit\" или \"exit\" - выход из программы");

        boolean exit = false;
        Scanner scan = new Scanner(System.in);
        String inputLine = "";
        while (!exit) {
            this.viewer.printInfo("Введите данные: ");
            inputLine = scan.nextLine();
            // inputLine = "Иванов Иван Иванович 04.05.1986 123456789 m";  // Для отладки!!!!
            if (inputLine.equals("quit") || inputLine.equals("exit")) {
                this.viewer.printWarning("Аварийное завершение программы");
                break;
            }
            try {
                this.viewer.printInfo(String.format("Вы ввели: %s%n", inputLine));
                Model data = new Model(inputLine); // Класс для работы - в конструкторе проверям корректность ввода
                                                   // в нем же - генерируем исключения, если надо
                this.viewer.printInfo("Данные введены корректно.");
                this.viewer.printInfo(String.format("Данные записаны в структуру: %s", data.toString()));
                data.saveToFile();
                this.viewer.printInfo("Будете еще вводить данные? ([Y]/n)");
                inputLine = scan.nextLine();
                if (!(inputLine.isEmpty() || (inputLine.toUpperCase().charAt(0) == 'Y'))) {
                    exit = true;
                }
            // Обработка возможно возникших исключений
            } catch (NotEnoughArgumentException e) {
                this.viewer.printWarning("Введенных данных недостаточно! Введите данные заново!");
            } catch (TooMuchArgumentException e) {
                this.viewer.printWarning("Введенных данных слишком много! Введите данные заново!");
            } catch (FieldSexNotFoundException e) {
                this.viewer.printWarning("Поле \"Пол\" не найдено! Введите данные заново!");
            } catch (FieldPhoneNotFoundException e) {
                this.viewer.printWarning("Поле \"Номер телефона\" не найдено! Введите данные заново!");
            } catch (FieldBirthdayNotFoundException e) {
                this.viewer.printWarning("Поле \"День рождения\" не найдено! Введите данные заново!");
            } catch (FieldLastNameNotFoundException e) {
                this.viewer.printWarning("Поле \"Фамилия\" не найдено! Введите данные заново!");
            } catch (FieldFirstNameNotFoundException e) {
                this.viewer.printWarning("Поле \"Имя\" не найдено! Введите данные заново!");
            } catch (FieldMiddleNameNotFoundException e) {
                this.viewer.printWarning("Поле \"Отчество\" не найдено! Введите данные заново!");
            } catch (IOException e) {
                this.viewer.printWarning(String.format("Ошибка ввода/вывода: %s",e.getMessage()));
            } catch (Exception e) { // Вдруг что-то непонятное
                this.viewer.printWarning("Неизвестная ошибка! Введите данные заново!");
            }
        }
        this.viewer.printInfo("Работа завершена.");
    }
}
