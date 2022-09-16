package git;
import java.util.*;
import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Tree {
	private static ArrayList<String> a;
	private static String sha;  
	public static void main (String[]args) throws FileNotFoundException, IOException, NoSuchAlgorithmException {
		File file = new File("objects");
		file.mkdir();
		ArrayList<String> blobThings = new ArrayList<String>();
		
		blobThings.add("blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f");
		blobThings.add("blob : 01d82591292494afd1602d175e165f94992f6f5f");
		blobThings.add("blob: f1d82236ab908c86ed095023b1d2e6ddf78a6d83");
		blobThings.add("tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b");
		blobThings.add("tree: e7d79898d3342fd15daf6ec36f4cb095b52fd976");
		
		Tree test = new Tree(blobThings);
		PrintWriter out = new PrintWriter(new File("objects/"+ sha));
		for (int i = 0; i < a.size(); i ++) {
			out.append(a.get(i) + "\n");
		}
		out.close();
	}
	public Tree(ArrayList<String> blobThings) throws NoSuchAlgorithmException, IOException {
		a = new ArrayList<String>();
		this.a = blobThings;
		
		String fileName = "";
		for (int i = 0; i < blobThings.size(); i++) {
			fileName+= blobThings.get(i) + "\n";
		}
		sha = encryptThisString(fileName);
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
