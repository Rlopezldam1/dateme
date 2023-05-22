package com.example.dateme;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class SQLiteConnection {
    public static Connection conectar() {
        Connection connection = null;
        try {
            // Cargar el controlador JDBC
            Class.forName("org.sqlite.JDBC");

            // Establecer la conexión con la base de datos
            String url = "jdbc:sqlite:dateme.db";
            connection = DriverManager.getConnection(url);

            System.out.println("Conexión exitosa a la base de datos SQLite.");

        } catch (ClassNotFoundException e) {
            System.out.println("Error al cargar el controlador JDBC: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error al establecer la conexión con la base de datos: " + e.getMessage());
        }

        return connection;
    }

    public static void crearBaseDeDatos() {
        Connection connection = conectar();
        Statement statement = null;

        try {
            // Crear un objeto Statement
            statement = connection.createStatement();

            // Ejecutar el comando para crear la base de datos
            String sql = "CREATE DATABASE dateme";
            statement.executeUpdate(sql);

            System.out.println("La base de datos se ha creado correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al crear la base de datos: " + e.getMessage());
        } finally {
            // Cerrar el Statement y la conexión
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar el Statement: " + e.getMessage());
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
    }

    public static void ejecutarScriptSQL(String archivoSQL) {
        Connection connection = null;
        Statement statement = null;
        BufferedReader reader = null;

        try {
            // Conectar a la base de datos SQLite
            connection = DriverManager.getConnection("jdbc:sqlite:dateme.db");

            // Crear un objeto Statement
            statement = connection.createStatement();

            // Leer el archivo SQL
            reader = new BufferedReader(new FileReader(archivoSQL));
            StringBuilder script = new StringBuilder();
            String linea;

            while ((linea = reader.readLine()) != null) {
                script.append(linea.trim());

                // Si la línea contiene un punto y coma, ejecutar la declaración SQL
                if (linea.trim().endsWith(";")) {
                    String sql = script.toString();
                    statement.executeUpdate(sql);

                    // Reiniciar el script para la siguiente declaración
                    script.setLength(0);
                } else {
                    script.append(" ");
                }
            }

            System.out.println("El script SQL se ha ejecutado correctamente.");

        } catch (IOException e) {
            System.out.println("Error al leer el archivo SQL: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error al ejecutar el script SQL: " + e.getMessage());
        } finally {
            // Cerrar el Statement, el BufferedReader y la conexión
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar el Statement: " + e.getMessage());
                }
            }

            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("Error al cerrar el BufferedReader: " + e.getMessage());
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
    }

    public static void ejecutarInsert(String nombreTabla, Object[] valores) {
        Connection connection = conectar();
        PreparedStatement preparedStatement = null;

        try {
            // Obtener los metadatos de la tabla para determinar las columnas
            String sqlMeta = "SELECT * FROM " + nombreTabla + " LIMIT 0";
            preparedStatement = connection.prepareStatement(sqlMeta);
            preparedStatement.execute();

            int columnCount = preparedStatement.getMetaData().getColumnCount();

            // Construir la sentencia SQL de inserción dinámica
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO ");
            sql.append(nombreTabla);
            sql.append(" VALUES (");

            for (int i = 0; i < columnCount; i++) {
                if (i > 0) {
                    sql.append(", ");
                }
                sql.append("?");
            }

            sql.append(")");

            // Preparar la sentencia SQL con los valores a insertar
            preparedStatement = connection.prepareStatement(sql.toString());

            for (int i = 0; i < valores.length; i++) {
                preparedStatement.setObject(i + 1, valores[i]);
            }

            // Ejecutar la inserción
            preparedStatement.executeUpdate();

            System.out.println("La inserción se ha realizado correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al ejecutar la inserción: " + e.getMessage());
        } finally {
            // Cerrar el PreparedStatement y la conexión
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar el PreparedStatement: " + e.getMessage());
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
    }
    public static String ejecutarConsulta(String consulta) {
        String url = "jdbc:sqlite:dateme.db";
        String resultado = "";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(consulta)) {

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Recorrer las filas del resultado
            while (rs.next()) {
                // Recorrer las columnas del resultado
                for (int i = 1; i <= columnCount; i++) {
                    resultado += rs.getString(i);
                    if (i < columnCount) {
                        resultado += ";";
                    }
                }
                resultado += "\n"; // Salto de línea entre filas
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultado;
    }


    // Ejemplo de uso
    public static void main() {
        String fileName = "dateme.db";
        File file = new File(fileName);

        if (!file.exists()) {
            ejecutarScriptSQL("dateme.sql");
            System.out.println("Script ejecutado");
        }
        Connection connection = conectar();
    }
}

