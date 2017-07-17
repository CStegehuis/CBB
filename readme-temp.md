Docker

Stap 1: De Docker image run je op je machine en mount hierbij 2 folders binnen de Docker. File 1 (database file),
file 2 (input csv files).

Commant:
docker run --name mysql -v /home/cstegehuis/IdeaProjects/ocict_cases/data:/var/lib/mysql -v /home/cstegehuis/IdeaProjects/ocict_cases/input:/var/lib/mysql-files -p 3306:3306 -e MYSQL_ROOT_PASSWORD='password123' -e MYSQL_ROOT_HOST='172.17.0.1' -d mysql:latest

Stap 2: Om te zien of de Docker draait en wat het id van de container is, draai;

Commant:
docker ps

Stap 3: Start je Docker container nu met het command;

Commant:
docker start <container-id>

Voor het stoppen van de Docker:
docker stop <container-id>


Om de MySql tables aan te maken en te vullen:
mysql -h 127.0.0.1 -P 3306 --protocol=tcp  -u root mysql -p

- ip: 127.0.0.1
- usern: root
- passw: password123





create table leveranciers (
lev_code INT NOT NULL,
lev_naam VARCHAR(255) NOT NULL,
lev_adres VARCHAR(255),
lev_woonplaats VARCHAR(255),
PRIMARY KEY (lev_code)
)



create table planten ( art_code INT NOT NULL, plant_naam VARCHAR(255) NOT NULL, soort VARCHAR(255) NOT NULL, kleur VARCHAR(255) NULL, hoogte INT NULL, bloei_beg INT NULL, bloei_eind INT NULL, prijs DOUBLE NOT NULL, vrr_aantal INT NOT NULL, vrr_min INT NOT NULL, PRIMARY KEY (art_code) );


create table bestelregels ( bestel_nr INT NOT NULL, art_code INT NOT NULL, aantal INT NOT NULL, bestel_prijs DOUBLE NOT NULL, PRIMARY KEY (bestel_nr, art_code) );

create table bestellingen (
bestel_nr INT NOT NULL,
lev_code INT NOT NULL,
bestel_datum DATE NOT NULL,
lever_datum DATE NOT NULL,
bedrag DOUBLE NOT NULL,
status CHAR(10) NOT NULL,
PRIMARY KEY (bestel_nr),
FOREIGN KEY (lev_code) REFERENCES leveranciers (lev_code)
);



LOAD DATA INFILE '/var/lib/mysql-files/bestellingen.csv' INTO TABLE bestellingen FIELDS TERMINATED BY ';' ENCLOSED BY '"' LINES TERMINATED BY '\n' IGNORE 1 ROWS (bestel_nr,lev_code,@bestel_datum,@lever_datum,bedrag,status) SET bestel_datum = STR_TO_DATE(@bestel_datum, '%d-%m-%Y'), lever_datum = STR_TO_DATE(@lever_datum, '%d-%m-%Y')
