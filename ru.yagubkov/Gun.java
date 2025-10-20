package ru.yagubkov.weapons;

public class Gun {
    private int bullets;
    private int maxBullets;

    public Gun() {
        this.bullets = 3;
    }

    public Gun(int maxBullets) {
        this.maxBullets = maxBullets;
    }

    public void reload(int bullets) {
        this.bullets = 0;
        if (bullets <= 0) {
            System.out.println("Недопустимое число");
        } else if (this.maxBullets - bullets < 0) {
            System.out.println("Загружено слишком много патронов. Избыток возвращен владельцу");
            this.bullets = this.maxBullets;
        } else {
            System.out.println("Патроны успешно загружены");
            this.bullets = bullets;
        }
    }

    public void unload() {
        System.out.println("Владельцу возвращено " + this.bullets + " патронов");
        this.bullets = 0;
    }

    public void howManyBullets() {
        System.out.println("Текущее кол-во патронов: " + this.bullets);
    }

    public void isLoaded() {
        if (this.bullets == 0) {
            System.out.println("Разряжен");
        } else {
            System.out.println("Заряжен");
        }
    }

    public void shoot() {
        if (this.bullets > 0) {
            System.out.println("БАХ");
            this.bullets--;
        } else {
            System.out.println("Клац");
        }
    }
}