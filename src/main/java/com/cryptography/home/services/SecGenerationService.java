package com.cryptography.home.services;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import com.cryptography.home.model.OpenKeyPair;
import com.cryptography.home.model.OpenKeySig;

public class SecGenerationService {
	
	private static final String SIGNATURE = "SHA1WithRSA";
	private static final String SEC_RANDOM = "SHA1PRNG";
		
	public Signature createSignature() throws Exception {
		return Signature.getInstance(SIGNATURE);
	}
	
	public OpenKeySig getOpenKeySigFrom(OpenKeyPair okp) throws Exception {
				
		KeyFactory kf = KeyFactory.getInstance(okp.getAlgorithm());
		
		PKCS8EncodedKeySpec priSpec = new PKCS8EncodedKeySpec(okp.getPrivateK());
		X509EncodedKeySpec pubSpec = new X509EncodedKeySpec(okp.getPublicK());
						
		PrivateKey privateK = kf.generatePrivate(priSpec);
		PublicKey publicK = kf.generatePublic(pubSpec);
			
		OpenKeySig oks = new OpenKeySig(publicK, privateK);
		return oks;
	}
	
	public OpenKeyPair generateKeyPair(String pairName, String algorithm) {
		try {
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance(algorithm);
			
			SecureRandom random = SecureRandom.getInstance(SEC_RANDOM);
			keyGen.initialize(1024, random);
			
			KeyPair pair = keyGen.generateKeyPair();
			PrivateKey priv = pair.getPrivate();
			PublicKey pub = pair.getPublic();
			
			OpenKeyPair okp = new OpenKeyPair(pairName, priv.getEncoded(), pub.getEncoded(), algorithm);
			
			return okp;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
	
}
