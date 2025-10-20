package ru.yagubkov.person;

import ru.yagubkov.weapons.Weapon;
import ru.yagubkov.weapons.GunNew;

public class Shooter {
    private String name;
    private Weapon weapon;

    public Shooter(String name) {
        this.name = name;
        this.weapon = null;
    }

    public Shooter(String name, Weapon weapon) {
        this.name = name;
        this.weapon = weapon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void shoot() {
        if (weapon == null) {
            System.out.println(name + ": не могу участвовать в перестрелке");
        } else {
            System.out.print(name + ": ");
            weapon.shoot();
        }
    }

    public void displayInfo() {
        System.out.println("Стрелок: " + name);
        if (weapon == null) {
            System.out.println("Оружие: отсутствует");
        } else {
            System.out.println("Оружие: " + weapon.getClass().getSimpleName());
            if (weapon instanceof GunNew) {
                GunNew gun = (GunNew) weapon;
                gun.howManyBullets();
            }
        }
        System.out.println("---");
    }
}