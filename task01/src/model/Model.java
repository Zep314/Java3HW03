package model;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.time.format.DateTimeFormatter;
import java.util.List;

import myexception.*;

public class Model {  // Рабочий класс - тут храним информацию по полям. Проверяем корректность ввода
    protected String firstName = null;
    protected String lastName = null;
    protected String middleName = null;
    protected LocalDateTime birthday = null;
    protected Long phone = null;
    protected boolean sex = true;  // true = man; false = woman
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
    private final int MAX_EXCEPTION_COUNT =6;
    Integer bitMask = 0b00000000;
    //        биты в bitMask:
    //        1 - lastName заполнен
    //        2 - firstName заполнен
    //        3 - middleName заполнен
    //        4 - birthday заполнен
    //        5 - phone заполнен
    //        6 - sex заполнен
    public Model(String input) throws NotEnoughArgumentException          // Нехватает аргументов
                                    , TooMuchArgumentException            // Аргументов слишком много
                                    , FieldSexNotFoundException           // Пол не указан
                                    , FieldPhoneNotFoundException         // Номер телефона не указан
                                    , FieldBirthdayNotFoundException      // День рождения не указан
                                    , FieldMiddleNameNotFoundException    // Отчество не указано
                                    , FieldFirstNameNotFoundException     // Имя не указано
                                    , FieldLastNameNotFoundException {    // Фамилия не указана

        List<String> list = Arrays.asList(input.split(" "));  // Делим строку по пробелам
        if (list.size() < 6) {    // проверяем на количество элементов
            throw new NotEnoughArgumentException();  // аргументов не хватает
        } else if (list.size() > 6) {
            throw new TooMuchArgumentException();    // аргументов слишком много
        } else {
            for (String item : list) {  // Пытаемся определить что приходит из списка
                                        // и если определяем - то записываем в соответствующие поля
                                        // и делаем отметки в bitMask, чтобы не писать второй раз
                if (((bitMask & 0b00100000) == 0) && this.isSex(item)) {
                    this.sex = item.equals("m");
                    bitMask = bitMask | 0b00100000;
                } else if (((bitMask & 0b00010000) == 0) && this.isPhone(item)) {
                    this.phone = Long.parseLong(item);
                    bitMask = bitMask | 0b00010000;
                } else if (((bitMask & 0b00001000) == 0) && this.isDate(item)) {
                    this.birthday = LocalDateTime.parse(String.format("%s 00:00:00", item), formatter);
                    bitMask = bitMask | 0b00001000;
                } else if ((bitMask & 0b00000001) == 0) {
                    this.lastName = item;
                    bitMask = bitMask | 0b00000001;
                } else if ((bitMask & 0b00000010) == 0) {
                    this.firstName = item;
                    bitMask = bitMask | 0b00000010;
                } else if ((bitMask & 0b00000100) == 0) {
                    this.middleName = item;
                    bitMask = bitMask | 0b00000100;
                }
            }

            if (bitMask != Math.pow(2,this.MAX_EXCEPTION_COUNT)) {  // если bitMask не равно 0b00111111 - то есть ошибки
                for(int i = 0; i < this.MAX_EXCEPTION_COUNT; i++) {
                    if ((this.bitMask & (int)Math.pow(2,i)) == 0) {  // вычисляем, в каком поле допущена ошибка
                        switch (i) {
                            case 0 -> {
                                throw new FieldLastNameNotFoundException();
                            }
                            case 1 -> {
                                throw new FieldFirstNameNotFoundException();
                            }
                            case 2 -> {
                                throw new FieldMiddleNameNotFoundException();
                            }
                            case 3 -> {
                                throw new FieldBirthdayNotFoundException();
                            }
                            case 4 -> {
                                throw new FieldPhoneNotFoundException();
                            }
                            case 5 -> {
                                throw new FieldSexNotFoundException();
                            }
                        }
                    }
                }
            }
        }
    }
    // Геттеры по всем полям
    public String getFirstName() {
        return (this.firstName != null) ? this.firstName : "";
    }
    public String getLastName() {
        return (this.lastName != null) ? this.lastName : "";
    }
    public String getMiddleName() {
        return (this.middleName != null) ? this.middleName : "";
    }
    public LocalDateTime getBirthday() {
        return (this.birthday != null) ? this.birthday : LocalDateTime.MIN;
    }
    public Long getPhone() {
        return (this.phone != null) ? this.phone : -1;
    }
    public boolean getSex() {
        return this.sex;
    }
    @Override
    public String toString() {  // Для вывода в лог и в файл
        return String.format("%s %s %s %s %s %s"
                , this.getLastName()
                , this.getFirstName()
                , this.getMiddleName()
                , this.getBirthday().format(formatter).toString().substring(0,10)
                , this.getPhone().toString()
                , this.getSex() ? "m" : "f"
                );
    }

    // Проверки введенных значений
    private boolean isSex(String item) {  // пол
        return item != null && (item.equals("m") || item.equals("f"));
    }

    private boolean isPhone(String item) {  // номер телефона
        return item != null && item.matches("[0-9]+");
    }

    private boolean isDate(String item) {  // корректность данных (дата)
        try {
            LocalDateTime.parse(String.format("%s 00:00:00", item), formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    public void saveToFile() throws IOException {  // Запись в файл
        try (FileWriter writer = new FileWriter(String.format("%s.txt",this.lastName),true)) {
            writer.write(String.format("%s%n",this.toString()));
            writer.flush();
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

}
