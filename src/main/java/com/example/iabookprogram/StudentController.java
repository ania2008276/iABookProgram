package com.example.iabookprogram;

import javafx.scene.control.MenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class StudentController {
    public MenuButton myMenuButton;

    public void initialize(){
        // Create an ImageView with your image
        Image menuImage = new Image(getClass().getResourceAsStream("/Images/blankProfileImage.png"));
        ImageView myImageView = new ImageView(menuImage);
        myImageView.setFitHeight(100);
        myImageView.setFitWidth(100);

        myMenuButton.setGraphic(myImageView);
    }
}
