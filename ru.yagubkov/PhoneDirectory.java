package ru.yagubkov.util;

import java.util.HashMap;
import java.util.stream.Collectors;

public class PhoneDirectory {
    private final HashMap<String, String> phoneToName;
    private final HashMap<String, String> nameToPhone;

    public PhoneDirectory() {
        this.phoneToName = new HashMap<>();
        this.nameToPhone = new HashMap<>();
    }

    public String addContact(String phone, String name) {
        if (phone == null || name == null || phone.trim().isEmpty() || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Телефон не должен быть пустым");
        }

        String oldPhone = null;

        if (nameToPhone.containsKey(name)) {
            oldPhone = nameToPhone.get(name);
            phoneToName.remove(oldPhone);
        }

        if (phoneToName.containsKey(phone)) {
            String oldName = phoneToName.get(phone);
            nameToPhone.remove(oldName);
        }

        phoneToName.put(phone, name);
        nameToPhone.put(name, phone);

        return oldPhone;
    }

    public boolean removeContactByName(String name) {
        if (nameToPhone.containsKey(name)) {
            String phone = nameToPhone.get(name);
            nameToPhone.remove(name);
            phoneToName.remove(phone);
            return true;
        }
        return false;
    }

    public String getPhoneByName(String name) {
        return nameToPhone.get(name);
    }

    public String getNameByPhone(String phone) {
        return phoneToName.get(phone);
    }

    public boolean containsPhone(String phone) {
        return phoneToName.containsKey(phone);
    }

    public boolean containsName(String name) {
        return nameToPhone.containsKey(name);
    }

    public int getContactCount() {
        return phoneToName.size();
    }

    public String[] getAllPairs() {
        return phoneToName.entrySet().stream()
                .map(entry -> entry.getKey() + " - " + entry.getValue())
                .toArray(String[]::new);
    }

    public String[] getAllPhones() {
        return phoneToName.keySet().toArray(new String[0]);
    }

    public String[] getAllNames() {
        return nameToPhone.keySet().toArray(new String[0]);
    }

    public String[] findNamesByPrefix(String namePrefix) {
        if (namePrefix == null) return new String[0];

        return nameToPhone.keySet().stream()
                .filter(name -> name.startsWith(namePrefix))
                .toArray(String[]::new);
    }

    @Override
    public String toString() {
        if (phoneToName.isEmpty()) {
            return "Phone directory is empty";
        }

        return phoneToName.entrySet().stream().map(entry -> entry.getKey() + " - " + entry.getValue()).sorted().collect(Collectors.joining("\n"));
    }
}
