package t;

import java.io.*;
import java.util.*;

class test implements Serializable{
	public static void main(String[] args) throws Exception {
		File f = new File(test.class.getClass().getResource("/data/abc.txt").getFile());
		BufferedReader br = new BufferedReader(new FileReader(f));
		System.out.println(br.readLine());		
	}
}