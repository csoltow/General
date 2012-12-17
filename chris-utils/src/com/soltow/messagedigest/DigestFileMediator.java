package com.soltow.messagedigest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DigestFileMediator {
	private DigestHandler creator;
	private MessageDigestAlgorithm algorithmName;
	
	public void create(File file) {
		try {
			String digest = creator.createDigest(file);
			String digestFileName = createOutputFileName(file.getCanonicalPath());
			createOutputFile(digest, digestFileName);
		} catch (Exception e) {
			throw new DigestFileRuntimeException("Failed to create "+this.getClass()+" file.", e.getCause());
		}
	}

	public void setMessageDigestAlgorithm(MessageDigestAlgorithm name) {
		creator = DigestHandlerFactory.getInstance(name);
		algorithmName = name;
	}
	
	public MessageDigestAlgorithm getMessageDigestAlgorithmName() {
		return algorithmName;
	}
	
	public DigestHandler getDigestHandler() {
		return creator;
	}
	
	protected String createOutputFileName(String fileName) {
		return fileName.substring(0, fileName.lastIndexOf("."))+"."+algorithmName;
	}

	protected void createOutputFile(String checksum, String checksumFileName) throws IOException {
		FileWriter writer = new FileWriter(new File(checksumFileName));
		BufferedWriter out = new BufferedWriter(writer);
		out.write(checksum);
		out.close();
	}

}
