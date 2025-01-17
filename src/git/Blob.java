package git;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Blob {
	private String file = "";
	private BufferedReader br;
	private final String FILE_NAME; 
	private FileWriter fw;
	private String en;
	
	public Blob (String f) throws IOException {
		br = new BufferedReader(new FileReader (f));
		while (br.ready()) {
			file += (char)br.read();
		}
		br.close();
		en = this.encryptThisString(file);
		FILE_NAME="/Users/asher/eclipse-workspace/Git Prereq/objects/"+en+".txt";
		makeFile(FILE_NAME);
		fw = new FileWriter(FILE_NAME);
		fw.write(file);
		fw.close();
	}
	
	public String getName () {
		return en;
	}
	
	private void makeFile(String s) throws IOException {
		Path newFilePath = Paths.get(s);
	    Files.createFile(newFilePath);
	}
	
	 public static String encryptThisString(String input)
	    {
	        try {
	            // getInstance() method is called with algorithm SHA-1
	            MessageDigest md = MessageDigest.getInstance("SHA-1");
	 
	            // digest() method is called
	            // to calculate message digest of the input string
	            // returned as array of byte
	            byte[] messageDigest = md.digest(input.getBytes());
	 
	            // Convert byte array into signum representation
	            BigInteger no = new BigInteger(1, messageDigest);
	 
	            // Convert message digest into hex value
	            String hashtext = no.toString(16);
	 
	            // Add preceding 0s to make it 32 bit
	            while (hashtext.length() < 32) {
	                hashtext = "0" + hashtext;
	            }
	 
	            // return the HashText
	            return hashtext;
	        }
	 
	        // For specifying wrong message digest algorithms
	        catch (NoSuchAlgorithmException e) {
	            throw new RuntimeException(e);
	        }
	    }
}
