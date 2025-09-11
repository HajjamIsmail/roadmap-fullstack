import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Advanced Stream API demonstration showcasing:
 * - Numeric streams with IntStream
 * - Terminal operations: reduce, count, min
 * - Infinite streams with limit
 * - Stream pipeline operations
 */
public class StreamsAdvancedDemo {

    /**
     * Main method demonstrating advanced Stream API features
     */
    public static void main(String[] args) {
        System.out.println("=== ADVANCED STREAMS DEMONSTRATION ===\n");

        // SECTION 1: NUMERIC STREAMS
        System.out.println("1. NUMERIC STREAMS:");
        System.out.println("Even numbers from 1 to 10:");

        // IntStream.rangeClosed() creates a stream of integers from 1 to 10 (inclusive)
        IntStream.rangeClosed(1, 10)
                .filter(n -> n % 2 == 0)  // Intermediate operation: filter even numbers
                .forEach(n -> System.out.print(n + " "));  // Terminal operation: print each element

        // SECTION 2: ADVANCED TERMINAL OPERATIONS
        System.out.println("\n\n2. ADVANCED TERMINAL OPERATIONS:");

        List<Integer> numbers = List.of(3, 1, 4, 1, 5, 9, 2, 6, 5);

        // Reduction operation - combines elements into a single result
        Optional<Integer> sum = numbers.stream()
                .reduce(Integer::sum);  // Method reference to Integer.sum()
        System.out.println("Sum: " + sum.orElse(0));  // Handle Optional with orElse()

        // Count operation - returns the number of elements in stream
        long count = numbers.stream().count();
        System.out.println("Element count: " + count);

        // Min operation - finds minimum element using comparator
        Optional<Integer> min = numbers.stream().min(Integer::compare);
        System.out.println("Minimum: " + min.orElse(0));

        // SECTION 3: INFINITE STREAMS WITH LIMIT
        System.out.println("\n3. INFINITE STREAMS WITH LIMIT:");

        // Stream.iterate() creates an infinite stream starting with 0, incrementing by 2
        // .limit(5) truncates the infinite stream to first 5 elements
        // .toList() (Java 16+) collects results into an immutable list
        List<Integer> first5Evens = Stream.iterate(0, n -> n + 2)
                .limit(5)
                .toList();
        System.out.println("First 5 even numbers: " + first5Evens);

        // SECTION 4: STREAM PIPELINE EXAMPLE
        System.out.println("\n4. COMPLETE STREAM PIPELINE:");

        // Complete stream pipeline with multiple operations
        double average = numbers.stream()
                .filter(n -> n > 2)          // Intermediate: filter numbers > 2
                .map(n -> n * 2)             // Intermediate: double each number
                .mapToInt(Integer::intValue)  // Intermediate: convert to IntStream
                .average()                    // Terminal: calculate average
                .orElse(0.0);                // Handle empty stream case

        System.out.println("Average of doubled numbers > 2: " + average);
    }

    /**
     * Demonstrates additional stream operations
     * - distinct(): removes duplicates
     * - sorted(): natural ordering
     * - collect(): to various collections
     */
    public static void showAdditionalStreamOperations() {
        List<Integer> numbers = List.of(3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5);

        System.out.println("\nAdditional Stream Operations:");
        System.out.println("Original: " + numbers);

        // Remove duplicates and sort
        List<Integer> distinctSorted = numbers.stream()
                .distinct()      // Remove duplicates
                .sorted()        // Natural order sorting
                .toList();       // Collect to list (Java 16+)

        System.out.println("Distinct & Sorted: " + distinctSorted);
    }
}