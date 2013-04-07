CREATE TABLE Author
(
	id         NUMERIC(8) NOT NULL,
	firstname  VARCHAR(50) NOT NULL,
	lastname   VARCHAR(50) NOT NULL,
	birthday   DATE
);


CREATE TABLE Book
(
	id     NUMERIC(8) NOT NULL,
	title  VARCHAR(100),
	isbn   VARCHAR(20) NOT NULL
);


CREATE TABLE Book2Author
(
	book_id    NUMERIC(8) NOT NULL,
	author_id  NUMERIC(8) NOT NULL
);


CREATE TABLE Book2Category
(
	book_id      NUMERIC(8) NOT NULL,
	category_id  NUMERIC(8) NOT NULL
);


CREATE TABLE Book2Genre
(
	book_id   NUMERIC(8) NOT NULL,
	genre_id  NUMERIC(8) NOT NULL
);


CREATE TABLE BookReview
(
	id              NUMERIC(8) NOT NULL,
	reviewdate      DATE NOT NULL,
	usr_id          NUMERIC(8) NOT NULL,
	book_id         NUMERIC(8) NOT NULL,
	rating          NUMERIC(3) NOT NULL,
	text            VARCHAR(2000),
	reviewType      CHAR(1) NOT NULL,
	editoralNotice  VARCHAR(1000),
	job             VARCHAR(50),
	position        VARCHAR(50),
	purchaseDate    DATE
);


CREATE TABLE Category
(
	id           NUMERIC(8) NOT NULL,
	name         VARCHAR(30) NOT NULL,
	description  VARCHAR(200)
);


CREATE TABLE Genre
(
	id           NUMERIC(8) NOT NULL,
	name         VARCHAR(30) NOT NULL,
	description  VARCHAR(200)
);


CREATE TABLE StockItem
(
	id        NUMERIC(8) NOT NULL,
	book_id   NUMERIC(8) NOT NULL,
	wh_id     NUMERIC(8) NOT NULL,
	quantity  NUMERIC(4) NOT NULL,
	price     NUMERIC(8,2) NOT NULL
);


CREATE TABLE Usr
(
	id         NUMERIC(8) NOT NULL,
	firstname  VARCHAR(50) NOT NULL,
	lastname   VARCHAR(50) NOT NULL,
	email      VARCHAR(100) NOT NULL,
	regdate    TIMESTAMP DEFAULT NOW()
);


CREATE TABLE Warehouse
(
	id           NUMERIC(8) NOT NULL,
	addressLine  VARCHAR(200) NOT NULL,
	telephone1   VARCHAR(30),
	telephone2   VARCHAR(30)
);



ALTER TABLE Author
	ADD CONSTRAINT UQ_Author UNIQUE (lastname, firstname, birthday);

ALTER TABLE Book
	ADD CONSTRAINT UQ_Book_isbn UNIQUE (isbn);

ALTER TABLE Book2Author
	ADD CONSTRAINT UQ_Book2Author UNIQUE (book_id, author_id);

ALTER TABLE Book2Category
	ADD CONSTRAINT UQ_Book2Category UNIQUE (book_id, category_id);

ALTER TABLE Book2Genre
	ADD CONSTRAINT UQ_Book2Genre UNIQUE (book_id, genre_id);

ALTER TABLE BookReview
ADD CONSTRAINT CHK_BookReview CHECK (reviewType in ('E','R'));

ALTER TABLE Category
	ADD CONSTRAINT UQ_Category_name UNIQUE (name);

ALTER TABLE Genre
	ADD CONSTRAINT UQ_Genre_name UNIQUE (name);

ALTER TABLE StockItem
	ADD CONSTRAINT UQ_StockItem UNIQUE (book_id, wh_id);

ALTER TABLE Author ADD CONSTRAINT PK_Author 
	PRIMARY KEY (id);

ALTER TABLE Book ADD CONSTRAINT PK_Book 
	PRIMARY KEY (id);

ALTER TABLE BookReview ADD CONSTRAINT PK_BookReview 
	PRIMARY KEY (id);

ALTER TABLE Category ADD CONSTRAINT PK_Category 
	PRIMARY KEY (id);

ALTER TABLE Genre ADD CONSTRAINT PK_Genre 
	PRIMARY KEY (id);

ALTER TABLE StockItem ADD CONSTRAINT PK_StockItem 
	PRIMARY KEY (id);

ALTER TABLE Usr ADD CONSTRAINT PK_Usr 
	PRIMARY KEY (id);

ALTER TABLE Warehouse ADD CONSTRAINT PK_Warehouse 
	PRIMARY KEY (id);



ALTER TABLE Book2Author ADD CONSTRAINT FK_Book2Author_Author 
	FOREIGN KEY (author_id) REFERENCES Author (id);

ALTER TABLE Book2Author ADD CONSTRAINT FK_Book2Author_Book 
	FOREIGN KEY (book_id) REFERENCES Book (id);

ALTER TABLE Book2Category ADD CONSTRAINT FK_Book2Category_Book 
	FOREIGN KEY (book_id) REFERENCES Book (id);

ALTER TABLE Book2Category ADD CONSTRAINT FK_Book2Category_Category 
	FOREIGN KEY (category_id) REFERENCES Category (id);

ALTER TABLE Book2Genre ADD CONSTRAINT FK_Book2Genre_Book 
	FOREIGN KEY (book_id) REFERENCES Book (id);

ALTER TABLE Book2Genre ADD CONSTRAINT FK_Book2Genre_Genre 
	FOREIGN KEY (genre_id) REFERENCES Genre (id);

ALTER TABLE BookReview ADD CONSTRAINT FK_BookReview_Book 
	FOREIGN KEY (book_id) REFERENCES Book (id);

ALTER TABLE BookReview ADD CONSTRAINT FK_BookReview_Usr 
	FOREIGN KEY (usr_id) REFERENCES Usr (id);

ALTER TABLE StockItem ADD CONSTRAINT FK_StockItem_Book 
	FOREIGN KEY (book_id) REFERENCES Book (id);

ALTER TABLE StockItem ADD CONSTRAINT FK_StockItem_Warehouse 
	FOREIGN KEY (wh_id) REFERENCES Warehouse (id);
