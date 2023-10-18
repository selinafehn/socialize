package com.mosbach.demo;

import com.mosbach.demo.data.api.SortOrder;
import com.mosbach.demo.data.api.TaskManager;
import com.mosbach.demo.data.api.UserManager;
import com.mosbach.demo.data.impl.*;
import com.mosbach.demo.model.*;
import com.mosbach.demo.model.auth.EmailToken;
import com.mosbach.demo.model.alexa.AlexaRO;
import com.mosbach.demo.model.alexa.OutputSpeechRO;
import com.mosbach.demo.model.alexa.ResponseRO;
import com.mosbach.demo.model.auth.OnlyToken;
import com.mosbach.demo.model.auth.SendBackToken;
import com.mosbach.demo.model.auth.User;
import com.mosbach.demo.model.student.Student;
import com.mosbach.demo.model.student.StudentList;
import com.mosbach.demo.model.student.StudentNoPassword;
import com.mosbach.demo.model.task.Task;
import com.mosbach.demo.model.task.TaskList;
import com.mosbach.demo.model.task.TokenTask;
import com.mosbach.demo.model.task.TokenTaskid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@CrossOrigin(origins = "", allowedHeaders = "")
@RestController
@RequestMapping("/api/v1.0")
public class MappingController {

    // Turn on if you store data to postgres
    // UserManager userManager = PostgresDBUserManagerImpl.getPostgresDBUserManagerImpl();

    // Turn on if you store data to property files
    UserManager userManager = PropertyFileUserManagerImpl.getPropertyFileUserManagerImpl("src/main/resources/users.properties");
    // TaskManager taskManager = PropertyFileTaskManagerImpl.getPropertyFileTaskManagerImpl("src/main/resources/tasks.properties");
    TaskManager taskManager = PostgresDBTaskManagerImpl.getPostgresDBUserManagerImpl();


    /**
     * The API Call header: ###
     *
     * POST https://radiant-ravine-78045-5b6112d7be12.herokuapp.com/api/v1.0/auth/register
     * Content-Type: application/JSON
     *
     * HERE HAS TO STAND THE JSON SENT IN! EVERY JSON IS WRITTEN IN FRONT OF THE API CALL
     */

    /**
     * GET /auth only for testing whether the server is alive
     */
    @GetMapping("/auth")
    public String getInfo(@RequestParam(value = "name", defaultValue = "Student") String name) {
        Logger.getLogger("MappingController").log(Level.INFO,"MappingController auth " + name);
        return "OK";
    }
    /**
     * POST to /auth/login. important you have to send the following Data with the Api Call:
     * {
     * "email":"email",
     * "password":"password"
     * }
     */
    @PostMapping(
            path = "/auth/login",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @ResponseStatus(HttpStatus.OK)
    public String userLogIn(@RequestBody Userlogin userlogin) {
        return "OK";
    }

    /**
     * POST to /auth/register. important you have to sent the following Data within the API call:
     {
     "email":"email",
     "password":"password",
     "firstname ":"firstname ",
     "lastname ":"lastname ",
     "username ":"username "
     }
     */
    @PostMapping(
            path = "/auth/register",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @ResponseStatus(HttpStatus.OK)
    public String userRegister(@RequestBody Userregister userregister) {
        return "OK";
    }
    /**
     * DELETE to same endpoint -> /auth/login, to revert the login syntactically. Send with following JSON
     * {
     * "token":"token"
     * }
     */
    @DeleteMapping(
            path = "/auth/login",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @ResponseStatus(HttpStatus.OK)
    public String userLogoff(@RequestBody Userlogoff userlogoff) {
        return "logged off";
    }
    /**
     * DELETE to same endpoint -> /auth/register, to delete the user account. Send with following JSON
     * {
     * "email":"email",
     * "token":"token",
     * "password":"password"
     * }
     */
    @DeleteMapping(
            path = "/auth/register",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @ResponseStatus(HttpStatus.OK)
    public String userDelete(@RequestBody Userdelete userdelete) {
        return "sucessfully deleted";
    }



    /**
     * POST to same endpoint -> /dashboard/create, to create a new meetup into the dashboard. Send with following JSON
     * {
     *    "title":"title",
     *    "friends":[
     *       "user1",
     *       "user2"
     *    ],
     *    "date":"date",
     *    "place":"place",
     *    "specification":"specification",
     *    "timerange":"timerange",
     *    "token":"token",
     *    "description":"decription"
     * }
     */
    @PostMapping(
            path = "/dashboard/create",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @ResponseStatus(HttpStatus.OK)
    public String meetupCreate(@RequestBody Meetupcreate Meetupcreate) {
        return "The Meetup was created";
    }


    /**
     * DELETE to same endpoint -> /auth/register, to delete the user account. Send with following JSON
     * {
     * "userID":"userID",
     * "meetupID":"meetupID"
     * }
     */
    @DeleteMapping(
            path = "/dashboard/create",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @ResponseStatus(HttpStatus.OK)
    public String meetupDelete(@RequestBody Meetupdelete Meetupdelete) {
        return "sucessfully deleted";
    }



}