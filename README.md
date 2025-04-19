# JSON Explorer CLI

A Java-based command-line tool for parsing, analyzing, and navigating complex JSON structures.  
It supports recursion, path-based navigation (e.g., `user.name`, `courses[0].title`), color-coded output, and element statistics.

## 📦 Features

- ✅ Recursively parse and print nested JSON objects and arrays
- ✅ Color-coded output for keys and values (via ANSI escape codes)
- ✅ Path-based navigation to access specific values (e.g. `user.name`, `items[2].details.price`)
- ✅ Type statistics: counts how many strings, numbers, booleans, nulls, arrays, and objects
- ✅ Error handling for invalid paths

## 🧠 Example

Given the input:

```json
{
  "user": {
    "name": "João",
    "skills": ["Java", "Python"]
  },
  "courses": [
    { "title": "Java POO" },
    { "title": "React" }
  ]
}
```

You can:

- View the entire structure recursively
- Type `user.name` to get `"João"`
- Type `user.skills[1]` to get `"Python"`
- Type `courses[0].title` to get `"Java POO"`

## ▶️ How to Run

1. Clone this repo:
   ```bash
   git clone https://github.com/joaobenedetmachado/JavaJSON.git
   cd JavaJSON
   ```

2. Compile with Maven or your preferred Java build tool (Java 8+):
   ```bash
   mvn clean package
   ```

3. Run:
   ```bash
   java -jar target/JavaJSON.jar
   ```

4. Paste your JSON when prompted, then use path navigation.

## 🎨 Dependencies

- [Gson](https://github.com/google/gson) for JSON parsing
- ANSI color codes for terminal styling

## 🛠️ Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── org/
│   │       └── example/
│   │           ├── Main.java
│   │           ├── colors/
│   │           │   └── Colors.java
│   │           └── models/
│   │               └── JsonStats.java
```

## ✍️ Author

João Victor Benedet Machado

---

Feel free to fork, contribute, and improve!
