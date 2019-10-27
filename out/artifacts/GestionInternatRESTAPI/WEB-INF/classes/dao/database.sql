CREATE TABLE AppUSER(
  IdUser int primary key AUTO_INCREMENT NOT NULL,
  NameUser varchar(150) NOT NULL,
  passwordUser varchar(255) NOT NULL,
  EmailUser varchar(200)  NOT NULL,
  PhoneUser varchar(20) NOT NULL,
  CINUser varchar(100)
);

CREATE TABLE Administrator(
  IdAdmin int primary key auto_increment not null,
  ImmatriculeAdmin int not null,
  IdUser int not null
);

CREATE TABLE Student(
  IdStudent int primary key auto_increment not null,
  Level varchar(100) not null,
  CNE varchar(100) not null,
  IdUser int not null
);



CREATE TABLE Batiment(
    IdBatiment int primary key auto_increment not null,
    gender char not null,
    NBEtage int not null
);

CREATE TABLE Etage(
  IdEtage int primary key auto_increment not null,
  CodeEtage varchar(100) not null,
  NbChambres int not null,
  IdBatiment int not null
);

CREATE TABLE Chambre(
  IdChambre int primary key not null AUTO_INCREMENT,
  CodeChambre varchar(20),
  IdEtage int not null
);

CREATE TABLE Inscription(
  IdInscription int primary key AUTO_INCREMENT NOT NULL,
  DateInscription Date not null default current_date,
  DateDebut DATE not null,
  Duree int not null,
  IdChambre int not null,
  IdStudent int not null;
);

CREATE TABLE ChambreIndiv(
  IdChambreIndiv int primary key auto_increment not null,
  EtatChambre boolean not null,
  IdChambre int not null
);

CREATE TABLE ChambreA2(
  IdChambreA2 int primary key auto_increment not null,
  EtaPlace1 boolean not null,
  EtaPlace2 boolean not null,
  IdChambre int not null
);

CREATE TABLE ChambreA3(
  IdChambreA3 int primary key auto_increment not null,
  EtaPlace1 boolean not null,
  EtaPlace2 boolean not null,
  EtaPlace3 boolean not null,
  IdChambre int not null
);


CREATE TABLE ChambreA4(
  IdChambreA3 int primary key auto_increment not null,
  EtaPlace1 boolean not null,
  EtaPlace2 boolean not null,
  EtaPlace3 boolean not null,
  IdChambre int not null
);

ALTER TABLE Administrator ADD FOREIGN KEY (IdUser) REFERENCES AppUSER(IdUser);
ALTER TABLE Student ADD FOREIGN KEY (IdUser) REFERENCES AppUSER(IdUser);
ALTER TABLE Etage ADD FOREIGN KEY (IdBatiment) REFERENCES Batiment(IdBatiment);
ALTER TABLE Inscription ADD FOREIGN KEY (IdChambre) REFERENCES Chambre(IdChambre);
ALTER TABLE Inscription ADD FOREIGN KEY (IdStudent) REFERENCES Student(IdStudent);
ALTER TABLE Chambre ADD FOREIGN KEY (IdEtage) REFERENCES Etage(IdEtage);
ALTER TABLE ChambreIndiv ADD FOREIGN KEY (IdChambre) REFERENCES Chambre(IdChambre);

ALTER TABLE ChambreA2 ADD FOREIGN KEY (IdChambre) REFERENCES Chambre(IdChambre);
ALTER TABLE ChambreA3 ADD FOREIGN KEY (IdChambre) REFERENCES Chambre(IdChambre);
ALTER TABLE ChambreA4 ADD FOREIGN KEY (IdChambre) REFERENCES Chambre(IdChambre);

/**
TODO : AminTypes,Reclamations
 */






