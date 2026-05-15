## Java Streams


### 📌 What is a Stream?

A stream is a lazy pipeline that transform data from a source into a result.

SOURCE -> INTERMEDIATE OPS -> TERMINAL OP

If there is no terminal operation, the stream does nothing.

### 📌 Rules

- A `Stream` is NOT a data structure
- A `Stream` is a pipeline of operations
- It processes data from a source to a `terminal` operation
- A Stream is `lazy`, nothing runs until terminal op
- Prefer `transformation` over side effects

### 📌 Source

    collection.stream()

### 📌 Intermediate Operations

    filter  -> keep some elements
    map -> transform elements

These return a Stream and `do not` run immediatelly.

### 📌 Terminal OPerations

These trigger execution:

    foreach -> action (side effects)
    collect -> build colletion
    reduse -> combine into one value
    count, findFirst, anyMatch

#
### 💡 Active Recall Questions

1. What triggers a stream to run?
2. Why does a stream somethimes do nothing?
3. Why is forEach dangerous?
4. Can you reuse a steam? Why not?
