# Java Lambdas

### 📌 What is a lambda?

- A lambda is a `short` implementation of a functional interface.
- A lambda works `only` with a functional interface.
- A functional interface has exactly `one` abstract method.

Examples:
- Runnable
- Comarator
- Function
- Predicate

No functional interface -> no lambda.

### 📌 Translation Rule

Lambdas do NOT add behavior - they remove boilerplate.

    () -> something();

Is just a short way of writing:

    new Runnable() {
        public void run() {
            something();
        }
    };



### 📌 Lambda Syntax

No params / One param / Multiple params / Block body

    () -> action
    x -> action
    (x, y) -> action

    x -> {
        something();
        return result;
    }

### 📌 Lambdas ≠ Threads

- Lambda does NOT create a thread
- It only provides code to run
- Lambda = code, Thread = execution

Example:

    new Thred(() -> doWork()).start();


#
### 💡 Active Recall Questions

1. What is a lambda, really?
2. What makes an interface `functional`?
3. Does lambda create a thread?
4. What is a lambda replacing under the hood?
