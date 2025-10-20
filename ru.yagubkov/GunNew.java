package ru.yagubkov.weapons;

public class GunNew extends Weapon {
    private int maxBullets;

    public GunNew() {
        super(3);
        this.maxBullets = 3;
    }

    public GunNew(int maxBullets) {
        super(0);
        if (maxBullets <= 0) {
            throw new IllegalArgumentException("Максимальное количество патронов должно быть положительным");
        }
        this.maxBullets = maxBullets;
    }

    @Override
    public void shoot() {
        if (ammo() > 0) {
            System.out.println("БАХ");
            getAmmo();
        } else {
            System.out.println("Клац");
        }
    }

    @Override
    public int load(int ammo) {
        if (ammo < 0) {
            System.out.println("Недопустимое число");
            return ammo;
        }

        int currentAmmo = ammo();
        int totalAmmo = currentAmmo + ammo;

        if (totalAmmo > maxBullets) {
            int excess = totalAmmo - maxBullets;
            System.out.println("Загружено слишком много патронов. Избыток возвращен владельцу");
            super.load(maxBullets);
            return excess;
        } else {
            System.out.println("Патроны успешно загружены");
            super.load(totalAmmo);
            return 0;
        }
    }

    public void unload() {
        int currentAmmo = ammo();
        System.out.println("Владельцу возвращено " + currentAmmo + " патронов");
        super.load(0);
    }

    public void howManyBullets() {
        System.out.println("Текущее кол-во патронов: " + ammo());
    }

    public void isLoaded() {
        if (ammo() == 0) {
            System.out.println("Разряжен");
        } else {
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
        if (ammo() > maxBullets) {
            super.load(maxBullets);
        }
    }
}