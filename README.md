# NullAway chained generic call repro

Since NullAway 0.14.0, in JSpecify mode, compile time grows exponentially with the length of a fluent
chain in which every link is a generic call. `Chain.java` reduces Spring's `GraphQlTester` assertion
chain (spring-graphql-test 2.0.5) to the types such a chain touches.

## Run

JDK 25 needed; the Gradle wrapper does the rest.

```
./gradlew compileJava --rerun -PnullawayVersion=0.13.8   # ~1s
./gradlew compileJava --rerun -PnullawayVersion=0.14.2   # ~84s
```

`python3 gen-repro.py <links> src/main/java/repro/Chain.java` regenerates the chain with another length;
the checked-in file has 12 links.

## Timings

Wall time of `compileJava` with a warm daemon (Error Prone 2.50.0, JDK 25.0.1, flags
`OnlyNullMarked` + `JSpecifyMode`):

| links | 0.13.8 | 0.14.2 |
|---|---|---|
| 1-7 | 1.0s | 1.0s |
| 8 | 1.0s | 2.6s |
| 10 | 1.0s | 12.2s |
| 12 | 1.0s | 84.0s |
| 16 | 1.6s | killed after 120s |
| 24 | 1.0s | - |

At 10 links, 0.14.0 takes 13.1s and 0.14.1 takes 8.2s.
