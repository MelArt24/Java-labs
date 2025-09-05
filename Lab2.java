/* Завдання (UA):
Завдання 2.2
Напишіть консольну програму, яка дозволяє створювати сутність "Запис в журналі куратора".
Для цього потрібно:
    - організувати введення даних з командної строки і передати
    результат введення у відповідну сутність;
    - перевіряти на правильність введення даних (зберігаючи правильно введені) і в
    разі повної коректності всіх даних – передати їх до відповідного класу в моделі;
    якщо дані не відповідають необхідному формату, то запропонувати повторне введення.
    - відображати всі записи журналу.
Сутність "Запис в журналі куратора" описана наступним набором:
    - прізвище студента;
    - ім'я студента;
    - дата народження студента;
    - телефон студента;
    - домашня адреса (вулиця, будинок, квартира).

Task (Eng):
Task 2.2
Write a console program that allows you to create the entity "Curator's Journal Entry".
To do this, you need to:
    - organize data entry from the command line and transfer
    the result of the entry to the corresponding entity;
    - check the correctness of the data entry (saving the correctly entered ones) and
    in the case of complete correctness of all data - transfer them to the corresponding class in the model;
    if the data does not correspond to the required format, then offer to re-enter.
    - display all journal entries.
The entity "Curator's Journal Entry" is described by the following set:
    - student's last name;
    - student's first name;
    - student's date of birth;
    - student's phone number;
    - home address (street, house, apartment). */



import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

class StudentRecord {
    private final String lastName;
    private final String firstName;
    private final String birthDate;
    private final String phone;
    private final String address;

    public StudentRecord(String lastName, String firstName, String birthDate, String phone, String address) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.phone = phone;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Last name: " + lastName +
                ", Name: " + firstName +
                ", Date pf birth: " + birthDate +
                ", Phone number: " + phone +
                ", Address: " + address;
    }
}

public class Lab2 {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<StudentRecord> records = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add entry");
            System.out.println("2. Show all entries");
            System.out.println("3. Exit");
            System.out.print("Your option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addRecord();
                    break;
                case "2":
                    showRecords();
                    break;
                case "3":
                    System.out.println("Exit...");
                    return;
                default:
                    System.out.println("Incorrect choice, try again.");
            }
        }
    }

    private static void addRecord() {
        String lastName = inputWithValidation("Enter student's last name: ", "[А-ЯҐЄІЇа-яґєіїA-Za-z\\-]+");
        String firstName = inputWithValidation("Enter student's name: ", "[А-ЯҐЄІЇа-яґєіїA-Za-z\\-]+");
        String birthDate = inputDate();
        String phone = inputWithValidation("Enter phone number (for example: +380XXXXXXXXX): ", "\\+?\\d{10,13}");
        System.out.print("Enter address (street, house, apartment): ");
        String address = scanner.nextLine();

        StudentRecord record = new StudentRecord(lastName, firstName, birthDate, phone, address);
        records.add(record);
        System.out.println("Entry is added!");
    }

    private static String inputWithValidation(String message, String regex) {
        Pattern pattern = Pattern.compile(regex);
        String input;
        while (true) {
            System.out.print(message);
            input = scanner.nextLine();
            if (pattern.matcher(input).matches()) {
                break;
            } else {
                System.out.println("Invalid format, try again.");
            }
        }
        return input;
    }

    private static void showRecords() {
        if (records.isEmpty()) {
            System.out.println("The journal is empty.");
        } else {
            System.out.println("\n=== Curator's Journal ===");
            for (int i = 0; i < records.size(); i++) {
                System.out.println((i + 1) + ". " + records.get(i));
            }
        }
    }

    private static String inputDate() {
        String input;
        while (true) {
            System.out.print("Enter student's date of birth (format: YYYY-MM-DD): ");
            input = scanner.nextLine();
            try {
                LocalDate.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date, try again.");
            }
        }
        return input;
    }
}
