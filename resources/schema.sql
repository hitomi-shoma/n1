CREATE TABLE IF NOT EXISTS roles (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS users (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	kana VARCHAR(50) NOT NULL,
	post_code VARCHAR(50) NOT NULL,
	address VARCHAR(50) NOT NULL,
	phone_number VARCHAR(50) NOT NULL,
	email VARCHAR(255) NOT NULL,
	password VARCHAR(255) NOT NULL,
	role_id INT NOT NULL,
	enabled BOOLEAN NOT NULL,
	created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	FOREIGN KEY (role_id) REFERENCES roles (id)
);

CREATE TABLE IF NOT EXISTS categories (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	category_name VARCHAR(50) NOT NULL,
	created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS shops (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	category_id INT NOT NULL,
	name VARCHAR(50) NOT NULL,
	image_name VARCHAR(255),
	description VARCHAR(255) NOT NULL,
	lower_price INT NOT NULL,
	upper_price INT NOT NULL,
	capacity INT NOT NULL,
	post_code VARCHAR(50) NOT NULL,
	address VARCHAR(255) NOT NULL,
	phone_number VARCHAR(50) NOT NULL,
	created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	FOREIGN KEY (category_id) REFERENCES categories (id)
);

CREATE TABLE IF NOT EXISTS verification_tokens (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	user_id INT NOT NULL UNIQUE,
	token VARCHAR(255) NOT NULL,
	created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS reservations (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	shop_id INT NOT NULL,
	user_id INT NOT NULL,
	reservation_date DATE NOT NULL,
	number_of_people INT NOT NULL,
	amount INT NOT NULL,
	created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	FOREIGN KEY (shop_id) REFERENCES shops (id),
	FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS review (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	shop_id INT NOT NULL,
	user_id INT NOT NULL,
	score INT NOT NULL,
	review_text VARCHAR(255) NOT NULL,
	created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	FOREIGN KEY (shop_id) REFERENCES shops (id),
	FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS favorite (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	user_id INT NOT NULL,
	shop_id INT NOT NULL,
	created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	UNIQUE (shop_id, user_id),
	FOREIGN KEY (user_id) REFERENCES users (id),
	FOREIGN KEY (shop_id) REFERENCES shops (id)
);

CREATE TABLE IF NOT EXISTS sales (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE,
    amount INT NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);