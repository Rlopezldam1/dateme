package com.example.dateme;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MatchController implements Initializable {

    @FXML
    private Button ajustes;

    @FXML
    private HBox chat1;

    @FXML
    private Button chats;

    @FXML
    private Button inicio;

    @FXML
    private Button matches;

    @FXML
    private VBox vboxMatch;

    @FXML
    void ajustesClick(MouseEvent event) {
        DateMeApp.cambiarPestana("settings/settings.fxml","Ajustes",900,600);
    }

    @FXML
    void chatsClick(MouseEvent event) {
        DateMeApp.cambiarPestana("chats/chats.fxml","Chats",900,600);
    }

    @FXML
    void inicioClick(MouseEvent event) {
        DateMeApp.cambiarPestana("mainpage/mainpage.fxml","DateMe",900,600);
    }

    @FXML
    void matchesClick(MouseEvent event) {
        DateMeApp.cambiarPestana("match/match.fxml","Match",900,600);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String consultaResultados = SQLiteConnection.ejecutarConsulta("SELECT CASE WHEN m.user_id_1 = '" + DateMeApp.nombreUsuarioIniciado + "' THEN m.user_id_2 ELSE m.user_id_1 END FROM match m WHERE m.user_id_1 = '" + DateMeApp.nombreUsuarioIniciado + "' OR m.user_id_2 = '" + DateMeApp.nombreUsuarioIniciado + "'");
        String[] lineas = consultaResultados.split("\n");

        // Recorrer las líneas y separar los campos
        for (String linea : lineas) {
            String rutaFoto = SQLiteConnection.ejecutarConsulta("SELECT foto FROM usuarios m WHERE id_usuario = '" + linea + "'");
            System.out.println(rutaFoto);
            String rutaFotoFinal = rutaFoto;
            if (!rutaFoto.isEmpty()) {
                rutaFotoFinal = rutaFoto.substring(0, rutaFoto.length() - 1);
            }
            Pane pane = new Pane();
            HBox hbox = new HBox();
            Separator separator = new Separator();
            separator.setPrefWidth(750);
            ImageView iv = new ImageView();
            iv.setFitWidth(50);
            iv.setFitHeight(50);
            String ruta = "fotosperfil/" + rutaFotoFinal;
            URL urlFoto = getClass().getResource(ruta);
            Image img = null;
            try {
                img = new Image(urlFoto.openStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            iv.setImage(img);
            HBox.setMargin(iv, new Insets(10,0,0,5));
            Label label = new Label();
            HBox.setMargin(label, new Insets(20,0,0,15));
            label.setFont(Font.font(18));
            label.setText(linea);
            Image finalImg = img;
            label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    try {
                        DateMeApp.cambiarChat(label.getText(), finalImg);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            hbox.getChildren().add(iv);
            hbox.getChildren().add(label);
            pane.getChildren().add(hbox);
            pane.getChildren().add(separator);
            vboxMatch.getChildren().add(pane);
            }


        }
    }
