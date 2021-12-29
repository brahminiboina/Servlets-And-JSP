
public class Test01 {
	private boolean printed;
	synchronized void printodd(int num) {
		if(printed) {
			try{wait();}
			catch(InterruptedException e) {}
	}
		
	public static void main(String[] args) {
		
	}

}
