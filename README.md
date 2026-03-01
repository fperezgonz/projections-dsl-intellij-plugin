# Projections DSL IntelliJ Plugin

An IntelliJ IDEA plugin that provides language support for the **Projections DSL**, used for defining DTO projections.

## Features

- **Language Injection**: DSL injection in Java code (within `DtoProjectionSpec` usages).
- **Syntax Highlighting**
- **Code Completion**: Autocomplete projection properties with the properties of referenced DTOs
- **Navigation & References**: Navigate from DSL property names to their corresponding declarations in Java classes.
- **Error Highlighting**: Real-time validation of DSL syntax and references

## Usage

### Language Injection
The DSL is automatically injected into Java methods that are part of the `solutions.sulfura.hyperkit.dsl.projections.DtoProjectionSpec` class, specifically the `value()` method.

## Installation

1. Open IntelliJ IDEA.
2. Go to `Settings` > `Plugins`.
3. Search for "Projections Dsl" in the Marketplace (if published) or install via "Install Plugin from Disk..." using the built JAR.

## Development

### Building the Plugin
This project uses Gradle with the IntelliJ Gradle Plugin. To build the plugin:

```bash
./gradlew buildPlugin
```

The resulting ZIP/JAR will be located in `build/distributions/`.


### Manual tests

#### Running with IDE
To launch a development instance of IntelliJ IDEA with the plugin installed:

```bash
./gradlew runIde
```
There is a [test-project](src/test/resources/test-project) with a sample project that can be used for manual verification