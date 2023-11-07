package com.mosbach.demo;
import com.mosbach.demo.data.api.*;
import com.mosbach.demo.data.impl.*;
import com.mosbach.demo.model.*;
import com.mosbach.demo.model.alexa.AlexaRO;
import com.mosbach.demo.model.alexa.OutputSpeechRO;
import com.mosbach.demo.model.alexa.ResponseRO;
import com.mosbach.demo.model.auth.SendBackToken;
import com.mosbach.utils.OptionSorter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1.0")
public class MappingController {

    // Datenbankenschnittstelle
    UserManager userManager = PostgresDBUserManagerImpl.getPostgresDBUserManagerImpl();
    MeetupManager meetupManager = PostgresDBMeetupManagerImpl.getPostgresDBUserManagerImpl();
    OptionsManager optionsManager = PostgresDBOptionManagerImpl.getPostgresDBOptionManagerImpl();
    AttendeesManager attendeesManager = PostgresDBAttendeesManagerImpl.getPostgresDBAttendeesManagerImpl();
    VotingManager votingManager = PostgresDBVotingManagerImpl.getPostgresDBVotingManagerImpl();


    // ---------------------------------------------------------------------------------------
    // CHECK SERVER
    // ---------------------------------------------------------------------------------------

    @GetMapping("/auth")
    public void getInfo(@RequestParam(value = "name", defaultValue = "Student") String name) {
        Logger.getLogger("MappingController").log(Level.INFO, "MappingController auth " + name);
    }

    // ---------------------------------------------------------------------------------------
    // CREATE TABLE
    // ---------------------------------------------------------------------------------------

    // erstellt die Datenbanktabelle zum User.
    @PostMapping ("/create-table/user")
    public String createUserTable(@RequestParam(value = "token", defaultValue = "Student") String name) {
        userManager.createUserTable();
        return "UserTable Created";
    }

    // erstellt die Datenbanktabelle zum Meetup.
    @PostMapping ("/create-table/meetup")
    public String createMeetupTable(@RequestParam(value = "token", defaultValue = "Student") String name) {
        meetupManager.createMeetupTable();
        return "MeetupTable Created";
    }

    // erstellt die Datenbanktabelle zu den Datums Optionen.
    @PostMapping ("/create-table/attendees")
    public String createAttendeesTable(@RequestParam(value = "token", defaultValue = "Student") String name) {
        attendeesManager.createAttendeesTable();
        return "AttendeesTable Created";
    }

    // erstellt die Datenbanktabelle zu den datumsoptionen.
    @PostMapping ("/create-table/options")
    public String createOptionsTable(@RequestParam(value = "token", defaultValue = "Student") String name) {
        optionsManager.createOptionsTable();
        return "OptionsTable Created";
    }

    // erstellt die Datenbanktabelle zum voting.
    @PostMapping ("/create-table/voting")
    public String createVotingTable(@RequestParam(value = "token", defaultValue = "Student") String name) {
        votingManager.createVotingTable();
        return "VotingTable Created";
    }

    // ---------------------------------------------------------------------------------------
    // USER ENDPUNKTE
    // ---------------------------------------------------------------------------------------

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

    @GetMapping("/auth/user")
    public List<User> getInfoUser(@RequestParam(value = "name", defaultValue = "Student") String name) {
        Logger.getLogger("MappingController").log(Level.INFO,"MappingController auth " + name);
        return userManager.readAllUsers();
    }

    /**
     * POST to /auth/login. FOLLOWING JSON HAS TO BE SEND:
     * <p>
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
    public SendBackToken userLogIn(@RequestBody Userlogin userlogin) {
        return userManager.logUserIn(userlogin.getEmail(), userlogin.getPassword());
    }

    /**
     * POST to /auth/register. FOLLOWING JSON HAS TO BE SEND:
     {
     "email":"email",
     "password":"password",
     "firstname ":"firstname ",
     "lastname ":"lastname "
     }
     */
    @PostMapping(
            path = "/auth/register",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @ResponseStatus(HttpStatus.OK)
    public String userRegister(@RequestBody CreateUser createUser) {
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
        if (userManager.logUserOff(userlogoff.getToken())){
            return "logged off";
        }
        return "could not log off";
    }

    @DeleteMapping(
            path = "createuser",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @ResponseStatus(HttpStatus.OK)
    public String deleteUserbyID(@RequestParam(name = "userID") String userID){
        return "deleted successfully ";
    }

    // ---------------------------------------------------------------------------------------
    // MEETUP
    // ---------------------------------------------------------------------------------------

    @PostMapping(
            path = "/createmeetup",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @ResponseStatus(HttpStatus.OK)
    public String meetupCreation(@RequestBody CreateMeetup createMeetup) {

        List<String> attendees = Arrays.asList(createMeetup.getFriends().split("\\s*,\\s*"));

        meetupManager.createMeetup(
                UUID.randomUUID().toString(),
                createMeetup.getTitle(),
                createMeetup.getFriends(),
                createMeetup.getOption(),
                createMeetup.getLocation(),
                createMeetup.getValiduntil(),
                createMeetup.getDescription(),
                attendees
                );
        return "meetup created";
    }

    @GetMapping("/auth/meetup")
    public List<Meetup> getInfoMeetup(@RequestParam(value = "name", defaultValue = "Student") String name) {
        Logger.getLogger("MappingController").log(Level.INFO,"MappingController auth " + name);
        return meetupManager.readAllMeetup();
    }

    @GetMapping("/auth/mymeetup")
    public List<Meetup> getMyMeetup(@RequestParam(value = "token") String token) {
        Logger.getLogger("MappingController").log(Level.INFO,"MappingController auth " + token);
        return meetupManager.readMyMeetups(token);
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
    //TODO
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


// ---------------------------------------------------------------------------------------
// ATTENDEES
// ---------------------------------------------------------------------------------------

    @GetMapping("/auth/attendees")
    public List<Attendees> getInfoAttendees(@RequestParam(value = "name", defaultValue = "Student") String name) {
        Logger.getLogger("MappingController").log(Level.INFO,"MappingController auth " + name);
        return attendeesManager.readAllAttendees();
    }

    @PostMapping(
            path = "/dashboard/addAttendee",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @ResponseStatus(HttpStatus.OK)
    public String createAttendee(@RequestBody CreateAttendee createAttendee) {
        attendeesManager.createAttendee(
                createAttendee.getRelID(),
                createAttendee.getUserID(),
                createAttendee.getMeetupID(),
                createAttendee.getHost());
        return "Attendee was added";
    }

// ---------------------------------------------------------------------------------------
// ATTENDEES
// ---------------------------------------------------------------------------------------

    @PostMapping(
            path = "/dashboard/addVoting",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @ResponseStatus(HttpStatus.OK)
    public String createVoting(@RequestBody CreateVoting createVoting) {
        votingManager.createVoting(
                UUID.randomUUID().toString(),
                createVoting.getUserid(),
                createVoting.getMeetupid(),
                createVoting.getOpt1(),
                createVoting.getOpt2(),
                createVoting.getOpt3(),
                createVoting.getOpt4(),
                createVoting.getOpt5(),
                createVoting.getOpt6(),
                createVoting.getOpt7());
        return "Voting was added";
    }

    @GetMapping("/auth/votings")
    public List<Voting> getInfoVotings(@RequestParam(value = "name", defaultValue = "Student") String name) {
        Logger.getLogger("MappingController").log(Level.INFO,"MappingController auth " + name);
        return votingManager.readAllVotings();
    }

    @GetMapping("/dashboard/doneVoting")
    public Map<String,Integer> getTopOptVoting(@RequestParam(value = "meetupid", defaultValue = "meetupid") String meetupid) {
        Logger.getLogger("MappingController").log(Level.INFO,"MappingController auth " + meetupid);
        return OptionSorter.Sorter(meetupid);
    }

// ---------------------------------------------------------------------------------------
// OPTIONS
// ---------------------------------------------------------------------------------------


    @PostMapping(
            path = "/dashboard/addOptions",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @ResponseStatus(HttpStatus.OK)
    public String createOptions(@RequestBody CreateOptions createOptions) {
        optionsManager.createOptions(
                UUID.randomUUID().toString(),
                createOptions.getOptionserial(),
                createOptions.getMeetupid(),
                createOptions.getDateandtime());
        return "Option was added";
    }

    //-----------------------------------------------------------------------------------------------------------


    @PostMapping(path= "/alexa", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public AlexaRO readTasksViaAlexa(@RequestBody AlexaRO alexaRO) {
        Logger.getLogger("MappingController").log(Level.INFO,"MappingController /alexa ");
        String outText = "";

        //launchRequest
        if(alexaRO.getRequest().getType().equalsIgnoreCase("LaunchRequest")){
            outText += "Welcome to socialize. ";
        }

        //TaskReadIntent
        if(alexaRO.getRequest().getType().equalsIgnoreCase("Meetupintend")
                && alexaRO.getRequest().getIntent().getName().equalsIgnoreCase("Meetupintend")){
            outText += "You have the following meetings: " + alexameetups();
        }
        return prepareResponse(alexaRO, outText,true);
    }

    public List<Meetup> alexameetups(){

        List<Meetup> list = new ArrayList<>();
        list = meetupManager.readAllMeetup();

        return list;

    }

    private AlexaRO prepareResponse(AlexaRO alexaRO, String outText, boolean shouldEndSession) {

        alexaRO.setRequest(null);
        alexaRO.setContext(null);
        alexaRO.setSession(null);
        OutputSpeechRO outputSpeechRO = new OutputSpeechRO();
        outputSpeechRO.setType("PlainText");
        outputSpeechRO.setText(outText);
        ResponseRO response = new ResponseRO(outputSpeechRO, shouldEndSession);
        alexaRO.setResponse(response);
        return alexaRO;
    }
}