package com.example.dateme;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.sql.*;
import java.time.LocalDate;
import java.time.Period;

public class perfilUsuarioController {

    @FXML
    private Button ajustes;

    @FXML
    private Label campoApellidos;

    @FXML
    private Label campoDescripcion;

    @FXML
    private Label campoEdad;

    @FXML
    private Label campoId;

    @FXML
    private ImageView campoImagen;

    @FXML
    private Label campoLocalidad;

    @FXML
    private Label campoNombre;

    @FXML
    private Button chats;

    @FXML
    private Button inicio;

    @FXML
    private Button matches;
    private static StringProperty campoApellidosProperty = new SimpleStringProperty();
    private static StringProperty campoDescripcionProperty = new SimpleStringProperty();
    private static StringProperty campoEdadProperty = new SimpleStringProperty();
    private static StringProperty campoIdProperty = new SimpleStringProperty();
    private static StringProperty campoLocalidadProperty = new SimpleStringProperty();
    private static StringProperty campoNombreProperty = new SimpleStringProperty();
    private static ObjectProperty<Image> imagenProperty = new SimpleObjectProperty<>();

    public void initialize() {
        campoApellidos.textProperty().bind(campoApellidosProperty);
        campoDescripcion.textProperty().bind(campoDescripcionProperty);
        campoEdad.textProperty().bind(campoEdadProperty);
        campoId.textProperty().bind(campoIdProperty);
        campoLocalidad.textProperty().bind(campoLocalidadProperty);
        campoNombre.textProperty().bind(campoNombreProperty);
        campoImagen.imageProperty().bind(imagenProperty);
    }
    public StringProperty campoApellidosProperty() {
        return campoApellidosProperty;
    }

    public StringProperty campoDescripcionProperty() {
        return campoDescripcionProperty;
    }


    public static void setImagen(Image imagen) {
        imagenProperty.set(imagen);
    }

    public StringProperty campoEdadProperty() {
        return campoEdadProperty;
    }

    public StringProperty campoIdProperty() {
        return campoIdProperty;
    }

    public StringProperty campoLocalidadProperty() {
        return campoLocalidadProperty;
    }

    public StringProperty campoNombreProperty() {
        return campoNombreProperty;
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
    public static void cargarDatosUsuario(String nombreUsuario, Image foto ) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:dateme.db")) {
            String query = "SELECT nombre, apellidos, descripcion, fecha_nacimiento, localidad FROM usuarios WHERE id_usuario = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, nombreUsuario);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                campoIdProperty.set(nombreUsuario);
                campoNombreProperty.set(resultSet.getString("nombre"));
                campoApellidosProperty.set(resultSet.getString("apellidos"));
                LocalDate fechaNacimiento = LocalDate.parse(resultSet.getString("fecha_nacimiento"));
                campoEdadProperty.set(calcularEdad(fechaNacimiento));
                campoDescripcionProperty.set(resultSet.getString("descripcion"));
                campoLocalidadProperty.set(resultSet.getString("localidad"));
                setImagen(foto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static String calcularEdad(LocalDate fechaNacimiento) {
        LocalDate fechaActual = LocalDate.now();
        Period periodo = Period.between(fechaNacimiento, fechaActual);
        int edad = periodo.getYears();
        return String.valueOf(edad);
    }
}
