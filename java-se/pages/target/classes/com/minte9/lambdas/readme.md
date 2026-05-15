# Java Lambdas

### 📌 What is a lambda?

A lambda is a `compact` way to pass behavior by implementing a functional interface.

- No functional interface -> no lambda.
- A functional interface has exactly `one` abstract method.

Examples:
- Runnable
- Comarator
- Function
- Predicate



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

No params

    () -> action

One param

    x -> action

Multiple params 

    (x, y) -> action

Block body

    x -> {
        something();
        return result;
    }

### 📌 Threads

- Lambda does NOT create a thread
- It only provides code to run
- Lambda = code, Thread = execution

Example:

    new Thred(() -> doWork()).start();


#
### 💡 Active Recall Questions

1. What is a `lambda`, really?
2. What makes an interface `functional`?
3. Does lambda create a `thread`?
4. What is a lambda replacing `under` the hood?
