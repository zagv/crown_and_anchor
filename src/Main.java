import java.util.List;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
	   
           BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
           System.out.print("Enter Your Name:");
            String input = br.readLine();
            System.out.print("Enter Your Age:");
           String input2 = br.readLine();
           int age=Integer.parseInt(input2);
           
           if (age<18)
           {
               System.out.print("Only 18+ allowed.....Sorryyy!!!!");
               System.exit(12);
           }
           else
           {
               

        Dice d1 = new Dice();
        Dice d2 = new Dice();
        Dice d3 = new Dice();
        
        Player player = new Player(input, 100);
        Game game = new Game(d1, d2, d3);
        List<DiceValue> cdv = game.getDiceValues();

        int totalWins = 0;
        int totalLosses = 0;


        /**
            removed the while loop, because it was waiting for input from the keyboard
            like this when the game is finished, it will exit the program automatically
        */
       // while (true)
        //{
            int winCount = 0;
            int loseCount = 0;
            
            for (int i = 1; i <= 50; i++)
            {
            	String name = input;
            	int balance = 50;
            	int limit = 0;
                player = new Player(name, balance);
                player.setLimit(limit);
                int bet = 5;

                System.out.println(String.format("Start Game %d: ", i));
                System.out.println(String.format("%s starts with balance %d, limit %d", 
                		player.getName(), player.getBalance(), player.getLimit()));

                int turn = 0;
                while (player.balanceExceedsLimitBy(bet) && player.getBalance() < 200)
                {
                    turn++;                    
                	DiceValue pick = DiceValue.getRandom();
                    
                	System.out.printf("Turn %d: %s bet %d on %s\n",
                			turn, player.getName(), bet, pick); 
                	
                	int winnings = game.playRound(player, pick, bet);
                    cdv = game.getDiceValues();
                    
                    System.out.printf("Rolled %s, %s, %s\n",
                    		cdv.get(0), cdv.get(1), cdv.get(2));
                    /**
                     * This is to fix bug 1:
                     * Some one add player.receiveWinnings(winnings). But it generate another bug: the game end when it has not reach the balance limit
                     * So I comment it out
                     * @author vuq.
                     * **/
                    if (winnings > 0) {
	                    System.out.printf("%s won %d, balance now %d\n\n",
	                    		player.getName(), winnings, player.getBalance());
	                	winCount++; 
                    }
                    else {
	                    System.out.printf("%s lost, balance now %d\n\n",
	                    		player.getName(), player.getBalance());
	                	loseCount++;
                    }
                    
                } //while

                System.out.print(String.format("%d turns later.\nEnd Game %d: ", turn, i));
                System.out.println(String.format("%s now has balance %d\n", player.getName(), player.getBalance()));
                
            } //for
            
            System.out.println(String.format("Win count = %d, Lose Count = %d, %.2f", winCount, loseCount, (float) winCount/(winCount+loseCount)));
            totalWins += winCount;
            totalLosses += loseCount;

            //String ans = console.readLine();
           // if (ans.equals("q")) break;
       // } //while true
        
        System.out.println(String.format("Overall win rate = %.1f%%", (float)(totalWins * 100) / (totalWins + totalLosses)));
	}
        }

}
