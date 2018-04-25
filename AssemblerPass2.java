import java.io.*;
import java.lang.*;
import java.util.*;
import java.io.FileWriter;  


class pass2
{    
    public static void main(String args[])
    {
        String[] mot={"L","A","ST","MOV","LR","BR","LH","H","F"};
        String[] pot={"START","USING","DROP","EQU","DC","DS","BALR","END"};
        int[] motlen = {4,4,4,6,2,2,4,2,4};
        int lc = 0;
        int j;
        Vector symtab = new Vector();
        Vector symtablc = new Vector();
        Vector littab = new Vector();
        Vector littablc = new Vector();
        
        try{
        Scanner input = new Scanner(new File("inputfile.txt"));
        //Scanner input2 = new Scanner(new File("inputfile.txt"));
        while(input.hasNextLine()){
            Scanner sc = new Scanner(input.nextLine());        
            //Scanner sc2 = Scanner(input.nextLine());
        while(sc.hasNext()){
            
            String label = sc.next();
            boolean present = false;
            
            for(String s : pot){
                if(s.equals(label)){
                    present=true;
                    break;
                }
            }

            for(String s : mot){
                if(s.equals(label)){
                    present=true;
                    int i = s.indexOf(label); // get index of label in mot
                    lc += motlen[i]; //add length of label to lc
                    break;
                }
            }
    

            if(!present && label.matches("[A-Za-z]+")){
                symtab.addElement(new String(label+","));//add label to symbol table
                symtablc.addElement(new Integer(lc));//add lc value of label to symbol table
            }

            if(!present && label.matches("=[FHBD+]'[0-9]'")){
                littab.addElement(new String(label));//literal table
                littablc.addElement(new Integer(lc));
            }
            
        }    

    }
    System.out.println("\nSymbol Table :");
            for(j = 0;j < symtab.size(); j++){

                    System.out.println(symtab.get(j) +" "+symtablc.get(j));
                    }
    System.out.println("\nLiteral Table :");
            for(j = 0;j < littab.size(); j++){
                
                    System.out.println(littab.get(j) +" "+littablc.get(j));
            }
    }catch (FileNotFoundException ex){}        

    //************************************pass2**************************************************
    
    //System.out.println("Pass 2 Started");
    lc=0;
    Vector bt = new Vector();
    Vector btlen = new Vector();
    Vector pass2op = new Vector();
    int index, value, basereg, offset;
    
    try{
        //Scanner input = new Scanner(new File("inputfile.txt"));
        Scanner input2 = new Scanner(new File("inputfile.txt"));
        /*FileWriter fw = new FileWriter("pass2output.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw);*/

        while(input2.hasNextLine()){
            //Scanner sc = new Scanner(input.nextLine());        
            Scanner sc2 = new Scanner(input2.nextLine());//one full line
        while(sc2.hasNext()){

            String label = sc2.next();
            boolean presentpot = false;
            boolean presentmot = false;
            //System.out.println("HEY");
            
            for(String s : pot){
                if(s.equals(label)){
                    presentpot=true;
                    presentmot=false;
                    break;
                }
            }

            for(String s : mot){
                if(s.equals(label)){
                    presentmot=true;
                    presentpot=false;
                    int i = s.indexOf(label); // get index of label in mot
                    lc += motlen[i]; //add length of label to lc
                    break;
                }
            }

if(presentpot || presentmot){
switch(label){
case "USING": String op1 = sc2.next();
      String op2 = sc2.next();
          bt.addElement(op2);
          btlen.addElement(lc);    
          break;
         
case "DS":
case "DC": lc +=4;
    break;    

case "L":
case "A":
case "ST":
case "BALR":
case "BR":
case "LR":
case "LH":    op1 = sc2.next();
    op2 = sc2.next();
    //String[] op = op1.split(",");
    //System.out.println(op);
    if(op1.matches("\\d+" + ",")){
        if(op2.matches("\\d+")){
            pass2op.addElement(new String(label+" "+op1+" "+op2));
            }
    }else{
        //first operand is DATAz
        if(!op1.matches("\\d+") && op2.matches("\\d+" + ";")){
        index = (int)symtab.indexOf(op1);
        value = (int)symtablc.get(index);
        pass2op.addElement(new String(label+" "+value+", "+op2));
        }
    }
    //1st operand is DATA and 2nd is Literal
    if(!op1.matches("\\d+" + ",")){
        if(op2.matches("=[FHBD+]'[0-9]'")){
            index = (int)symtab.indexOf(op1);
             value = (int)symtablc.get(index);
            int index2 = (int)littab.indexOf(op2);
            int value2 = (int)littablc.get(index2);
            basereg = 0;
            offset = value2 - basereg;
            pass2op.addElement(new String(label+" "+value+", "+offset+"(0,15)"));
        }
    }
     break;
}
}

}
        }//end of while
        System.out.println("\nBase Table :");
        for(j = 0; j < bt.size(); j++){

              System.out.println(bt.get(j)+"  "+btlen.get(j));
          }

          System.out.println("\nPass 2 Output :");
          for(j = 0; j < pass2op.size(); j++){

              System.out.println(pass2op.get(j));
          }

    }catch (IOException e){}
}
}



input


OCT START 0
    BALR 15, 0
    USING *, 15
    LR 10, 15
    L DATAx, =F'4'
    A DATAy, =F'5'
    USING *, 10
    LH DATAz, 3;
    BR 1, 4
DATAx DC F '1'
DATAy DC F '2'
DATAz DC F '3'
END
