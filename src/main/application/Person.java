package main.application;

import java.util.Arrays;
import java.util.Optional;

public record Person(String name, String age, String gender, String marriedWith, String childrenOf,
                     String fatherOf, String motherOf, String brotherOf,
                     String sisterOf) {

    public Person(String name, String age, String gender, String marriedWith, String childrenOf,
                  String fatherOf, String motherOf, String brotherOf,
                  String sisterOf) {
        this.childrenOf = formatMultipleName(childrenOf);
        this.fatherOf = formatMultipleName(fatherOf);
        this.motherOf = formatMultipleName(motherOf);
        this.brotherOf = formatMultipleName(brotherOf);
        this.sisterOf = formatMultipleName(sisterOf);
        this.marriedWith = formatMultipleName(marriedWith);
        this.name = name;
        this.age = formatAge(age);
        this.gender = formatGender(gender);
    }

    private String formatName(String name) {
        if (name != null && name.split("#").length >= 2) {
            return name.split("#")[1];
        } else {
            return name;
        }
    }

    private String formatAge(String age) {
        return age.split("\\^\\^")[0];
    }

    private String formatGender(String gender) {
        return gender.split(",")[0].split("#")[1];
    }

    private String formatMultipleName(String name) {
        if (name != null && name.split(",").length > 1) {
            return String.join(",", Arrays.stream(name.split(",")).map(this::formatName).toArray(String[]::new));
        } else return formatName(name);
    }
}
