package repro;

import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/** The fluent shape of Spring's GraphQlTester (spring-graphql-test 2.0.5), reduced to the types a chain touches. */
@NullMarked
class Chain {

    interface Traversable {
        Path path(String path);
    }

    interface Path extends Traversable {
        <D> Entity<D, ?> entity(Class<D> entityType);
    }

    interface Entity<D, S extends Entity<D, S>> extends Traversable {
        <T extends S> T isEqualTo(@Nullable Object expected);
    }

    static void chain(Traversable response) {
        response
            .path("field0").entity(String.class).isEqualTo("value0")
            .path("field1").entity(String.class).isEqualTo("value1")
            .path("field2").entity(String.class).isEqualTo("value2")
            .path("field3").entity(String.class).isEqualTo("value3")
            .path("field4").entity(String.class).isEqualTo("value4")
            .path("field5").entity(String.class).isEqualTo("value5")
            .path("field6").entity(String.class).isEqualTo("value6")
            .path("field7").entity(String.class).isEqualTo("value7")
            .path("field8").entity(String.class).isEqualTo("value8")
            .path("field9").entity(String.class).isEqualTo("value9")
            .path("field10").entity(String.class).isEqualTo("value10")
            .path("field11").entity(String.class).isEqualTo("value11");
    }
}
