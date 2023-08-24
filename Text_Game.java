import java.util.Random;
import java.util.Scanner;

public class Text_Game {
public static void main(String[] args) {
    // Create Scanner object to read user input data & Random object       to generate random number
    Scanner in = new Scanner(System.in);
    Random rand = new Random();

    // Enemy Variables
    String[] enemies = {"Lion", "Gorilla", "Zebra", "Elephant"}; //       Array of enemies
    int maxEnemyHealth = 90; // Max health enemies can have
    int enemyAttackDam = 25; // Amount of damage enemy can exert on         player

    // Player Variables
    int health = 100; // player starting health
    int attackDam = 30; // damage player can exert on enemy
    int healthPot = 3; // Each potion adds health to heal player
    int healthPotHealAmt = 30; // Each potion adds 30 to health
    int healthPotDrop = 40; // Percent change of potion being dropped     after killing an enemy

    boolean running = true;

    // Print welcome message
    System.out.println(" Welcome to the Jungle ");

    // while loop to run the game
    GAME: 
    while (running) {
      System.out.println();

      // Generate enemy health and select random enemy
      int enemyHealth = rand.nextInt(maxEnemyHealth); 
      String enemy = enemies[rand.nextInt(enemies.length)];
                                                                            System.out.println("\t" + enemy + " has appeared " + "\n");

      // while loop for combat
      while (enemyHealth > 0) {
         // Display player's and enemy's health and provides commands           to play
        System.out.println("\tYour HP: " + health);
        System.out.println("\t" + enemy + "'s HP: " + enemyHealth);
        System.out.println("\n\tWhat would you like to do? ");
        System.out.println("\t1. Attack ");
         
        // Prevent drinking health potion when health is at 100 or               over
        if(health < 100) {
         System.out.println("\t2. Drink health potion "); 
        }
        
        System.out.println("\t3. Run ");

        String input = in.nextLine();

        // conditional statements to validate user input
        if (input.equals("1")) {
           // Handle attack command
          int damageDealt = rand.nextInt(attackDam);
          int damageTaken = rand.nextInt(enemyAttackDam);

          enemyHealth -= damageDealt;
          health -= damageTaken;

          System.out.println("\tYou dealt " + damageDealt + " to " +            enemy);
          System.out.println("\tYou took " + damageTaken + " by the "           + enemy);

          if (health <= 0) {
            System.out.println("\tYou have taken too much damage");

            break;
          }
        }

        else if (input.equals("2")) {
          // Handle health potion command
          if (healthPot > 0 && health < 100) {
            // Calculate how much health to heal without exceeding 100
            int potentialHeal = Math.min(health + healthPotHealAmt,               100) - health;
            health += potentialHeal;
            healthPot--;
            System.out.println("\tYou drank the health potion healing             yourself for:" + healthPotHealAmt
                + "\n\tYou now have " + health + " HP "
                + "\n\tYou have " + healthPot + " health potions left                 \n");

          } else {
            System.out.println("\tYou Have no Health potions left.                Defeat enemy to unlock one");
          }

        }

        else if (input.equals("3")) {
          // Handle run command
          System.out.println("\tYou run away from the " + enemy + " !           ");
          continue GAME;

        } else {
          System.out.println("\tinvalid command ");
        }

      }
      // if we break out of the loop because we lost
      if (health < 1) {
        System.out.println("You limp out of the jungle weak from              battle");
        break;
      }
        // Handle victory
      System.out.println(" # " + enemy + "was defeated");
      System.out.println(" You have " + health + "HP left");

       // Check for health potion drop
      if (rand.nextInt(100) < healthPotDrop) {
        healthPot++;
        System.out.println(" The " + enemy + "dropped a health                potion");
        System.out.println(" You have " + healthPot + " Health                potions ");
      }

       // Display options after victory
      System.out.println(" what would you like to do now? ");
      System.out.println("4. Continue fighting ");
      System.out.println("5. Exit Jungle ");

      String input = in.nextLine();

       // Validate user input
      while (!input.equals("4") && !input.equals("5")) {
        System.out.println(" Invalid Command ");
        input = in.nextLine();
      }

    if (input.equals("4")) {
        System.out.println("Your adventure continues");
    }

    else if (input.equals("5")) {
        System.out.println("You exit the jungle, successful from your         adventure");
        break;
}
    }
    System.out.println(" Thanks for playing");

}
}