package com.soltow.messagedigest;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.junit.Test;

import com.soltow.messagedigest.DigestFileMediator;
import com.soltow.messagedigest.Md5DigestHandler;
import com.soltow.messagedigest.MessageDigestAlgorithm;

public class DigestFileUtility_IT {
	private static final int STREAM_BUFFER_LENGTH = 1024;
	private final String TEST_DIGEST = "6ac6faecd591cb7259760bb9c7ccf26c";
	DigestFileMediator utility = new DigestFileMediator();
	 
	@Test
	public void testCreate() throws IOException {
		try {
			File file = new File(utility.getClass().getResource("/digest-files/md5-tester.txt").getFile());
			utility.setMessageDigestAlgorithm(MessageDigestAlgorithm.MD5);
			utility.create(file);
			File md5File = new File(utility.getClass().getResource("/digest-files/md5-tester.md5").getFile());
			FileInputStream fis = new FileInputStream(md5File);
			byte[] buffer = new byte[STREAM_BUFFER_LENGTH];
			fis.read(buffer, 0, STREAM_BUFFER_LENGTH);
			String actual = new String(buffer);
			assertTrue("MD5 values are not equal.", TEST_DIGEST.equals(actual.trim()));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testSetMessageDigestAlgorithm() {
		utility.setMessageDigestAlgorithm(MessageDigestAlgorithm.MD5);
		assertEquals("MessageDigestAlgorithm doesn't match.", utility.getMessageDigestAlgorithmName(), MessageDigestAlgorithm.MD5);
		assertTrue("DigestHandler is of wrong type.", Md5DigestHandler.class.isInstance(utility.getDigestHandler()));
	}

	@Test
	public void testCreateOutputFileName() {
		utility.setMessageDigestAlgorithm(MessageDigestAlgorithm.MD2);
		String fileName = utility.createOutputFileName("/mytest/m.t/test.txt");
		assertEquals("Created fileName does not match.", "/mytest/m.t/test.md2", fileName);
	}

}
