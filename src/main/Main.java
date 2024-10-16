package main;

import data.BrandList;
import data.CategoryList;
import data.ProductMap;
import data.Product;
import java.util.ArrayList;
import java.util.TreeSet;
import ui.Menu;
import util.FileName;
import util.Inputer;

public class Main {
    public static void main(String[] args) {
        //load data into app
        CategoryList cateList = new CategoryList();
        cateList.addCategoriesFromFile(FileName.CATEGORY_FILE);
        BrandList braList = new BrandList();
        braList.addBrandsFromFile(FileName.BRAND_FILE);
        ProductMap proMap = new ProductMap();
        proMap.addAllProductsFromFile(FileName.PRODUCT_FILE);
        
        int choice;
        do {
            //menu and get choice
            System.out.println("");
            Menu menu = new Menu("Bike Stores Management System");
            menu.addNewOption("1. Add a product.");
            menu.addNewOption("2. Search product by product name.");
            menu.addNewOption("3. Update product.");
            menu.addNewOption("4. Delete product.");
            menu.addNewOption("5. Save products to file.");
            menu.addNewOption("6. Print list products from the file.");
            menu.addNewOption("7. Find by brand name");
            menu.addNewOption("8. Quit.");
            
            menu.printMenu();
            choice = menu.getChoice();
            //process
            switch(choice) {
                case 1:
                    //add a product
                    int con = 0;
                    do {
                        proMap.addNewProduct(braList, cateList);
                        System.out.println("adding product successfully");
                        con = Inputer.inputAnIntegerInRange("Do you want to go back menu(0) or continue adding new product(1)??",
                                                             0, 1);
                    } while (con == 1);
                    break;
                case 2:
                    //search product by product name
                    
                    con = 0;
                    do {
                        String nameSearch = Inputer.inputAString("Input a name that you want to find");
                        TreeSet<Product> rs = proMap.searchProductByName(nameSearch);
                        if(rs == null)
                            System.out.println("Have no any Product");
                        else {
                            for (Product p : rs) {
                                p.printInfor(cateList, braList);
                            }
                        }
                        con = Inputer.inputAnIntegerInRange("Do you want to go back menu(0) or continue searching product(1)??",
                                                             0, 1);
                    } while (con == 1);
                    break;  
                case 3:
                    //update product
                    con = 0;
                    do {
                        System.out.println("Enter the id of product that you like to change in4");
                        String id = Inputer.inputId("^P[0-9]{3}$",
                                                    "Input product's id (Pxxx with x is a digit)",
                                                    "Product's id is not valid");
                        Product p = proMap.updateProductById(id, braList, cateList);
                        if(p == null) {
                            System.out.println("Update fail");
                        } else {
                            System.out.println("Update successfully");
                            System.out.println("The information after updating");
                            System.out.println(p.toString());
                        }
                        con = Inputer.inputAnIntegerInRange("Do you want to go back menu(0) or continue updating product(1)??",
                                                             0, 1);
                    } while(con == 1);
                    break;
                case 4:
                    //delete product
                    con = 0;
                    do {                        
                        System.out.println("Enter the id of product that you like to delete");
                        String id = Inputer.inputId("^P[0-9]{3}$",
                                                    "Input product's id (Pxxx with x is a digit)",
                                                    "Product's id is not valid");
                        Product p = proMap.deleteProductById(id);
                        if(p == null)
                            System.out.println("delete fail");
                        else {
                            System.out.println("delete successfully");
                            System.out.println("in4 of product you just deleted");
                            System.out.println(p);
                        }
                        con = Inputer.inputAnIntegerInRange("Do you want to go back menu(0) or continue deleting product(1)??",
                                                             0, 1);
                    } while (con == 1);
                    break;
                case 5:
                    //save products to the file
                    con = 0;
                    do {
                        proMap.writeToFile(FileName.PRODUCT_FILE);
                        System.out.println("Write to file successfully");
                        con = Inputer.inputAnIntegerInRange("Press 0 if you want to go back main menu and press 1 if you not",
                                                            0, 1);
                    }while(con == 1);
                    break;
                case 6:
                    //print list products from the file
                    con = 0;
                    do {
                        System.out.println("-------Bike Store--------");
                        proMap.displayAllProducts(cateList, braList);
                        con = Inputer.inputAnIntegerInRange("Press 0 if you want to go back main menu and press 1 if you want to print more",
                                                            0, 1);
                    } while(con == 1);
                    break;
                case 7:
                    String brandName = Inputer.inputAString("Input brand name");
                    ArrayList<Product> rs2 = proMap.findByBrandName(brandName, braList);
                    if(rs2.isEmpty()) {
                        System.out.println("Find nothing");
                    }else {
                        for (Product product : rs2) {
                            product.printInfor(cateList, braList);
                        }
                    }
                    break;
                default:
                    System.out.println("Thank you for using app, see you next time!!!!!");
            }
        } while(choice != 8);
    }
}
