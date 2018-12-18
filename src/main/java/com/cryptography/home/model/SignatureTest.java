package com.cryptography.home.model;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;

public class SignatureTest {

	public static void main(String[] args) throws Exception {

		sign();
		valSig();
				
    }
	
	

	private static void valSig() throws Exception {
		
		/*FileInputStream keypfis = new FileInputStream("private-key");
		byte[] encpKey = new byte[keypfis.available()];  
		keypfis.read(encpKey);
		keypfis.close();
		X509EncodedKeySpec privKeySpec = new X509EncodedKeySpec(encpKey);*/
		
		
		FileInputStream keyfis = new FileInputStream("public-key");
		byte[] encKey = new byte[keyfis.available()];  
		keyfis.read(encKey);
		keyfis.close();				
		X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(encKey);
		
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		
		PublicKey pubKey = keyFactory.generatePublic(pubKeySpec);
		
		//PublicKey priKey = keyFactory.generatePublic(privKeySpec);

		
		FileInputStream sigfis = new FileInputStream("signature");
		byte[] sigToVerify = new byte[sigfis.available()]; 
		sigfis.read(sigToVerify);
		sigfis.close();
		
		sigToVerify = java.util.Base64.getDecoder().decode(sigToVerify);
		
		Signature sig = Signature.getInstance("SHA1withRSA");
		
		sig.initVerify(pubKey);

		
		FileInputStream datafis = new FileInputStream("plain");
		BufferedInputStream bufin = new BufferedInputStream(datafis);

		byte[] buffer = new byte[1024];
		int len;
		while (bufin.available() != 0) {
		    len = bufin.read(buffer);
		    sig.update(buffer, 0, len);
		};

		bufin.close();
		
		boolean verifies = sig.verify(sigToVerify);

		System.out.println("signature verifies: " + verifies);
		
	}
	
	private static void sign() throws Exception {

		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
		
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		keyGen.initialize(1024, random);
		
		KeyPair pair = keyGen.generateKeyPair();
		PrivateKey priv = pair.getPrivate();
		PublicKey pub = pair.getPublic();

		Signature dsa = Signature.getInstance("SHA1withRSA"); 
		
		dsa.initSign(priv);
		
		FileInputStream fis = new FileInputStream(/*args[0]*/"plain");
		BufferedInputStream bufin = new BufferedInputStream(fis);
		byte[] buffer = new byte[1024];
		int len;
		while ((len = bufin.read(buffer)) >= 0) {
		    dsa.update(buffer, 0, len);
		};
		bufin.close();		
		
		byte[] realSig = dsa.sign();
		
		String encoded64 = java.util.Base64.getEncoder().encodeToString(realSig);
		System.out.println(encoded64);
		
		/* save the signature in a file */
		FileOutputStream sigfos = new FileOutputStream("signature");
		//sigfos.write(realSig);
		sigfos.write(encoded64.getBytes());
		sigfos.close();
		
		
		/* save the public key in a file */
		byte[] key = pub.getEncoded();
		FileOutputStream keyfos = new FileOutputStream("public-key");
		keyfos.write(key);
		keyfos.close();
		
		/* save the private key in a file */
		byte[] keyp = pub.getEncoded();
		FileOutputStream keypfos = new FileOutputStream("private-key");
		keypfos.write(keyp);
		keypfos.close();
		
	}

}
