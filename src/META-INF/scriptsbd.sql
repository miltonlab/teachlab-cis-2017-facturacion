CREATE TABLE CLIENTE (CEDULA VARCHAR(255) NOT NULL, APELLIDOS VARCHAR(255), DIRECCION VARCHAR(255), NOMBRES VARCHAR(255), TELEFONO VARCHAR(255), PRIMARY KEY (CEDULA))
CREATE TABLE FACTURA (NUMERO INTEGER NOT NULL, FECHA DATE, PORCENTAJE_IVA INTEGER, cliente_id VARCHAR(255), PRIMARY KEY (NUMERO))
CREATE TABLE LINEAFACTURA (NUMERO INTEGER NOT NULL, factura_id INTEGER, producto_id VARCHAR(255), PRIMARY KEY (NUMERO))
CREATE TABLE PRODUCTO (CODIGO VARCHAR(255) NOT NULL, DESCRIPCION VARCHAR(255), PRECIO FLOAT, PRIMARY KEY (CODIGO))
ALTER TABLE FACTURA ADD CONSTRAINT FACTURA_cliente_id FOREIGN KEY (cliente_id) REFERENCES CLIENTE (CEDULA)
ALTER TABLE LINEAFACTURA ADD CONSTRAINT LNAFACTURAfcturaid FOREIGN KEY (factura_id) REFERENCES FACTURA (NUMERO)
ALTER TABLE LINEAFACTURA ADD CONSTRAINT LNFACTURAprductoid FOREIGN KEY (producto_id) REFERENCES PRODUCTO (CODIGO)
