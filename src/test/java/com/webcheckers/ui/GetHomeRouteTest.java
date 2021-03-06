//package com.webcheckers.ui;
//
//import com.google.gson.Gson;
//import com.webcheckers.model.Player;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Tag;
//import org.junit.jupiter.api.Test;
//import spark.*;
//
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//import static org.mockito.ArgumentMatchers.any;
//
//@Tag("UI-tier")
//public class GetHomeRouteTest {
//
//    /**
//     * Parameters used in testing.
//     */
//    private GetGameRoute CuT;
//    private TemplateEngine templateEngine;
//    private Request request;
//    private Session session;
//    private Response response;
//    private Gson gson;
//
//    /**
//     * Initialization.
//     */
//    @BeforeEach
//    private void setup(){
//        request = mock(Request.class);
//        session = mock(Session.class);
//        when(request.session()).thenReturn(session);
//        templateEngine = mock(TemplateEngine.class);
//        response = mock(Response.class);
//        CuT = new GetGameRoute(templateEngine, gson);
//        WebServer webServer = mock(WebServer.class);
//        webServer.initialize();
//
//        Player red = mock(Player.class);
//        Player white = mock(Player.class);
//
//    }
//
//
//
//}
