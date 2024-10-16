
package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Inputer {
    private static Scanner sc = new Scanner(System.in);
    
    /**
     * check whether your input is an integer or not 
     * @param inputMsg
     * @return an integer
     */
    public static int inputAnInteger(String inputMsg) {
        int rs = 0;
        boolean loop;
        do {
            try {
                System.out.println(inputMsg);
                rs = Integer.parseInt(sc.nextLine());
                loop = false;
            } catch (NumberFormatException e) {
                System.out.println("Your input is not an integer");
                loop = true;
            }
            
        } while(loop);
        return rs;
    }
    
    /**
     * check whether input is an integer that is greater than lowerBound or not
     * @param inputMsg
     * @param lowerBound
     * @return an integer > lowerBound
     */
    public static int inputAnInteger(String inputMsg, int lowerBound) {
        int rs = 0;
        boolean loop;
        do {
            loop = false;
            rs = inputAnInteger(inputMsg);
            if (rs <= lowerBound) {
                System.out.println("Your integer is not greater than " + lowerBound);
                loop = true;
            }
        } while(loop);
        return rs;
    }
    
    /**
     * input an integer that is limit in range
     * @param inputMsg: the message that you want to print 
     * @param lowerBound: the minimum value of valid input
     * @param upperBound: the maximum vale of valid input
     * @return the valid input
     */
    public static int inputAnIntegerInRange(String inputMsg, int lowerBound, int upperBound) {
        int rs = 0;
        boolean isLoop = false;
        if(lowerBound > upperBound) {
            int tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;
        }
        
        do {
            isLoop = false;
            try {
                System.out.println(inputMsg); //nếu in yêu cầu nhập ở ngoài thì sẽ không lặp được
                rs = Integer.parseInt(sc.nextLine());//có thể có lỗi do nhập chuỗi không phải dạng digit
                if(rs < lowerBound || rs > upperBound)
                    throw new Exception(); //lỗi ngoài khoảng
            } catch (NumberFormatException e) {
                System.out.println("Your input is not an integer");
                isLoop = true;
            } catch (Exception ee) {
                //System.out.println(errMsg);
                System.out.println("Your input is out of " + lowerBound + " and " + upperBound);
                isLoop = true;
            } 
        } while (isLoop);
        return rs;
    }
    
    /**
     * check whether your input is a double or not 
     * @param inputMsg
     * @return a double
     */
    public static double inputADouble(String inputMsg) {
        double rs = 0;
        boolean loop=false;
        do {
            try {
                System.out.println(inputMsg);
                rs = Double.parseDouble(sc.nextLine());
                loop = false;
            } catch (NumberFormatException e) {
                System.out.println("Your input is not a double");
                loop = true;
            }
            
        } while(loop);
        return rs;
    }
    
    /**
     * check whether input is a double that is greater than lowerBound or not
     * @param inputMsg
     * @param lowerBound
     * @return 
     */
    public static double inputADouble(String inputMsg, double lowerBound) {
        double rs = 0;
        boolean loop;
        do {
            loop = false;
            rs = inputADouble(inputMsg);
            if (rs <= lowerBound) {
                System.out.println("Your double is not greater than " + lowerBound);
                loop = true;
            }
        } while(loop);
        return rs;
    }
    
    /**
     * input a string that is not empty
     * @param inputMsg
     * @return 
     */
    public static String inputAString(String inputMsg) {
        String rs;
        boolean loop;
        do {
            loop = false;
            System.out.println(inputMsg);
            rs = sc.nextLine();
            rs = rs.trim();
            if(rs.isEmpty()) {
                System.out.println("Your inputed string is empty");
                loop = true;
            }
        } while (loop);
        return rs;
    }
    
    /**
     * input an id that is not empty and matches with pattern parameter
     * @param pattern
     * @param inputMsg
     * @param inputErr
     * @return 
     */
    public static String inputId(String pattern, String inputMsg, String inputErr) {
        boolean loop = false;
        String id;
        do {
            loop = false;
            id = inputAString(inputMsg).toUpperCase();
            //id = id.toUpperCase(); //id nhập vào luôn luôn uppercase
            //check pattern of id wheater it is valid or not
            if(!id.matches(pattern)) {
                System.out.println(inputErr);
                loop = true;
            }
        } while (loop);
        return id;
    }
    
//    public static String inputId(String pattern, String inputMsg, String inputErr, boolean beEmpty) {
//        boolean loop = false;
//        String id;
//        if(beEmpty) {
//            do {
//                loop = false;
//                System.out.println(inputMsg);
//                id = sc.nextLine();
//                id = id.toUpperCase(); //id nhập vào luôn luôn uppercase
//                //check pattern of id wheater it is valid or not
//                if(!id.matches(pattern)) {
//                    System.out.println(inputErr);
//                    loop = true;
//                }
//            } while (loop);
//        } else { //beEmpty = false --> id khong duoc phep rong
//            id = inputId(pattern, inputMsg, inputErr);
//        }
//        return id;
//    }
    
    
    /** 
     * method help to read file
     * @param fileName
     * @return an arrayList of string is read from file
     */
    public static ArrayList<String> readFile(String fileName) {
        ArrayList<String> rs = new ArrayList<>();
        FileReader fR = null;
        BufferedReader bR = null;
        try {
            fR = new FileReader(fileName);
            bR = new BufferedReader(fR);
            while(bR.ready()) {
                rs.add(bR.readLine());
            }
        } catch (Exception e) {
            System.out.println("Reading file is error");
        } finally {
            try {
                if(fR != null) fR.close();
                if(bR != null) bR.close();
            } catch (Exception e) {
                System.out.println("Reading file is error");
            }
        }
        return rs;
    }
    
    
    
//    public static void main(String[] args) {
//        int rs = inputAnInteger("Nhap vao so nguyen trong khoang 1, 10", 1, 10);
//        System.out.println("Rs = " + rs);
//    }
}
