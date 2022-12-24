package view;

import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class View { // Класс вьювер - отображение на экране и взаимодействие с пользователем
    private final Logger log = Logger.getLogger(View.class.getName());  // Логи

    public View() {
        try {  // Инициализация логгера
            LogManager.getLogManager().readConfiguration( // берем конфиг для логов
                    View.class.getResourceAsStream("../log.config"));
        } catch (IOException e) {  // печаль, беда...
            System.err.println("Could not setup logger configuration: " + e.getMessage());
        }
    }
    public void printInfo(String string) {
        log.info(string);  // Выводим информацию в лог
    }
    public void printWarning(String string) {
        log.warning(string);  // Выводим warning в лог
    }

}
