import java.io.Serializable;
import java.io.File;

class test implements Serializable{

		public void p(int x1,int x2){
			System.out.println("yeah");
		}

		public void p(int x1){
			System.out.println("yeah2");
		}

	public static void main(String[] args) throws Exception {
		test t = new test();
		t.p(2);
		t.p(3,4);
		/*String s = "Hello my name is ";
		String w = "";
		for(int i = 2; i < 5 ;i++){
			w += s.charAt(i);
		}
		System.out.println(w);*/
		
	//	SaveData sd = new SaveData("test.txt");
	//	sd.saveData(t);
		/*Object o = sd.getData();
		if(o == null)
			System.out.println("Yes");*/
			File f = new File("test.txt");
			System.out.println(f.delete());
	}
}