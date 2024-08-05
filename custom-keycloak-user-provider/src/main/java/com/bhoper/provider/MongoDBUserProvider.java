package com.bhoper.provider;

import com.bhoper.User;
import com.bhoper.UserAdapter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.keycloak.component.ComponentModel;
import org.keycloak.credential.CredentialInput;
import org.keycloak.credential.CredentialInputUpdater;
import org.keycloak.credential.CredentialInputValidator;
import org.keycloak.models.*;
import org.keycloak.models.credential.PasswordCredentialModel;
import org.keycloak.storage.StorageId;
import org.keycloak.storage.UserStorageProvider;
import org.keycloak.storage.user.UserLookupProvider;
import org.keycloak.storage.user.UserQueryProvider;
import org.keycloak.storage.user.UserRegistrationProvider;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class MongoDBUserProvider implements UserStorageProvider, UserLookupProvider,
                                            UserQueryProvider, CredentialInputValidator,
                                            CredentialInputUpdater, UserRegistrationProvider {

    private final KeycloakSession session;
    private final ComponentModel model;
    private final MongoClient mongoClient;
    private final MongoCollection<Document> userCollection;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public MongoDBUserProvider(KeycloakSession session, ComponentModel componentModel) {
        this.model = componentModel;
        this.session = session;
        this.mongoClient = MongoClients.create("mongodb://mongodb-keycloak-new-version:27017/");
        MongoDatabase database = mongoClient.getDatabase("keycloak");
        this.userCollection = database.getCollection("users");
    }

    @Override
    public boolean updateCredential(RealmModel realmModel, UserModel userModel, CredentialInput credentialInput) {
        if (credentialInput.getType().equals(PasswordCredentialModel.TYPE)) {
            UserCredentialModel cred = (UserCredentialModel) credentialInput;
            String hashedPassword = BCrypt.hashpw(cred.getChallengeResponse(), BCrypt.gensalt());
            String userEmail = userModel.getEmail();
            try {
                userCollection.updateOne(Filters.eq("email", userEmail),
                        new Document("$set", new Document("password", hashedPassword)));
                return true;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    @Override
    public void disableCredentialType(RealmModel realmModel, UserModel userModel, String s) {

    }

    @Override
    public Stream<String> getDisableableCredentialTypesStream(RealmModel realmModel, UserModel userModel) {
        return Stream.empty();
    }

    @Override
    public boolean supportsCredentialType(String s) {
        return PasswordCredentialModel.TYPE.equals(s);
    }

    @Override
    public boolean isConfiguredFor(RealmModel realmModel, UserModel userModel, String s) {
        return supportsCredentialType(s);
    }

    @Override
    public boolean isValid(RealmModel realmModel, UserModel userModel, CredentialInput credentialInput) {
        if (!(credentialInput instanceof UserCredentialModel)) {
            return false;
        }

        String username = userModel.getUsername();
        try {
            Document userDoc = userCollection.find(Filters.eq("username", username)).first();
            if (userDoc != null) {
                UserCredentialModel cred = (UserCredentialModel) credentialInput;
                String storedPassword = userDoc.getString("password");
//                return storedPassword.equals(cred.getChallengeResponse());
                return BCrypt.checkpw(cred.getChallengeResponse(), storedPassword);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public UserModel addUser(RealmModel realmModel, String username) {
        try {
            ObjectId id = new ObjectId();
            Document newUser = new Document("_id", id)
                    .append("username", username);
            userCollection.insertOne(newUser);
            return getUserById(realmModel, id.toHexString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean removeUser(RealmModel realmModel, UserModel userModel) {
        try {
            String email = userModel.getUsername();
            userCollection.deleteOne(Filters.eq("username", email));
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void preRemove(RealmModel realmModel) {

    }

    @Override
    public void preRemove(RealmModel realmModel, RoleModel roleModel) {

    }

    @Override
    public void preRemove(RealmModel realmModel, GroupModel groupModel) {

    }

    @Override
    public int getUsersCount(RealmModel realm) {
        return UserQueryProvider.super.getUsersCount(realm);
    }

    @Override
    public void close() {
        mongoClient.close();
    }

//    private UserModel mapToUserModel(User user, RealmModel realm) {
//        UserModel userModel = session.users().getUserById(realm, String.valueOf(user.getId()));
//        if (userModel == null) {
//            userModel = session.users().addUser(realm, user.getUsername());
//            userModel.setEmail(user.getEmail());
//            userModel.setFirstName(user.getFirstName());
//            userModel.setLastName(user.getLastName());
//            for (String roleName : user.getRoles()) {
//                RoleModel role = realm.getRole(roleName);
//                if (role != null) {
//                    userModel.grantRole(role);
//                }
//            }
//        }
//        return new UserAdapter(session, realm, model, user);
//    }

    @Override
    public UserModel getUserById(RealmModel realmModel, String s) {
//        Document doc = userCollection.find(new Document("id", s)).first();
//        if (doc != null) {
//            User user = User.fromDocument(doc);
//            return mapToUserModel(user, realmModel);
//        }
//        return null;

        return findUser(realmModel, s);
    }

    @Override
    public UserModel getUserByUsername(RealmModel realmModel, String username) {
//        Document doc = userCollection.find(new Document("username", username)).first();
//        if (doc != null) {
//            User user = User.fromDocument(doc);
//            return mapToUserModel(user, realmModel);
//        }
//        return null;

        try {
            Document userDoc = userCollection.find(Filters.eq("username", username)).first();
            if (userDoc != null) {
                return new UserAdapter(session, realmModel, model, buildUserAdapter(userDoc));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public UserModel getUserByEmail(RealmModel realmModel, String s) {
//        Document doc = userCollection.find(new Document("email", s)).first();
//        if (doc != null) {
//            User user = User.fromDocument(doc);
//            return mapToUserModel(user, realmModel);
//        }
//        return null;

        try {
            Document userDoc = userCollection.find(Filters.eq("email", s)).first();
            if (userDoc != null) {
                return new UserAdapter(session, realmModel, model, buildUserAdapter(userDoc));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public Stream<UserModel> searchForUserStream(RealmModel realmModel, Map<String, String> map, Integer integer, Integer integer1) {
//        List<Document> docs = userCollection.find().skip(integer).limit(integer1).into(new ArrayList<>());
//        return docs.stream().map(User::fromDocument).map(user -> mapToUserModel(user, realmModel));

        List<UserModel> users = new ArrayList<>();

        for (Document document : userCollection.find()) {
            users.add(new UserAdapter(session, realmModel, model, buildUserAdapter(document)));
        }

        return users.stream();
    }

    @Override
    public Stream<UserModel> getGroupMembersStream(RealmModel realmModel, GroupModel groupModel, Integer integer, Integer integer1) {
        return Stream.empty();
    }

    @Override
    public Stream<UserModel> searchForUserByUserAttributeStream(RealmModel realmModel, String s, String s1) {
//        Document query = new Document(s, s1);
//        List<Document> docs = userCollection.find(query).into(new ArrayList<>());
//        return docs.stream().map(User::fromDocument).map(user -> mapToUserModel(user, realmModel));

        return Stream.empty();
    }

    private User buildUserAdapter(Document userDoc) {
        User user = new User();
        user.setId(userDoc.getObjectId("_id"));
        user.setEmail(userDoc.getString("email"));
        user.setUsername(userDoc.getString("username"));
        user.setPassword(userDoc.getString("password"));
        user.setFirstName(userDoc.getString("firstName"));
        user.setLastName(userDoc.getString("lastName"));
        user.setRoles(List.of("ROLE_USER"));
        return user;
    }

    private UserModel findUser(RealmModel realm, String identifier) {
        try {
            Document userDoc = userCollection.find(Filters.eq("username", StorageId.externalId(identifier))).first();

            if (userDoc != null) {
                return new UserAdapter(session, realm, model, buildUserAdapter(userDoc));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
