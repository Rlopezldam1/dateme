package com.example.dateme;

import com.gluonhq.charm.glisten.control.Avatar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

import static java.lang.Double.MAX_VALUE;

public class MensajesController {

    @FXML
    private VBox vboxTexto;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private ImageView back;

    @FXML
    private Button enviar;

    @FXML
    private Avatar icono_escritor;

    @FXML
    private Avatar icono_principal;

    @FXML
    private Avatar icono_receptor;

    @FXML
    private Label nombre;

    @FXML
    private TextField texto;

    @FXML
    private Label texto_escritor;

    @FXML
    private Label texto_receptor;

    @FXML
    void back_click(MouseEvent event) {
        DateMeApp.cambiarPestana("mainpage/mainpage.fxml", "Inicio",900,600);
    }

    @FXML
    void clickPulsado(MouseEvent event) throws IOException {
        comprobarEnviarTexto();
    }

    @FXML
    void icono_principal_click(MouseEvent event) {
        DateMeApp.cambiarPestana("perfilUsuario/perfilUsuario.fxml", "Usuario",900,600);
    }

    @FXML
    void nombre_click(MouseEvent event) {
        DateMeApp.cambiarPestana("perfilUsuario/perfilUsuario.fxml", "Usuario",900,600);
    }

    @FXML
    void teclaPulsada(KeyEvent event) throws IOException {
        KeyCode caracter = event.getCode();
        if (caracter == KeyCode.ENTER){
            comprobarEnviarTexto();
        }
    }
    void comprobarEnviarTexto() throws IOException {
        if (!Objects.equals(texto.getText(), "")){
            HBox hbox = new HBox();
            hbox.setPadding(new Insets(15,0,0,0));
            ImageView iv = new ImageView();
            iv.setFitWidth(50);
            iv.setFitHeight(50);
            URL url = getClass().getResource("imgs/user.png");
            Image img = new Image(url.openStream());
            iv.setImage(img);
            Label label = new Label();
            HBox.setHgrow(label, Priority.ALWAYS);
            label.setPadding(new Insets(0,0,0,20));
            HBox.setMargin(label,new Insets(0,25,0,25));
            label.setMaxHeight(MAX_VALUE);
            label.setMaxWidth(MAX_VALUE);
            label.setText(texto.getText());
            label.setTextFill(Paint.valueOf("black"));
            label.setAlignment(Pos.CENTER_LEFT);
            hbox.getChildren().add(iv);
            hbox.getChildren().add(label);
            label.setStyle("-fx-background-color: darkgrey; -fx-background-radius: 10;");
            vboxTexto.getChildren().add(hbox);
            texto.setText("");
        }
    }

    public void ajustesClick(ActionEvent actionEvent) {
    }

    public void chatsClick(ActionEvent actionEvent) {
    }

    public void matchesClick(ActionEvent actionEvent) {
    }

    public void inicioClick(ActionEvent actionEvent) {
    }
}
