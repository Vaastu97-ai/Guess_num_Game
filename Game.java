import java.util.Scanner;

public class Game
{
    public static void main(String[] args)
    {
        Scanner Input = new Scanner(System.in);
      System.out.println("Welcome to the game!");
    

    int Guess_number;
    int Random_number = (int)(Math.random() * 100) + 1;

    while(true)
    {
        System.out.println("Guess a number (1-100): ");
        Guess_number = Input.nextInt();

        if(Guess_number == Random_number)
        {
            System.out.println("yipeeeee that's correct cutieee!");
            break;
        }

        else if(Guess_number < 0)
        {
            break;
        }

        else if(Guess_number > 100)
        {
            break;
        }

        else if(Guess_number < Random_number)
        {
            System.out.println("The number is too low , Try again!");
        }

        else
        {
            System.out.println("The number is too high , Try again!");
        }
    }
        Input.close();
        System.out.println("Thanks for playing!");
  }
}