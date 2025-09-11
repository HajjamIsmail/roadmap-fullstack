# 🚀 Java Modern Features Explained

## 📋 Records (Java 16+)

### Primary Purpose
Records are special classes designed specifically to store immutable data in a concise and predictable way.

### Problem They Solve
Before records, creating a data class required writing a lot of boilerplate code:
```
// OLD APPROACH (lots of repetitive code)
public class Person {
    private final String name;
    private final int age;
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public String getName() { return name; }
    public int getAge() { return age; }
    
    @Override
    public boolean equals(Object o) { /* tedious implementation */ }
    
    @Override
    public int hashCode() { /* tedious implementation */ }
    
    @Override
    public String toString() { /* tedious implementation */ }
}
```

### With Records
```
// NEW APPROACH (very concise)
public record Person(String name, int age) {
    // Everything generated automatically!
}
```

### Key Characteristics
- 🛡️ **Immutable by default:** All fields are final
- ⚡ **Auto-generated methods:** equals(), hashCode(), toString()
- 📝 **Access methods:** Getters generated (field name without "get")
- 🎯 **Destructuring:** Ability to decompose the object
```
// COMPLETE EXAMPLE
public record Person(String name, int age, String department) {
    
    // Can add custom methods
    public boolean isAdult() {
        return age >= 18;
    }
    
    // Can override auto-generated methods
    @Override
    public String toString() {
        return name + " (" + age + " years)";
    }
}

// USAGE
Person person = new Person("Alice", 25, "IT");
System.out.println(person.name()); // "Alice" - auto getter
System.out.println(person); // "Alice (25 years)" - custom toString
```

## 🔄 Streams API

### Core Concept

Streams allow processing collections of data in a declarative way (WHAT to do) rather than imperative (HOW to do it).

### Advantages

- ➕ More readable: More expressive code
- ⚡ Better performance: Automatic optimizations
- 🔧 More flexible: Parallelizable operations

### Main Operations
#### 1. Creating Streams
```
// From a collection
List<String> names = List.of("Alice", "Bob", "Charlie");
Stream<String> stream = names.stream();

// Numeric streams
IntStream.range(1, 10); // 1 to 9
IntStream.rangeClosed(1, 10); // 1 to 10

// Infinite stream
Stream.iterate(0, n -> n + 2); // 0, 2, 4, 6, ...
```

#### 2. Intermediate Operations (lazy)
```
names.stream()
    .filter(name -> name.startsWith("A")) // Filtering
    .map(String::toUpperCase)             // Transformation
    .sorted()                             // Sorting
    .distinct()                           // Deduplication
    .limit(5)                             // Limiting
    .skip(2);                             // Skipping elements
```

#### 3. Terminal Operations (eager)
```
// Conversion
List<String> resultList = stream.toList();
Set<String> resultSet = stream.collect(Collectors.toSet());

// Searching
Optional<String> first = stream.findFirst();
boolean hasA = stream.anyMatch(name -> name.contains("A"));

// Aggregation
long count = stream.count();
int sum = stream.mapToInt(String::length).sum();
Optional<String> max = stream.max(Comparator.naturalOrder());

// Iteration
stream.forEach(System.out::println);
```

### Complete Example
```
// IMPERATIVE APPROACH (old way)
List<String> result = new ArrayList<>();
for (String name : names) {
    if (name.startsWith("A")) {
        String upper = name.toUpperCase();
        result.add(upper);
    }
}
Collections.sort(result);

// DECLARATIVE APPROACH (streams)
List<String> result = names.stream()
    .filter(name -> name.startsWith("A"))
    .map(String::toUpperCase)
    .sorted()
    .toList();
```

## 🔀 Switch Expressions (Java 14+)

### Evolution from Traditional Switch

#### Old Switch (problematic)
```
// OLD VERSION (error-prone)
String role;
switch (department) {
    case "IT":
        role = "Developer";
        break; // FORGET break → fallthrough!
    case "HR":
        role = "HR";
        break;
    default:
        role = "Unknown";
        break;
}
```
#### New Switch Expression
```
// NEW VERSION (safe and expressive)
String role = switch (department) {
    case "IT" -> "Developer";
    case "HR" -> "HR";
    default -> "Unknown";
}; // Note: semicolon required
```

### Advantages of New Switch
- ✅ No fallthrough: No risk of forgetting break
- 🎯 Returns value: Can return values directly
- 🔍 Pattern matching: Supports complex patterns (Java 21+)
- 📝 Concise syntax: Less verbose

### Advanced Features

#### Code blocks with yield
```
String description = switch (person) {
    case "IT" -> {
        String level = person.age() > 30 ? "Senior" : "Junior";
        yield person.name() + " - " + level + " Developer";
    }
    case "HR" -> person.name() + " - Human Resources";
    default -> person.name() + " - Employee";
};
```

#### Pattern Matching (Java 21+)
```
// Pattern matching with switch
Object obj = "Hello";
String result = switch (obj) {
    case Integer i -> "Number: " + i;
    case String s -> "String: " + s;
    case null -> "Null value";
    default -> "Unknown type";
};
```

#### Guards (additional conditions)
```
String message = switch (person) {
    case "IT" when person.age() > 40 -> "Experienced Developer";
    case "IT" when person.age() > 25 -> "Confirmed Developer";
    case "IT" -> "Junior Developer";
    default -> "Other profession";
};
```

#### Side-by-Side Comparison
| Traditional Switch | Switch Expression |
|-----------|------------|
| break required | No break needed |
| Error-prone| Safe |
| Doesn't return value | Returns value |
| Verbose | Concise |
| Statement | Expression |

## 🎯 Summary

### Records

**For:** Quickly creating immutable data classes

**When:** You need a simple immutable POJO

**Advantage:** 90% reduction in boilerplate code

### Streams

**For:** Processing collections declaratively

**When:** Complex operations on collections

**Advantage:** More readable and potentially better performing code

### Switch Expressions

**For:** Writing safer, more expressive switches

**When:** Replacing all traditional switches

**Advantage:** Eliminates fallthrough bugs, returns values

These three features significantly modernize Java, making it more concise, safer, and more expressive! 🚀

## 📚 When to Use Each Feature
### Use Records when:
- You need a simple data carrier class
- Immutability is desired
- You want automatic equals/hashCode/toString
- Reducing boilerplate is important

### Use Streams when:
- Processing collections of data
- You want to chain multiple operations
- Parallel processing might be beneficial
- Readability is more important than absolute performance

### Use Switch Expressions when:
- Replacing traditional switch statements
- You need to return a value from different cases
- Pattern matching would simplify complex conditionals
- You want to avoid fallthrough bugs

## 🔧 Migration Tips
### From Classes to Records:
````
// BEFORE
public class Person {
    private final String name;
    private final int age;
    // ... lots of boilerplate
}

// AFTER
public record Person(String name, int age) {}
````

### From Loops to Streams:
````
// BEFORE
List<String> results = new ArrayList<>();
for (String item : items) {
    if (item.startsWith("A")) {
        results.add(item.toUpperCase());
    }
}

// AFTER
List<String> results = items.stream()
    .filter(item -> item.startsWith("A"))
    .map(String::toUpperCase)
    .toList();
````

### From Traditional to Modern Switch:
````
// BEFORE
String result;
switch (value) {
    case 1:
        result = "One";
        break;
    case 2:
        result = "Two";
        break;
    default:
        result = "Unknown";
}

// AFTER
String result = switch (value) {
    case 1 -> "One";
    case 2 -> "Two";
    default -> "Unknown";
};
````
These modern Java features work together to create more maintainable, readable, and efficient code while reducing the potential for common programming errors.
