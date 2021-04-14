# I/O Example
This repo contains example tests for testing labs which observe output from a method (often the `main` method). Tests can also observe output based on an input.

## Usage
To test the instructor solution:
```bash
mvn -Denv=dev test
```

To test the student solution:
```bash
mvn -Denv=prod test
```

To create a portable JAR:
```bash
mvn -DskipTests package
```

## License
Licensed under the [MIT License](LICENSE).
