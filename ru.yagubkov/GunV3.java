package ru.yagubkov.weapons;

public class GunV3 extends WeaponNew {
    private int maxBullets;

    public GunV3() {
        super(3);
        this.maxBullets = 3;
    }

    public GunV3(int maxBullets) {
        super(0);
        if (maxBullets <= 0) {
            throw new IllegalArgumentException("Максимальное количество патронов должно быть положительным");
        }
        this.maxBullets = maxBullets;
    }

    @Override
    public void shoot() {
        if (ammo > 0) {
            System.out.println("БАХ");
            ammo--;
        }
        else {
            System.out.println("Клац");
        }
    }

    @Override
    public int load(int ammo) {
        if (ammo < 0) {
            System.out.println("Недопустимое число");
            return ammo;
        }

        int totalAmmo = this.ammo + ammo;

        if (totalAmmo > maxBullets) {
            int excess = totalAmmo - maxBullets;
            System.out.println("Загружено слишком много патронов. Избыток возвращен владельцу");
            this.ammo = maxBullets;
            return excess;
        }
        else {
            System.out.println("Патроны успешно загружены");
            this.ammo = totalAmmo;
            return 0;
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
        }
        else {
            System.out.println("Заряжен");
        }
    }

    public int getMaxBullets() {
        return maxBullets;
    }

    public void setMaxBullets(int maxBullets) {
        if (maxBullets <= 0) {
            throw new IllegalArgumentException("Максимальное количество патронов должно быть положительным");
        }
        this.maxBullets = maxBullets;
        if (ammo > maxBullets) {
            ammo = maxBullets;
        }
    }
}