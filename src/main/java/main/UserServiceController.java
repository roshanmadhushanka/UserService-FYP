package main;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import db.UserDAO;
import exception.RequiredAttributeMissingException;
import exception.ValidationFailedException;
import model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sys.Password;
import validation.Validation;

import java.util.List;

/**
 * Created by roshanalwis on 8/23/17.
 */

@RestController
public class UserServiceController {

    @RequestMapping(value = "/userService")
    public ResponseEntity<String> userService(){
        return new ResponseEntity<String>("ACK", HttpStatus.OK);
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ResponseEntity<String> addUser(@RequestBody User user){
        /*
            Register user for service
         */

        try {
            // Validate user object
            Validation.userValidation(user);

            // Hash password
            user.setPassword(Password.getSaltedHash(user.getPassword()));

            // Create database access object
            UserDAO userDAO = new UserDAO();

            // Check for existing users for particular email
            User tempUser = new User();
            tempUser.setEmail(user.getEmail());
            List<User> result = userDAO.read(tempUser);

            if(result.size() == 0){
                // Add user to the database
                userDAO.create(user);
                return new ResponseEntity<String>("Success", HttpStatus.OK);
            } else {
                // User already in the database
                return new ResponseEntity<String>("Email has already registered to the service", HttpStatus.OK);
            }

        } catch (RequiredAttributeMissingException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
        } catch (ValidationFailedException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/getUser", method = RequestMethod.POST)
    public ResponseEntity<String> getUser(@RequestBody User user) throws Exception {
        /*
            Get user details
         */

        String response = null;

        // Load users
        UserDAO userDAO = new UserDAO();
        List<User> userList = userDAO.read(user);

        // Convert result in to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            response = objectMapper.writeValueAsString(userList);
        } catch (JsonProcessingException e) {

        }

        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/removeUser", method = RequestMethod.POST)
    public ResponseEntity<User> removeUser(@RequestBody User user){
        /*
            Remove users
         */

        UserDAO userDAO = new UserDAO();
        userDAO.delete(user);

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestBody User user) throws Exception {
        try{
            Validation.loginValidation(user);

            UserDAO userDAO = new UserDAO();

            // Create temp user to search
            User tempUser = new User();
            tempUser.setEmail(user.getEmail());

            // Load user from database
            List<User> userList = userDAO.read(tempUser);
            if(userList.size() == 0){
                // If there is no user for the given email
                return new ResponseEntity<String>("Fail", HttpStatus.OK);
            } else {
                // Select first user from the user list
                User storedUser = userDAO.read(tempUser).get(0);

                if(Password.check(user.getPassword(), storedUser.getPassword())){
                    return new ResponseEntity<String>("Success", HttpStatus.OK);
                } else {
                    return new ResponseEntity<String>("Fail", HttpStatus.OK);
                }
            }
        } catch (RequiredAttributeMissingException e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
        } catch (ValidationFailedException e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
        }
    }
}
