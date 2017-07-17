/**
 * Created by cstegehuis on 05/07/17.
 */
public class TestBoekToets {

    public static void main(String[] args){
        StringBuilder sb = new StringBuilder("animals");

        sb.insert(7, "-");
        sb.insert(0, "-");
        sb.insert(4, "-");

        System.out.println(sb);

        String a = "";
        a +=2;
        a+='c';
        a+=false;
        if (a == "2cfalse") System.out.println("==");
        if (a.equals("2cfalse")) System.out.println("equals");


        StringBuilder puzzle = new StringBuilder("Java");
        puzzle.append("vaJ$").substring(0,4);
        String puzzle2 = puzzle.substring(0,4);
        System.out.println(puzzle);
        System.out.println(puzzle2);


    }

}
