package ru.yagubkov.main;

import java.util.Scanner;
import static java.lang.Integer.parseInt;
import static java.lang.Math.pow;

import ru.yagubkov.geometry.Point;
import ru.yagubkov.person.Name;
import ru.yagubkov.person.Shooter;
import ru.yagubkov.util.PhoneDirectory;
import ru.yagubkov.weapons.Gun;
import ru.yagubkov.weapons.GunNew;
import ru.yagubkov.weapons.Weapon;
import ru.yagubkov.weapons.Automat;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static double power(String xStr, String yStr) {
        int x = parseInt(xStr);
        int y = parseInt(yStr);
        return pow(x, y);
    }

    public static void task1() {
        System.out.println("Макс патронов в пистолете");
        int bullets = scanner.nextInt();
        Gun gun = new Gun(bullets);

        boolean running = true;

        while (running) {
            System.out.println("1 - Перезарядить пистолет");
            System.out.println("2 - Разрядить пистолет");
            System.out.println("3 - Выстрелить");
            System.out.println("0 - Выход");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine();


            switch (choice) {
                case 1:
                    System.out.println("Сколько патронов зарядить? :");
                    bullets = scanner.nextInt();
                    gun.reload(bullets);
                    break;
                case 2:
                    System.out.println("Пистолет разряжен");
                    gun.unload();
                    break;
                case 3:
                    System.out.println("Сколько раз выстрелить? ");
                    int shoots = scanner.nextInt();
                    for (int i = 0;i<=shoots;i++){
                        gun.shoot();
                    }

            }
        }
    }

    public static void task2() {
        System.out.println("Непустые Имена");

        System.out.print("Введите имя: ");
        String name = scanner.nextLine();

        System.out.print("Введите фамилию (или Enter чтобы пропустить): ");
        String surname = scanner.nextLine();
        if (surname.isEmpty()) surname = null;

        System.out.print("Введите отчество (или Enter чтобы пропустить): ");
        String fatherName = scanner.nextLine();
        if (fatherName.isEmpty()) fatherName = null;

        try {
            Name personName;
            if (fatherName != null) {
                personName = new Name(name, surname, fatherName);
            }
            else if (surname != null) {
                personName = new Name(name, surname);
            }
            else {
                personName = new Name(name);
            }
            System.out.println("Созданное имя: " + personName);
        }
        catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        System.out.println("Попытка создать пустое имя:");
        try {
            Name emptyName = new Name(null, null, null);
        }
        catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public static void task3() {
        System.out.println("Желтые страницы");

        PhoneDirectory directory = new PhoneDirectory();
        boolean running = true;

        while (running) {
            System.out.println("1 - Добавить контакт");
            System.out.println("2 - Удалить контакт");
            System.out.println("3 - Найти телефон по имени");
            System.out.println("4 - Найти имя по телефону");
            System.out.println("5 - Показать все контакты");
            System.out.println("6 - Поиск по началу имени");
            System.out.println("7 - Количество контактов");
            System.out.println("0 - Выход");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Введите телефон: ");
                    String phone = scanner.nextLine();
                    System.out.print("Введите имя: ");
                    String name = scanner.nextLine();
                    try {
                        String oldPhone = directory.addContact(phone, name);
                        if (oldPhone != null) {
                            System.out.println("Обновлен. Старый телефон: " + oldPhone);
                        } else {
                            System.out.println("Добавлен");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Введите имя для удаления: ");
                    String removeName = scanner.nextLine();
                    boolean removed = directory.removeContactByName(removeName);
                    System.out.println(removed ? "Удален" : "Не найден");
                    break;

                case 3:
                    System.out.print("Введите имя для поиска: ");
                    String searchName = scanner.nextLine();
                    String foundPhone = directory.getPhoneByName(searchName);
                    System.out.println(foundPhone != null ? "Телефон: " + foundPhone : "Не найден");
                    break;

                case 4:
                    System.out.print("Введите телефон для поиска: ");
                    String searchPhone = scanner.nextLine();
                    String foundName = directory.getNameByPhone(searchPhone);
                    System.out.println(foundName != null ? "Имя: " + foundName : "Не найден");
                    break;

                case 5:
                    System.out.println("Все контакты:");
                    System.out.println(directory);
                    break;

                case 6:
                    System.out.print("Введите начало имени: ");
                    String prefix = scanner.nextLine();
                    String[] names = directory.findNamesByPrefix(prefix);
                    if (names.length > 0) {
                        for (String n : names) {
                            System.out.println("- " + n);
                        }
                    } else {
                        System.out.println("Не найдено");
                    }
                    break;

                case 7:
                    System.out.println("Количество контактов: " + directory.getContactCount());
                    break;

                case 0:
                    running = false;
                    break;

                default:
                    System.out.println("Неверный выбор");
            }
        }
    }

    public static void task4() {
        System.out.println("Автомат");

        System.out.println("1 - Автомат по умолчанию");
        System.out.println("2 - Автомат с указанием обоймы");
        System.out.println("3 - Автомат с обоймой и скорострельностью");
        System.out.print("Выберите тип: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        Automat automat;
        switch (choice) {
            case 1:
                automat = new Automat();
                break;
            case 2:
                System.out.print("Введите размер обоймы: ");
                int maxBullets = scanner.nextInt();
                scanner.nextLine();
                automat = new Automat(maxBullets);
                break;
            case 3:
                System.out.print("Введите размер обоймы: ");
                int maxB = scanner.nextInt();
                System.out.print("Введите скорострельность: ");
                int speed = scanner.nextInt();
                scanner.nextLine();
                automat = new Automat(maxB, speed);
                break;
            default:
                automat = new Automat();
        }

        System.out.print("Сколько патронов зарядить: ");
        int ammo = scanner.nextInt();
        scanner.nextLine();
        automat.reload(ammo);

        System.out.print("Сколько раз выстрелить: ");
        int shots = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < shots; i++) {
            automat.shoot();
        }

        System.out.print("Стрелять сколько секунд: ");
        int seconds = scanner.nextInt();
        scanner.nextLine();
        automat.shootSeconds(seconds);
    }

    public static void task5() {
        System.out.println("Введите макс патронов");
        int bullets = scanner.nextInt();
        GunNew gun = new GunNew(bullets);
        System.out.println("Введите сколько зарядить");
        bullets = scanner.nextInt();
        gun.load(bullets);
        System.out.println("Введите сколько выстрелить");
        int shoots = scanner.nextInt();
        System.out.println("Стрельба ");
        for (int i = 0; i <= shoots; i++) {
            gun.shoot();
        }
        gun.howManyBullets();
        gun.isLoaded();
        System.out.println("Разряжаем:");
        gun.unload();
        gun.isLoaded();
    }

    public static void task6() {
        System.out.println("Задайте имя стрелку");
        String name = scanner.nextLine();
        Shooter unarmed = new Shooter(name);
        System.out.println("Задайте имя 2 стрелку");
        name = scanner.nextLine();
        System.out.println("Задайте макс патроны");
        int bullets = scanner.nextInt();
        Shooter withPistol = new Shooter(name, new GunNew(bullets));
        System.out.println("Сколько зарядить");
        bullets = scanner.nextInt();
        ((GunNew) withPistol.getWeapon()).load(bullets);
        System.out.println("Выстрелы стрелков:");
        unarmed.shoot();
        withPistol.shoot();
        System.out.println("Информация о стрелках:");
        unarmed.displayInfo();
        withPistol.displayInfo();

    }

    public static void task7() {
        System.out.println("Сравнение точек");

        System.out.print("Введите X для точки 1: ");
        double x1 = scanner.nextDouble();
        System.out.print("Введите Y для точки 1: ");
        double y1 = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Введите X для точки 2: ");
        double x2 = scanner.nextDouble();
        System.out.print("Введите Y для точки 2: ");
        double y2 = scanner.nextDouble();
        scanner.nextLine();

        Point p1 = new Point(x1, y1);
        Point p2 = new Point(x2, y2);

        System.out.println("Точка 1: " + p1);
        System.out.println("Точка 2: " + p2);
        System.out.println("Точки равны: " + p1.equals(p2));
    }

    public static void task8() {
        System.out.println("Возведение в степень");

        System.out.print("Введите число X: ");
        String xStr = scanner.nextLine();

        System.out.print("Введите степень Y: ");
        String yStr = scanner.nextLine();

        try {
            double result = power(xStr, yStr);
            System.out.println(xStr + " в степени " + yStr + " = " + result);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: введите целые числа");
        }
    }

    public static void task9() {
        System.out.println("Патроны наследникам");

        GunNew gun = new GunNew(5);
        gun.load(3);

        System.out.println("Доступ к патронам через protected поле:");
        gun.howManyBullets();

        System.out.println("Стрельба:");
        gun.shoot();
        gun.shoot();

        System.out.println("После стрельбы:");
        gun.howManyBullets();

        System.out.println("Упрощенная разрядка:");
        gun.unload();
        gun.howManyBullets();
    }

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("1 - Перезарядка Пистолета");
            System.out.println("2 - Непустые Имена");
            System.out.println("3 - Желтые страницы");
            System.out.println("4 - Автомат");
            System.out.println("5 - Оружие");
            System.out.println("6 - Лучший стрелок");
            System.out.println("7 - Сравнение точек");
            System.out.println("8 - Возведение в степень");
            System.out.println("9 - Патроны наследникам");
            System.out.println("0 - Выход");
            System.out.print("Выберите задание: ");

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Ошибка ввода");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    task1();
                    break;
                case 2:
                    task2();
                    break;
                case 3:
                    task3();
                    break;
                case 4:
                    task4();
                    break;
                case 5:
                    task5();
                    break;
                case 6:
                    task6();
                    break;
                case 7:
                    task7();
                    break;
                case 8:
                    task8();
                    break;
                case 9:
                    task9();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор");
            }

            if (running && choice != 0) {
                System.out.print("Нажмите Enter для продолжения...");
                scanner.nextLine();
            }
        }

        scanner.close();
    }
}
