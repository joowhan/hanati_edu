package kr.co.kopo.classEx.exception_19_04w1;

import java.io.FileWriter;
import java.io.IOException;

public class ExceptionEx {
    public static void main(String[] args) {
        FileWriter fw = null;
        try{
            fw = new FileWriter("data.txt");


        }
        catch (IOException e){
            System.out.println("잠이 온다.");
        }
        finally {
            try {
                fw.close();
            }
            catch (IOException e){
                e.printStackTrace();;
            }
        }
        String[] strArr = {"100","200", "12fd"};

        for(int i=0;i<strArr.length;i++){
            try{
                int iValue = Integer.parseInt(strArr[i]);
                System.out.println(iValue);
            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println(e.getMessage());
            } catch (NumberFormatException e ){
                System.out.println(e.getMessage());
            }
        }
        try {
            findClass();
            throw new InsufficientException("집 가고 싶다.");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        } catch (InsufficientException e) {
            System.out.println(e.getMessage());
        }

        Person person = new Person();
        try{
            person.setAge(-30);
        }catch(InsufficientException e){
            System.out.println(e.getMessage());
        }


    }
    public static void findClass() throws ClassNotFoundException{
        Class.forName("java.lang.String");
    }
}
