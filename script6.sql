create database if not exists dbproduits; 

use dbproduits; 

create table if not exists PRODUIT
(
	numProd int primary key auto_increment,
	description varchar(40),
	prixAchat decimal(10,2),
	prixVente decimal(10,2) 
);

insert into PRODUIT values (null,'Cuisinière',1200.80,1500.70);
insert into PRODUIT values (null,'Four micro-ondes',550.50,760.00);
insert into PRODUIT values (null,'Four à vapeur',2300.80,3000.70);
insert into PRODUIT values (null,'Cuisinière à gaz',850.50,1260.00);
insert into PRODUIT values (null,'Lave-vaisselle',2460.80,2800.00);
insert into PRODUIT values (null,'Sèche-linge',350.50,660.99);
insert into PRODUIT values (null,'Raisin',22.80,29.70);
insert into PRODUIT values (null,'Cerises',30.99,35.00);
insert into PRODUIT values (null,'Céleri branche',32.40,35.70);
insert into PRODUIT values (null,'Pomme',15.50,19.00);
insert into PRODUIT values (null,'Orange',10.80,13.00);
insert into PRODUIT values (null,'Abricot',30.50,33.00);
insert into PRODUIT values (null,'Tomates',7.50,10.00);
insert into PRODUIT values (null,'Carottes','Legumes',2.80,4.00);
insert into PRODUIT values (null,'Pois',10.50,13.00);
insert into PRODUIT values (null,'Sel',5.50,7.00);
insert into PRODUIT values (null,'Poivres',6.50,8.50);
insert into PRODUIT values (null,'Epices',2.80,4.00);
insert into PRODUIT values (null,'Vanille',1.50,3.00);
insert into PRODUIT values (null,'Huiles',17.50,20.00);

