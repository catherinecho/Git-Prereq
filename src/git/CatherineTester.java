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
        Path p = Paths.get("test.txt");
        try {
            Files.writeString(p, "example", StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            // TODO Auto-generated catch block
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
		index ind = new index(); 
		ind.init();
		
		File f = new File("index.txt");
		assertTrue(f.exists());
		
		Path path = Paths.get("objects");
		assertTrue(Files.exists(path));
		
		
	}
	

}
