package com.cryptography.home.services;

import java.security.Signature;

import com.cryptography.home.model.OpenKeySig;

public class SignService {
		
	public boolean verifySignature(byte[] signed, byte[] text, Signature signature, OpenKeySig oks) {
		try {			
			synchronized (signature) {
				signature.initVerify(oks.getPublicK());
				signature.update(text);
				return signature.verify(signed);
			}

		} catch (Exception e){
			e.printStackTrace();
			return false;
		}		

	}
	
	public byte[] emitSignature(byte[] text, Signature signature, OpenKeySig oks) {		
		try {
			synchronized (signature) {
				signature.initSign(oks.getPrivateK());
		        signature.update(text);
		        return signature.sign();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
