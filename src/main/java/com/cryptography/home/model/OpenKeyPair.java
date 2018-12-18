package com.cryptography.home.model;

public class OpenKeyPair {

	private String name;
	
	private byte[] privateK;
	
	private byte[] publicK;	
	
	private String algorithm;

	public OpenKeyPair(String name, byte[] privateK, byte[] publicK, String algorithm) {
		super();
		this.name = name;
		this.privateK = privateK;
		this.publicK = publicK;
		this.algorithm = algorithm;
	}
	
	public OpenKeyPair() {
		super();		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getPrivateK() {
		return privateK;
	}

	public void setPrivateK(byte[] privateK) {
		this.privateK = privateK;
	}

	public byte[] getPublicK() {
		return publicK;
	}

	public void setPublicK(byte[] publicK) {
		this.publicK = publicK;
	}

	public String getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}
		
	
}
