

CREATE TABLE  apartment  (
   id  int NOT NULL AUTO_INCREMENT,
   apartmentNumber  int NOT NULL,
   description  varchar(255 NOT NULL,
   intercom  varchar DEFAULT NULL,
  PRIMARY KEY ( id ),
  UNIQUE KEY  UK_3ivm5ayi9khyj2vkjjr9y6rib  ( apartmentNumber )
) 



insert into apartment(id, apartmentNumber, description, intercom) values(1, 0, 'Część Wspólna','6666');
insert into apartment(id, apartmentNumber, description, intercom) values(2, 1, 'Piwnica','6666');
insert into apartment(id, apartmentNumber, description, intercom) values(3, 2, 'Parter','6666');
insert into apartment(id, apartmentNumber, description, intercom) values(4, 3, 'I Piętro','6666');
insert into apartment(id, apartmentNumber, description, intercom) values(5, 4, 'II Piętro- Mozna kasować','6666');

