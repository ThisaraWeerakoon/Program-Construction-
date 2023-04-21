import java.util.ArrayList;
import java.util.Scanner;

class BeverageMachine{
    private ArrayList<BeverageCup> cupList=new ArrayList<BeverageCup>();


    public  BeverageCup  produceBeverage(String type){
        if(type.equals("Water")){
            WaterCup waterCup=new WaterCup();
            addToTrackList(waterCup);
            return waterCup;
        } else if (type.equals("Tea")) {
            TeaCup teaCup=new TeaCup();
            addToTrackList(teaCup);
            return teaCup;
        }
        else if(type.equals("Coffee")){
            CoffeeCup coffeeCup=new CoffeeCup();
            addToTrackList(coffeeCup);
            return coffeeCup;
        }
        else{
            System.out.println("Invalid beverage type try again ");
            return null;
        }

    }

    private void addToTrackList(BeverageCup beverageCup){
        cupList.add(beverageCup);
    }

    public BeverageCup getLastCup(){
        return cupList.get(cupList.size()-1);
    }




}


abstract class BeverageCup{


    private String type;

    private int calories;

    static int totalTeaCoffeeCups=0;
    static int totalCups=0;//to generate id
    private int id=totalCups;

    public BeverageCup(String type){
        this.type=type;

        System.out.println("Producing "+this.type+" cup. Id: "+this.id);
        totalCups++;


    }

    public String getType() {
        return type;
    }

    protected int getCalories() {
        return calories;
    }

    public static int getTotalTeaCoffeeCups() {
        return totalTeaCoffeeCups;
    }

    public static int getTotalCups() {
        return totalCups;
    }

    public int getId() {
        return id;
    }

    protected void setCalories(int calories) {
        this.calories = calories;
    }

    abstract void calculateCalories();




}
class WaterCup extends BeverageCup{
    public WaterCup(){
        super("Water");
    }
    public void calculateCalories(){
        super.setCalories(0);
        System.out.println("Calories of the last cup: "+super.getCalories());
    }

}

class CoffeeCup extends BeverageCup {
    public CoffeeCup(){
        super("Coffee");
        totalTeaCoffeeCups++;
    }

    @Override
    public void calculateCalories() {
        System.out.println("Calculate calories for Coffee");
        super.setCalories(100);
        System.out.println("Calories of the last cup: "+super.getCalories());
    }
}

class TeaCup extends BeverageCup {
    public TeaCup(){
        super("Tea");
        totalTeaCoffeeCups++;
    }

    @Override
    public void calculateCalories() {
        System.out.println("Calculate calories for Tea");
        super.setCalories(10);
        System.out.println("Calories of the last cup: "+super.getCalories());
    }
}


public class Main {
    public static void main(String[] args) {
        BeverageMachine beverageMachine = new BeverageMachine();//create a beverage machine


        while(true){
            System.out.println("Enter option 0 - produce beverage, 1 - amount of calories in the last cup, 2 - number of coffee + tea cups produced 3-shutdown");
            Scanner in = new Scanner(System.in);// take console input
            int option = in.nextInt();
            in.nextLine(); // Consume the newline character left after reading the integer input
            if (option == 0) {
                System.out.println("What beverage type do you want? Water, Tea, or Coffee?");
                String type = in.nextLine();
                beverageMachine.produceBeverage(type);
            } else if (option == 1) {
                try {
                    BeverageCup lastCup = beverageMachine.getLastCup();
                    lastCup.calculateCalories();


                }catch (Exception e){
                    System.out.println("No cups produced yet.");

                }

            } else if (option == 2) {
                int totalTeaCoffeeCups = BeverageCup.getTotalTeaCoffeeCups();
                System.out.println("Total number of coffee + tea cups produced: " + totalTeaCoffeeCups);
            } else {
                System.out.println("Shutting down");
                break;
            }

        }

    }
}
