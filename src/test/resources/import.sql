
insert into residence(id, street, number, city) values (1, 'Świętojańska', '45', 'Gdynia');
insert into residence(id, street, number, city) values (2, 'Pusta', '45', 'Gdynia');

insert into apartment(id, apartmentNumber, description, intercom, residence_id) values(1, 0, 'Część Wspólna','6666', 1);
insert into apartment(id, apartmentNumber, description, intercom, residence_id) values(2, 1, 'Piwnica','6666', 1);
insert into apartment(id, apartmentNumber, description, intercom, residence_id) values(3, 2, 'Parter','6666', 1);
insert into apartment(id, apartmentNumber, description, intercom, residence_id) values(4, 3, 'I Piętro','6666', 1);
insert into apartment(id, apartmentNumber, description, intercom, residence_id) values(5, 2, 'Mieszkanie z drugiej rezydencji','6666', 2);

INSERT INTO  tenant  ( id , email , firstName , lastName , movementDate , password , phone , role , status , apartment_id ) VALUES (1,'owner@res1','LivesAndOwns','1Residence','2016-07-25','witaj','530081187','OWNER','ACTIVE',2);
INSERT INTO  tenant  ( id , email , firstName , lastName , movementDate , password , phone , role , status , apartment_id ) VALUES (2,'kow@wp.pl','Andrzej','Kowalski','2014-07-01','witaj','4456','TENANT','INACTIVE',3);
INSERT INTO  tenant  ( id , email , firstName , lastName , movementDate , password , phone , role , status , apartment_id ) VALUES (3,'par@wp.pl','Kasia','Para','2015-07-26','witaj','23636','TENANT','ACTIVE',4);
INSERT INTO  tenant  ( id , email , firstName , lastName , movementDate , password , phone , role , status , apartment_id ) VALUES (4,'kasia@wp.pl','Kasia','Kowalska','2016-07-29','witaj','3456775','TENANT','ACTIVE',3);
INSERT INTO  tenant  ( id , email , firstName , lastName , movementDate , password , phone , role , status , apartment_id ) VALUES (5,'klej@wp.pl','Piotr','Kulej','2010-07-29','witaj','23526564','TENANT','INACTIVE',2);
INSERT INTO  tenant  ( id , email , firstName , lastName , movementDate , password , phone , role , status , apartment_id ) VALUES (6,'tenant@rez2','Tenant','2Residence','2010-07-29','witaj','23526564','TENANT','ACTIVE',5);
INSERT INTO  tenant  ( id , email , firstName , lastName , movementDate , password , phone , role , status , apartment_id ) VALUES (7,'owner@rez2','Owner','2Residence','2010-07-29','witaj','23526564','OWNER','ACTIVE',null);


INSERT INTO RESIDENCE_OWNERSHIP (id, owner_id, residenceOwned_id)  VALUES (1,1,1);
INSERT INTO RESIDENCE_OWNERSHIP (id, owner_id, residenceOwned_id)  VALUES (2,7,2);

INSERT INTO  division  ( id , date , divisionValue , apartment_id , tenant_id ) VALUES (1,'2016-07-01',0.33,1,1);
INSERT INTO  division  ( id , date , divisionValue , apartment_id , tenant_id ) VALUES (2,'2016-07-01',1,2,1);
INSERT INTO  division  ( id , date , divisionValue , apartment_id , tenant_id ) VALUES (3,'2016-07-01',0,3,1);
INSERT INTO  division  ( id , date , divisionValue , apartment_id , tenant_id ) VALUES (4,'2016-07-01',0,4,1);
INSERT INTO  division  ( id , date , divisionValue , apartment_id , tenant_id ) VALUES (6,'2016-07-01',0.33,1,3);
INSERT INTO  division  ( id , date , divisionValue , apartment_id , tenant_id ) VALUES (7,'2016-07-01',0,2,3);
INSERT INTO  division  ( id , date , divisionValue , apartment_id , tenant_id ) VALUES (8,'2016-07-01',0,3,3);
INSERT INTO  division  ( id , date , divisionValue , apartment_id , tenant_id ) VALUES (9,'2016-07-01',1,4,3);
INSERT INTO  division  ( id , date , divisionValue , apartment_id , tenant_id ) VALUES (11,'2016-07-01',0.33,1,4);
INSERT INTO  division  ( id , date , divisionValue , apartment_id , tenant_id ) VALUES (12,'2016-07-01',0,2,4);
INSERT INTO  division  ( id , date , divisionValue , apartment_id , tenant_id ) VALUES (13,'2016-07-01',1,3,4);
INSERT INTO  division  ( id , date , divisionValue , apartment_id , tenant_id ) VALUES (14,'2016-07-01',0,4,4);

INSERT INTO  meterenergy  ( id , deactivation , description , main , serialNumber , unit , apartment_id, residence_id) VALUES (1,'2600-01-01','Główny',1,'0','kWh',NULL, 1);
INSERT INTO  meterenergy  ( id , deactivation , description , main , serialNumber , unit , apartment_id, residence_id ) VALUES (2,'2600-01-01','Czesc Wspolna',0,'00','kWh',1, 1);
INSERT INTO  meterenergy  ( id , deactivation , description , main , serialNumber , unit , apartment_id, residence_id ) VALUES (3,'2600-01-01','Piwnica',0,'1','kWh',2, 1);
INSERT INTO  meterenergy  ( id , deactivation , description , main , serialNumber , unit , apartment_id, residence_id ) VALUES (4,'2600-01-01','Parter',0,'2','kWh',3, 1);
INSERT INTO  meterenergy  ( id , deactivation , description , main , serialNumber , unit , apartment_id, residence_id ) VALUES (5,'2600-01-01','I pietro',0,'3','kWh',4, 1);
INSERT INTO  meterenergy  ( id , deactivation , description , main , serialNumber , unit , apartment_id, residence_id ) VALUES (6,'2600-01-01','2res',0,'53','kWh',5, 2);

INSERT INTO  meterwater  ( id , deactivation , description , main , serialNumber , unit , isWarmWater , apartment_id, residence_id ) VALUES (1,'2600-01-01','G?ówny',1,'00','m3','0',NULL, 1);
INSERT INTO  meterwater  ( id , deactivation , description , main , serialNumber , unit , isWarmWater , apartment_id, residence_id ) VALUES (2,'2600-01-01','Piwnica',0,'1','m3','0',2, 1);
INSERT INTO  meterwater  ( id , deactivation , description , main , serialNumber , unit , isWarmWater , apartment_id, residence_id ) VALUES (3,'2600-01-01','Piwnica ciepla',0,'11','m3','1',2, 1);
INSERT INTO  meterwater  ( id , deactivation , description , main , serialNumber , unit , isWarmWater , apartment_id, residence_id ) VALUES (4,'2600-01-01','Parter zimna',0,'22','m3','0',3, 1);
INSERT INTO  meterwater  ( id , deactivation , description , main , serialNumber , unit , isWarmWater , apartment_id, residence_id ) VALUES (5,'2600-01-01','Parter ciepla',0,'1111','m3','1',3, 1);
INSERT INTO  meterwater  ( id , deactivation , description , main , serialNumber , unit , isWarmWater , apartment_id, residence_id ) VALUES (6,'2600-01-01','I pietro zimna',0,'3','m3','0',4, 1);
INSERT INTO  meterwater  ( id , deactivation , description , main , serialNumber , unit , isWarmWater , apartment_id, residence_id ) VALUES (7,'2600-01-01','I pietro ciepla',0,'34','m3','1',4, 1);
INSERT INTO  meterwater  ( id , deactivation , description , main , serialNumber , unit , isWarmWater , apartment_id, residence_id ) VALUES (8,'2600-01-01','2res',0,'53','m3','1',5, 2);

INSERT INTO  metergas  ( id , deactivation , description , main , serialNumber , unit , cwu , apartment_id, residence_id ) VALUES (1,'2600-01-01','Główny',1,'00','m3','0',NULL, 1);
INSERT INTO  metergas  ( id , deactivation , description , main , serialNumber , unit , cwu , apartment_id, residence_id ) VALUES (2,'2600-01-01','Ogrzewanie',0,'0000','m3','0',1, 1);
INSERT INTO  metergas  ( id , deactivation , description , main , serialNumber , unit , cwu , apartment_id, residence_id ) VALUES (3,'2600-01-01','Piwnica',0,'1','m3','0',2, 1);
INSERT INTO  metergas  ( id , deactivation , description , main , serialNumber , unit , cwu , apartment_id, residence_id ) VALUES (4,'2600-01-01','Parter',0,'2','m3','0',3, 1);
INSERT INTO  metergas  ( id , deactivation , description , main , serialNumber , unit , cwu , apartment_id, residence_id ) VALUES (5,'2600-01-01','I pietro',0,'3','m3','0',4, 1);
INSERT INTO  metergas  ( id , deactivation , description , main , serialNumber , unit , cwu , apartment_id, residence_id ) VALUES (6,'2600-01-01','CWU',0,'000000','m3','1',1, 1);
INSERT INTO  metergas  ( id , deactivation , description , main , serialNumber , unit , cwu , apartment_id, residence_id ) VALUES (7,'2600-01-01','2res',0,'53','kWh','0',5, 2);


INSERT INTO  readinggas  ( id , readingDate , resolved , value , unit , meter_id ) VALUES (1,'2016-07-29','0',114,'m3',1);
INSERT INTO  readinggas  ( id , readingDate , resolved , value , unit , meter_id ) VALUES (2,'2016-07-29','0',1.5,'m3',2);
INSERT INTO  readinggas  ( id , readingDate , resolved , value , unit , meter_id ) VALUES (3,'2016-07-29','0',4.5,'m3',3);
INSERT INTO  readinggas  ( id , readingDate , resolved , value , unit , meter_id ) VALUES (4,'2016-07-29','0',3,'m3',4);
INSERT INTO  readinggas  ( id , readingDate , resolved , value , unit , meter_id ) VALUES (5,'2016-07-29','0',5,'m3',5);
INSERT INTO  readinggas  ( id , readingDate , resolved , value , unit , meter_id ) VALUES (6,'2016-07-29','0',100,'m3',6);



INSERT INTO  readinggas  ( id , readingDate , resolved , value , unit , meter_id ) VALUES (7,'2016-09-01','1',169,'m3',1);
INSERT INTO  readinggas  ( id , readingDate , resolved , value , unit , meter_id ) VALUES (8,'2016-09-01','1',2,'m3',2);
INSERT INTO  readinggas  ( id , readingDate , resolved , value , unit , meter_id ) VALUES (9,'2016-09-01','1',6,'m3',3);
INSERT INTO  readinggas  ( id , readingDate , resolved , value , unit , meter_id ) VALUES (10,'2016-09-01','1',4,'m3',4);
INSERT INTO  readinggas  ( id , readingDate , resolved , value , unit , meter_id ) VALUES (11,'2016-09-01','1',7,'m3',5);
INSERT INTO  readinggas  ( id , readingDate , resolved , value , unit , meter_id ) VALUES (12,'2016-09-01','1',150,'m3',6);

INSERT INTO  readinggas  ( id , readingDate , resolved , value , unit , meter_id ) VALUES (13,'2016-10-01','0',196,'m3',1);
INSERT INTO  readinggas  ( id , readingDate , resolved , value , unit , meter_id ) VALUES (14,'2016-10-01','0',2,'m3',2);
INSERT INTO  readinggas  ( id , readingDate , resolved , value , unit , meter_id ) VALUES (15,'2016-10-01','0',9,'m3',3);
INSERT INTO  readinggas  ( id , readingDate , resolved , value , unit , meter_id ) VALUES (16,'2016-10-01','0',5,'m3',4);
INSERT INTO  readinggas  ( id , readingDate , resolved , value , unit , meter_id ) VALUES (17,'2016-10-01','0',10,'m3',5);
INSERT INTO  readinggas  ( id , readingDate , resolved , value , unit , meter_id ) VALUES (18,'2016-10-01','0',170,'m3',6);
INSERT INTO  readinggas  ( id , readingDate , resolved , value , unit , meter_id ) VALUES (19,'2016-07-29','0',100,'m3',7);
---------

INSERT INTO  readingwater  ( id , readingDate , resolved , value , unit , meter_id ) VALUES (1,'2016-07-01','0',33,'m3',1);
INSERT INTO  readingwater  ( id , readingDate , resolved , value , unit , meter_id ) VALUES (2,'2016-07-01','0',4,'m3',2);
INSERT INTO  readingwater  ( id , readingDate , resolved , value , unit , meter_id ) VALUES (3,'2016-07-01','0',2,'m3',3);
INSERT INTO  readingwater  ( id , readingDate , resolved , value , unit , meter_id ) VALUES (4,'2016-07-01','0',5,'m3',4);
INSERT INTO  readingwater  ( id , readingDate , resolved , value , unit , meter_id ) VALUES (5,'2016-07-01','0',10,'m3',5);
INSERT INTO  readingwater  ( id , readingDate , resolved , value , unit , meter_id ) VALUES (6,'2016-07-01','0',6,'m3',6);
INSERT INTO  readingwater  ( id , readingDate , resolved , value , unit , meter_id ) VALUES (7,'2016-07-01','0',6,'m3',7);

INSERT INTO  readingwater  ( id , readingDate , resolved , value , unit , meter_id ) VALUES (8,'2016-08-01','1',46,'m3',1);
INSERT INTO  readingwater  ( id , readingDate , resolved , value , unit , meter_id ) VALUES (9,'2016-08-01','1',5,'m3',2);
INSERT INTO  readingwater  ( id , readingDate , resolved , value , unit , meter_id ) VALUES (10,'2016-08-01','1',3,'m3',3);
INSERT INTO  readingwater  ( id , readingDate , resolved , value , unit , meter_id ) VALUES (11,'2016-08-01','1',7,'m3',4);
INSERT INTO  readingwater  ( id , readingDate , resolved , value , unit , meter_id ) VALUES (12,'2016-08-01','1',12,'m3',5);
INSERT INTO  readingwater  ( id , readingDate , resolved , value , unit , meter_id ) VALUES (13,'2016-08-01','1',10,'m3',6);
INSERT INTO  readingwater  ( id , readingDate , resolved , value , unit , meter_id ) VALUES (14,'2016-08-01','1',7,'m3',7);

INSERT INTO  readingwater  ( id , readingDate , resolved , value , unit , meter_id ) VALUES (15,'2016-09-01','0',60,'m3',1);
INSERT INTO  readingwater  ( id , readingDate , resolved , value , unit , meter_id ) VALUES (16,'2016-09-01','0',6,'m3',2);
INSERT INTO  readingwater  ( id , readingDate , resolved , value , unit , meter_id ) VALUES (17,'2016-09-01','0',4,'m3',3);
INSERT INTO  readingwater  ( id , readingDate , resolved , value , unit , meter_id ) VALUES (18,'2016-09-01','0',10,'m3',4);
INSERT INTO  readingwater  ( id , readingDate , resolved , value , unit , meter_id ) VALUES (19,'2016-09-01','0',16,'m3',5);
INSERT INTO  readingwater  ( id , readingDate , resolved , value , unit , meter_id ) VALUES (20,'2016-09-01','0',11,'m3',6);
INSERT INTO  readingwater  ( id , readingDate , resolved , value , unit , meter_id ) VALUES (21,'2016-09-01','0',9,'m3',7);
INSERT INTO  readingwater  ( id , readingDate , resolved , value , unit , meter_id ) VALUES (22,'2016-09-01','0',9,'m3',8);
-------------------------------------------------
INSERT INTO  readingenergy  ( id , readingDate , resolved , value , unit , meter_id ) VALUES (1,'2016-07-01','0',11,'kWh',1);
INSERT INTO  readingenergy  ( id , readingDate , resolved , value , unit , meter_id ) VALUES (2,'2016-07-01','0',5,'kWh',2);
INSERT INTO  readingenergy  ( id , readingDate , resolved , value , unit , meter_id ) VALUES (3,'2016-07-01','0',1,'kWh',3);
INSERT INTO  readingenergy  ( id , readingDate , resolved , value , unit , meter_id ) VALUES (4,'2016-07-01','0',2,'kWh',4);
INSERT INTO  readingenergy  ( id , readingDate , resolved , value , unit , meter_id ) VALUES (5,'2016-07-01','0',3,'kWh',5);
INSERT INTO  readingenergy  ( id , readingDate , resolved , value , unit , meter_id ) VALUES (6,'2016-08-01','1',20,'kWh',1);
INSERT INTO  readingenergy  ( id , readingDate , resolved , value , unit , meter_id ) VALUES (7,'2016-08-01','1',8,'kWh',2);
INSERT INTO  readingenergy  ( id , readingDate , resolved , value , unit , meter_id ) VALUES (8,'2016-08-01','1',4,'kWh',3);
INSERT INTO  readingenergy  ( id , readingDate , resolved , value , unit , meter_id ) VALUES (9,'2016-08-01','1',3,'kWh',4);
INSERT INTO  readingenergy  ( id , readingDate , resolved , value , unit , meter_id ) VALUES (10,'2016-08-01','1',5,'kWh',5);
INSERT INTO  readingenergy  ( id , readingDate , resolved , value , unit , meter_id ) VALUES (11,'2016-09-01','0',31,'kWh',1);
INSERT INTO  readingenergy  ( id , readingDate , resolved , value , unit , meter_id ) VALUES (12,'2016-09-01','0',10,'kWh',2);
INSERT INTO  readingenergy  ( id , readingDate , resolved , value , unit , meter_id ) VALUES (13,'2016-09-01','0',5,'kWh',3);
INSERT INTO  readingenergy  ( id , readingDate , resolved , value , unit , meter_id ) VALUES (14,'2016-09-01','0',9,'kWh',4);
INSERT INTO  readingenergy  ( id , readingDate , resolved , value , unit , meter_id ) VALUES (15,'2016-09-01','0',7,'kWh',5);
INSERT INTO  readingenergy  ( id , readingDate , resolved , value , unit , meter_id ) VALUES (16,'2016-09-01','0',7,'kWh',6);

INSERT INTO  invoicewater  ( id , date , serialNumber , totalAmount , baseReading_id , residence_id) VALUES (1,'2016-08-01','123',150,8,1);
INSERT INTO  invoicegas  ( id , date , serialNumber , totalAmount , baseReading_id, residence_id) VALUES (1,'2016-09-01','123',150,7,1);
INSERT INTO  invoiceenergy  ( id , date , serialNumber , totalAmount , baseReading_id, residence_id) VALUES (1,'2016-08-01','1',200,6,1);


INSERT INTO  paymentenergy  ( id , paymentAmount , paymentDate , tenant_id , invoice_id) VALUES (1,88.67,'2016-07-29',1,1);
INSERT INTO  paymentenergy  ( id , paymentAmount , paymentDate , tenant_id , invoice_id) VALUES (2,66.44,'2016-07-29',3,1);
INSERT INTO  paymentenergy  ( id , paymentAmount , paymentDate , tenant_id , invoice_id) VALUES (3,44.22,'2016-07-29',4,1);

INSERT INTO  paymentgas  ( id , paymentAmount , paymentDate , tenant_id , invoice_id ) VALUES (1,38.63,'2016-07-29',1,1);
INSERT INTO  paymentgas  ( id , paymentAmount , paymentDate , tenant_id , invoice_id ) VALUES (2,40,'2016-07-29',3,1);
INSERT INTO  paymentgas  ( id , paymentAmount , paymentDate , tenant_id , invoice_id ) VALUES (3,71.36,'2016-07-29',4,1);

INSERT INTO  paymentwater  ( id , paymentAmount , paymentDate , tenant_id , invoice_id ) VALUES (1,27.27,'2016-07-29',1,1);
INSERT INTO  paymentwater  ( id , paymentAmount , paymentDate , tenant_id , invoice_id ) VALUES (2,68.18,'2016-07-29',3,1);
INSERT INTO  paymentwater  ( id , paymentAmount , paymentDate , tenant_id , invoice_id ) VALUES (3,54.55,'2016-07-29',4,1);


INSERT INTO  settings  ( id , correctDivision , garbage , gas , internet , waterHeatingSystem ) VALUES (1,'1','0','1','0','SHARED_GAS');
