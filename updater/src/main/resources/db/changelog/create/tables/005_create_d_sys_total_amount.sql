CREATE TABLE IF NOT EXISTS SR_SYS_CONFIG (
	CODE VARCHAR(255) NOT NULL, /* ПАРАМЕТР */
	VALUE VARCHAR(255) NOT NULL /* ЗНАЧЕНИЕ */,
	ANNOTATION VARCHAR(255) NOT NULL /* ОПИСАНИЕ */,
	PRIMARY KEY(CODE)
);