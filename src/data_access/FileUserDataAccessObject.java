package data_access;

import entity.User;
import entity.UserFactory;
import entity.GroceryList;
import entity.GroceryListFactory;

import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;
import use_case.grocery_list.GroceryListDataAccessInterface;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class FileUserDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface {

    private final File jsonFile;

    private final String JSON_PATH = "./users.json";

    private final Map<String, User> accounts = new HashMap<>();

    private UserFactory userFactory;

    private final ObjectMapper objectMapper;


    public FileUserDataAccessObject(String JSON_PATH, UserFactory userFactory) throws IOException {
        this.userFactory = userFactory;
        jsonFile = new File(JSON_PATH);
        objectMapper = new ObjectMapper();
//        headers.put("username", 0);
//        headers.put("password", 1);

        if (!jsonFile.exists() || jsonFile.length() == 0) {
            save();
        } else {
            load();
        }
    }
    private void load() throws IOException {
        if (jsonFile.length() > 0) {
            User[] users = objectMapper.readValue(jsonFile, User[].class);
            for (User user : users) {
                accounts.put(user.getUsername(), user);
            }
        }
    }

//
//        if (csvFile.length() == 0) {
//            save();
//        } else {
//
//            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
//                String header = reader.readLine();
//
//                // For later: clean this up by creating a new Exception subclass and handling it in the UI.
//                assert header.equals("username,password");
//
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    String[] col = line.split(",");
//                    String username = String.valueOf(col[headers.get("username")]);
//                    String password = String.valueOf(col[headers.get("password")]);
//                    User user = userFactory.create(username, password);
//                    accounts.put(username, user);
//                }
//            }
//        }
//

    private void save() {
//        BufferedWriter writer;
        try {
            objectMapper.writeValue(jsonFile, accounts.values().toArray(new User[0]));
//            writer = new BufferedWriter(new FileWriter(csvFile));
//            writer.write(String.join(",", headers.keySet()));
//            writer.newLine();
//
//            for (User user : accounts.values()) {
//                String line = String.format("%s,%s",
//                        user.getUsername(), user.getPassword());
//                writer.write(line);
//                writer.newLine();
//            }
//
//            writer.close();

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
