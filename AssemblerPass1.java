import java.io.*;
import java.lang.*;
import java.util.*;

class assembler
{    
    public static void main(String args[])
    {
        String[] mot={"L","A","ST","MOV","LR","BR","LH","H","F"};
        String[] pot={"START","USING","DROP","EQU","DC","DS","BALR","END"};
        int[] motlen = {4,4,4,6,2,4,2,2,4};
        int lc = 0;
        Vector symtab = new Vector();
        Vector symtablc = new Vector();
        Vector littab = new Vector();
        Vector littablc = new Vector();
        
        try{
        //File ipfile=new File("inputfile.txt");
        Scanner input = new Scanner(new File("inputfile.txt"));
        while(input.hasNextLine()){
            Scanner sc =new Scanner(input.nextLine());

            /*while(sc.hasNext()){
                String inst_word = sc.next();            

                for(int i = 0;i < mot.length; i++){                            
                    if(inst_word.equals(mot[i])){    
                    lc += motlen[i];
                       System.out.println(" LC= "+lc);
                       break;
                    }
                }

            }*/
        

        //Scanner input2 = new Scanner(new File("inputfile.txt"));
        while(sc.hasNext()){
            
            String label = sc.next();
            boolean present = false;
            
            for(String s : pot){
                if(s.equals(label)){
                    present=true;
                    //System.out.println(label);
                    break;
                }
            }

            for(String s : mot){
                if(s.equals(label)){
                    present=true;
                    //System.out.println("HEy");
                    int i = s.indexOf(label); // get index of label in mot
                    //System.out.println(label);
                    lc += motlen[i]; //add length of label to lc
                    break;
                }
            }
    

            if(!present && label.matches("[A-Za-z]+")){
                //System.out.println("HEy");
                symtab.addElement(new String(label));//add label to symbol table
                symtablc.addElement(new Integer(lc));//add lc value of label to symbol table
            }

            if(!present && label.matches("=[FHBD+]'[0-9]'")){
                //System.out.println("HEy");
                littab.addElement(new String(label));//literal table
                littablc.addElement(new Integer(lc));
            }
            
        }    

    }
    System.out.println("Symbol Table :");
            for(int j = 0;j < symtab.size(); j++){

                    System.out.println(symtab.get(j) +" "+symtablc.get(j));
                    //System.out.println(j);
                    //System.out.println(symtablc);
                    }
    System.out.println("Literal Table :");
            for(int j = 0;j < littab.size(); j++){
                
                    System.out.println(littab.get(j) +" "+littablc.get(j));
            }    
    }catch (FileNotFoundException ex){}
}
}



input

OCT         START 0
            BALR 15,0
            USING *,15
            LR 10,15
            L INDEX, =F'4'
            A INDEX, =F'5'
            USING *,10
            LH 1,DATA2
            BR 1,4
DATAx     DC H '1'
DATAy     DC H '2'
DATAz     DC H '3'
END


