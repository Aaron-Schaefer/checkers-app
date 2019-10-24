package com.webcheckers.ui;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import com.webcheckers.model.Player;
import com.webcheckers.model.PlayerLobby;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.Request;
import spark.Response;
import spark.Session;
import spark.TemplateEngine;

@Tag("UI-tier")
public class GetSignInRouteTester {

    private GetSignInRoute CuT;

    private TemplateEngine templateEngine;
    private Request request;
    private Session session;
    private Response response;
    private PlayerLobby playerLobby;
    private String[] names = {"Gavin", "Zach", "Ries", "Aaron", "Mark"};

    @BeforeEach
    private void setup() {
        request = mock(Request.class);
        session = mock(Session.class);
        templateEngine = mock(TemplateEngine.class);
        Response response = mock(Response.class);
        PlayerLobby playerLobby = mock(PlayerLobby.class);
        for (String name : names) {
            playerLobby.addPlayer(new Player(name));
        }
        session.attribute("currentPlayer", new Player("Gavin"));
        CuT = new GetSignInRoute(templateEngine);
    }

    @Test
    public void test_no_currentPlayer(){
        CuT.handle(request, response);
        Player currentPlayer = new Player("hi");
        assertTrue(playerLobby.addPlayer(currentPlayer));
    }

    @Test
    public void test_current_player(){
        CuT.handle(request, response);
        Player currentPlayer = session.attribute("currentPlayer");
        playerLobby.addPlayer(currentPlayer);
        assert((playerLobby.getPlayer(currentPlayer.getName())) != null);
    }
}
