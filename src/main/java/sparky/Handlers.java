package sparky;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class Handlers {
    static final Handler greetingHandler = (Context ctx) -> {
        final Greeting greeting = Greeting.builder()
                .name(ctx.queryParam("name"))
                .greeting("hello")
                .build();
        ctx.json(greeting);
    };
}
