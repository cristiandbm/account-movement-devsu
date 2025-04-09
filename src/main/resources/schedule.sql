create table accounts (
	number INT NOT NULL PRIMARY KEY,
	type VARCHAR(10) NOT NULL,
	opening_balance decimal(10,2) NOT NULL,
	status BOOLEAN NOT NULL,
	client_id INT NOT NULL
);

create table movements (
	id VARCHAR(40) NOT NULL PRIMARY KEY,
	type VARCHAR(10) NOT NULL,
	opening_balance DECIMAL(10,2) NOT NULL,
	value_tx DECIMAL(10,2) NOT NULL,
	status BOOLEAN NOT NULL,
	movement_date TIMESTAMP NOT NULL,
	account_number INT NOT NULL
	FOREIGN KEY (account_number) REFERENCES accounts(number)
);

