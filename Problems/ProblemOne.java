import java.util.ArrayList;
import java.util.List;

public class ProblemOne{

    public static void main(String args[]){
        int[] numList = {5, 5, 5};
        int totalValue = 0;

        //for Loop
        for(int a=0; a<numList.length; a++){
            totalValue += numList[a];
        }
        System.out.println(totalValue);


        // while
        int arIndex = 0;
        while (numList.length>arIndex){
            totalValue += numList[arIndex];
            arIndex++;
        }
        System.out.println(totalValue);




    }

}