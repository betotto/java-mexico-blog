CREATE TABLE `comentarios` (
    `idcomentario` INT,
    `metadata` JSON,
    `contenido` BLOB,
    `creador` INT,
    `post` INT
);

CREATE TABLE `categorias` (
    `idcategoria` INT,
    `nombre` VARCHAR(100)
);

INSERT INTO categorias (idcategoria, nombre) VALUES (1, 'DESARROLLO');
INSERT INTO categorias (idcategoria, nombre) VALUES (2, 'TEST');
