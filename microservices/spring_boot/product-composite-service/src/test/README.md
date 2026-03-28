### Simplest Useful Tests

1) Does the app start?

This test already exists implicitly.

- Catches broken configuration
- Catches missing beans

/test/.../ProductCompositeApplicationTest.java

    @SpringBootTest
    class ProductCompositeApplicationTest {
    
        @Test
        void contextLoads() {
        }
    }

Run tests:

    ./gradlew test

    BUILD SUCCESSFUL in 6s
    4 actionable tasks: 4 executed


2) Controller Test

Test Product Composite with @WebMvcTest.

We test:
- HTTP endpoints exists
- it returns 200
- JSON shape is correct

