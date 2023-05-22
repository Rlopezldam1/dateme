package com.example.dateme;

import com.gluonhq.charm.glisten.control.Avatar;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.ResourceBundle;

import static java.lang.Double.MAX_VALUE;

public class MensajesController {

    @FXML
    private Button ajustes;

    @FXML
    private Button chats;

    @FXML
    private Button enviar;

    @FXML
    private ImageView iconoPrincipal;

    @FXML
    private Button inicio;

    @FXML
    private Button matches;

    @FXML
    private Label nombre;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TextField texto;

    @FXML
    private VBox vboxTexto;

    private static ObjectProperty<VBox> vboxProperty = new SimpleObjectProperty<>();
    private static StringProperty nombreProperty = new SimpleStringProperty();
    private static ObjectProperty<Image> imagenProperty = new SimpleObjectProperty<>();

    public void initialize() {
        nombre.textProperty().bind(nombreProperty);
        iconoPrincipal.imageProperty().bind(imagenProperty);
        vboxProperty.set(vboxTexto);
    }
    public static VBox getVBox() {
        return vboxProperty.get();
    }
    public static void setNombre(String nombre) {
        nombreProperty.set(nombre);
    }
    public static String getNombre() {
        return nombreProperty.get();
    }
    public static void setImagen(Image imagen) {
        imagenProperty.set(imagen);
    }

    @FXML
    void clickPulsado(MouseEvent event) throws IOException {
        comprobarEnviarTexto();
    }

    @FXML
    void icono_principal_click(MouseEvent event) throws IOException {
        DateMeApp.cambiarADatos(nombre.getText(),iconoPrincipal.getImage());
    }

    @FXML
    void nombre_click(MouseEvent event) throws IOException {
        DateMeApp.cambiarADatos(nombre.getText(),iconoPrincipal.getImage());
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
            ponerTextoEnviado(texto.getText());
            texto.setText("");
        }
    }

    public static void ponerTextoRecibido(String texto, Image foto) throws IOException {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15,0,0,0));
        ImageView iv = new ImageView();
        iv.setFitWidth(50);
        iv.setFitHeight(50);
        iv.setImage(foto);
        Label label = new Label();
        HBox.setHgrow(label, Priority.ALWAYS);
        label.setPadding(new Insets(0,0,0,20));
        HBox.setMargin(label,new Insets(0,25,0,25));
        label.setMaxHeight(MAX_VALUE);
        label.setMaxWidth(MAX_VALUE);
        label.setText(texto);
        label.setTextFill(Paint.valueOf("black"));
        label.setAlignment(Pos.CENTER_LEFT);
        hbox.getChildren().add(iv);
        hbox.getChildren().add(label);
        label.setStyle("-fx-background-color: darkgrey; -fx-background-radius: 10;");
        getVBox().getChildren().add(hbox);
    }
    public static void ponerTextoEnviado(String texto) throws IOException {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15,0,0,0));
        ImageView iv = new ImageView();
        iv.setFitWidth(50);
        iv.setFitHeight(50);
        iv.setImage(DateMeApp.fotoUsuario);
        Label label = new Label();
        HBox.setHgrow(label, Priority.ALWAYS);
        label.setPadding(new Insets(0,0,0,20));
        HBox.setMargin(label,new Insets(0,25,0,25));
        label.setMaxHeight(MAX_VALUE);
        label.setMaxWidth(MAX_VALUE);
        label.setText(texto);
        label.setTextFill(Paint.valueOf("black"));
        label.setAlignment(Pos.CENTER_LEFT);
        hbox.getChildren().add(label);
        hbox.getChildren().add(iv);
        label.setStyle("-fx-background-color: lightblue; -fx-background-radius: 10;");
        getVBox().getChildren().add(hbox);
        LocalDateTime dateTime = LocalDateTime.now();
        String hora = dateTime.toString();
        String[] datos = {DateMeApp.nombreUsuarioIniciado, getNombre(), texto, "no leido", hora};
        SQLiteConnection.ejecutarInsertMensaje(datos);
    }


    public void ajustesClick(ActionEvent actionEvent) {
        DateMeApp.cambiarPestana("settings/settings.fxml", "Ajustes",900,600);
    }

    public void chatsClick(ActionEvent actionEvent) {
        DateMeApp.cambiarPestana("chats/chats.fxml", "Chats",900,600);
    }

    public void matchesClick(ActionEvent actionEvent) {
        DateMeApp.cambiarPestana("match/match.fxml", "Matches",900,600);
    }

    public void inicioClick(ActionEvent actionEvent) {
        DateMeApp.cambiarPestana("mainpage/mainpage.fxml", "DateMe",900,600);
    }
    private static Image rutaAImage(String rutaImagen){
        String ruta = "fotosperfil/" + rutaImagen;
        URL urlFoto = MensajesController.class.getResource(ruta);
        Image img = null;
        try {
            img = new Image(urlFoto.openStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return img;
    }

    public static void cambiarChat(String nombreUser, Image foto) throws IOException {
        setNombre(nombreUser);
        setImagen(foto);
        String consultaResultados = SQLiteConnection.ejecutarConsulta("SELECT m.mensaje, m.user_id FROM mensajes m WHERE (m.user_id = '" + DateMeApp.nombreUsuarioIniciado + "' AND m.perfil_id = '" + nombreUser + "') OR (m.user_id = '" + nombreUser + "' AND m.perfil_id = '" + DateMeApp.nombreUsuarioIniciado + "')");
        String[] lineas = consultaResultados.split("\n");

        // Recorrer las líneas y separar los campos
        for (String linea : lineas) {
            String[] campos = linea.split(";");
            // Obtener los valores de los campos
            if (campos.length == 2) {
                String mensaje = campos[0];
                String userEnviador = campos[1];
                if (userEnviador != nombreUser){
                    ponerTextoRecibido(mensaje,foto);
                }else {
                    ponerTextoEnviado(mensaje);
                }
            }
        }
    }

}
