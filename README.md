# Proof of concept for linting in Kotlin(Datalore)

## Description

This project implements several custom rules for [Structural search and replace in InteliJ IDEA and inspections](https://www.jetbrains.com/help/idea/structural-search-and-replace.html) and [Detekt](https://github.com/detekt/detekt) to compare these tools' effectiveness for linting/formatting.

## Project structure

- In *src/main/kotlin* you can find the files on which the experiment is being conducted
### Structural search and replace in InteliJ IDEA and inspections
- In *.idea/structuralSearch.xml* there is the file with Structural Search templates saved for this project
### Detekt
- In package *myRules* there are [custom rules](https://detekt.dev/docs/introduction/extensions/) for Detekt
- *config/detekt/detekt.yml* is config for rules of Detekt

## How run Detect

Use for running Delekt next command:

```
./gradlew detekt
```

## Autocorrect

To enable autocorrection for a rule, you need to follow these steps:
1. Go to config *config/detekt/detekt.yml*
2. Set the flag *autoCorrect: true*
```yml
MyRuleSet:
  FileWithReactComponentNameCheck:
    autoCorrect: true
    active: true
  ReactComponentClassNameCheck:
    autoCorrect: true
    active: true
  ReactPropsClassNameCheck:
    autoCorrect: true
    active: true
  ReactStateClassNameCheck:
    autoCorrect: true
    active: true
```

## Rules

There are three classes:
```
PromoButton - Component class name

PromoButtonProps - Props class name

PromoButtonState - State class name
```

Also, the file name matches the name of the component *promoButton.kt*

1. The prefixes of the class names must be the same "PromoButton"
2. The file name must match the name of the main class + be with a lowercase letter
