package ru.yagubkov.weapons;

public class Automat extends WeaponNew {
    private int maxBullets;
    private final int shootSpeed;

    public Automat() {
        super(0);
        this.maxBullets = 30;
        this.shootSpeed = 30;
    }

    public Automat(int maxBullets) {
        super(0);
        this.maxBullets = maxBullets;
        this.shootSpeed = maxBullets / 2;
    }

    public Automat(int maxBullets, int shootSpeed) {
        super(0);
        this.maxBullets = maxBullets;
        this.shootSpeed = shootSpeed;
    }

    @Override
    public void shoot() {
        for (int i = 0; i < shootSpeed; i++) {
            if (ammo > 0) {
                System.out.println("БАХ");
                ammo--;
            } else {
                System.out.println("Клац");
            }
        }
    }

    public void shootSeconds(int seconds) {
        int totalShots = seconds * shootSpeed;
        for (int i = 0; i < totalShots; i++) {
            if (ammo > 0) {
                System.out.println("БАХ");
                ammo--;
            } else {
                System.out.println("Клац");
            }
        }
    }

    public void reload(int bullets) {
        if (bullets <= 0) {
            System.out.println("Недопустимое число");
            return;
        }

        int totalAmmo = ammo + bullets;
        if (totalAmmo > maxBullets) {
            int excess = totalAmmo - maxBullets;
            System.out.println("Загружено слишком много патронов. Избыток возвращен владельцу");
            ammo = maxBullets;
        } else {
            System.out.println("Патроны успешно загружены");
            ammo = totalAmmo;
        }
    }

    public void unload() {
        System.out.println("Владельцу возвращено " + ammo + " патронов");
        ammo = 0;
    }

    public void howManyBullets() {
        System.out.println("Текущее кол-во патронов: " + ammo);
    }

    public void isLoaded() {
        if (ammo == 0) {
            System.out.println("Разряжен");
        } else {
            System.out.println("Заряжен");
        }
    }
}