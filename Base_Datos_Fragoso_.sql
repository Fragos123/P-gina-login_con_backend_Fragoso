CREATE DATABASE login_sistema;
USE login_sistema;

CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO usuarios (usuario, password) VALUES 
('admin@ejemplo.com', 'Admin123'),
('usuario@test.com', 'Password1'),
('demo@correo.com', 'Demo2024');