package git;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class CatherineTester {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		File file = new File("objects");
		file.mkdir();
		
        Path p = Paths.get("test.txt");
        try {
            Files.writeString(p, "test", StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        Path path2 = Paths.get("test2.txt");
        try {
            Files.writeString(path2, "another test", StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Path path3 = Paths.get("test3.txt");
        try {
            Files.writeString(path3, "third test", StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		
		File f = new File("test.txt");
		f.delete();
	}

	@Test
	void testInit() throws IOException {
		index i = new index();
		i.init();
		File f = new File("test.txt");
		assertTrue(f.exists());
		
		Path path = Paths.get("objects");
		assertTrue(Files.exists(path));
	}
	
	
	@Test
	void testAdd() throws Exception {
		index ind = new index();
		ind.init();
		ind.add("test2.txt");
		ind.add("test3.txt");
		
		File f1 = new File("objects/afc8edc74ae9e7b8d290f945a6d613f1d264a2b2");
		assertTrue(f1.exists());
		File f2 = new File("objects/47ac5e026d9a194ca03638b368d6825d68e1537c");
		assertTrue(f2.exists());
	}
	@Test
	void testBlob() throws Exception {
		Blob b = new Blob("test2.txt");
		File f = new File("objects/afc8edc74ae9e7b8d290f945a6d613f1d264a2b2");
		assertTrue(f.exists());
	}
	

	@Test
	void testRemove() throws IOException {
		index ind = new index();
		ind.init(); 
		ind.remove("test3.txt");
		
		File file = new File("objects/47ac5e026d9a194ca03638b368d6825d68e1537c");
		assertTrue(file.exists());
	}

}
