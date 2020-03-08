package sparky;

import io.javalin.http.Context;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static sparky.Handlers.greetingHandler;

@ExtendWith(MockitoExtension.class)
class HandlersTest {
    private final String name = "Albert";
    private final Greeting greeting = Greeting.builder().name(name).greeting("hello").build();

    @Mock
    private Context context;

    @Test
    void greetingHandlerUsesName() throws Exception {
        doReturn(name).when(context).queryParam("name");

        greetingHandler.handle(context);

        verify(context).json(greeting);
        verify(context).queryParam("name");
        verifyNoMoreInteractions(context);
    }
}