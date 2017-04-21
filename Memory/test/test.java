package t;

import java.io.*;
import java.util.*;

class test implements Serializable{
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("/tmp/abc.txt"));
		System.out.println(br.readLine());		
	}
}