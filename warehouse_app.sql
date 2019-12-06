CREATE DATABASE  IF NOT EXISTS `warehouse_app`;
USE `warehouse_app`;

DROP TABLE IF EXISTS `warehouse`;

CREATE TABLE `warehouse` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `shelf`;

CREATE TABLE `shelf` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `code` int(11) DEFAULT NULL,
  `warehouse_id` int(11) DEFAULT NULL,
  
  PRIMARY KEY (`id`),
  
  KEY `FK_DETAILs_idx` (`warehouse_id`),
  CONSTRAINT `FK_WAREHOUSE_SHELF` FOREIGN KEY (`warehouse_id`) 
  REFERENCES `warehouse` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  KEY `FK_DETAILss_idx` (`id`),
  CONSTRAINT `FK_form_products` FOREIGN KEY (`id`) 
  REFERENCES `form_products` (`shelf_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
  
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `barcode` varchar(45) DEFAULT NULL,
  `category` varchar(256) DEFAULT NULL,
  `description` varchar(256) DEFAULT NULL,
  `m_unit` varchar(45) DEFAULT NULL, 
  
  PRIMARY KEY (`id`)
  
 # KEY `FK_DETAI_idx` (`id`),
 # CONSTRAINT `FK_form_products2` FOREIGN KEY (`id`) 
 # REFERENCES `form_products` (`product_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

-- DROP TABLE IF EXISTS  `shelf_products`;

-- CREATE TABLE `shelf_products` (
--   `shelf_id` bigint(11) NOT NULL,
--   `product_id` bigint(11) NOT NULL,
--   
--   PRIMARY KEY (`shelf_id`,`product_id`),
--   
--   KEY `FK_SHELF_idx` (`shelf_id`),
--   
--   CONSTRAINT `FK_SHELF_02` FOREIGN KEY (`shelf_id`) 
--   REFERENCES `shelf` (`id`) 
--   ON DELETE NO ACTION ON UPDATE NO ACTION,
--   
--   CONSTRAINT `FK_PRODUCTS_02` FOREIGN KEY (`product_id`) 
--   REFERENCES `product` (`id`) 
--   ON DELETE NO ACTION ON UPDATE NO ACTION
-- ) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS  `form_products`;

CREATE TABLE `form_products` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `shelf_id` bigint(11) NOT NULL,
  `product_id` bigint(11) NOT NULL,
  `form_id` bigint(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`form_id`,`product_id`),
  
  KEY `FK_FORM_PRODUCTS_idx` (`id`),
  
  CONSTRAINT `FK_SHELF_03` FOREIGN KEY (`shelf_id`) 
  REFERENCES `shelf` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_FORM_03` FOREIGN KEY (`form_id`) 
  REFERENCES `form` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_PRODUCTS_03` FOREIGN KEY (`product_id`) 
  REFERENCES `product` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `form`;

CREATE TABLE `form` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(256) DEFAULT NULL,
  `type` varchar(45),
  `person_name` varchar(45) DEFAULT NULL,
  `date` date,
  PRIMARY KEY (`id`),

  KEY `FK_DETAILf_idx` (`id`),
  CONSTRAINT `FK_form_p` FOREIGN KEY (`id`) 
  REFERENCES `form` (`form_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
	
ALTER TABLE `form` MODIFY COLUMN `date` varchar(45);


DROP TABLE IF EXISTS `stock`;
CREATE TABLE `stock` (
  `product_id` bigint(11) NOT NULL,
  `shelf_id` bigint(11) DEFAULT NULL,
  `curr_stock` int(11) DEFAULT NULL,
  
  PRIMARY KEY (`product_id`)
)ENGINE=InnoDB  DEFAULT CHARSET=latin1;

ALTER TABLE `stock`
   ADD CONSTRAINT FK_stock_formproduct FOREIGN KEY (product_id)
      REFERENCES `form_products` (`product_id`)
      ON DELETE CASCADE
      ON UPDATE CASCADE





