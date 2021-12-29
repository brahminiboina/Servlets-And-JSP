import java.util.Scanner;

public class GuessGame {
	public static void main(String[] args) {
		guessNumber();
	}
	
	public  static void guessNumber() {
		Scanner sc = new Scanner(System.in);
		int num = 1 + (int)(100*Math.random());
		int k = 5;
		int i,guess;
		System.out.println("A number is chosen between 1 to 100 Guess the number within 5 trials.");
		for(i = 0; i<k;i++) {
			System.out.println("Guess the number:");
			guess = sc.nextInt();
			
			if(num == guess) {
				System.out.println("Congrats lucky!!! Your guess is absolutely correct");
				break;
			}else if(num>guess && i!=k-1) {
				System.out.println("The number is greater than "+guess);
			}else if(num<guess && i!=k-1) {
				System.out.println("The number is less than "+guess);
			}
		
		}
		if(i == k) {
			System.out.println("Sorry!!! Your trails are completed");
			System.out.println("The number is: " +num);
		}
		

	}

}
