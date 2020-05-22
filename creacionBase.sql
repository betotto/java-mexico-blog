use javamexico_blog;

-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema javamexico_blog
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema javamexico_blog
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `javamexico_blog` ;
USE `javamexico_blog` ;

-- -----------------------------------------------------
-- Table `javamexico_blog`.`usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `javamexico_blog`.`usuarios` (
  `idusuario` INT NOT NULL AUTO_INCREMENT,
  `metadata` JSON NOT NULL,
  `password` VARCHAR(260) NOT NULL,
  `apodo` VARCHAR(100) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `nombre` VARCHAR(100) NULL,
  `apellidos` VARCHAR(100) NULL,
  PRIMARY KEY (`idusuario`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `javamexico_blog`.`categorias`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `javamexico_blog`.`categorias` (
  `idcategoria` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`idcategoria`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `javamexico_blog`.`posts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `javamexico_blog`.`posts` (
  `idpost` INT NOT NULL AUTO_INCREMENT,
  `metadata` JSON NOT NULL,
  `titulo` VARCHAR(250) NOT NULL,
  `contenido` BLOB NOT NULL,
  `creador` INT NOT NULL,
  `categoria` INT NOT NULL,
  PRIMARY KEY (`idpost`),
  INDEX `post_usuario_creador_idx` (`creador` ASC),
  INDEX `post_categoria_idx` (`categoria` ASC),
  CONSTRAINT `post_usuario_creador`
    FOREIGN KEY (`creador`)
    REFERENCES `javamexico_blog`.`usuarios` (`idusuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `post_categoria`
    FOREIGN KEY (`categoria`)
    REFERENCES `javamexico_blog`.`categorias` (`idcategoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `javamexico_blog`.`comentarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `javamexico_blog`.`comentarios` (
  `idcomentario` INT NOT NULL AUTO_INCREMENT,
  `metadata` JSON NOT NULL,
  `contenido` BLOB NOT NULL,
  `creador` INT NOT NULL,
  `post` INT NOT NULL,
  PRIMARY KEY (`idcomentario`),
  INDEX `comentario_post_idx` (`post` ASC),
  INDEX `creador_idx` (`creador` ASC),
  CONSTRAINT `comentario_post`
    FOREIGN KEY (`post`)
    REFERENCES `javamexico_blog`.`posts` (`idpost`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `comentario_usuario_creador`
    FOREIGN KEY (`creador`)
    REFERENCES `javamexico_blog`.`usuarios` (`idusuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `javamexico_blog`.`foros`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `javamexico_blog`.`foros` (
  `idforo` INT NOT NULL AUTO_INCREMENT,
  `metadata` JSON NOT NULL,
  `creador` INT NOT NULL,
  `titulo` VARCHAR(250) NOT NULL,
  PRIMARY KEY (`idforo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `javamexico_blog`.`comentarios-foros`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `javamexico_blog`.`comentarios-foros` (
  `comentario` INT NOT NULL,
  `foro` INT NOT NULL,
  PRIMARY KEY (`comentario`, `foro`),
  INDEX `comentarios_foros_foro_idx` (`foro` ASC),
  CONSTRAINT `comentarios_foros_comentario`
    FOREIGN KEY (`comentario`)
    REFERENCES `javamexico_blog`.`comentarios` (`idcomentario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `comentarios_foros_foro`
    FOREIGN KEY (`foro`)
    REFERENCES `javamexico_blog`.`foros` (`idforo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
