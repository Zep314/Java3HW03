package model;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Model {  // Рабочий класс
    protected String firstName = null;
    protected String lastName = null;
    protected String middleName = null;
    protected LocalDateTime birthday = null;
    protected Integer phone = null;
    protected boolean sex = true;  // true = man
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
    Integer bitMask = 0b00000000;
    //        биты в bitMask:
    //        1 - lastName заполнен
    //        2 - firstName заполнен
    //        3 - middleName заполнен
    //        4 - birthday заполнен
    //        5 - phone заполнен
    //        6 - sex заполнен
    public Model(String input) {


        List<String> list = Arrays.asList(input.split(" "));
        if (list.size() != 6) {
            throw new IllegalArgumentException("Invalid number of arguments");
        }
        for (String item: list) {
            //System.out.println(item);
            if (this.isSex(item)) {
                this.sex = item.equals("m");
                bitMask = bitMask | 0b00100000;
            } else if (this.isPhone(item)) {
                this.phone = Integer.parseInt(item);
                bitMask = bitMask | 0b00010000;
            }
        }



//        this.firstName = "Иван";
//        this.lastName = "Иванов";
//        this.middleName = "Иванович";
//        this.birthday = LocalDateTime.parse("04.05.1986 00:00:00", formatter);
//        this.phone = 123456789;
//        this.sex = true;
    }
    public String getFirstName() {
        return this.firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    public String getMiddleName() {
        return this.middleName;
    }
    public LocalDateTime getBirthday() {
        return this.birthday;
    }
    public Integer getPhone() {
        return this.phone;
    }
    public boolean getSex() {
        return this.sex;
    }
    @Override
    public String toString() {
        return String.format("%s %s %s %s %s %s"
                , this.getLastName()
                , this.getFirstName()
                , this.getMiddleName()
                , this.getBirthday().format(formatter).toString().substring(0,10)
                , this.getPhone().toString()
                , this.getSex() ? "m" : "f"
                );
    }

    private boolean isSex(String item) {
        return item != null && (item.equals("m") || item.equals("f"));
    }

    private boolean isPhone(String item) {
        return item != null && item.matches("[0-9]+");
    }

}
