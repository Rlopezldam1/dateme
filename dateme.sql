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
    ('ermigel', 'Migeliko', 'ElCabesa', 'root', 'Ceuta', 'migelikokeloke@gmail.com', 'Masculino', 'Femenino', '1993-01-05', '18:26', 'La foto es bieja, aora estoy mazau', 'ermigel.jpg');

INSERT INTO usuarios (id_usuario, nombre, apellidos, contraseña, localidad, correo_electronico, genero, preferencia_genero, fecha_nacimiento, preferencia_edad, descripcion, foto) VALUES
    ('amaiarobles', 'Amaia', 'Robles', 'root', 'Asturias', 'amaiaroblees@gmail.com', 'Femenino', 'Masculino', '1993-03-25', '18:25', 'Me gustan las fiestas y la naturaleza', 'amaiarobles.jpg');

INSERT INTO usuarios (id_usuario, nombre, apellidos, contraseña, localidad, correo_electronico, genero, preferencia_genero, fecha_nacimiento, preferencia_edad, descripcion, foto) VALUES
    ('anefernandez', 'Ane', 'Fernandez', 'root', 'Navarra', 'afer1999@gmail.com', 'Femenino', 'Masculino', '1999-01-10', '18:25', 'Soy fan del horoscopo, que signo eres?', 'anefernandez.jpg');

INSERT INTO usuarios (id_usuario, nombre, apellidos, contraseña, localidad, correo_electronico, genero, preferencia_genero, fecha_nacimiento, preferencia_edad, descripcion, foto) VALUES
    ('rauljimenez', 'Raul', 'Jimenez', 'root', 'Granada', 'rauljimenez@gmail.com', 'Masculino', 'Femenino', '2003-11-21', '18:26', 'Chulo y orgulloso', 'rauljimenez.jpg');

INSERT INTO usuarios (id_usuario, nombre, apellidos, contraseña, localidad, correo_electronico, genero, preferencia_genero, fecha_nacimiento, preferencia_edad, descripcion, foto) VALUES
    ('lavanesaa', 'Vanessa', 'Lopez', 'root', 'Madrid', 'vaneekeloke@gmail.com', 'Femenino', 'Masculino', '1992-05-12', '18:25-26:30', 'Me flipa la juerga', 'lavanesaa.jpg');

INSERT INTO usuarios (id_usuario, nombre, apellidos, contraseña, localidad, correo_electronico, genero, preferencia_genero, fecha_nacimiento, preferencia_edad, descripcion, foto) VALUES
    ('marcosgarcia', 'Marcos', 'Garcia', 'root', 'Aragon', 'marcosgarcia@gmail.com', 'Masculino', 'Femenino', '1994-12-01', '18:26', 'Hago skate, no ves la gorra o que?', 'marcosgarcia.jpg');

INSERT INTO usuarios (id_usuario, nombre, apellidos, contraseña, localidad, correo_electronico, genero, preferencia_genero, fecha_nacimiento, preferencia_edad, descripcion, foto) VALUES
    ('maricarmen', 'Maria', 'Del Carmen', 'root', 'Galicia', 'maricarmen@gmail.com', 'Femenino', 'Masculino', '1940-02-24', '51:60-61:70-71:80', 'Hago las mejores croquetas de Galicia', 'maricarmen.jpg');

INSERT INTO usuarios (id_usuario, nombre, apellidos, contraseña, localidad, correo_electronico, genero, preferencia_genero, fecha_nacimiento, preferencia_edad, descripcion, foto) VALUES
    ('sofiamartinez', 'Sofía', 'Martínez', 'root', 'Valencia', 'sofiamartinez@gmail.com', 'Femenino', 'Indiferente', '1936-08-05', '26:30', 'Hola, soy Sofía, me gusta el arte y la naturaleza', 'sofiamartinez.jpg');

INSERT INTO usuarios (id_usuario, nombre, apellidos, contraseña, localidad, correo_electronico, genero, preferencia_genero, fecha_nacimiento, preferencia_edad, descripcion, foto) VALUES
    ('marijose', 'Maria', 'Jose', 'root', 'Madrid', 'marijose@gmail.com', 'Femenino', 'Masculino', '1970-02-01', '31:40', 'Me gusta leer', 'marijose.jpg');

INSERT INTO usuarios (id_usuario, nombre, apellidos, contraseña, localidad, correo_electronico, genero, preferencia_genero, fecha_nacimiento, preferencia_edad, descripcion, foto) VALUES
    ('josebatrigo', 'Joseba', 'Trigo', 'root', 'Navarra', 'jtrigosdam1@gmail.com', 'Masculino', 'Femenino', '2004-01-08', '18:26', 'Solo busco sexo', 'josebatrigo.jpg');

INSERT INTO usuarios (id_usuario, nombre, apellidos, contraseña, localidad, correo_electronico, genero, preferencia_genero, fecha_nacimiento, preferencia_edad, descripcion, foto) VALUES
    ('marcofernandez', 'Marco', 'Fernandez', 'root', 'Sevilla', 'marco@gmail.com', 'Masculino', 'Femenino', '1992-06-21', '18:26', 'Me gusta el campo', 'marcofernandez.jpg');

INSERT INTO usuarios (id_usuario, nombre, apellidos, contraseña, localidad, correo_electronico, genero, preferencia_genero, fecha_nacimiento, preferencia_edad, descripcion, foto) VALUES
    ('mirennavarro', 'Miren', 'Navarro', 'root', 'Asturias', 'mirennav@gmail.com', 'Femenino', 'Masculino', '1993-04-23', '18:25-26:30', 'Romantica pero no mucho', 'mirennavarro.jpg');

INSERT INTO usuarios (id_usuario, nombre, apellidos, contraseña, localidad, correo_electronico, genero, preferencia_genero, fecha_nacimiento, preferencia_edad, descripcion, foto) VALUES
    ('luisillo', 'Luis', 'Doradp', 'root', 'Navarra', 'luisdoradogarces@gmail.com', 'Masculino', 'Femenino', '1982-03-15', '18:26', 'Puntual y atento', 'luisillo.jpg');

INSERT INTO usuarios (id_usuario, nombre, apellidos, contraseña, localidad, correo_electronico, genero, preferencia_genero, fecha_nacimiento, preferencia_edad, descripcion, foto) VALUES
    ('naroaliza', 'Naroa', 'Lizarraga', 'root', 'Cantabria', 'naroaliza@gmail.com', 'Femenino', 'Masculino', '2004-02-05', '18:25', 'Despistada y torpe, pero maja', 'naroaliza.jpg');

INSERT INTO usuarios (id_usuario, nombre, apellidos, contraseña, localidad, correo_electronico, genero, preferencia_genero, fecha_nacimiento, preferencia_edad, descripcion, foto) VALUES
    ('pacaalonso', 'Paca', 'Alonso', 'root', 'Navarra', 'lapaki12@gmail.com', 'Femenino', 'Masculino', '1956-03-12', '51:60', 'Moderna y muy coqueta', 'pacaalonso.jpg');

INSERT INTO usuarios (id_usuario, nombre, apellidos, contraseña, localidad, correo_electronico, genero, preferencia_genero, fecha_nacimiento, preferencia_edad, descripcion, foto) VALUES
    ('abranico86', 'Abran', 'Jimenez', 'root', 'Navarra', 'abran@gmail.com', 'Masculino', 'Femenino', '1999-01-23', '18:26', 'Este ladron te va a robar el corazon', 'abranico86.jpg');

INSERT INTO usuarios (id_usuario, nombre, apellidos, contraseña, localidad, correo_electronico, genero, preferencia_genero, fecha_nacimiento, preferencia_edad, descripcion, foto) VALUES
    ('amingarcia', 'Amin', 'Garcia', 'root', 'Madrid', 'amingar@gmail.com', 'Masculino', 'Femenino', '2001-08-22', '18:26', 'Enrrollao ;)', 'amingarcia.jpg');

INSERT INTO usuarios (id_usuario, nombre, apellidos, contraseña, localidad, correo_electronico, genero, preferencia_genero, fecha_nacimiento, preferencia_edad, descripcion, foto) VALUES
    ('benjamin', 'Benjamin', 'Gonzalez', 'root', 'Madrid', 'benjaamin@gmail.com', 'Masculino', 'Femenino', '1998-03-17', '18:26', 'Me gusta la hipica y el golf', 'benjamin.jpg');

INSERT INTO likes (like_id, user_id_1, user_id_2) VALUES
    (1, 'sofiamartinez', 'josebatrigo');

INSERT INTO interacciones (id_interaccion, user_id, perfil_id, tipo_interaccion, fecha_hora_mesaje) VALUES
    (1, 'juanperez', 'mariagarcia', 'like', '2023-04-25 12:30:00');

INSERT INTO interacciones (id_interaccion, user_id, perfil_id, tipo_interaccion, fecha_hora_mesaje) VALUES
    (2, 'alejandrohernandez', 1, 'match', '2023-04-26 10:45:00');

INSERT INTO interacciones (id_interaccion, user_id, perfil_id, tipo_interaccion, fecha_hora_mesaje) VALUES
    (3, 'mariagarcia', 4, 'perfil_visto', '2023-04-27 16:20:00');

INSERT INTO interacciones (id_interaccion, user_id, perfil_id, tipo_interaccion, fecha_hora_mesaje) VALUES
    (4, 'juanperez', 4, 'mensaje', '2023-04-28 14:15:00');

INSERT INTO mensajes (id_mensaje, user_id, perfil_id, mensaje, estado_mensaje, fecha_hora_mesaje) VALUES
    (1, 'alejandrohernandez', 'mariagarcia', 'Hola, ¿cómo estás?', 'no leido', '2023-05-01 10:00:00');

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

INSERT INTO match (user_id_1, user_id_2)
VALUES ('sofiamartinez', 'alejandrohernandez');

INSERT INTO match (user_id_1, user_id_2)
VALUES ('juanperez', 'sofiamartinez');

INSERT INTO match (user_id_1, user_id_2)
VALUES ('alejandrohernandez', 'mariagarcia');

INSERT INTO match (user_id_1, user_id_2)
VALUES ('sofiamartinez', 'mariagarcia');
