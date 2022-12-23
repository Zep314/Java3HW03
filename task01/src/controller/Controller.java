package controller;
import view.View;
import model.Model;

import java.io.IOException;
import java.util.Arrays;
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

        boolean exit = false;
        Scanner scan = new Scanner(System.in);
        String inputLine = "";
        while (!exit) {
            this.viewer.printInfo("Введите данные: ");
            // inputLine = scan.nextLine();

            inputLine = "Иванов Иван Иванович 04.05.1986 123456789 m";

            Model data = new Model(inputLine); // Класс для работы

            this.viewer.printInfo(String.format("Вы ввели: %s%n", inputLine));

            this.viewer.printInfo(data.toString());
            exit = true;
        }
        this.viewer.printInfo("Работа завершена.");
    }
}
