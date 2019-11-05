package com.webcheckers.ui;

/**
 * Language: Java
 * @Author: An Chang (Mark)
 * Purpose: A class to test GetSignInRoute.
 */

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.webcheckers.appl.PlayerLobby;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.*;

@Tag("UI-tier")
public class GetSignInRouteTest {

    /**
     * Parameters used for testing.
     */
    private GetSignInRoute CuT;
    private TemplateEngine templateEngine;
    private Request request;
    private Session session;
    private Response response;

    /**
     * Initialization.
     */
    @BeforeEach
    private void setup() {
        request = mock(Request.class);
        session = mock(Session.class);
        templateEngine = mock(TemplateEngine.class);
        Response response = mock(Response.class);
        PlayerLobby playerLobby = mock(PlayerLobby.class);
        CuT = new GetSignInRoute(templateEngine);
    }

    /**
     * See if the attributes from getSignInRoute works.
     */
    @Test
    public void testAttributes(){

        final TemplateEngineTester helper = new TemplateEngineTester();

        when(templateEngine.render(any(ModelAndView.class) )).thenAnswer(helper.makeAnswer());
        CuT.handle(request, response);

        helper.assertViewModelExists();
        helper.assertViewModelIsaMap();



    }
}
