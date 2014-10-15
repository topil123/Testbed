create table Detector(
  detectorid varchar(15),
  description   varchar(50),
  status  varchar(10),
  constraint pk_detector primary key(detectorid)
);


create table AirConditioning(
   acid varchar(15),
   description varchar(50),
   controlmode varchar(10),
   status varchar(10),
  constraint pk_ac primary key(acid)
);


create table ACSetting(
   settingid int PRIMARY key,
   acid varchar(15),
   starttime TIMESTAMP,
   endtime TIMESTAMP,
   temperature int,
   dayofweek int,
  constraint fk_ac_setting foreign key (acid) references airconditioning(acid)
);

CREATE SEQUENCE acsetting_sequence
START WITH 1
INCREMENT BY 1;

CREATE OR REPLACE TRIGGER acsetting_trigger
BEFORE INSERT ON acsetting
FOR EACH ROW
BEGIN
SELECT acsetting_sequence.nextval INTO :NEW.SETTINGID FROM dual;
END;
/

CREATE TABLE USERS(
userid varchar(20) primary key,
name  varchar(20) ,
password  varchar(50)
);

create table Sprinkler(
   sprinklerid varchar(15) primary key,
   description varchar(50),
   controlmode varchar(10),
   status varchar(10)
);

create table SprinklerSetting(
   settingid int PRIMARY key,
   sprinklerid varchar(15),
   starttime TIMESTAMP,
   endtime TIMESTAMP,
   dayofweek int,
  constraint fk_sprinkler_setting foreign key (sprinklerid) references sprinkler(sprinklerid)
);

CREATE SEQUENCE sprinklersetting_sequence
START WITH 1
INCREMENT BY 1;

CREATE OR REPLACE TRIGGER sprinklersetting_trigger
BEFORE INSERT ON sprinklersetting
FOR EACH ROW
BEGIN
SELECT sprinklersetting_sequence.nextval INTO :NEW.SETTINGID FROM dual;
END;
/
