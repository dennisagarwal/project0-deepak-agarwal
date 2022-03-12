package com.revature.controller;

import com.revature.exception.ClientNotFoundException;
import io.javalin.Javalin;
import io.javalin.http.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//Instead of using try-catch (directly from Java language), we can abstract that process away
//using Javalin's exception mapping functionality
//This exception controller provide us with the ability to "map" exceptions i one central location
//that get thrown from the endpoint handler themselves


public class ExceptionController implements Controller{

    private Logger logger = LoggerFactory.getLogger(ExceptionController.class);
    private ExceptionHandler clientNotFound= (e, ctx) -> {
        logger.warn("User attempted to retrieve a student that was not found. Exception message is " + e.getMessage());
        ctx.status(404);
        ctx.json(e.getMessage());
    };

    private ExceptionHandler badArgument = (e, ctx) -> {
        logger.warn("User input a bad argument. Exception message is" + e.getMessage());
        ctx.status(400);
        ctx.json(e.getMessage());
    };
    @Override
    public void mapEndpoints(Javalin app) {
   app.exception(ClientNotFoundException.class, clientNotFound);
        app.exception(IllegalArgumentException.class, badArgument);
    }
}
