package com.webcheckers.ui;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.*;
/**
 * Class to test the PostSignOutRoute.
 *
 * @author Aaron, Gavin, Mark, Ries, Zach
 */
@Tag("UI-tier")
public class PostSignOutRouteTest {

    /**
     * Parameters to be used in testing.
     */
    private PostSignOutRoute CuT;
    private TemplateEngine templateEngine;
    private Request request;
    private Session session;
    private Response response;

    /**
     * The setup of the PostSignOutRoute.
     */
    @BeforeEach
    private void setup() {
        request = mock(Request.class);
        response = mock(Response.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        templateEngine = mock(TemplateEngine.class);
        CuT = new PostSignOutRoute(templateEngine);
    }

    /**
     * Tests the PostSignOut.
     */
    @Test
    public void test_PostSignOut(){
        assertEquals(CuT.handle(request, response), "");
    }
}