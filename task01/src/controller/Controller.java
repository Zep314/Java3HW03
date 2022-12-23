package controller;
import view.View;
import model.Model;

import java.io.IOException;
import java.util.Arrays;

public class Controller {  // Класс - контроллер - тут все и происходит
    View viewer = new View();  // вьювер
    Model model = new Model(); // Класс для работы

    public Controller() {
    }

    public void run() {
        this.viewer.printInfo("Начало работы!");

        this.viewer.printInfo("Работа завершена.");
    }
}
