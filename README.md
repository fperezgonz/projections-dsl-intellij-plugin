# Projections DSL IntelliJ Plugin

An IntelliJ IDEA plugin that provides language support for the **Projections DSL**, used for defining DTO projections.

## Features

- **Syntax Highlighting**: Enhanced readability for Projections DSL files (`.dpd`).
- **Code Completion**: Intelligent suggestions for field names and projection structures.
- **Language Injection**: Support for Projections DSL injections in Java code (e.g., within `@DtoProjection` or `DtoProjectionSpec` usages).
- **Navigation & References**: Navigate from DSL field names to their corresponding declarations in Java/Kotlin classes.
- **Error Highlighting**: Real-time validation of DSL syntax.

## Usage

### File Support
The plugin automatically recognizes files with the `.dpd` extension.

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

### Running with IDE
To launch a development instance of IntelliJ IDEA with the plugin installed:

```bash
./gradlew runIde
```

## Vendor
Developed by **Sulfura code**.
