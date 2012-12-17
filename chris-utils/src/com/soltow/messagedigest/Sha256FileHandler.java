package com.soltow.messagedigest;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.codec.digest.DigestUtils;

public class Sha256FileHandler  implements DigestHandler {
	
	public String createDigest(File file) {
		String digest = null;
		try {
			FileInputStream fis = new FileInputStream(file);
			digest = DigestUtils.sha256Hex(fis);
		} catch (Exception e) {
			throw new DigestFileRuntimeException(e.getMessage(), e.getCause());
		}
		return digest;
	}

}
