package com.ismo.crjj.metier;

import java.util.List;

import com.ismo.crjj.dao.DaoProduit;
import com.ismo.crjj.dao.Idao;
import com.ismo.crjj.model.Produit;

public class MetierProduit implements IMetier<Produit> {

	Idao<Produit> dao = (Idao<Produit>) new DaoProduit();
	
	@Override
	public List<Produit> getAll() {
		// TODO Auto-generated method stub
		return dao.getAll();
	}

	@Override
	public Produit getOne(int id) {
		// TODO Auto-generated method stub
		return dao.getOne(id);
	}

	@Override
	public boolean save(Produit obj) {
		// TODO Auto-generated method stub
		return dao.save(obj);
	}

	@Override
	public boolean update(Produit obj) {
		// TODO Auto-generated method stub
		return dao.update(obj);
	}

	@Override
	public boolean delete(Produit obj) {
		// TODO Auto-generated method stub
		return dao.delete(obj);
	}

}
