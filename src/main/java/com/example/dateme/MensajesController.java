package com.example.dateme;

import com.gluonhq.charm.glisten.control.Avatar;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import java.util.Objects;

public class MensajesController {

    @FXML
    private ImageView back;

    @FXML
    private ImageView back1;

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

    }

    @FXML
    void clickPulsado(MouseEvent event) {
        comprobarEnviarTexto();
    }

    @FXML
    void icono_principal_click(MouseEvent event) {

    }

    @FXML
    void nombre_click(MouseEvent event) {

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
            texto_escritor.setText(texto.getText());
            texto.setText("");
        }
    }
}
