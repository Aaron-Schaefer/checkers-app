package com.webcheckers.ui;

import static org.junit.jupiter.api.Assertions.*;
import spark.Response;
import spark.Session;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
/**
 * Class to test the PostValidateMoveRoute.
 *
 * @author Aaron, Gavin, Mark, Ries, Zach
 */
public class PostValidateMoveRouteTest {

    /**
     * Parameters used in testing.
     */
    private Response request;
    private Session session;
    private Response response;


    /**
     * Initialization.
     */
    @BeforeEach
    private void setup(){

        request = mock(Response.class);
        response = mock(Response.class);
        session = mock(Session.class);

    }

}
