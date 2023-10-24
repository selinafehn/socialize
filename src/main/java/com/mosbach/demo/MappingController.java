package com.mosbach.demo;

import com.mosbach.demo.data.api.TaskManager;
import com.mosbach.demo.data.api.User;
import com.mosbach.demo.data.api.UserManager;
import com.mosbach.demo.data.impl.*;
import com.mosbach.demo.model.*;
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

    // Datenbankenschnittstelle zum POSTGRESDBUSERMANAGERIMPL
    UserManager userManager = PostgresDBUserManagerImpl.getPostgresDBUserManagerImpl();

    // KANN MAN DOCH EIGENTLICH LÖSCHEN????
    TaskManager taskManager = PostgresDBTaskManagerImpl.getPostgresDBUserManagerImpl();

    //meetUp Manager (?)

    /**
     *
     * HIER ENTSTEHT DER DATENBANKEN CALL ZUM ERSTELLEN DER USER TABELLE, ZUM ERSTELLEN EINES USERS, UND WSL AUCH ZUM LÖSCHEN EINES USERS
     *
     * SCHRITT FÜR SCHRITT ERKLÄRUNG:
     *
     * WRITE ENDPOINT FOR CREATING THE TABLE.
     * -> IMPORTANT: IN POSTGRESDBUSERMANAGERIMPL THERE IS -- public void createUserTable() -- METHOD.
     * THIS HAS TO BE ADDED FRIST. AND THEN IT HAS TO BE USED IN ENDPOINT MAPPING
     *
     * String createTable= "CREATE TABLE users (" +
     *                     "userid varchar(100) PRIMARY KEY NOT NULL, " +
     *                     "firstname varchar(255) NOT NULL," +
     *                     "lastname varchar(255) NOT NULL," +
     *                     "password varchar(255) NOT NULL," +
     *                     "email varchar(255) NOT NULL," +
     *                     "token varchar(255) NOT NULL," +
     *                     "validuntil int NOT NULL)";
     * ---> DIE DATEN WELCHE HIER STEHEN SIND ALLE AUS DEM ERM ZU ENTNEHMEN
     *
     * IM USERMANAGER.java SIND DANN ERGÄNZUNGEN VORZUNEHMEN BEZÜGLICH DES INTERFACES UND DER METHODEN
     * DIE FUNKTIONEN SIND IM POSTGRESDBUSERMANAGERIMPL.java HINTERLEGT. WELCHE DANN AUCH ANGEPASST WERDEN MÜSSEN.
     *
     * DAS CREATE USER MAPPING IST AUCH ALS POST REALISIERT.
     * WELCHER DANN AN DEN USERMANAGER IN DER ALLE WICHTIGEN DATEN ALS ATTRIBUTE HINTERLEGT UND SPEICHERT.
     * DIE ID'S WERDEN ALLE ALS UUID AUTOGENERIERT.
     *
     * DAS USER INTERFACE MUSS AUCH ALLE INFORMATIONEN HINTERLEGT HABEN. MIT TYPEN
     * PER JSONPOJO MUSS DANN AUCH DAS JSON WELCHES MIT DEM CALL GESENDET WIRD IN EINE KLASSE UMGEWANDELT WERDEN.
     *
     * IM POSTGRESDBUSERMANAGER IST DANN DIE EIGENTLICHE CREATEUSER() FUNKTION HINTERLEGT.
     *
     */

    // erstellt die Datenbanktabelle zum User.
    @PostMapping ("/create-table/user")
    public String createUserTable(@RequestParam(value = "token", defaultValue = "Student") String name) {
        userManager.createUserTable();
        return "User Created";
    }

    @PostMapping(
            path = "/createuser",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @ResponseStatus(HttpStatus.OK)
    public String userRegistration(@RequestBody CreateUser createUser) {
        userManager.createUser(
                UUID.randomUUID().toString(),
                createUser.getFirstname(),
                createUser.getLastname(),
                createUser.getPassword(),
                createUser.getEmail(),
                createUser.getToken(),
                createUser.getValiduntil());
        return "user created";
    }


    /**
     * ----------------------------------------------------------------------------------------------------------------
     * FOLGEND SIND DIE API'S HINTERLEGT
     * ----------------------------------------------------------------------------------------------------------------
     */

    /**
     * GET /auth only for testing whether the server is alive
     */

    @GetMapping("/auth")
    public List<User> getInfo(@RequestParam(value = "name", defaultValue = "Student") String name) {
        Logger.getLogger("MappingController").log(Level.INFO,"MappingController auth " + name);
        return userManager.readAllUsers();
    }


    /**
     * POST to /auth/login. FOLLOWING JSON HAS TO BE SEND:
     *
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
        return "Token\n" +
                "Validinmin\n" +
                "\n" +
                "Oder\n" +
                "\n" +
                "Error pw falsch\n";
    }

    /**
     * POST to /auth/register. FOLLOWING JSON HAS TO BE SEND:
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
        return "OK \n" +
                "oder \n" +
                "error\n";
    }


    /**
     * DELETE to /auth/login, to revert the login syntactically. FOLLOWING JSON HAS TO BE SEND:
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
     * DELETE to  /auth/register, to delete the user account. FOLLOWING JSON HAS TO BE SEND:
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
     * POST to endpoint -> /dashboard/create, to create a new meetup into the dashboard. FOLLOWING JSON HAS TO BE SEND:
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
     * DELETE to same endpoint -> /dashboard/create, to delete the Meetup. FOLLOWING JSON HAS TO BE SEND:
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



    /**
     * POST to /dashboard/edit, to change some things in the MeetUp. FOLLOWING JSON HAS TO BE SEND:
     *
     */
    @PostMapping(
            path = "/dashboard/edit",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @ResponseStatus(HttpStatus.OK)
    public String meetupEdit(@RequestBody Meetupedit Meetupedit) {
        return "Sucessfully changed the Meetup";
    }



    /**
     * GET /dashboard/show to show the User the meetup connected with the MeetupID. FOLLOWING JSON HAS TO BE SEND:
     * {
     * "meetupID":"meetupID",
     * "token":"token"
     * }
     */
    @GetMapping(
        path = "/dashboard/show",
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @ResponseStatus(HttpStatus.OK)
    public String meetupShow(@RequestBody Meetupshow Meetupshow) {
        return "Sucessfully changed the Meetup" +
                "- title\n" +
                "- friends\n" +
                "- date\n" +
                "- place\n" +
                "- sepcification\n" +
                "- timerange\n" +
                "- description\n" +
                "\n";
    }


    /**
     * GET /dashboard/overview to show the User the dashboard in an overview. FOLLOWING JSON HAS TO BE SEND:
     * {
     * "token":"token"
     * }
     */
    @GetMapping(
            path = "/dashboard/overview",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @ResponseStatus(HttpStatus.OK)
    public String meetupOverview(@RequestBody Meetupoverview Meetupoverview) {
        return "LIST(meetup)";
    }
}