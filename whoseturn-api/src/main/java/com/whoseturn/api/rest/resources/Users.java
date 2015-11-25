package com.whoseturn.api.rest.resources;

import com.whoseturn.api.entities.User;
import com.whoseturn.api.services.UserService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Named
@Path("/users")
public class Users {

    @Inject
    private UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> findUsers() {

        return userService.find();
    }
}