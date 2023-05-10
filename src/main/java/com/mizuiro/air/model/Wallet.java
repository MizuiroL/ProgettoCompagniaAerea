package com.mizuiro.air.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "wallet")
public class Wallet {
	@Id
	@Column(name = "wallet_id")
	private Integer walletId;
	
	@Column(name = "balance")
	private Double balance;
	
	@OneToOne(mappedBy = "wallet")
	private Customer owner;
	
	public Wallet() {
		
	}

	public Wallet(Double balance) {
		this.balance = balance;
	}
	
	public Integer getWalletId() {
		return walletId;
	}

	public void setWalletId(Integer walletId) {
		this.walletId = walletId;
	}

	public Customer getOwner() {
		return owner;
	}

	public void setOwner(Customer owner) {
		this.owner = owner;
	}
	
	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public void add(Double amount) {
		balance += amount;
	}

	public boolean charge(Double amount) {
		boolean enoughBalance = balance >= amount; 
		if (enoughBalance) {
			balance -= amount;
			System.out.println("Wallet: charged " + amount + ". New balance: " + balance);
		}
		return enoughBalance;
	}
}