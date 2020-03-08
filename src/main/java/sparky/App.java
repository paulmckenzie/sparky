package sparky;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import io.javalin.Javalin;
import io.javalin.plugin.json.JavalinJackson;

public class App {
    private static final ObjectMapper jsonMapper = JsonMapper.builder()
            .addModule(new ParameterNamesModule())
            .addModule(new Jdk8Module())
            .addModule(new JavaTimeModule())
            .build();

    public static void main(String[] args) {
        JavalinJackson.configure(jsonMapper);
        final Javalin javalin = Javalin.create().start(7000);
        javalin.get("/hello", Handlers.greetingHandler);
    }
}
