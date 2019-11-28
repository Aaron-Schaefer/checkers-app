package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
/**
 * Class to test the GetGameRoute.
 *
 * @author Aaron, Gavin, Mark, Ries, Zach
 */
@Tag("UI-tier")
public class GetGameRouteTest {

    /**
     * Parameters used in testing.
     */
    private GetGameRoute CuT;
    private TemplateEngine templateEngine;
    private Request request;
    private Session session;
    private Response response;
    private Gson gson;

    /**
     * Initialization.
     */
    @BeforeEach
    public void setup(){
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        templateEngine = mock(TemplateEngine.class);
        response = mock(Response.class);
        CuT = new GetGameRoute(templateEngine, gson);
        WebServer webServer = mock(WebServer.class);
        webServer.initialize();

    }

    /**
     * Tests the attributes of the GetGameRoute.
     */
//    @Test
//    public void testAttributes(){
//
//        final TemplateEngineTester helper = new TemplateEngineTester();
//
//        when(templateEngine.render(any(ModelAndView.class) )).thenAnswer(helper.makeAnswer());
//        CuT.handle(request, response);
//
//        helper.assertViewModelExists();
//        helper.assertViewModelIsaMap();
//        when(session.attribute("mode")).thenReturn("PLAY");
//
//        helper.assertViewModelAttribute("viewMode", "PLAY");
//        //helper.assertViewModelAttribute("currentUser", session.attribute("currentUser"));
//
//
//    }



}
