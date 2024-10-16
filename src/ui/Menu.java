
package ui;

import java.util.ArrayList;
import util.Inputer;

public class Menu {
    private String menuTitle;
    private ArrayList<String> optionList = new ArrayList<>();

    public Menu(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public ArrayList<String> getOptionList() {
        return optionList;
    }

    public void setOptionList(ArrayList<String> optionList) {
        this.optionList = optionList;
    }
    
    /**
     * add new option to menu
     * @param newOption 
     */
    public void addNewOption(String newOption) {
        boolean exist = false;
        for (String str : optionList) {
            if(str.equalsIgnoreCase(newOption)) {
                exist = true;
                break;
            }
            
            //---   
//            exist = str.equalsIgnoreCase(newOption);
//            if (exist) break;
        }
        if(exist) {
            System.out.println("Your inputed option is exist");
        } else {
            optionList.add(newOption);
        }
    }
    
    
    public int getChoice() {
        int quantity = optionList.size();
        int choice = 0;
        if (quantity == 0)
            System.out.println("your menu has no option!!!");
        else {
            String inputMsg = "Input your choice between " + 1 + " and " + quantity;
            choice = Inputer.inputAnIntegerInRange(inputMsg, 1, quantity);
        }
        return choice;
    }
    
    public void printMenu() {
        if (optionList.isEmpty()) {
            System.out.println("Menu has no option");
            return;
        }
        System.out.println("------------------------");
        System.out.println("Welcome to " + menuTitle);
        for (String str : optionList) {
            System.out.println(str);
        }
    }

//    public static void main(String[] args) {
//        Menu m = new Menu("Menu ban xe dap");
//        m.addNewOption("tao la 1");
//        m.addNewOption("tao la 2");
//        m.addNewOption("tao la 3");
//        m.addNewOption("tao la 4");
//        
//        m.printMenu();
//        m.getChoice(); //gọi empty sẽ in ra no choice 2 lần
//    }
}
