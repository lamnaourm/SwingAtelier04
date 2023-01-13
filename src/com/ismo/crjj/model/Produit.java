package com.ismo.crjj.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data
public class Produit {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	int numProd; 
	
	@Column
	String description;
	
	@Column
	double prixAchat;
	
	@Column
	double prixVente;

}
