DROP DATABASE IF EXISTS bakery;
CREATE DATABASE bakery;

-- MySQLの「ON UPDATE TIMESTAMP」はPOSTGRESではこの関数を設定しておいて、
CREATE OR REPLACE FUNCTION trigger_set_timestamp()
RETURNS TRIGGER AS $$
  BEGIN NEW.updated_at = NOW();
  RETURN NEW;
END;
$$ LANGUAGE 'plpgsql';

CREATE TABLE mst_user (
  id SERIAL PRIMARY KEY,
  user_name VARCHAR(32) NOT NULL UNIQUE,
  password VARCHAR(16) NOT NULL,
  mail_adress VARCHAR(255) NOT NULL,
  family_name VARCHAR(255) NOT NULL,
  first_name VARCHAR(255) NOT NULL,
  family_name_kana VARCHAR(255) NOT NULL,
  first_name_kana VARCHAR(255) NOT NULL,
  gender TINYINT DEFAULT 0,
  delete_flag BOOLEAN NOT NULL DEFAULT 'FALSE',
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

SELECT * FROM mst_user;
ALTER TABLE mst_user ADD COLUMN mail_adress VARCHAR NOT NULL;

CREATE TABLE mst_category (
  id SERIAL PRIMARY KEY,
  category_name VARCHAR(255) NOT NULL UNIQUE,
  category_description VARCHAR(255),
  delete_flag BOOLEAN NOT NULL DEFAULT 'FALSE',
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE mst_product (
  id SERIAL PRIMARY KEY,
  product_name VARCHAR(255) NOT NULL UNIQUE,
  product_name_kana VARCHAR(255) NOT NULL UNIQUE,
  product_description VARCHAR(255),
  category_id INTEGER,
  price INTEGER NOT NULL,
  image_full_path VARCHAR(255) NOT NULL,
  release_date VARCHAR(10),
  release_company VARCHAR(255),
  delete_flag BOOLEAN NOT NULL DEFAULT 'FALSE',
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY(category_id) REFERENCES mst_category(id)
);

CREATE TABLE mst_destination (
  id SERIAL PRIMARY KEY,
  user_id INTEGER NOT NULL,
  family_name VARCHAR(255) NOT NULL,
  first_name VARCHAR(255) NOT NULL,
  tel_number VARCHAR(13),
  address VARCHAR(255) NOT NULL,
  status SMALLINT NOT NULL DEFAULT 1,
  delete_flag BOOLEAN NOT NULL DEFAULT 'FALSE',
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY(user_id) REFERENCES mst_user(id)
);

CREATE TABLE tbl_cart (
  id SERIAL PRIMARY KEY,
  user_id INTEGER NOT NULL,
  product_id INTEGER NOT NULL,
  product_count INTEGER NOT NULL,
  delete_flag BOOLEAN NOT NULL DEFAULT 'FALSE',
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY(product_id) REFERENCES mst_product(id)
);
SELECT * FROM tbl_cart WHERE delete_flag = FALSE;
delete from tbl_cart WHERE delete_flag = FALSE;

CREATE TABLE tbl_purchase_history (
  id SERIAL PRIMARY KEY,
  user_id INTEGER NOT NULL,
  product_id INTEGER NOT NULL,
  product_count INTEGER NOT NULL,
  price INTEGER NOT NULL,
  destination_id INTEGER NOT NULL,
  status SMALLINT NOT NULL DEFAULT 1,
  purchased_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  delete_flag BOOLEAN DEFAULT FALSE,
  FOREIGN KEY(user_id) REFERENCES mst_user(id),
  FOREIGN KEY(product_id) REFERENCES mst_product(id),
  FOREIGN KEY(destination_id) REFERENCES mst_destination(id)
);
SELECT * FROM tbl_purchase_history;
DELETE FROM tbl_purchase_history;

ALTER TABLE tbl_cart ALTER COLUMN delete_flag SET DEFAULT FALSE;
ALTER TABLE tbl_cart ADD COLUMN delete_flag BOOLEAN DEFAULT FALSE;
ALTER TABLE mst_destination ADD COLUMN delete_flag BOOLEAN DEFAULT FALSE;
SELECT * FROM tbl_cart WHERE user_id = 1 ORDER BY id;
SELECT * FROM mst_destination ORDER BY id;


INSERT INTO tbl_purchase_history  (user_id, product_id, product_count, price, destination_id)
  SELECT cart.user_id, cart.product_id, cart.product_count, product.price, destination.id
  FROM tbl_cart AS cart
    INNER JOIN mst_product AS product
    ON cart.product_id = product.id
    INNER JOIN mst_destination AS destination
    ON cart.user_id = destination.user_id
  WHERE cart.user_id = :#{#cart.userId}
  AND destination.id = :#{#cart.destinationId}
