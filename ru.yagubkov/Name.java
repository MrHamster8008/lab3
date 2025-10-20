package ru.yagubkov.person;

public class Name {
    final String name;
    String surname;
    String fatherName;

    public Name(String name) {
        this.name = name;
        this.surname = null;
        this.fatherName = null;
    }

    public Name(String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.fatherName = null;
    }

    public Name(String name, String surname, String fatherName) {
        this.name = name;
        this.surname = surname;
        this.fatherName = fatherName;
    }

    private boolean notEmpty() {
        return this.name != null || this.surname != null || this.fatherName != null;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        if (name != null) {
            result.append(name).append(" ");
        }
        if (surname != null) {
            result.append(surname).append(" ");
        }
        if (fatherName != null) {
            result.append(fatherName).append(" ");
        }
        return result.toString().trim();
    }
}