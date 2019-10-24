package com.webcheckers.ui;

import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

public class GetGameRouteTester {

    private GetGameRoute CuT;

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
        CuT = new GetGameRoute(templateEngine);
        WebServer webServer = mock(WebServer.class);
        webServer.initialize();

        Player red = mock(Player.class);
        Player white = mock(Player.class);

        webServer.PLAYER_LOBBY.addToGame(red);
        webServer.PLAYER_LOBBY.addToGame(white);


    }

    @Test
    public void testAttributes(){

        final TemplateEngineTester helper = new TemplateEngineTester();

        when(templateEngine.render(any(ModelAndView.class) )).thenAnswer(helper.makeAnswer());
        CuT.handle(request, response);

        helper.assertViewModelExists();
        helper.assertViewModelIsaMap();

        helper.assertViewModelAttribute("viewMode", "PLAY");
        helper.assertViewModelAttribute("currentUser", session.attribute("currentUser"));


    }



}
