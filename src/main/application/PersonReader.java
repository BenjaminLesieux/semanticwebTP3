package main.application;

import main.tools.JenaEngine;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.RDFNode;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PersonReader {

    public static Person readPerson(OntModel model, String object1Name) {
        Map<String, String> value = JenaEngine.getProperties(model, object1Name);

        return new Person(
                value.get("name"),
                value.get("age"),
                value.getOrDefault("type", "unknown"),
                value.getOrDefault("isMarriedWith", "not married"),
                value.getOrDefault("isChildOf", "no parents"),
                value.getOrDefault("isFatherOf", "no children"),
                value.getOrDefault("isMotherOf", "no children"),
                value.getOrDefault("isBrotherOf", "no brothers"),
                value.getOrDefault("isSisterOf", "no sisters")
        );
    }

    public static Person getPerson(List<Person> persons, String person) {
        return persons.stream().filter(p -> p.name().equals(person)).findFirst().orElse(null);
    }

    public static void displayHusbandOrWife(Person person, List<Person> persons) {
        if (!person.marriedWith().equals("not married")) {
            System.out.println(person.name() + " is married with " + person.marriedWith() + " of age " + getPerson(persons, person.marriedWith()).age());
        } else {
            System.out.println(person.name() + " is not married");
            System.out.println("Here are all the persons that are not married & whose gender are different : ");

            List<String> others = persons.stream()
                    .filter(p -> !p.equals(person))
                    .filter(p -> p.gender().equals("unknown") || !p.gender().equals(person.gender()))
                    .filter(p -> Math.max(Integer.parseInt(p.age()), Integer.parseInt(person.age())) - Math.min(Integer.parseInt(p.age()), Integer.parseInt(person.age())) <= 5)
                    .map(Person::name)
                    .toList();

            System.out.println(others);
        }
    }

    public static void displayParentsAndSiblings(Person person) {
        if (!person.brotherOf().equals("no brothers")) {
            System.out.println(person.name() + " has brothers: " + person.brotherOf());
        }
        if (!person.sisterOf().equals("no sisters")) {
            System.out.println(person.name() + " has sisters: " + person.sisterOf());
        }
        if (!person.fatherOf().equals("no children")) {
            System.out.println(person.name() + " has children: " + person.fatherOf());
        }
        if (!person.motherOf().equals("no children")) {
            System.out.println(person.name() + " has children: " + person.motherOf());
        }
        if (!person.childrenOf().equals("no parents")) {
            System.out.println(person.name() + " has parents: " + person.childrenOf());
        }
    }

    public static void printUsefulInfo(Person person, List<Person> persons) {
        System.out.println("Here are some useful info about : " + person.name());
        System.out.println("Age: " + person.age());
        System.out.println("Gender: " + person.gender());

        displayParentsAndSiblings(person);
        displayHusbandOrWife(person, persons);

        System.out.println("\n\n");
    }
}
