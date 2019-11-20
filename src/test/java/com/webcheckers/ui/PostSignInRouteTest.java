package com.webcheckers.ui;

/**
 * Language: Java
 * @Author: An Chang (Mark)
 * Purpose: A class to test PostSignInRoute.
 */

import com.webcheckers.model.Player;
import com.webcheckers.appl.PlayerLobby;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Tag("UI-tier")
public class PostSignInRouteTest {

    /**
     * The class under test.
     */
    private PostSignInRoute CuT;

    /**
     * Parameters used for initialization.
     */
    private TemplateEngine templateEngine;
    private Request request;
    private Session session;
    private Response response;
    private PlayerLobby playerLobby;

    /**
     * Initialization for route testers, standard accross all route testers.
     */
    @BeforeEach
    private void setup(){
        request = mock(Request.class);
        session = mock(Session.class);
        playerLobby = mock(PlayerLobby.class);
        when(request.session()).thenReturn(session);
        templateEngine = mock(TemplateEngine.class);
        response = mock(Response.class);
        CuT = new PostSignInRoute(templateEngine);
        WebServer webServer = mock(WebServer.class);
        webServer.initialize();
    }

    /**
     * Make sure the player was added correctly. This example used a player with name "h".
     */
    @Test
    public void testSignIn(){

        final TemplateEngineTester helper = new TemplateEngineTester();
        String sadf = "h";

        when(templateEngine.render(any(ModelAndView.class) )).thenAnswer(helper.makeAnswer());
        when(request.queryParams("playerName")).thenReturn("h");
        //when(request.queryParams("playerName").thenAnswer("playerName","h"));
        //session.attribute("playerName","h");
        //request.params().put("playerName","hsadf");

        //System.out.println(request.queryParams("playerName"));

        CuT.handle(request,response);

        Player player = new Player("h");
        assertNotNull(playerLobby.getUser("h")!= null);

    }

}
