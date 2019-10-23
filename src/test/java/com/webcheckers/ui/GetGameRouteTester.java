package com.webcheckers.ui;

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

    }

    @Test
    public void testAtributes(){

        final TemplateEngineTester helper = new TemplateEngineTester();

        when(templateEngine.render(any(ModelAndView.class) )).thenAnswer(helper.makeAnswer());
        CuT.handle(request, response);

        helper.assertViewModelExists();
        helper.assertViewModelIsaMap();

        helper.assertViewModelAttribute("viewMode", "PLAY");
        helper.assertViewModelAttribute("currentUser", session.attribute("currentUser"));


    }



}
