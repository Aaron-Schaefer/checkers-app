package com.webcheckers.ui;

import static org.junit.jupiter.api.Assertions.*;
import spark.Response;
import spark.Session;
import sun.misc.Request;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class PostValidateMoveRouteTest {

    private Request request;
    private Session session;
    private Response response;


    @BeforeEach
    private void setup(){

        request = mock(Request.class);
        response = mock(Response.class);
        session = mock(Session.class);

    }
}
