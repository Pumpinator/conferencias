INSERT INTO persona (nombres, apellidos, email, telefono)
VALUES ('Bill', 'Gates', 'billgates@hotmail.com', '1234567890'),
       ('Jeff', 'Bezos', 'jeffbezos@amazon.com', '7894563210'),
       ('Elon', 'Musk', 'elonmusk@spacex.com', '4561230987');

INSERT INTO conferencista (persona_id)
VALUES (1),
       (2),
       (3);

-- Las fechas y horas se insertan en formato 'YYYY-MM-DD HH:MM:SS'
INSERT INTO conferencia (ruta_foto, nombre, fecha_hora, conferencista_id)
VALUES ('billgates.jpg', 'Nuevos Inventos Tecnológicos', NOW() + INTERVAL 30 MINUTE, 1),
       ('jeffbezos.jpg', 'Cómputo en la Nube', NOW() + INTERVAL 1 HOUR + INTERVAL 30 MINUTE, 2),
       ('elonmusk.jpg', 'Viajes Espaciales', NOW() + INTERVAL 2 HOUR + INTERVAL 30 MINUTE, 3);

-- Insertar carreras
INSERT INTO carrera (nombre)
VALUES ('Desarrollo de Software Multiplataforma'),
       ('Entornos Virtuales y Negocios Digitales'),
       ('Infraestructura de Redes Digitales'),
       ('Gastronomía'),
       ('Turismo');