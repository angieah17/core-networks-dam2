CREATE TABLE rios (
    id_rio INT AUTO_INCREMENT PRIMARY KEY,
    nombre_rio VARCHAR(100) NOT NULL,
    longitud_rio INT NOT NULL,
    paises_rio VARCHAR(255) NOT NULL,
    descripcion TEXT NOT NULL,
    continente VARCHAR(50) NOT NULL
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_unicode_ci;
