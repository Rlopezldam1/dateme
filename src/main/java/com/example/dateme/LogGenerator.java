package com.example.dateme;

import java.io.*;

public class LogGenerator {
    private static final String LOG_FILE_PATH = "salida.log";

    public static void main() throws IOException {
        crearArchivoLog();
    }

    public static void escribirLog(String mensaje, LogLevel nivel) {
        File archivo = new File(LOG_FILE_PATH);
        FileWriter fileWriter = null;
        PrintWriter printWriter = null;
        try {
            fileWriter = new FileWriter(archivo, true);
            printWriter = new PrintWriter(fileWriter);
            String timestamp = obtenerTimestamp();
            String logLine = timestamp + " [" + nivel + "] --> " + mensaje;
            printWriter.println(logLine);
            printWriter.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private static String obtenerTimestamp() {
        return java.time.LocalDateTime.now().toString();
    }

    public static void crearArchivoLog() throws IOException {
        File archivo = new File(LOG_FILE_PATH);
        if (archivo.createNewFile()) {
            System.out.println("Archivo de log creado: " + archivo.getAbsolutePath());
        } else {
            System.out.println("El archivo de log ya existe.");
        }
    }
}

enum LogLevel {
    ERROR,
    INFO,
    DEBUG
}

