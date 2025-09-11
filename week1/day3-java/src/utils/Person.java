package utils;

/**
 * Person record demonstrating Java 21 record features
 * Records provide a compact syntax for declaring classes that are transparent holders for shallowly immutable data
 */

public record Person(String name, int age, String department) {
    /**
     * Custom method in record - demonstrates that records can have additional methods
     * @return true if person is 18 years or older
     */
    public boolean isAdult() {
        return age >= 28;
    }
}
