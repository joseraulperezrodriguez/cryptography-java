package com.cryptography.home.model;

import java.security.PrivateKey;
import java.security.PublicKey;

public class OpenKeySig {
		
	private PublicKey publicK;
	
	private PrivateKey privateK;

	public OpenKeySig() { }
	
	public OpenKeySig(PublicKey publicK, PrivateKey privateK) {
		super();
		this.publicK = publicK;
		this.privateK = privateK;
	}

	public PublicKey getPublicK() {
		return publicK;
	}

	public void setPublicK(PublicKey publicK) {
		this.publicK = publicK;
	}

	public PrivateKey getPrivateK() {
		return privateK;
	}

	public void setPrivateK(PrivateKey privateK) {
		this.privateK = privateK;
	}
	
	

}
