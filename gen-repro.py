import sys
n=int(sys.argv[1]); out=sys.argv[2]
links="\n".join(f'            .path("field{i}").entity(String.class).isEqualTo("value{i}")' for i in range(n))
open(out,'w').write(f'''package repro;

import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/** The fluent shape of Spring's GraphQlTester (spring-graphql-test 2.0.5), reduced to the types a chain touches. */
@NullMarked
class Chain {{

    interface Traversable {{
        Path path(String path);
    }}

    interface Path extends Traversable {{
        <D> Entity<D, ?> entity(Class<D> entityType);
    }}

    interface Entity<D, S extends Entity<D, S>> extends Traversable {{
        <T extends S> T isEqualTo(@Nullable Object expected);
    }}

    static void chain(Traversable response) {{
        response
{links};
    }}
}}
''')
