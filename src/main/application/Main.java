package main.application;

import main.Queries;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //part1();
        part2();
    }

    private static void part2() {
        OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_RDFS_INF);
        FileManager.getInternal().readModelInternal(ontModel, "src/data/family.owl");

        List<Person> persons = ontModel.listIndividuals().toList()
                        .stream()
                        .map((resource) -> PersonReader.readPerson(ontModel, resource.getLocalName()))
                        .toList();

        persons.forEach((person) -> {
            PersonReader.printUsefulInfo(person, persons);
        });


    }

    private static void part1() {
        OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_RDFS_INF);
        FileManager.getInternal().readModelInternal(ontModel, "src/data/family.owl");

        String[] sparqlQueries = {
                Queries.BROTHER_INSTANCE,
                Queries.MOTHER_INSTANCE,
                Queries.DAUGHTER_INSTANCE,
                Queries.SISTER_INSTANCE,
                Queries.FATHER_INSTANCE,
                Queries.FATHER_OVER_40,
                Queries.GRAND_FATHER_INSTANCE,
                Queries.GRAND_MOTHER_INSTANCE,
                Queries.SON_INSTANCE,
                Queries.FRENCH_CITIZENS,
                Queries.LIST_BROTHERS,
                Queries.LIST_DAUGHTER,
                Queries.LIST_MARRIED,
                Queries.LIST_UNCLE
        };
        List<String> mappedQueries = Arrays.stream(sparqlQueries).map((query) -> Queries.QUERY_HEADER + query).toList();
        List<Query> queries = mappedQueries
                .stream()
                .map(QueryFactory::create)
                .toList();

        queries.forEach((query) -> {
            System.out.println("\n \n");
            System.out.println(query);
            try (var qe = QueryExecutionFactory.create(query, ontModel)) {
                var rs = qe.execSelect();

                System.out.println("Results:");
                while (rs.hasNext()) {
                    var sol = rs.next();
                    sol.varNames().forEachRemaining((varName) -> {
                        System.out.printf("%s : %s\n", varName, sol.get(varName));
                    });
                }
            } catch (QueryParseException e) {
                e.printStackTrace();
            }
        });
    }
}