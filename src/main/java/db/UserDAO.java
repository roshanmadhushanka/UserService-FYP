package db;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import exception.ValidationFailedException;
import model.User;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by roshanalwis on 8/23/17.
 */
public class UserDAO {
    private MongoClient mongoClient;
    private MongoCollection userCollection;

    public UserDAO(){
        mongoClient = MongoConnector.getMongoClient();
        userCollection = mongoClient.getDatabase("ozious").getCollection("user");
    }

    public static Document toDocument(User user){

        Document userObject = new Document();
        if(user.getEmail() != null)
            userObject.put("email", user.getEmail());

        if(user.getFirstName() != null)
            userObject.put("firstName", user.getFirstName());

        if(user.getLastName() != null)
            userObject.put("lastName", user.getLastName());

        if(user.getPassword() != null)
            userObject.put("password", user.getPassword());

        return userObject;
    }

    public void create(User user){
        Document userObject = toDocument(user);
        userCollection.insertOne(userObject);
    }

    public void update(User oldUser, User newUser){
        Document oldUserObject = toDocument(oldUser);
        Document newUserObject = toDocument(newUser);

        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$set", newUserObject);

        userCollection.updateMany(oldUserObject, updateObject);
    }

    public List<User> read(User user) {
        Document userObject = toDocument(user);

        List<User> userList = new ArrayList<>();
        FindIterable<Document> cursor = userCollection.find(userObject);
        for (Document obj: cursor
                ) {
            User userObj = new User();
            userObj.setEmail(obj.getString("email"));
            userObj.setFirstName(obj.getString("firstName"));
            userObj.setLastName(obj.getString("lastName"));
            userList.add(userObj);
        }
        return userList;
    }

    public void delete(User user){
        Document userObject = toDocument(user);
        userCollection.deleteMany(userObject);
    }
}
