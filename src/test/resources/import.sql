
insert into RESIDENCE(id, street, number, city) values (1, 'Swietojanska', '45', 'Gdynia');
insert into RESIDENCE(id, street, number, city) values (2, 'Pusta', '45', 'Gdynia');

insert into APARTMENT(id, apartmentNumber, description, intercom, residence_id) values(1, 0, 'Część Wspólna','6666', 1);
insert into APARTMENT(id, apartmentNumber, description, intercom, residence_id) values(2, 1, 'Piwnica','6666', 1);
insert into APARTMENT(id, apartmentNumber, description, intercom, residence_id) values(3, 2, 'Parter','6666', 1);
insert into APARTMENT(id, apartmentNumber, description, intercom, residence_id) values(4, 3, 'I Piętro','6666', 1);
insert into APARTMENT(id, apartmentNumber, description, intercom, residence_id) values(6, 4, 'II Piętro puste','6666', 1);
insert into APARTMENT(id, apartmentNumber, description, intercom, residence_id) values(5, 2, 'Mieszkanie z drugiej rezydencji','6666', 2);


insert into RENT_CONTRACT(id, apartment_id, rentCost, contractStart, contractEnd) VALUES (1, 2, 0, '2016-07-25', '2100-01-01');
insert into RENT_CONTRACT(id, apartment_id, rentCost, contractStart, contractEnd) VALUES (2, 2, 10, '2010-07-29','2016-07-24');
insert into RENT_CONTRACT(id, apartment_id, rentCost, contractStart, contractEnd) VALUES (3, 3, 20, '2014-07-01', '2015-01-01');
insert into RENT_CONTRACT(id, apartment_id, rentCost, contractStart, contractEnd) VALUES (4, 3, 30, '2016-07-29', '2100-01-01');
insert into RENT_CONTRACT(id, apartment_id, rentCost, contractStart, contractEnd) VALUES (5, 4, 25, '2015-07-26', '2100-01-01');
insert into RENT_CONTRACT(id, apartment_id, rentCost, contractStart, contractEnd) VALUES (6, 5, 200, '2010-07-29', '2100-01-01');
insert into RENT_CONTRACT(id, apartment_id, rentCost, contractStart, contractEnd) VALUES (7, null, 200, '2010-07-29', '2100-01-01');

INSERT INTO  TENANT  ( id , email , firstName , lastName, password , phone , role, rentcontract_id) VALUES (1,'owner@res1','LivesAndOwns','1Residence','witaj','530081187','OWNER', 1);
INSERT INTO  TENANT  ( id , email , firstName , lastName, password , phone , role, rentcontract_id) VALUES (5,'klej@wp.pl','Piotr','Kulej','witaj','23526564','TENANT', 2);

INSERT INTO  TENANT  ( id , email , firstName , lastName, password , phone , role, rentcontract_id) VALUES (2,'kow@wp.pl','Andrzej','Kowalski','witaj','4456','TENANT', 3);
INSERT INTO  TENANT  ( id , email , firstName , lastName, password , phone , role, rentcontract_id) VALUES (4,'kasia@wp.pl','Kasia','Kowalska','witaj','3456775','TENANT', 4);

INSERT INTO  TENANT  ( id , email , firstName , lastName, password , phone , role, rentcontract_id) VALUES (3,'par@wp.pl','Kasia','Para','witaj','23636','TENANT', 5);

INSERT INTO  TENANT  ( id , email , firstName , lastName, password , phone , role, rentcontract_id) VALUES (6,'tenant@rez2','Tenant','2Residence','witaj','23526564','TENANT', 6);
INSERT INTO  TENANT  ( id , email , firstName , lastName, password , phone , role, rentcontract_id) VALUES (7,'owner@rez2','Owner','2Residence','witaj','23526564','OWNER', 7);



INSERT INTO RESIDENCE_OWNERSHIP (id, owner_id, residence_id)  VALUES (1,1,1);
INSERT INTO RESIDENCE_OWNERSHIP (id, owner_id, residence_id)  VALUES (2,7,2);

INSERT INTO  METER  ( id , status , description , main , serialNumber , unit , apartment_id, residence_id, media, isWarmWater, cwu ) VALUES (1,'ACTIVE','Energia Główny',1,'0','kWh',NULL, 1, 'ENERGY', '0', '0');
INSERT INTO  METER  ( id , status , description , main , serialNumber , unit , apartment_id, residence_id, media, isWarmWater, cwu  ) VALUES (2,'ACTIVE','E Czesc Wspolna',0,'00','kWh',1, 1, 'ENERGY', '0', '0');
INSERT INTO  METER  ( id , status , description , main , serialNumber , unit , apartment_id, residence_id, media, isWarmWater, cwu  ) VALUES (3,'ACTIVE','E Piwnica',0,'1','kWh',2, 1, 'ENERGY', '0', '0');
INSERT INTO  METER  ( id , status , description , main , serialNumber , unit , apartment_id, residence_id, media, isWarmWater, cwu  ) VALUES (4,'ACTIVE','E Parter',0,'2','kWh',3, 1, 'ENERGY', '0', '0');
INSERT INTO  METER  ( id , status , description , main , serialNumber , unit , apartment_id, residence_id, media, isWarmWater, cwu  ) VALUES (5,'ACTIVE','E I pietro',0,'3','kWh',4, 1, 'ENERGY', '0', '0');
INSERT INTO  METER  ( id , status , description , main , serialNumber , unit , apartment_id, residence_id, media, isWarmWater , cwu ) VALUES (6,'ACTIVE','E 2res',0,'53','kWh',5, 2, 'ENERGY', '0', '0');

INSERT INTO  METER  ( id , status , description , main , serialNumber , unit , isWarmWater , apartment_id, residence_id, media, cwu ) VALUES (7,'ACTIVE','Woda G?ówny',1,'wg','m3','0',NULL, 1, 'WATER', '0');
INSERT INTO  METER  ( id , status , description , main , serialNumber , unit , isWarmWater , apartment_id, residence_id, media, cwu ) VALUES (8,'ACTIVE','Woda Piwnica',0,'wp','m3','0',2, 1, 'WATER', '0');
INSERT INTO  METER  ( id , status , description , main , serialNumber , unit , isWarmWater , apartment_id, residence_id, media, cwu ) VALUES (9,'ACTIVE','Piwnica ciepla',0,'wpiwc','m3','1',2, 1, 'WATER', '0');
INSERT INTO  METER  ( id , status , description , main , serialNumber , unit , isWarmWater , apartment_id, residence_id, media, cwu ) VALUES (10,'ACTIVE','Parter zimna',0,'wparz','m3','0',3, 1, 'WATER', '0');
INSERT INTO  METER  ( id , status , description , main , serialNumber , unit , isWarmWater , apartment_id, residence_id, media, cwu ) VALUES (11,'ACTIVE','Parter ciepla',0,'wparc','m3','1',3, 1, 'WATER', '0');
INSERT INTO  METER  ( id , status , description , main , serialNumber , unit , isWarmWater , apartment_id, residence_id, media, cwu ) VALUES (12,'ACTIVE','I pietro zimna',0,'1pz','m3','0',4, 1, 'WATER', '0');
INSERT INTO  METER  ( id , status , description , main , serialNumber , unit , isWarmWater , apartment_id, residence_id, media, cwu ) VALUES (13,'ACTIVE','I pietro ciepla',0,'1pc','m3','1',4, 1, 'WATER', '0');
INSERT INTO  METER  ( id , status , description , main , serialNumber , unit , isWarmWater , apartment_id, residence_id, media, cwu ) VALUES (14,'ACTIVE','W 2res woda    ',0,'2resw','m3','0',5, 2, 'WATER', '0');

INSERT INTO  METER  ( id , status , description , main , serialNumber , unit , cwu , apartment_id, residence_id, media, isWarmWater  ) VALUES (15,'ACTIVE','Gaz Główny',1,'ser1','m3','0',NULL, 1, 'GAS', '0');
INSERT INTO  METER  ( id , status , description , main , serialNumber , unit , cwu , apartment_id, residence_id, media, isWarmWater  ) VALUES (16,'ACTIVE','G Ogrzewanie',0,'gaz2','m3','0',1, 1, 'GAS', '0');
INSERT INTO  METER  ( id , status , description , main , serialNumber , unit , cwu , apartment_id, residence_id, media, isWarmWater  ) VALUES (17,'ACTIVE','G Piwnica',0,'gaz3','m3','0',2, 1, 'GAS', '0');
INSERT INTO  METER  ( id , status , description , main , serialNumber , unit , cwu , apartment_id, residence_id, media , isWarmWater ) VALUES (18,'ACTIVE','G Parter',0,'gaz4','m3','0',3, 1, 'GAS', '0');
INSERT INTO  METER  ( id , status , description , main , serialNumber , unit , cwu , apartment_id, residence_id, media, isWarmWater  ) VALUES (19,'ACTIVE','G I pietro',0,'gaz5','m3','0',4, 1, 'GAS', '0');
INSERT INTO  METER  ( id , status , description , main , serialNumber , unit , cwu , apartment_id, residence_id, media , isWarmWater ) VALUES (20,'ACTIVE','CWU',0,'gaz6','m3','1',null, 1, 'GAS', '0');
INSERT INTO  METER  ( id , status , description , main , serialNumber , unit , cwu , apartment_id, residence_id, media , isWarmWater ) VALUES (21,'ACTIVE','G 2res',0,'gaz7','m3','0',5, 2, 'GAS', '0');



INSERT INTO  READING_DETAILS (id, readingDate, resolvement, media, residence_id) VALUES (1,'2016-07-29','UNRESOLVED','GAS', 1);
INSERT INTO  READING_DETAILS (id, readingDate, resolvement, media, residence_id) VALUES (2, '2016-09-01','RESOLVED','GAS',1);
INSERT INTO  READING_DETAILS (id, readingDate, resolvement, media, residence_id) VALUES (3,'2016-10-01','UNRESOLVED','GAS',1);
INSERT INTO  READING_DETAILS (id, readingDate, resolvement, media, residence_id) VALUES (11,'2016-10-01','UNRESOLVED','GAS',2);


INSERT INTO READING ( id , readingDetails_id,  value , meter_id, residence_id ) VALUES (1,1,114,15, 1);
INSERT INTO READING ( id , readingDetails_id,  value , meter_id, residence_id ) VALUES (2,1,1.5,16, 1);
INSERT INTO READING ( id , readingDetails_id,  value , meter_id, residence_id ) VALUES (3,1,4.5,17, 1);
INSERT INTO READING ( id , readingDetails_id,  value , meter_id, residence_id ) VALUES (4,1,3,18, 1);
INSERT INTO READING ( id , readingDetails_id,  value , meter_id, residence_id ) VALUES (5,1, 5,19, 1);
INSERT INTO READING ( id , readingDetails_id,  value , meter_id, residence_id ) VALUES (6,1, 100,20, 1);

INSERT INTO READING ( id , readingDetails_id,  value , meter_id, residence_id ) VALUES (7,2,169,15, 1);
INSERT INTO READING ( id , readingDetails_id,  value , meter_id, residence_id ) VALUES (8,2,2,16, 1);
INSERT INTO READING ( id , readingDetails_id,  value , meter_id, residence_id ) VALUES (9,2,6,17, 1);
INSERT INTO READING ( id , readingDetails_id,  value , meter_id, residence_id ) VALUES (10,2,4,18, 1);
INSERT INTO READING ( id , readingDetails_id,  value , meter_id, residence_id ) VALUES (11,2,7,19, 1);
INSERT INTO READING ( id , readingDetails_id,  value , meter_id, residence_id ) VALUES (12,2,150,20, 1);

INSERT INTO READING ( id , readingDetails_id,  value , meter_id, residence_id ) VALUES (13,3,196,15, 1);
INSERT INTO READING ( id , readingDetails_id,  value , meter_id, residence_id ) VALUES (14,3,2,16, 1);
INSERT INTO READING ( id , readingDetails_id,  value , meter_id, residence_id ) VALUES (15,3,9,17, 1);
INSERT INTO READING ( id , readingDetails_id,  value , meter_id, residence_id ) VALUES (16,3,5,18, 1);
INSERT INTO READING ( id , readingDetails_id,  value , meter_id, residence_id ) VALUES (17,3,10,19, 1);
INSERT INTO READING ( id , readingDetails_id,  value , meter_id, residence_id ) VALUES (18,3,170,20, 1);
INSERT INTO READING ( id , readingDetails_id,  value , meter_id, residence_id ) VALUES (19,11,100,21, 2);

INSERT INTO  READING_DETAILS (id, readingDate, resolvement, media, residence_id) VALUES (4,'2016-07-01','UNRESOLVED','WATER', 1);
INSERT INTO  READING_DETAILS (id, readingDate, resolvement, media, residence_id) VALUES (5, '2016-08-01','RESOLVED','WATER',1);
INSERT INTO  READING_DETAILS (id, readingDate, resolvement, media, residence_id) VALUES (6,'2016-09-01','UNRESOLVED','WATER',1);
INSERT INTO  READING_DETAILS (id, readingDate, resolvement, media, residence_id) VALUES (12,'2016-09-01','UNRESOLVED','WATER',2);

INSERT INTO READING ( id , readingDetails_id,  value , meter_id, residence_id ) VALUES (20,4, 33,7, 1);
INSERT INTO READING ( id , readingDetails_id,  value , meter_id, residence_id ) VALUES (21,4,4,8, 1);
INSERT INTO READING ( id , readingDetails_id,  value , meter_id, residence_id ) VALUES (22,4,2,9, 1);
INSERT INTO READING ( id , readingDetails_id,  value , meter_id, residence_id ) VALUES (23,4,5,10, 1);
INSERT INTO READING ( id , readingDetails_id,  value , meter_id, residence_id ) VALUES (24,4,10,11, 1);
INSERT INTO READING ( id , readingDetails_id,  value , meter_id, residence_id ) VALUES (25,4,6,12, 1);
INSERT INTO READING ( id , readingDetails_id,  value , meter_id, residence_id ) VALUES (26,4,6,13, 1);

INSERT INTO READING ( id , readingDetails_id,  value , meter_id, residence_id ) VALUES (27,5,46,7, 1);
INSERT INTO READING ( id , readingDetails_id,  value , meter_id, residence_id ) VALUES (28,5,5,8, 1);
INSERT INTO READING ( id , readingDetails_id,  value , meter_id, residence_id ) VALUES (29,5,3,9, 1);
INSERT INTO READING ( id , readingDetails_id,  value , meter_id, residence_id ) VALUES (30,5,7,10, 1);
INSERT INTO READING ( id , readingDetails_id,  value , meter_id, residence_id ) VALUES (31,5,12,11, 1);
INSERT INTO READING ( id , readingDetails_id,  value , meter_id, residence_id ) VALUES (32,5,10,12, 1);
INSERT INTO READING ( id , readingDetails_id,  value , meter_id, residence_id ) VALUES (33,5,7,13, 1);

INSERT INTO READING ( id , readingDetails_id,  value , meter_id, residence_id ) VALUES (34,6,60,7, 1);
INSERT INTO READING ( id , readingDetails_id,  value , meter_id, residence_id ) VALUES (35,6,6,8, 1);
INSERT INTO READING ( id , readingDetails_id,  value , meter_id, residence_id ) VALUES (36,6,4,9, 1);
INSERT INTO READING ( id , readingDetails_id,  value , meter_id, residence_id ) VALUES (37,6,10,10, 1);
INSERT INTO READING ( id , readingDetails_id,  value , meter_id, residence_id ) VALUES (38,6,16,11, 1);
INSERT INTO READING ( id , readingDetails_id,  value , meter_id, residence_id ) VALUES (39,6,11,12, 1);
INSERT INTO READING ( id , readingDetails_id,  value , meter_id, residence_id ) VALUES (40,6,9,13, 1);
INSERT INTO READING ( id , readingDetails_id,  value , meter_id, residence_id ) VALUES (41,12,9,14, 2);

INSERT INTO  READING_DETAILS (id, readingDate, resolvement, media, residence_id) VALUES (7,'2016-07-01','UNRESOLVED','ENERGY', 1);
INSERT INTO  READING_DETAILS (id, readingDate, resolvement, media, residence_id) VALUES (8, '2016-08-01','RESOLVED','ENERGY',1);
INSERT INTO  READING_DETAILS (id, readingDate, resolvement, media, residence_id) VALUES (9,'2016-09-01','UNRESOLVED','ENERGY',1);
INSERT INTO  READING_DETAILS (id, readingDate, resolvement, media, residence_id) VALUES (13,'2016-09-01','UNRESOLVED','ENERGY',2);

INSERT INTO READING ( id , readingDetails_id,  value , meter_id, residence_id ) VALUES (42,7,11,1, 1);
INSERT INTO READING ( id , readingDetails_id,  value , meter_id, residence_id ) VALUES (43,7,5,2, 1);
INSERT INTO READING ( id , readingDetails_id,  value , meter_id, residence_id ) VALUES (44,7,1,3, 1);
INSERT INTO READING ( id , readingDetails_id,  value , meter_id, residence_id ) VALUES (45,7,2,4, 1);
INSERT INTO READING ( id , readingDetails_id,  value , meter_id, residence_id ) VALUES (46,7,3,5, 1);

INSERT INTO READING ( id , readingDetails_id,  value , meter_id, residence_id ) VALUES (47,8,20,1, 1);
INSERT INTO READING ( id , readingDetails_id,  value , meter_id, residence_id ) VALUES (48,8,8,2, 1);
INSERT INTO READING ( id , readingDetails_id,  value , meter_id, residence_id ) VALUES (49,8,4,3, 1);
INSERT INTO READING ( id , readingDetails_id,  value , meter_id, residence_id ) VALUES (50,8,3,4, 1);
INSERT INTO READING ( id , readingDetails_id,  value , meter_id, residence_id ) VALUES (51,8,5,5, 1);

INSERT INTO READING ( id , readingDetails_id,  value , meter_id, residence_id ) VALUES (52,9,31,1, 1);
INSERT INTO READING ( id , readingDetails_id,  value , meter_id, residence_id ) VALUES (53,9,10,2, 1);
INSERT INTO READING ( id , readingDetails_id,  value , meter_id, residence_id ) VALUES (54,9,5,3, 1);
INSERT INTO READING ( id , readingDetails_id,  value , meter_id, residence_id ) VALUES (55,9,9,4, 1);
INSERT INTO READING ( id , readingDetails_id,  value , meter_id, residence_id ) VALUES (56,9,7,5, 1);
INSERT INTO READING ( id , readingDetails_id,  value , meter_id, residence_id ) VALUES (57,13,7,6, 2);

--String serialNumber, LocalDate invoiceDate, double totalAmount, Residence residence, ReadingDetails readingDetails, Media media
INSERT INTO INVOICE  ( id , invoiceDate , serialNumber , totalAmount , readingDetails_id , residence_id, media) VALUES (1,'2016-08-01','1',150,5,1, 'WATER');
INSERT INTO INVOICE  ( id , invoiceDate , serialNumber , totalAmount , readingDetails_id, residence_id, media) VALUES (2,'2016-09-01','2',150,2,1, 'GAS');
INSERT INTO INVOICE  ( id , invoiceDate , serialNumber , totalAmount , readingDetails_id, residence_id, media) VALUES (3,'2016-08-01','3',200,8,1, 'ENERGY');


INSERT INTO PAYMENT ( id , paymentAmount , paymentDate , tenant_id , invoice_id) VALUES (1,88.67,'2016-07-29',1,3);
INSERT INTO PAYMENT ( id , paymentAmount , paymentDate , tenant_id , invoice_id) VALUES (2,66.44,'2016-07-29',3,3);
INSERT INTO PAYMENT ( id , paymentAmount , paymentDate , tenant_id , invoice_id) VALUES (3,44.22,'2016-07-29',4,3);

INSERT INTO PAYMENT ( id , paymentAmount , paymentDate , tenant_id , invoice_id ) VALUES (4,38.63,'2016-07-29',1,2);
INSERT INTO PAYMENT ( id , paymentAmount , paymentDate , tenant_id , invoice_id ) VALUES (5,40.00,'2016-07-29',3,2);
INSERT INTO PAYMENT ( id , paymentAmount , paymentDate , tenant_id , invoice_id ) VALUES (6,71.36,'2016-07-29',4,2);

INSERT INTO PAYMENT  ( id , paymentAmount , paymentDate , tenant_id , invoice_id ) VALUES (7,27.27,'2016-07-29',1,1);
INSERT INTO PAYMENT  ( id , paymentAmount , paymentDate , tenant_id , invoice_id ) VALUES (8,68.18,'2016-07-29',3,1);
INSERT INTO PAYMENT  ( id , paymentAmount , paymentDate , tenant_id , invoice_id ) VALUES (9,54.55,'2016-07-29',4,1);


INSERT INTO SETTINGS  ( id, waterHeatingSystem, residence_id ) VALUES (1,'SHARED_GAS', 1);
