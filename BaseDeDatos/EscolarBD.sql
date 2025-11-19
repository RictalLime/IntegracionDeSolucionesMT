-- Crea la base de datos
CREATE DATABASE escolarMT;
USE escolarMT;

-- Crea la tabla Rol
CREATE TABLE Rol (
    idRol INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    rol VARCHAR(100) NOT NULL
);

-- Crea la tabla Profesor
CREATE TABLE Profesor (
    idProfesor INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    apellidoPaterno VARCHAR(100) NOT NULL,
    apellidoMaterno VARCHAR(100) NOT NULL,
    noPersonal VARCHAR(20) NOT NULL,
    password VARCHAR(20) NOT NULL,
    fechaNacimiento DATE,
    fechaContratacion DATE NOT NULL,
    idRol INT NOT NULL,
    FOREIGN KEY (idRol) REFERENCES Rol(idRol)
);

SHOW TABLES;

-- Insertar roles en la tabla Rol
INSERT INTO Rol (rol) VALUES ('Administrador');
INSERT INTO Rol (rol) VALUES ('Directivo');
INSERT INTO Rol (rol) VALUES ('Profesor frente al grupo');

-- Insertar profesores en la tabla Profesor

INSERT INTO Profesor (nombre, apellidoPaterno, apellidoMaterno, noPersonal, password, fechaNacimiento, fechaContratacion, idRol)
VALUES ('Laura', 'González', 'Ramírez', 'NP01', '123456', '2000-05-12', '2020-08-15', 1);

INSERT INTO Profesor (nombre, apellidoPaterno, apellidoMaterno, noPersonal, password, fechaNacimiento, fechaContratacion, idRol)
VALUES ('Carlos', 'Mendoza', 'López', 'NP02', '123456', '2001-11-03', '2018-01-10', 2);

INSERT INTO Profesor (nombre, apellidoPaterno, apellidoMaterno, noPersonal, password, fechaNacimiento, fechaContratacion, idRol)
VALUES ('Ana', 'Martínez', 'Santos', 'NP03', '123456', '2002-07-22', '2021-03-05', 3);

SELECT * FROM Profesor WHERE noPersonal = '' AND password = '';

-- Corregir la columna foto para permitir NULL
ALTER TABLE Profesor MODIFY COLUMN foto LONGBLOB NULL;

-- Crear tabla Facultad
CREATE TABLE Facultad (
    idFacultad INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    facultad VARCHAR(255) NOT NULL
);

-- Crear tabla Carrera
CREATE TABLE Carrera (
    idCarrera INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    carrera VARCHAR(255) NOT NULL,
    idFacultad INT NOT NULL,
    FOREIGN KEY (idFacultad) REFERENCES Facultad(idFacultad)
);

-- Crear tabla Periodo
CREATE TABLE Periodo (
    idPeriodo INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    periodo VARCHAR(300) NOT NULL,
    fechaInicio DATE NOT NULL,
    fechaFin DATE NOT NULL
);

-- Crear tabla Materia
CREATE TABLE Materia (
    idMateria INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    materia VARCHAR(255) NOT NULL,
    creditos INT NOT NULL,
    nrc VARCHAR(6) NOT NULL,
    idProfesor INT NOT NULL,
    idPeriodo INT NOT NULL,
    FOREIGN KEY (idProfesor) REFERENCES Profesor(idProfesor),
    FOREIGN KEY (idPeriodo) REFERENCES Periodo(idPeriodo)
);

-- Crear tabla Alumno
CREATE TABLE Alumno (
    idAlumno INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    nombre VARCHAR(255) NOT NULL,
    apellidoPaterno VARCHAR(255) NOT NULL,
    apellidoMaterno VARCHAR(255) NOT NULL,
    matricula VARCHAR(20) NOT NULL,
    correo VARCHAR(50) NOT NULL,
    fechaNacimiento DATE NOT NULL,
    foto LONGBLOB NULL,
    idCarrera INT NOT NULL,
    FOREIGN KEY (idCarrera) REFERENCES Carrera(idCarrera)
);

-- Crear tabla Calificacion
CREATE TABLE Calificacion (
    idCalificacion INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    idAlumno INT NOT NULL,
    idMateria INT NOT NULL,
    calificacion INT NOT NULL,
    oportunidad INT NOT NULL,
    FOREIGN KEY (idAlumno) REFERENCES Alumno(idAlumno),
    FOREIGN KEY (idMateria) REFERENCES Materia(idMateria)
);

-- Facultades
INSERT INTO Facultad (facultad) VALUES 
('Ingeniería'),
('Ciencias de la Salud'),
('Economía'),
('Artes'),
('Ciencias Biológicas');

-- Carreras
INSERT INTO Carrera (carrera, idFacultad) VALUES 
('Ingeniería de Software', 1),
('Medicina', 2),
('Economía', 3),
('Música', 4),
('Biología', 5);

-- Periodos (últimos 5 de la UV)
INSERT INTO Periodo (periodo, fechaInicio, fechaFin) VALUES
('Febrero - Julio 2023', '2023-02-06', '2023-07-07'),
('Agosto 2023 - Enero 2024', '2023-08-07', '2024-01-12'),
('Febrero - Julio 2024', '2024-02-05', '2024-07-05'),
('Agosto 2024 - Enero 2025', '2024-08-05', '2025-01-10'),
('Febrero - Julio 2025', '2025-02-03', '2025-07-04');

-- Materias
INSERT INTO Materia (materia, creditos, nrc, idProfesor, idPeriodo) VALUES
('Estructuras de Datos', 8, 'ED1234', 1, 3),
('Bases de Datos', 8, 'BD2345', 1, 4),
('Análisis Económico', 6, 'AE3456', 1, 2),
('Anatomía Humana', 9, 'AH4567', 1, 1),
('Biología Celular', 7, 'BC5678', 1, 5);

-- Alumnos
INSERT INTO Alumno (nombre, apellidoPaterno, apellidoMaterno, matricula, correo, fechaNacimiento, idCarrera) VALUES
('Luis', 'Pérez', 'Gómez', 'A001', 'luispg@uv.mx', '2003-04-15', 1),
('María', 'López', 'Hernández', 'A002', 'marialh@uv.mx', '2002-11-22', 2),
('José', 'Martínez', 'Sánchez', 'A003', 'josems@uv.mx', '2001-06-10', 3),
('Ana', 'Ramírez', 'Torres', 'A004', 'anart@uv.mx', '2004-01-30', 4),
('Elena', 'García', 'Vega', 'A005', 'elenagv@uv.mx', '2003-09-18', 5);

-- Calificaciones
INSERT INTO Calificacion (idAlumno, idMateria, calificacion, oportunidad) VALUES
(1, 1, 90, 1),
(2, 2, 85, 1),
(3, 3, 70, 2),
(4, 4, 95, 1),
(5, 5, 88, 1);

ALTER TABLE alumno ADD password VARCHAR(20) NOT NULL;