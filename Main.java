import java.util.Arrays;
import java.util.Scanner;
//import java.util.Math;
import com.sun.source.util.TaskEvent;

public class Main{
  //create the scanner class here so that it can be used through out the function
  static final Scanner userInput = new Scanner(System.in);
  // a method to slice a given string into an amount of indexes
  private String slice(String str, int startingIndex, int endingIndex){
    if (startingIndex < 0){
      startingIndex = str.length() + startingIndex;
    }
    if (endingIndex < 0){
      endingIndex = str.length() + endingIndex;
    }
    return str.substring(startingIndex, endingIndex);
  }
  // a method to get the numbers into a 5 index list that can be called from and used for other funtions. The list is set to 1 to make the multiplication and division process easier.
  private int[] getNumbers(int userAnswer, String state){
    if(userAnswer > 5){
      System.out.println("Remember: max limit of numbers is 5");
      int[] failed = {-1};
      return failed;
    }
    int[] numbers = {1, 1, 1, 1, 1};
    for(int i = 0; i < userAnswer; i++){
      System.out.println("Enter number " + (i + 1));
      int num = userInput.nextInt();
      numbers[i] = num;
    }
    return numbers;
  }
  // all of the functions listed blow work in the same manner: they walk through the list and do whatever calculation they are meant to do.
  // the add function
  private int add(int[] arr, int loops){
    int total = 0;
    for (int i: arr){
      total += i;
    }
    return total - (5 - loops);
  }
  // the multiply function
  private int multiply(int[] arr){
    int total = 1;
    for (int i: arr){
      total = total * i;
    }
    return total;
  }
  // the subtract function
  private int sub(int[] arr, int loops){
    int total = arr[0];
    for (int i: arr){
      if (total != i){
        total = total - i;
      }
    }
    total = (loops == 1) ? total + 1 : (total + (5 - loops));
    return total;
  }
  // the division function
  private int div(int[] arr){
    int total = 0;
    for (int i = 0; i < 5; i++){
      System.out.println(i);
      if (i == 0){
        total = arr[0];
      }
      total = total / arr[i];
    }
    return total;
  }
  // the start screen method
  private void start(){
    System.out.println("Welcome to calculator");
    System.out.println("Enter your calculation: ");
  }
  // the end screen method that displays the result with the correct texts
  private void end(String slicedAnswer, int result){
    if (slicedAnswer == "div"){
      System.out.println("Note: This result below is an approximation");
      System.out.println("RESULT IS: " + result);
    } else{
      System.out.println("RESULT IS: " + result);
    }
  }
  // the main funtion that does stuff
  public static void main(String[] args) {
    // setup
    Main calc = new Main();
    calc.start();
    String answer = userInput.nextLine();
    System.out.println("Enter the amount of numbers to process [max 5]");
    // calls the other funtions is is responsible for getting the numbers, while also checking if the result is a valid funtion i.e. it isnt a function like square root or something else
    int loopTimes = userInput.nextInt();
    String result = calc.slice(answer, 0, 3);
    int[] endCase = {0};
    int[] r = (result.equals("add") || result.equals("mul") || result.equals("div") || result.equals("sub")) ? calc.getNumbers(loopTimes, result) : endCase;
    // just assigns a state to the correct function
    int finalResult = 0;
    if (result.equals("add") || result.equals("sum")){
      finalResult = calc.add(r, loopTimes);
    } 
    if (result.equals("mul")){
      finalResult = calc.multiply(r);
    }
    if (result.equals("sub")){
      finalResult = calc.sub(r, loopTimes);
    }
    if (result.equals("div")){
      finalResult = calc.div(r);
    }
    calc.end(result, finalResult);
    userInput.close();
  }
}
