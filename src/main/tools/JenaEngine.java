package main.tools;

import org.apache.jena.rdf.model.*;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class JenaEngine {

    public static Map<String, String> getProperties(Model model, String object1Name) {
        Resource rs1 = model.getResource("http://www.owl-ontologies.com/unnamed.owl#" + object1Name);

        return rs1.listProperties().toList()
                .stream()
                .peek(System.out::println)
                .collect(Collectors
                        .toMap(
                                (statement -> statement.getPredicate().getLocalName()),
                                (statement -> statement.getObject().toString()),
                                ((existing, newValue) -> existing + ", " + newValue
                        )
                ));
    }

    public static Optional<Statement> getValueOfDataProperty(Model model, String object1Name, String propertyName) {
        Resource rs1 = model.getResource("http://www.owl-ontologies.com/unnamed.owl#" + object1Name);
        Property p = model.getProperty("http://www.owl-ontologies.com/unnamed.owl#" + propertyName);
        var prop = model.getProperty(rs1, p);

        return (p != null && rs1 != null && prop != null) ? Optional.of(model.getProperty(rs1, p)) : Optional.empty();
    }
}
