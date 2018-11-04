package ru.mail.horo.data;

public enum HoroData {
    listOpen(" dropdown_active"),
    listclose(""),
    three("3"),
    listRuneStatic(""),
    listRuneDynamicBefore(" p-outside-block__content_dynamic js-dynamic"),
    listRuneDynamicAfter(" js-dynamic");

    private final String name;

    HoroData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}