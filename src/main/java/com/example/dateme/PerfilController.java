package com.example.dateme;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PerfilController implements Initializable {

    /*
    * Declaracion de todos los elementos que forman parte
    * de la creacion de un perfil
    */

    private static File archivoImg;
    private final  Integer[] EDADES1 = {18,19,20,21,22,23,24,25};
    private final  Integer[] EDADES2 = {26,27,28,29,30};
    private final  Integer[] EDADES3 = {31,32,33,34,35,36,37,38,39,40};
    private final  Integer[] EDADES4 = {41,42,43,44,45,46,47,48,49,50};
    private final  Integer[] EDADES5 = {51,52,53,54,55,56,57,58,59,60};
    private final  Integer[] EDADES6 = {61,62,63,64,65,66,67,68,69,70};
    private final  Integer[] EDADES7 = {71,72,73,74,75,76,77,78,79,80};
    private final  Integer[] EDADES8 = {81,82,83,84,85,86,87,88,89,90};
    private final  Integer[] EDADES9 = {91,92,93,94,95,96,97,98,99,100};
    private Image fotoPerfil;
    private String idUsuario = SignUpController.idUsuario;
    private String contraseña = SignUpController.contraseñaUsuario;
    @FXML
    private Button botonContinuar;
    @FXML
    private RadioButton botonHombre;
    @FXML
    private RadioButton botonMujer;
    @FXML
    private RadioButton botonOtros;
    @FXML
    private Button botonSelectImagen;
    @FXML
    private TextField campoApellidos;
    @FXML
    private TextField campoCorreo;
    @FXML
    private TextArea campoDescripcion;
    @FXML
    private DatePicker campoFecha;
    @FXML
    private ImageView campoImagen;
    @FXML
    private MenuButton campoLocalidad;
    @FXML
    private TextField campoNombre;
    @FXML
    private CheckBox prefEdad1;
    @FXML
    private CheckBox prefEdad2;
    @FXML
    private CheckBox prefEdad3;
    @FXML
    private CheckBox prefEdad4;
    @FXML
    private CheckBox prefEdad5;
    @FXML
    private CheckBox prefEdad6;
    @FXML
    private CheckBox prefEdad7;
    @FXML
    private CheckBox prefEdad8;
    @FXML
    private CheckBox prefEdad9;
    @FXML
    private CheckBox prefHombre;
    @FXML
    private CheckBox prefMujeres;
    @FXML
    private Label errorNombre;
    @FXML
    private Label errorApellidos;
    @FXML
    private Label errorCorreo;
    @FXML
    private Label errorFecha;
    @FXML
    private Label errorLocalidad;
    @FXML
    private Label errorGenero;
    @FXML
    private Label errorImagen;
    private final ToggleGroup grupoGenero = new ToggleGroup();

    /*
    * Metodo para abrir un buscador de archivos para
    * seleccionar la foto de perfil
    */
    @FXML
    void seleccionarImagen(ActionEvent event) {
        FileChooser buscador = new FileChooser();
        buscador.setTitle("Buscar Imagen");
        buscador.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "jpg"),
                new FileChooser.ExtensionFilter("PNG", "png")
        );

        File archivoImagen = buscador.showOpenDialog(null);

        if (archivoImagen != null) {
            fotoPerfil = new Image(archivoImagen.getAbsolutePath());
            campoImagen.setImage(fotoPerfil);
            archivoImg = archivoImagen;
        }

    }

    /*
    * Metodo para dejar seleccionada la localidad que escoja el
    * usuario
    */
    @FXML
    void setLocalidad (ActionEvent event) {
        MenuItem localidad = (MenuItem) event.getSource();
        campoLocalidad.setText(localidad.getText());
    }

    /*
    * Metodo que se ejecuta cuando el usuario haya rellenado el formulario,
    * valida que los campos no esten vacios y no tengan errores y
    * en ese caso crea un nuevo usuario
    */
    @FXML
    void actionBotonContinuar(ActionEvent event) {
        if (validarPerfil()) {
            Usuario usuario = crearUsuario();
            GestorUsuarios.addUsuario(usuario);
            copiarImagen(usuario);
            GestorUsuarios.inicializarUsuarioActual(usuario.getIdUsuario());
            cambiarEscena(event, "mainpage/mainpage.fxml", 900, 600);
        }
        else {
            System.out.println("El perfil no cumple los requisitos");
        }
    }

    public void copiarImagen(Usuario usuario) {
        URL url = PerfilController.class.getResource("fotosperfil/");
        String ruta = url.getPath() + usuario.getIdUsuario() + ".jpg";
        File archivoCopia = new File(ruta);
        try {
            Files.copy(archivoImg.toPath(), archivoCopia.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Foto guardada");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /*
    * Crea el usuario acorde a las caracteristicas seleccionadas en el formulario
    */
    public Usuario crearUsuario() {
        //Usuario usuario = null;
        String nombre = campoNombre.getText();
        String apellidos = campoApellidos.getText();
        String correo = campoCorreo.getText();
        LocalDate fechaNacimiento = campoFecha.getValue();
        String localidad = campoLocalidad.getText();
        Usuario.Genero genero = setGeneroUsuario();
        String descripcion = campoDescripcion.getText();
        Image fotoPerfil = campoImagen.getImage();
        ArrayList<Usuario.Genero> preferenciaGenero = setPreferenciaGenero();
        ArrayList<Integer> preferenciaEdad = setPreferenciaEdad();
        Usuario usuario = new Usuario(idUsuario, nombre, apellidos, contraseña, localidad, correo, fechaNacimiento, descripcion, genero, fotoPerfil, preferenciaGenero, preferenciaEdad);
        return usuario;
    }

    public Usuario.Genero setGeneroUsuario() {
        if (botonHombre.isSelected()) {
            return Usuario.Genero.HOMBRE;
        } else if (botonMujer.isSelected()) {
            return Usuario.Genero.MUJER;
        }
        return Usuario.Genero.OTRO;
    }

    /*
    * Crea una lista de los generos seleccionados en el apartado de preferencias
     */
    public ArrayList<Usuario.Genero> setPreferenciaGenero() {
        ArrayList<Usuario.Genero> preferenciaGenero = new ArrayList<>();
        if (prefHombre.isSelected()) {
            preferenciaGenero.add(Usuario.Genero.HOMBRE);
        }
        if (prefMujeres.isSelected()) {
            preferenciaGenero.add(Usuario.Genero.MUJER);
        }
        return preferenciaGenero;
    }

    /*
    * Crea una lista de las edades seleccionadas en el apartado de preferencias
    */
    public ArrayList<Integer> setPreferenciaEdad() {
        ArrayList<Integer> preferenciaEdad = new ArrayList<>();
        if (prefEdad1.isSelected()) {
            preferenciaEdad.addAll(List.of(EDADES1));
        }
        if (prefEdad2.isSelected()) {
            preferenciaEdad.addAll(List.of(EDADES2));
        }
        if (prefEdad3.isSelected()) {
            preferenciaEdad.addAll(List.of(EDADES3));
        }
        if (prefEdad4.isSelected()) {
            preferenciaEdad.addAll(List.of(EDADES4));
        }
        if (prefEdad5.isSelected()) {
            preferenciaEdad.addAll(List.of(EDADES5));
        }
        if (prefEdad6.isSelected()) {
            preferenciaEdad.addAll(List.of(EDADES6));
        }
        if (prefEdad7.isSelected()) {
            preferenciaEdad.addAll(List.of(EDADES7));
        }
        if (prefEdad8.isSelected()) {
            preferenciaEdad.addAll(List.of(EDADES8));
        }
        if (prefEdad9.isSelected()) {
            preferenciaEdad.addAll(List.of(EDADES9));
        }
        return preferenciaEdad;
    }

    /*
     * Metodo que llama a las validaciones
     * de cada campo de la creacion del perfil
     */
    public boolean validarPerfil() {
        boolean valido = true;
        if (!validarNombre()) {
            campoNombre.setText("");
            errorNombre.setText("*");
            valido = false;
        }
        if (!validarApellidos()) {
            campoApellidos.setText("");
            errorApellidos.setText("*");
            valido = false;
        }
        if (!validarCorreo()) {
            campoCorreo.setText("");
            errorCorreo.setText("*");
            valido = false;
        }
        if (!validarFecha()) {
            errorFecha.setText("*");
            valido = false;
        }
        if (!validarLocalidad()) {
            errorLocalidad.setText("*");
            valido = false;
        }
        if (!validarGenero()) {
            errorGenero.setText("*");
            valido = false;
        }
        if (!validarImagen()) {
            errorImagen.setText("*");
            valido = false;
        }
        return valido;
    }

    /*
    * Valida el campo nombre comprobando que no este vacio, que no tenga
    * espacios y que no contenga numeros
    */
    public boolean validarNombre() {
        String nombre = campoNombre.getText();
        if (nombre.equals("") || nombre.contains(" ")) {
            return false;
        }

        for (int i = 0; i < nombre.length(); i++) {
            if (Character.isDigit(nombre.charAt(i))) {
                return false;
            }
        }
        errorNombre.setText("");
        return true;
    }

    /*
    * Valida que el campo del apellido no este vacio y que
    * no contenga numeros
    */
    public boolean validarApellidos() {
        String apellidos = campoApellidos.getText();
        if (apellidos.equals("")) {
            return false;
        }

        for (int i = 0; i < apellidos.length(); i++) {
            if (Character.isDigit(apellidos.charAt(i))) {
                return false;
            }
        }
        errorApellidos.setText("");
        return true;
    }

    /*
    * Valida el campo del correo comprobando que no este vacio, que contenga el caracter @
    * y que no contenga espacios
    */
    public boolean validarCorreo() {
        String correo = campoCorreo.getText();
        if (correo.equals("") || !correo.contains("@") || correo.contains(" ")) {
            return false;
        }
        errorCorreo.setText("");
        return true;
    }

    /*
    * Valida el campo de la fecha de nacimiento comprobando
    * que no este vacio y que el usuario sea mayor de edad
    */
    public boolean validarFecha() {
        LocalDate fechaNacimiento = campoFecha.getValue();
        if (fechaNacimiento == null) {
            return false;
        }
        if (!esMayorDeEdad(fechaNacimiento)) {
            return false;
        }
        errorFecha.setText("");
        return true;

    }


    /*
    * Compara la fecha pasada por parametro para
    * comprobar si sobrepasa a la edad actual por
    * 18 o mas años
    */
    public boolean esMayorDeEdad(LocalDate fecha) {
        LocalDate fechaActual = LocalDate.now();
        if (fechaActual.compareTo(fecha) > 18) {
            return true;
        }
        else if (fechaActual.compareTo(fecha) == 18) {
            if (fechaActual.getMonthValue() > fecha.getMonthValue()) {
                return true;
            }
            else if (fechaActual.getMonthValue() == fecha.getMonthValue() &&
                    fechaActual.getDayOfMonth() >= fecha.getDayOfMonth()) {
                return true;
            }
        }
        return false;
    }

    public boolean validarLocalidad() {
        if (!campoLocalidad.getText().equals("Localidad")) {
            errorLocalidad.setText("");
            return true;
        }
        return false;
    }

    /*
     * Valida el campo Genero comprobando que el usuario haya seleccionado alguna opción
     */
    public boolean validarGenero() {
        if (botonHombre.isSelected() || botonMujer.isSelected() || botonOtros.isSelected()) {
            errorGenero.setText("");
            return true;
        }
        return false;
    }

    /*
     * Valida el campo de la foto de perfil comprobando que el usuario
     * haya seleccionado una imagen
     */
    public boolean validarImagen() {
        if (campoImagen.getImage() != null) {
            errorImagen.setText("");
            return true;
        }
        return false;
    }

    /*
    * Metodo para inicializar los grupos de botones al
    * cargar el fichero fxml
    */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        botonHombre.setToggleGroup(grupoGenero);
        botonMujer.setToggleGroup(grupoGenero);
        botonOtros.setToggleGroup(grupoGenero);
        campoImagen.setPreserveRatio(false);
    }

    public void cambiarEscena(ActionEvent event, String fxml, int width, int height) {
        Button botonEvento = (Button) event.getSource();
        Stage stage = (Stage) botonEvento.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(fxml));
        try {
            Scene escena = new Scene(loader.load(), width, height);
            stage.setScene(escena);
        } catch(IOException exception) {
            exception.printStackTrace();
        }
    }

    public static void main(String[] args) {
        URL url = PerfilController.class.getResource("fotosperfil/");
        String b = url.getPath() + "josebatrigo" + ".jpg";
        String c = url.toString() + "josebatrigo" + ".jpg";

        System.out.println(b);
        System.out.println(c);
    }
}
