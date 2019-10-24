package com.webcheckers.ui;

/**
 * Language: Java
 * @Author: An Chang (Mark)
 * Purpose: A class to test PostSignInRoute.
 */

import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Tag("UI-tier")
public class PostSignInRouteTester {

    private PostSignInRoute CuT;

    private TemplateEngine templateEngine;
    private Request request;
    private Session session;
    private Response response;

    @BeforeEach
    private void setup(){
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        templateEngine = mock(TemplateEngine.class);
        response = mock(Response.class);
        CuT = new PostSignInRoute(templateEngine);
        WebServer webServer = mock(WebServer.class);
        webServer.initialize();
    }

    @Test
    public void testSignIn(){

        final TemplateEngineTester helper = new TemplateEngineTester();

        when(templateEngine.render(any(ModelAndView.class) )).thenAnswer(helper.makeAnswer());
        session.attribute("playerName","h");
        request.attribute("playerName","h");

        CuT.handle(request,response);

        helper.assertViewModelExists();
        helper.assertViewModelIsaMap();


        helper.assertViewModelAttributeIsAbsent("currentPlayer");

    }

}
