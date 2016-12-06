USE Master

--DROP DATABASE Ecig_Nordic
--GO

--CREATE DATABASE Ecig_Nordic
--GO

--USE Ecig_Nordic
--GO

USE dmab0913_1


CREATE TABLE Product
   (EAN				VARCHAR(30)		PRIMARY KEY,
	name			VARCHAR(30)		NOT NULL,
	description		VARCHAR(30),
    price		    FLOAT			NOT NULL,
    stock			INT				NOT NULL,
	minStock	    INT				NOT NULL);

CREATE TABLE Customer
   (name			VARCHAR(30)		NOT NULL,
    ssn				VARCHAR(12)		PRIMARY KEY,
	phoneno			VARCHAR(12)		NOT NULL UNIQUE,
	email			VARCHAR(30)		NOT NULL,
	address			VARCHAR(30)		NOT NULL,
	zipCode			VARCHAR(10)		NOT NULL);

CREATE TABLE Salesman
   (name			VARCHAR(30)		NOT NULL,
	ssn				VARCHAR(12)		PRIMARY KEY,
	phoneno			VARCHAR(12)		NOT NULL,
	email			VARCHAR(30)		NOT NULL,
	address			VARCHAR(30)		NOT NULL,
	zipCode			VARCHAR(10)		NOT NULL,
	salesmanID		VARCHAR(30)		NOT NULL	UNIQUE,
	paymentLevel	VARCHAR(10)		NOT NULL);

CREATE TABLE SalesOrder
   (orderNO			VARCHAR(30)		PRIMARY KEY,
	customerPhoneno	VARCHAR(12)		FOREIGN KEY REFERENCES Customer(phoneno),
	salesmanID		VARCHAR(30)		FOREIGN KEY REFERENCES Salesman(salesmanID),
	totalPrice		FLOAT			NOT NULL,
	orderDate		DATE			NOT NULL);

CREATE TABLE PartOrder
   (orderNO			VARCHAR(30)		FOREIGN KEY REFERENCES SalesOrder(orderNO),
	productEAN		VARCHAR(30)		FOREIGN KEY REFERENCES Product(EAN),
	amount			INT				NOT NULL,
	partOrderPrice	FLOAT			NOT NULL,
	PRIMARY KEY(orderNO, productEAN));