import utils.Person;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Main demonstration class showcasing Java 21 features:
 * - Records for data classes
 * - Enhanced switch expressions with pattern matching
 * - Stream API for functional data processing
 */
public class Java21FeaturesDemo {

    // Department enum for switch expression demonstration
    enum Department {
        IT, HR, SALES, FINANCE, MARKETING
    }

    /**
     * Main executable method demonstrating Java 21 features
     */
    public static void main(String[] args) {
        System.out.println("=== JAVA 21 FEATURES DEMONSTRATION ===\n");

        // Create a list of Person records - demonstrating record instantiation
        List<Person> people = List.of(
                new Person("P1", 25, "IT"),
                new Person("P2", 17, "HR"),
                new Person("P3", 32, "SALES"),
                new Person("P4", 45, "IT"),
                new Person("P5", 19, "FINANCE"),
                new Person("P6", 28, "MARKETING"),
                new Person("P7", 22, "IT")
        );

        // SECTION 1: RECORDS DEMONSTRATION
        System.out.println("1. RECORDS USAGE:");
        System.out.println("-----------------");
        people.forEach(person ->
                System.out.println(person.name() + " - " + person.age() + " years - " + person.department())
        );

        // SECTION 2: SWITCH EXPRESSIONS WITH PATTERN MATCHING
        System.out.println("\n2. SWITCH EXPRESSIONS WITH PATTERN MATCHING:");
        System.out.println("--------------------------------------------");
        people.forEach(Java21FeaturesDemo::processPersonWithSwitch);

        // SECTION 3: STREAMS API FOR DATA PROCESSING
        System.out.println("\n3. STREAMS FILTERING:");
        System.out.println("---------------------");

        // Filter adults from IT department using Stream API
        List<Person> itAdults = people.stream()
                .filter(Person::isAdult)          // Method reference to record method
                .filter(person -> "IT".equals(person.department())) // Lambda expression
                .collect(Collectors.toList());    // Terminal operation to collect results

        System.out.println("Adults from IT department:");
        itAdults.forEach(person ->
                System.out.println("• " + person.name() + " (" + person.age() + " years)")
        );
    }

    /**
     * Demonstrates enhanced switch expressions with pattern matching (Java 14+)
     * - Switch can return values (no break statements needed)
     * - More concise and readable syntax
     * - Pattern matching support for type patterns
     *
     * @param person the Person record to process
     */
    private static void processPersonWithSwitch(Person person) {
        String message = switch (person.department()) {
            case "IT" -> {
                // Using yield to return value from switch expression block
                String role = person.age() > 30 ? "Senior Developer" : "Junior Developer";
                yield person.name() + " is a " + role + " in IT";
            }
            case "HR" -> person.name() + " works in Human Resources";
            case "SALES" -> person.name() + " is in Sales team";
            case "FINANCE" -> person.name() + " works in Finance";
            case "MARKETING" -> person.name() + " does Marketing";
            default -> person.name() + " works in unspecified department";
        };

        System.out.println(message);
    }

    /**
     * Additional method showing switch expressions comparison
     * Old approach vs new switch expressions (Java 14+)
     *
     * @param department the department string
     * @return description of the department
     */
    public static String getDepartmentDescription(String department) {
        // New approach with switch expression (Java 14+)
        // More concise, returns values directly, no fall-through issues
        return switch (department) {
            case "IT" -> "Information Technology Department";
            case "HR" -> "Human Resources Department";
            case "SALES" -> "Sales Department";
            case "FINANCE" -> "Finance Department";
            case "MARKETING" -> "Marketing Department";
            default -> "Unknown Department";
        };
    }
}