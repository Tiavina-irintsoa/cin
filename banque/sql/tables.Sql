CREATE TABLE operation (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    idcin varchar,
    montant_en_plus REAL,
    montant_en_moins REAL,
    timestamp DATETIME
);

ALTER TABLE operation RENAME COLUMN timestamp TO dateOperation;


create table transfert (
    idTransfert INTEGER PRIMARY KEY AUTOINCREMENT, 
    iddevise integer, 
    montant real,
    sender varchar,
    receiver  varchar
);

ALTER TABLE operation ADD COLUMN idtransfert integer references transfert(idtransfert);
ALTER TABLE transfert ADD COLUMN dateTransfert date ;
