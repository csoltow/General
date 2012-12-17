package com.soltow.messagedigest;

public enum MessageDigestAlgorithm {
	MD5("md5"), 
	MD2("md2"),
	SHA512("sha-512"),
	SHA256("sha-256"),
	SHA1("sha-1");
	
	private String algorithmName;
	
	private MessageDigestAlgorithm(String name) {
		algorithmName = name;
	}
	
	@Override
	public String toString() {
		return algorithmName;
	}
}
