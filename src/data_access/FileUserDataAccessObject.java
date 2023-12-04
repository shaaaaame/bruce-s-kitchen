package data_access;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.module.SimpleModule;
import entity.User;
import entity.UserFactory;

import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.*;

public class FileUserDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface {

    private final File jsonFile;

    private final String JSON_PATH = "./users.json";

    private final Map<String, User> accounts = new HashMap<>();

    private UserFactory userFactory;

    private final ObjectMapper objectMapper;


    public FileUserDataAccessObject(String JSON_PATH, UserFactory userFactory) throws IOException {
        this.userFactory = userFactory;
        objectMapper = new ObjectMapper();
        jsonFile = new File(JSON_PATH);

        if (jsonFile.length() == 0) {
            this.save();
        } else {
            load();
        }
    }
    private void load() throws IOException {
        StringBuilder jsonStringBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(jsonFile))) {
            String row;
            while ((row = reader.readLine()) != null) {
                jsonStringBuilder.append(row);
            }
        }

        ObjectMapper objectMapper = new ObjectMapper();

        SimpleModule module = new SimpleModule();
        module.addDeserializer(User.class, new UserDeserializer());
        objectMapper.registerModule(module);
        List<User> users = objectMapper.readValue(jsonStringBuilder.toString(), new TypeReference<List<User>>() {});

        for (User user : users) {
            accounts.put(user.getUsername(), user);

        }
    }

    public void save() {
        try {
            objectMapper.writeValue(jsonFile, accounts.values().toArray(new User[0]));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User get(String username) {
        return accounts.get(username);
    }

    @Override
    public void save(User user) {
        accounts.put(user.getUsername(), user);
        this.save();
    }

    @Override
    public boolean existsByName(String username) {
        return accounts.containsKey(username);
    }
}


//package data_access;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.module.SimpleModule;
//import entity.User;
//import entity.UserFactory;
//
//import use_case.login.LoginUserDataAccessInterface;
//import use_case.signup.SignupUserDataAccessInterface;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import java.io.*;
//import java.util.*;
//
//public class FileUserDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface {
//
//    private final File jsonFile;
//
//    private String JSON_PATH;
//
//    private final Map<String, User> accounts = new HashMap<>();
//
//    private UserFactory userFactory;
//
//    private final ObjectMapper objectMapper;
//
//
//    public FileUserDataAccessObject(String JSON_PATH, UserFactory userFactory) throws IOException {
//        this.userFactory = userFactory;
//        objectMapper = new ObjectMapper();
//        jsonFile = new File(JSON_PATH);
//
//        if (jsonFile.length() == 0) {
//            this.save();
//        } else {
//            load();
//        }
//    }
//    private void load() throws IOException {
//        StringBuilder jsonStringBuilder = new StringBuilder();
//
//        try (BufferedReader reader = new BufferedReader(new FileReader(jsonFile))) {
//            String row;
//            while ((row = reader.readLine()) != null) {
//                jsonStringBuilder.append(row);
//            }
//        }
//
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        SimpleModule module = new SimpleModule();
//        module.addDeserializer(User.class, new UserDeserializer());
//        objectMapper.registerModule(module);
//        List<User> users = objectMapper.readerFor(new TypeReference<List<User>>() {}).readValue(jsonStringBuilder.toString());
//        // List<User> users = objectMapper.readValue(jsonStringBuilder.toString(), new TypeReference<List<User>>() {});
//
//        for (User user : users) {
//            accounts.put(user.getUsername(), user);
//        }
//    }
//
//    private void save() {
//        try {
//            objectMapper.writeValue(jsonFile, accounts.values());
//        // .toArray(new User[0])
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public User get(String username) {
//        return accounts.get(username);
//    }
//
//    @Override
//    public void save(User user) {
//        accounts.put(user.getUsername(), user);
//        this.save();
//    }
//
//    @Override
//    public boolean existsByName(String username) {
//        return accounts.containsKey(username);
//    }
//}
