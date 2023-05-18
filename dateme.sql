DROP TABLE IF EXISTS usuarios;
CREATE TABLE IF NOT EXISTS usuarios
(
    id_usuario         TEXT PRIMARY KEY,
    nombre             TEXT    NOT NULL,
    apellidos          TEXT    NOT NULL,
    contraseña         TEXT    NOT NULL,
    localidad          TEXT    NOT NULL,
    correo_electronico TEXT    NOT NULL,
    genero             TEXT    NOT NULL CHECK (genero IN ('Masculino', 'Femenino', 'Otro')),
    preferencia_genero TEXT    NOT NULL CHECK (preferencia_genero IN ('Masculino', 'Femenino', 'Indiferente')),
    fecha_nacimiento   TEXT    NOT NULL,
    preferencia_edad   TEXT,
    descripcion        TEXT,
    foto               TEXT
);
DROP TABLE IF EXISTS visitados;
CREATE TABLE IF NOT EXISTS visitados
(
    id_visita INTEGER PRIMARY KEY AUTOINCREMENT ,
    user_id_1 TEXT NOT NULL,
    user_id_2 TEXT NOT NULL,
    FOREIGN KEY (user_id_1) REFERENCES  usuarios (id_usuario) ON DELETE CASCADE,
    FOREIGN KEY (user_id_2) REFERENCES  usuarios (id_usuario) ON DELETE CASCADE
);
DROP TABLE IF EXISTS match;
CREATE TABLE IF NOT EXISTS match
(
    id_match         INTEGER PRIMARY KEY AUTOINCREMENT ,
    user_id_1        TEXT  NOT NULL,
    user_id_2        TEXT  NOT NULL,
    FOREIGN KEY (user_id_1) REFERENCES usuarios (id_usuario) ON DELETE CASCADE,
    FOREIGN KEY (user_id_2) REFERENCES usuarios (id_usuario) ON DELETE CASCADE
);
DROP TABLE IF EXISTS likes;
CREATE TABLE IF NOT EXISTS likes
(
    like_id         INTEGER PRIMARY KEY AUTOINCREMENT ,
    user_id_1         TEXT  NOT NULL,
    user_id_2       TEXT  NOT NULL,
    FOREIGN KEY (user_id_1) REFERENCES usuarios (id_usuario) ON DELETE CASCADE,
    FOREIGN KEY (user_id_2) REFERENCES usuarios (id_usuario) ON DELETE CASCADE
);
DROP TABLE IF EXISTS interacciones;
CREATE TABLE IF NOT EXISTS interacciones
(
    id_interaccion    INTEGER PRIMARY KEY AUTOINCREMENT ,
    user_id           TEXT  NOT NULL,
    perfil_id         TEXT  NOT NULL,
    tipo_interaccion  TEXT     NOT NULL CHECK (tipo_interaccion IN('like', 'match', 'perfil_visto', 'mensaje', 'bloqueo', 'reporte', 'izquierda', 'derecha', 'compartir_perfil')),
    fecha_hora_mesaje DATETIME NOT NULL,
    FOREIGN KEY (user_id) REFERENCES usuarios (id_usuario) ON DELETE CASCADE,
    FOREIGN KEY (perfil_id) REFERENCES usuarios (id_usuario) ON DELETE CASCADE
);
DROP TABLE IF EXISTS mensajes;
CREATE TABLE IF NOT EXISTS mensajes
(
    id_mensaje        INTEGER PRIMARY KEY AUTOINCREMENT ,
    user_id           TEXT  NOT NULL,
    perfil_id         TEXT  NOT NULL,
    mensaje           TEXT     NOT NULL,
    estado_mensaje    TEXT     NOT NULL CHECK (estado_mensaje IN ('leido', 'no leido')),
    fecha_hora_mesaje DATETIME NOT NULL,
    FOREIGN KEY (user_id) REFERENCES usuarios (id_usuario) ON DELETE CASCADE,
    FOREIGN KEY (perfil_id) REFERENCES usuarios (id_usuario) ON DELETE CASCADE
);
DROP TABLE IF EXISTS notificaciones;
CREATE TABLE IF NOT EXISTS notificaciones
(
    id_notificacion         INTEGER PRIMARY KEY AUTOINCREMENT ,
    user_id                 TEXT  NOT NULL,
    tipo_notificacion       TEXT     NOT NULL CHECK (tipo_notificacion IN ('like', 'match', 'mensaje', 'publicidad', 'actualización')),
    fecha_hora_notificacion DATETIME NOT NULL,
    FOREIGN KEY (user_id) REFERENCES usuarios (id_usuario) ON DELETE CASCADE
);
DROP TABLE IF EXISTS reportes;
CREATE TABLE IF NOT EXISTS reportes
(
    id_reporte            INTEGER PRIMARY KEY AUTOINCREMENT ,
    user_id               TEXT  NOT NULL,
    perfil_id             TEXT  NOT NULL,
    razon_denuncia        TEXT     NOT NULL,
    informacion_adicional TEXT,
    fecha_hora_reporte    DATETIME NOT NULL,
    FOREIGN KEY (user_id) REFERENCES usuarios(id_usuario) ON DELETE CASCADE,
    FOREIGN KEY (perfil_id) REFERENCES usuarios(id_usuario) ON DELETE CASCADE
);
DROP TABLE IF EXISTS bloqueos;
CREATE TABLE IF NOT EXISTS bloqueos
(
    id_bloqueo INTEGER PRIMARY KEY AUTOINCREMENT ,
    user_id TEXT NOT NULL,
    perfil_id TEXT NOT NULL,
    fecha_hora_bloqueo DATETIME NOT NULL,
    FOREIGN KEY (user_id) REFERENCES usuarios(id_usuario) ON DELETE CASCADE,
    FOREIGN KEY (perfil_id) REFERENCES usuarios(id_usuario) ON DELETE CASCADE
);

INSERT INTO usuarios (id_usuario, nombre, apellidos, contraseña, localidad, correo_electronico, genero, preferencia_genero, fecha_nacimiento, preferencia_edad, descripcion, foto) VALUES
    ('juanperez', 'Juan', 'Pérez', 'root', 'Madrid', 'juanperez@gmail.com', 'Masculino', 'Femenino', '1995-03-15', '26:30', 'Hola, soy Juan, me gusta la música y el deporte', 'juanperez.jpg');

INSERT INTO usuarios (id_usuario, nombre, apellidos, contraseña, localidad, correo_electronico, genero, preferencia_genero, fecha_nacimiento, preferencia_edad, descripcion, foto) VALUES
    ('mariagarcia', 'María', 'García', 'root', 'Barcelona', 'mariagarcia@gmail.com', 'Femenino', 'Masculino', '1999-06-20', '18:25-31:40', 'Hola, soy María, me encanta viajar y conocer nuevas culturas', 'mariagarcia.jpg');

INSERT INTO usuarios (id_usuario, nombre, apellidos, contraseña, localidad, correo_electronico, genero, preferencia_genero, fecha_nacimiento, preferencia_edad, descripcion, foto) VALUES
    ('alejandrohernandez', 'Alejandro', 'Hernández', 'root', 'Sevilla', 'alejandrohdez@gmail.com', 'Masculino', 'Indiferente', '1988-11-10', '31:40', 'Hola, soy Alejandro, me gusta la cocina y el cine', 'alejandrohernandez.jpg');

INSERT INTO usuarios (id_usuario, nombre, apellidos, contraseña, localidad, correo_electronico, genero, preferencia_genero, fecha_nacimiento, preferencia_edad, descripcion, foto) VALUES
    ('sofiamartinez', 'Sofía', 'Martínez', 'root', 'Valencia', 'sofiamartinez@gmail.com', 'Femenino', 'Indiferente', '1936-08-05', '26:30', 'Hola, soy Sofía, me gusta el arte y la naturaleza', 'sofiamartinez.jpg');

INSERT INTO match (id_match, user_id_1, user_id_2) VALUES
    (1, 'juanperez', 'mariagarcia');

INSERT INTO match (id_match, user_id_1, user_id_2) VALUES
    (2, 'alejandrohernandez', 'juanperez');

INSERT INTO match (id_match, user_id_1, user_id_2) VALUES
    (3, 'mariagarcia', 'sofiamartinez');

INSERT INTO match (id_match, user_id_1, user_id_2) VALUES
    (4, 'juanperez', 'sofiamartinez');
/*
INSERT INTO likes (like_id, user_id, perfil_id) VALUES
    (1, 'juanperez', 'mariagarcia');

INSERT INTO likes (like_id, user_id, perfil_id) VALUES
    (2, 'alejandrohernandez', 'juanperez');

INSERT INTO likes (like_id, user_id, perfil_id) VALUES
    (3, 'mariagarcia', 'juanperez');

INSERT INTO likes (like_id, user_id, perfil_id) VALUES
    (4, 'juanperez', 'sofiamartinez');
*/
INSERT INTO interacciones (id_interaccion, user_id, perfil_id, tipo_interaccion, fecha_hora_mesaje) VALUES
    (1, 'juanperez', 'mariagarcia', 'like', '2023-04-25 12:30:00');

INSERT INTO interacciones (id_interaccion, user_id, perfil_id, tipo_interaccion, fecha_hora_mesaje) VALUES
    (2, 'alejandrohernandez', 1, 'match', '2023-04-26 10:45:00');

INSERT INTO interacciones (id_interaccion, user_id, perfil_id, tipo_interaccion, fecha_hora_mesaje) VALUES
    (3, 'mariagarcia', 4, 'perfil_visto', '2023-04-27 16:20:00');

INSERT INTO interacciones (id_interaccion, user_id, perfil_id, tipo_interaccion, fecha_hora_mesaje) VALUES
    (4, 'juanperez', 4, 'mensaje', '2023-04-28 14:15:00');

INSERT INTO mensajes (id_mensaje, user_id, perfil_id, mensaje, estado_mensaje, fecha_hora_mesaje) VALUES
    (1, 1, 'mariagarcia', 'Hola, ¿cómo estás?', 'no leido', '2023-05-01 10:00:00');

INSERT INTO mensajes (id_mensaje, user_id, perfil_id, mensaje, estado_mensaje, fecha_hora_mesaje) VALUES
    (2, 'alejandrohernandez', 'juanperez', 'Hola, ¿qué tal?', 'leido', '2023-05-01 11:00:00');

INSERT INTO mensajes (id_mensaje, user_id, perfil_id, mensaje, estado_mensaje, fecha_hora_mesaje) VALUES
    (3, 'mariagarcia', 'sofiamartinez', 'Bien, gracias. ¿Y tú?', 'no leido', '2023-05-01 12:00:00');

INSERT INTO mensajes (id_mensaje, user_id, perfil_id, mensaje, estado_mensaje, fecha_hora_mesaje) VALUES
    (4, 'juanperez', 'sofiamartinez', 'Hola, ¿qué planes tienes para hoy?', 'leido', '2023-05-01 13:00:00');

INSERT INTO notificaciones (id_notificacion, user_id, tipo_notificacion, fecha_hora_notificacion) VALUES
    (1, 'juanperez', 'like', '2022-04-30 14:30:00');

INSERT INTO notificaciones (id_notificacion, user_id, tipo_notificacion, fecha_hora_notificacion) VALUES
    (2, 'mariagarcia', 'match', '2022-04-29 20:45:00');

INSERT INTO notificaciones (id_notificacion, user_id, tipo_notificacion, fecha_hora_notificacion) VALUES
    (3, 'alejandrohernandez', 'mensaje', '2022-04-28 10:15:00');

INSERT INTO notificaciones (id_notificacion, user_id, tipo_notificacion, fecha_hora_notificacion) VALUES
    (4, 'sofiamartinez', 'publicidad', '2022-04-30 08:00:00');

INSERT INTO reportes (id_reporte, user_id, perfil_id, razon_denuncia, informacion_adicional, fecha_hora_reporte) VALUES
    (1, 'juanperez', 'mariagarcia', 'Comportamiento inapropiado', 'El perfil del usuario en cuestión está mostrando comportamientos ofensivos y fuera de lugar', '2023-05-01 14:35:00');

INSERT INTO reportes (id_reporte, user_id, perfil_id, razon_denuncia, informacion_adicional, fecha_hora_reporte) VALUES
    (2, 'alejandrohernandez', 'juanperez', 'Fotos falsas', 'Sospecho que las fotos del usuario en cuestión son falsas y que están tratando de engañar a los usuarios', '2023-04-29 11:20:00');

INSERT INTO reportes (id_reporte, user_id, perfil_id, razon_denuncia, informacion_adicional, fecha_hora_reporte) VALUES
    (3, 'mariagarcia', 'sofiamartinez', 'Información falsa', 'Creo que el perfil del usuario en cuestión contiene información falsa y engañosa', '2023-04-28 19:45:00');

INSERT INTO reportes (id_reporte, user_id, perfil_id, razon_denuncia, informacion_adicional, fecha_hora_reporte) VALUES
    (4, 'juanperez', 'sofiamartinez', 'Suplantación de identidad', 'Creo que el perfil del usuario en cuestión está suplantando la identidad de otra persona', '2023-04-27 15:10:00');

INSERT INTO bloqueos (id_bloqueo, user_id, perfil_id, fecha_hora_bloqueo) VALUES
    (1, 'juanperez', 'mariagarcia', '2023-05-01 16:40:00');

INSERT INTO bloqueos (id_bloqueo, user_id, perfil_id, fecha_hora_bloqueo) VALUES
    (2, 'alejandrohernandez', 'juanperez', '2023-04-30 08:15:00');

INSERT INTO bloqueos (id_bloqueo, user_id, perfil_id, fecha_hora_bloqueo) VALUES
    (3, 'mariagarcia', 'sofiamartinez', '2023-04-29 20:30:00');

INSERT INTO bloqueos (id_bloqueo, user_id, perfil_id, fecha_hora_bloqueo) VALUES
    (4, 'juanperez', 'sofiamartinez', '2023-04-28 11:55:00');