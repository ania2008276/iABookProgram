package com.example.iabookprogram;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginController {
    public static User user;
    public TextField firstNameTextField;
    public TextField surnameTextField;

    public void initialize() throws IOException {
        fromJson();
        if (user != null) {
            firstNameTextField.setText(user.getFirstName());
            surnameTextField.setText(user.getSurname());
        }
    }
    @FXML
    protected void onHelloButtonClick() throws IOException {

        String regex = ".*\\d.*";  // regex to check if string contains any numbers


            Pattern pattern = Pattern.compile(regex);  // compiles the regex
            // find match between given string and pattern
            Matcher matcherText = pattern.matcher(firstNameTextField.getText() + surnameTextField.getText());
            // return true if the string matched the regex
            Boolean textMatches = matcherText.matches();
            if (!(firstNameTextField.getText().equals("")) && !(surnameTextField.getText().equals("")) && !textMatches) {
                user = new User(firstNameTextField.getText(), surnameTextField.getText());
                System.out.println(user.getFirstName());
                System.out.println(user.getSurname());
                toJson();
                IaBookProgram.setRoot("student-view");
            } else if (textMatches) {
                System.out.println("You entered a number.");
            } else {
                System.out.println("The first or surname is blank.");
            }

    }


    //https://mkyong.com/java/how-do-convert-java-object-to-from-json-format-gson-api/
    public static void toJson() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter("user.json")) {
            gson.toJson(user, writer);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void fromJson() throws IOException {
        Gson gson = new Gson();

        try (Reader reader = new FileReader("user.json")) {
            // Convert JSON File to Java Object
            user = gson.fromJson(reader, User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}