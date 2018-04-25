import java.io.*;
import java.lang.*;
import java.util.*;
import java.io.FileWriter;

class Lex {
    public static void main(String args[]){
        String keywords []={"if","while","else"};
        String operators []={"+","-","*","/"};
        String reloperators []={"<",">",">=","<=","="};
        String punctuations []={";",",","//"};
        String parenthesis []={"(",")","{","}","[","]"};
        try{
        String input =new Scanner(new File("lexip.txt")).useDelimiter("\\Z").next();
        //String result[]=input.split("\n");//split at every line
        String result[]=input.split("[\\s]");//split at every whitespace
        for(int i=0;i<result.length;i++){

            if(Arrays.asList(keywords).contains(result[i])){
                System.out.println(result[i]+" is a keyword");
            }
            else if(Arrays.asList(operators).contains(result[i])){
                     System.out.println(result[i]+" is an operator");
                    }
                 else if (Arrays.asList(reloperators).contains(result[i])){
                         System.out.println(result[i]+" is a reloperator");
                         }
                       else if(Arrays.asList(punctuations).contains(result[i])){
                               System.out.println(result[i]+" is a punctuation");
                               }
                              else if(Arrays.asList(parenthesis).contains(result[i])){
                                   System.out.println(result[i]+" is a parenthesis");
                                   }
                                   else if(result[i].matches("[A-Za-z][0-9]*")){
                                       System.out.println(result[i]+" is an identifier");
                                   }
                                        else if(result[i].matches("[0-9][A-Za-z]+")){
                                           System.out.println(result[i]+" is an invalid token");         
                                        }
                       }
                 }catch (FileNotFoundException ex){}
            }    
        }


if ( a > b )
    a = a + b ;
else a = a + c ;
a1 ;
1a ;

