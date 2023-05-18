package com.example.dateme;

import com.gluonhq.charm.glisten.control.Avatar;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import java.util.Objects;

public class MensajesController {

    @FXML
    private VBox vboxTexto;

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
        cambiarVentana("mainpage/mainpage.fxml", "Inicio");
    }

    @FXML
    void clickPulsado(MouseEvent event) {
        comprobarEnviarTexto();
    }

    @FXML
    void icono_principal_click(MouseEvent event) {
        cambiarVentana("perfilUser/perfilUser.fxml", "Usuario");
    }

    @FXML
    void nombre_click(MouseEvent event) {
        cambiarVentana("perfilUser/perfilUser.fxml", "Usuario");
    }

    @FXML
    void teclaPulsada(KeyEvent event) {
        KeyCode caracter = event.getCode();
        if (caracter == KeyCode.ENTER){
            comprobarEnviarTexto();
        }
    }
    void comprobarEnviarTexto(){
        if (!Objects.equals(texto.getText(), "")){
            HBox hbox = new HBox();
            ImageView iv = new ImageView();
            iv.setLayoutX(530);
            Label label = new Label();
            label.setLayoutX(330);
            label.setMaxWidth(500);
            label.setText(texto.getText());
            label.setTextFill(Paint.valueOf("black"));
            label.autosize();
            label.setStyle("-fx-background-color:  darkgrey");
            label.setStyle("-fx-background-radius:  10");
            label.setAlignment(Pos.CENTER_LEFT);
            hbox.getChildren().add(iv);
            hbox.getChildren().add(label);
            vboxTexto.getChildren().add(hbox);
            texto.setText("");
        }
    }

    private void cambiarVentana(String ruta, String nombreVentana) {
        DateMeApp.cambiarPestana(ruta,nombreVentana);
    }

}
