package com.mizuiro.air.dao;

import java.util.List;

import com.mizuiro.air.model.Wallet;

import jakarta.ejb.Stateful;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateful
public class WalletEntityManager {
	@PersistenceContext(unitName = "airdb")
	EntityManager entityManager;
	
	public void insertWallet(Wallet wallet) {
		entityManager.persist(wallet);
	}

	public List<Wallet> getAllWallets() {
		Query query = entityManager.createQuery("SELECT w FROM wallet w");
		return query.getResultList();
	}

	public Wallet getWallet(Integer id) {
		return entityManager.find(Wallet.class, id);
	}
	
	public void updateWallet(Wallet wallet) {
		entityManager.merge(wallet);
	}
	
	public void deleteWallet(Wallet wallet) {
		entityManager.remove(wallet);
	}
}

