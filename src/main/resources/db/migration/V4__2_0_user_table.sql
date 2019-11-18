CREATE TABLE IF NOT EXISTS user (

    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name varchar(20),
    surname varchar(50),
    email varchar(50),
    password varchar (20),
    salt varchar(20)
);
CREATE TABLE IF NOT EXISTS role (

    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name varchar(20)
);
CREATE TABLE user_role(
    user_id int NOT NULL,
    role_id int NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES user(id),
    CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES role(id)
);
CREATE TABLE IF NOT EXISTS tariff (

    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    cost_per_night DECIMAL
);
CREATE TABLE IF NOT EXISTS image (

    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    url varchar(128) NOT NULL
);
CREATE TABLE IF NOT EXISTS address (

    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    country varchar(50),
    city varchar(50),
    address varchar(128)
);
CREATE TABLE IF NOT EXISTS apartment (

    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name varchar(50),
    address_id int NOT NULL,
    description varchar(255),
    tariff_id int NOT NULL,
    CONSTRAINT  fk_address FOREIGN KEY (address_id) REFERENCES address(id),
    CONSTRAINT fk_tariff FOREIGN KEY (tariff_id) REFERENCES tariff(id)
);
CREATE TABLE IF NOT EXISTS app_order (

    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    apartment_id int NOT NULL,
    customer_id int NOT NULL,
    creation_date timestamp NOT NULL,
    check_in_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    check_out_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_apartment FOREIGN KEY (apartment_id) REFERENCES apartment(id),
    CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES user(id)
);
CREATE TABLE apartment_image(
    apartment_id int NOT NULL,
    image_id int NOT NULL,
    CONSTRAINT fk_apartments FOREIGN KEY (apartment_id) REFERENCES apartment(id),
    CONSTRAINT fk_image FOREIGN KEY (image_id) REFERENCES image(id)
);
CREATE TABLE IF NOT EXISTS file (

    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    url varchar(128) NOT NULL,
    file blob NOT NULL
);