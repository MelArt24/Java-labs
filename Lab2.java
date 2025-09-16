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
import java.time.Period;
import java.util.function.Function;

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
                ", Date of birth: " + birthDate +
                ", Phone number: " + phone +
                ", Address: " + address;
    }
}

public class Lab2 {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<StudentRecord> records = new ArrayList<>();

    private static final int NAME_MIN_LEN = 2;
    private static final int NAME_MAX_LEN = 50;
    private static final int ADDRESS_MAX_LEN = 200;
    private static final int MAX_INPUT_LENGTH = 1000;
    private static final int MIN_AGE = 14;
    private static final int MAX_AGE = 120;
    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("uuuu-MM-dd")
            .withResolverStyle(java.time.format.ResolverStyle.STRICT);


    static class ValidationResult {
        final boolean valid;
        final String errorMessage;

        ValidationResult(boolean valid, String errorMessage) {
            this.valid = valid;
            this.errorMessage = errorMessage;
        }

        static ValidationResult ok() {
            return new ValidationResult(true, "");
        }

        static ValidationResult error(String msg) {
            return new ValidationResult(false, msg);
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add entry");
            System.out.println("2. Show all entries");
            System.out.println("3. Exit");
            System.out.print("Your option: ");
            String choice = readLineWithLimit();

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
        String lastName = inputWithValidation(
                "Enter student's last name: ",
                Lab2::validateName
        );

        String firstName = inputWithValidation(
                "Enter student's name: ",
                Lab2::validateName
        );

        String birthDate = inputDate();

        String phone = inputWithValidation(
                "Enter phone number (format: +380XXXXXXXXX): ",
                Lab2::validatePhone
        );

        String address = inputWithValidation(
                "Enter address (City, Street, house, apartment): ",
                Lab2::validateAddress
        );

        StudentRecord record = new StudentRecord(lastName, firstName, birthDate, phone, address);
        records.add(record);
        System.out.println("Entry is added!");
    }

    private static String readLineWithLimit() {
        String line = scanner.nextLine();
        if (line.length() > MAX_INPUT_LENGTH) {
            System.out.println("Input too long. It will be truncated to " + MAX_INPUT_LENGTH + " chars.");
            return line.substring(0, MAX_INPUT_LENGTH);
        }
        return line;
    }

    private static String inputWithValidation(String message, Function<String, ValidationResult> validator) {
        while (true) {
            System.out.print(message);
            String input = readLineWithLimit().trim();
            ValidationResult result = validator.apply(input);
            if (result.valid) {
                return input;
            } else {
                System.out.println(result.errorMessage);
            }
        }
    }

    private static ValidationResult validateName(String s) {
        if (s == null || s.isEmpty()) return ValidationResult.error("Name cannot be empty.");
        if (s.length() < NAME_MIN_LEN || s.length() > NAME_MAX_LEN)
            return ValidationResult.error("Length must be between " + NAME_MIN_LEN + " and " + NAME_MAX_LEN + " characters.");

        Pattern p = Pattern.compile("^[\\p{L}'\\- ]+$");
        if (!p.matcher(s).matches())
            return ValidationResult.error("Only letters, apostrophes, hyphens and spaces are allowed.");

        if (!Character.isUpperCase(s.charAt(0)))
            return ValidationResult.error("First letter must be uppercase.");

        return ValidationResult.ok();
    }

    // +380XXXXXXXXX
    private static ValidationResult validatePhone(String s) {
        if (s == null || s.isEmpty())
            return ValidationResult.error("Phone cannot be empty.");

        Pattern p = Pattern.compile("^\\+380\\d{9}$");
        if (!p.matcher(s).matches())
            return ValidationResult.error("Invalid phone. Required format: +380 followed by 9 digits (e.g. +380631234567).");

        return ValidationResult.ok();
    }

    private static ValidationResult validateAddress(String s) {
        if (s == null || s.trim().isEmpty()) {
            return ValidationResult.error("Address cannot be empty.");
        }
        if (s.length() > ADDRESS_MAX_LEN) {
            return ValidationResult.error("Address too long. Max " + ADDRESS_MAX_LEN + " characters.");
        }

        // City, Street, house, apartment
        String[] parts = s.split(",");
        if (parts.length != 4) {
            return ValidationResult.error("Address must be in format: City, Street, house, apartment");
        }

        String city = parts[0].trim();
        String street = parts[1].trim();
        String house = parts[2].trim();
        String apartment = parts[3].trim();

        Pattern namePattern = Pattern.compile("^[\\p{L}'\\-]+$");
        if (!city.isEmpty()) {
            if (!namePattern.matcher(city).matches()) {
                return ValidationResult.error("City must contain only letters, apostrophes or hyphens.");
            }
            if (!Character.isUpperCase(city.charAt(0))) {
                return ValidationResult.error("City must start with an uppercase letter.");
            }
        } else {
            return ValidationResult.error("City cannot be empty.");
        }

        if (!street.isEmpty()) {
            if (!namePattern.matcher(street).matches()) {
                return ValidationResult.error("Street must contain only letters, apostrophes or hyphens.");
            }
            if (!Character.isUpperCase(street.charAt(0))) {
                return ValidationResult.error("Street must start with an uppercase letter.");
            }
        } else {
            return ValidationResult.error("Street cannot be empty.");
        }

        Pattern housePattern = Pattern.compile("^\\d+([-/]\\d+)?$");
        if (!housePattern.matcher(house).matches()) {
            return ValidationResult.error("House must be a number (e.g. 12 or 12-14 or 12/14).");
        }

        Pattern aptPattern = Pattern.compile("^\\d+$");
        if (!aptPattern.matcher(apartment).matches()) {
            return ValidationResult.error("Apartment must be a number.");
        }

        return ValidationResult.ok();
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
        while (true) {
            System.out.print("Enter student's date of birth (format: YYYY-MM-DD): ");
            String input = readLineWithLimit().trim();
            try {
                LocalDate date = LocalDate.parse(input, DATE_FMT);
                LocalDate today = LocalDate.now();

                if (date.isAfter(today)) {
                    System.out.println("Date must be in the past.");
                    continue;
                }

                int age = Period.between(date, today).getYears();
                if (age < MIN_AGE) {
                    System.out.println("Student is too young. Minimum age is " + MIN_AGE + ".");
                    continue;
                }
                if (age > MAX_AGE) {
                    System.out.println("Unrealistic age (" + age + "). Maximum allowed is " + MAX_AGE + ".");
                    continue;
                }
                LocalDate earliest = LocalDate.of(1900, 1, 1);
                if (date.isBefore(earliest)) {
                    System.out.println("Date is too old. Please enter a realistic birth date (>= 1900-01-01).");
                    continue;
                }
                return date.format(DATE_FMT);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format, try again (YYYY-MM-DD).");
            } catch (Exception e) {
                System.out.println("Error processing the date, try again.");
            }
        }
    }
}
