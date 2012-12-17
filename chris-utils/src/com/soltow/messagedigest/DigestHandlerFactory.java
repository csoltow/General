package com.soltow.messagedigest;

public class DigestHandlerFactory {

	public static DigestHandler getInstance(MessageDigestAlgorithm algorithm) {
		DigestHandler creator = null;
		switch (algorithm) {
			case MD5:
				creator = new Md5DigestHandler();
				break;
			case MD2:
				creator = new Md2DigestHandler();
				break;
			case SHA1:
				creator = new Sha1FileHandler();
				break;
			case SHA256:
				creator = new Sha256FileHandler();
				break;
			case SHA512:
				creator = new Sha512FileHandler();
				break;
			default:
				break;
		}
		return creator;
	}
}
