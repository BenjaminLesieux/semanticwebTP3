package main;

public class Queries {
    public static String QUERY_HEADER = """
            PREFIX owl: <http://www.w3.org/2002/07/owl#>
            PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
            PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
            PREFIX ns: <http://www.owl-ontologies.com/unnamed.owl#>
            """;
    public static String SON_INSTANCE = """
            SELECT *
            WHERE {
            	?person rdf:type ns:Person
            }
            """;
    public static String DAUGHTER_INSTANCE = """
            SELECT *
            WHERE {
            	?person rdf:type ns:Daughter
            }
            """;

    public static String FATHER_INSTANCE = """
            SELECT *
            WHERE {
            	?person rdf:type ns:Parent
            }
            """;

    public static String MOTHER_INSTANCE = """
            SELECT *
            WHERE {
            	?person rdf:type ns:Mother
            }
            """;

    public static String GRAND_MOTHER_INSTANCE = """
            SELECT *
            WHERE {
            	?person rdf:type ns:Grandmother
            }
            """;

    public static String GRAND_FATHER_INSTANCE = """
            SELECT *
            WHERE {
            	?person rdf:type ns:Grandfather
            }
            """;

    public static String BROTHER_INSTANCE = """
            SELECT *
            WHERE {
            	?person rdf:type ns:Brother
            }
            """;

    public static String SISTER_INSTANCE = """
            SELECT *
            WHERE {
            	?person rdf:type ns:Sister
            }
            """;

    public static String PETER_CHILDREN = """
            SELECT ?name ?age
            WHERE{
            	?peter ns:name "Peter" .
            	?peter ns:isParentOf ?childofpeter .
            	?childofpeter ns:name ?name .
            	?childofpeter ns:age ?age
            }
            """;

    public static String FATHER_OVER_40 = """
            SELECT ?fathers ?children ?age
            WHERE{
            	?fathers ns:isFatherOf ?children .
            	?fathers ns:age ?age .
            	FILTER(?age >40)
            }
            """;

    public static String FRENCH_CITIZENS = """
            SELECT ?name ?age ?isMarriedWith
            WHERE {
            	?person rdf:type ns:Person .
            	?person ns:nationality ?nationality .
            	FILTER(?nationality = "French")
            	?person ns:name ?name .
            	?person ns:age ?age .
            	?person ns:isMarriedWith ?isMarriedWith
            }
            """;

    public static String LIST_BROTHERS = """
            SELECT DISTINCT ?child1
            WHERE {
            	?parent1 ns:isParentOf ?child1 .
            	?child1 rdf:type ns:Male .
            	?parent2 ns:isParentOf ?child2 .
            	FILTER(?child1 != ?child2)
            }
            """;

    public static String LIST_DAUGHTER = """
            SELECT DISTINCT ?child1
            WHERE {
            	?parent1 ns:isParentOf ?child1 .
            	?child1 rdf:type ns:Female .
            	?parent1 ns:isParentOf ?child2 .
            	FILTER(?child1 != ?child2)
            }
            """;

    public static String LIST_UNCLE = """
            SELECT DISTINCT ?child1
            WHERE {
            	?parent1 ns:isParentOf ?child1 .
            	?child1 rdf:type ns:Female .
            	?parent1 ns:isParentOf ?child2 .
            	FILTER(?child1 != ?child2)
            }
            """;

    public static String LIST_MARRIED = """
            SELECT DISTINCT?grandparent ?uncle ?parent ?son
            WHERE {
            	?grandparent ns:isParentOf ?parent.
            	?parent rdf:type ns:Male.
            	?grandparent ns:isParentOf ?uncle.
            	?uncle rdf:type ns:Male.
            	?parent ns:isParentOf ?son.
            	FILTER(?parent != ?uncle)
            }
            """;
}
