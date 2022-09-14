package git;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class index {
	//private boolean bool = false;
	private HashMap<String, String> hm;
	
	public index () throws IOException {
//		File file = new File("/Users/asher/eclipse-workspace/Git Prereq");
//		//creating the directory
//		bool = file.mkdir();
		hm = new HashMap<>();
	}
	
	public void init() throws IOException {
		makeFile("/Users/asher/eclipse-workspace/Git Prereq/index.txt");
		new File ("/Users/asher/eclipse-workspace/Git Prereq/objects/").mkdirs();
	}
	
	public void add(String fn) throws IOException {
		Blob blobby = new Blob (fn);
		if (!hm.containsKey(fn.substring(42))) {
			hm.put(fn.substring(42), blobby.getName());
			FileWriter fw = new FileWriter("/Users/asher/eclipse-workspace/Git Prereq/index.txt");
			fw.write(fn.substring(42)+" : "+blobby.getName()+"\n");
			fw.close();
		}
	}
	
	public void remove(String fn) throws IOException {
		if (hm.containsKey(fn.substring(42))) {
			String str = Blob.encryptThisString(fn);
			hm.remove(fn.substring(42), str);
			BufferedReader br = new BufferedReader(new FileReader ("/Users/asher/eclipse-workspace/Git Prereq/index.txt"));
			PrintWriter pw = new PrintWriter("/Users/asher/eclipse-workspace/Git Prereq/temp.txt");
			while (br.ready()) {
				String read = br.readLine();
				if (read.indexOf(fn.substring(42))<0) {
					pw.print (read);
				}
			}
			br.close();
			pw.close();
			File ind = new File ("/Users/asher/eclipse-workspace/Git Prereq/index.txt");
			File temp = new File ("/Users/asher/eclipse-workspace/Git Prereq/temp.txt");
			ind = temp;
			temp.delete();
			
			File file = new File (fn);
			file.delete();
		}
		
	}
	
	private void makeFile(String s) throws IOException {
		Path newFilePath = Paths.get(s);
	    Files.createFile(newFilePath);
	}
}
