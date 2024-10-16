package data;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;
import util.Inputer;

public class ProductMap extends HashMap<String, Product> {
    public ArrayList<Product> findByBrandName(String brandName, BrandList braList) {
        ArrayList<Product> rs = new ArrayList<>();
        Collection<Product> tmp = this.values();
        String idOfBrand = "";
        boolean exist = false;
        for (Brand b : braList) {
            if(b.getBrandName().equalsIgnoreCase(brandName)) {
                idOfBrand = b.getBrandId();
                exist = true;
                break;
            }
        }
        if(exist) {
            for (Product p : tmp) {
                if(p.getBrandId().equalsIgnoreCase(idOfBrand)) {
                    rs.add(p);
                }
            }
        }
        return rs;
    }
    
    
    /**
     * delete a product in list
     * @param id id of the product that you want to delete
     * @return null if the product does not exist or you do not want to continue deleting
     * return product when deleting successfully
     */
    public Product deleteProductById(String id) {
        Product p = findProductById(id);
        if(p == null)
            System.out.println("Product does not exist");
        else {
            int y = Inputer.inputAnIntegerInRange("Are you sure to delete(0/1)", 0, 1);
            if(y == 1) {
                this.remove(id);
            }else
                p = null; //không delete thì trả null
        }
        return p;
    }
    
    public Product updateProductById(String id, BrandList braList, CategoryList cateList) {
        Product p = findProductById(id);
        if(p == null) {
            System.out.println("Product does not exist");
        }
        else {
            System.out.println("The information of the product that you want to update");
            System.out.println(p.toString());
            Scanner sc = new Scanner(System.in);
            //update name
            System.out.println("Input new name(press enter when you do not want to update name)");
            String nName = sc.nextLine();
            p.setName(nName);
            //update brandId
            boolean con = false;
            do {
                System.out.println("Input new brandId(press enter when you do not want to update brandId)");
                String nBrandId = sc.nextLine().toUpperCase();
                if(!nBrandId.isEmpty()) {
                    if(nBrandId.matches("^B[0-9]{3}$")) { //thỏa mãn format
                        if(braList.getBrandById(nBrandId) == null) {
                            System.out.println("Brand Id does not exist, type again");
                            con = true;
                        } else {
                            p.setBrandId(nBrandId);
                            con = false;
                        }
                    }else {
                        System.out.println("Your inputed brand id is invalid, type again");
                        con = true;
                    }
                } else {
                    con = false;
                }
            } while(con);
            //update categoryId
            do {
                System.out.println("Input new categoryId(press enter when you do not want to update categoryId)");
                String nCateId = sc.nextLine().toUpperCase();
                if(!nCateId.isEmpty()) {
                    if(nCateId.matches("^C[0-9]{3}$")) {
                        if(cateList.getCategoryById(nCateId) == null) {
                            System.out.println("Category id does not exist, type again");
                            con = true;
                        }else {
                            p.setCategoryId(nCateId);
                            con = false;
                        }
                    }else {
                        System.out.println("Your inputed category id is invalid, type again");
                        con = true;
                    }
                } else {
                    con = false;
                }
            } while(con);
            //update model year
            do {
                Date d = new Date();
                System.out.println("Input new model year (>0 and " + (d.getYear() + 1900) + " )(press enter when you do not want to update model year)");
                String mY = sc.nextLine();
                if(!mY.isEmpty()) {
                    try {
                        int nModelYear = Integer.parseInt(mY);
                        
                        if(nModelYear <= 0 || nModelYear > d.getYear() + 1900) throw new Exception();
                        p.setModelYear(nModelYear);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Not an integer, try again");
                    } catch (Exception e) {
                        System.out.println("Your input is out of 0 and " + (d.getYear() + 1900));
                    }
                } else
                    break;
            } while(true);
            
            //update listPrice
            
            do {
                System.out.println("Input new listPrice (>0)(press enter when you do not want to update listPrice)");
                String lP = sc.nextLine();
                if(!lP.isEmpty()) {
                    try {
                        double nListPrice = Double.parseDouble(lP);
                        if(nListPrice <= 0) throw new Exception();
                        p.setListPrice(nListPrice);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Not a double, try again");
                    } catch (Exception e) {
                        System.out.println("Your input is less than or equal 0");
                    }
                } else 
                    break;
            } while(true);
            
        }
        
        return p;
    }
    
    /**
     * find product by id
     * @param id
     * @return a product if inputed id exist or null if inputed id not exist
     */
    public Product findProductById(String id) {
        Product rs = null;
        Collection<Product> tmp = this.values();
        for (Product p : tmp) {
            if(p.getId().equalsIgnoreCase(id)) {
                rs = p;
                break;
            }
        }
        return rs;
    }
    
    /**
     * finding products have name that contain name parameter 
     * @param name
     * @return a set of product is sorted by ascending by model year
     */
    public TreeSet<Product> searchProductByName(String name) {
        name = name.toUpperCase();
        TreeSet<Product> rs = new TreeSet<>();
        Collection<Product> tmp = this.values();
        for (Product p : tmp) {
            if(p.getName().toUpperCase().contains(name)) {
                rs.add(p);
            }
        }
        if(rs.isEmpty()) {
            rs = null; 
        }
        return rs;
    }
    
    /**
     * add new Product
     * @param braList to print brand list for user to choose 
     * @param cateList to print category list for user to choose
     */ 
    public void addNewProduct(BrandList braList, CategoryList cateList) {
        String id, name, brandId, categoryId;
        int modelYear;
        double listPrice;
        
        boolean loop = false;
        //input id done
//        do {
//            loop = false;
//            id = Inputer.inputId("^P[0-9]{3}$",
//                                    "Input product's id (Pxxx with x is a digit)",
//                                    "Product's id is not valid");
//            Collection<Product> pros = this.values();
//            for (Product pro : pros) {
//                if(pro.getId().equalsIgnoreCase(id)) {
//                    loop = true;
//                    break;
//                }
//            }
//            if (loop)
//                System.out.println("Product's id exist");
//        } while (loop);
        
        //generate id
        Collection<Product> tmp = this.values();
        ArrayList<Product> proList = new ArrayList<>();
        for (Product p : tmp) { //pass all elements from collection to list
            proList.add(p);
        }
        Collections.sort(proList, Product.compareProduct2); //sort by id ascending
        String endId = proList.get(proList.size() - 1).getId(); //find the id of the end element
        int indexLastId = Integer.parseInt(endId.substring(1));
        id = String.format("P%03d", indexLastId + 1);
        
        //input name
        name = Inputer.inputAString("Input product's name: ");
        
        //input brandId
        Brand bra = null;
        do {
            braList.displayAllBrands();
            loop = false;
            brandId = Inputer.inputId("^B[0-9]{3}$",
                                    "Input brand's id (Bxxx with x is a digit)",
                                    "Brand's id is not valid");
            bra = braList.getBrandById(brandId);
            if (bra == null) {
                System.out.println("Brand's id does not exist");
                loop = true;
            }
        } while (loop);
        
        //input categoryId
        Category cate = null;
        do {
            cateList.displayAllCategories();
            loop = false;
            categoryId = Inputer.inputId("^C[0-9]{3}$",
                                    "Input category's id (Cxxx with x is a digit)",
                                    "Category's id is not valid");
            cate = cateList.getCategoryById(categoryId);
            if (cate == null) {
                System.out.println("category's id does not exist");
                loop = true;
            }
        } while (loop);
        
        //input model year
        Date d = new Date();
        String msg = "input model year ( > 0 and <= " + (d.getYear() + 1900) + ")"; 
        modelYear = Inputer.inputAnIntegerInRange(msg, 1, d.getYear() + 1900);
        
        //input list price
        listPrice = Inputer.inputADouble("input list price (>0)", 0);
        this.put(id, new Product(id, name, brandId, categoryId, modelYear, listPrice));
    }
    
    /**
     * display all products in list that does not contain brand's name and category's name
     */
    public void displayAllProducts() {
        Collection<Product> pros = values();
        for (Product pro : pros) {
            System.out.println(pro.toString());
        }
    }
    
    /**
     * display all products in list that contain brand's name and category's name
     * @param catList
     * @param braList 
     */
    public void displayAllProducts(CategoryList catList, BrandList braList) {
        Collection<Product> pros = values();
        ArrayList<Product> tmp = new ArrayList<>();
        for (Product pro : pros) {
            tmp.add(pro);
        }
        Collections.sort(tmp, Product.compareProduct);
        for (Product p : tmp) {
            p.printInfor(catList, braList);
        }
    }
    
    /**
     * add all products that is loaded from file to this ProductMap
     *
     * @param fileName
     */
    public void addAllProductsFromFile(String fileName) {
        ArrayList<String> rs = Inputer.readFile(fileName);
        String id;
        String name;
        String brandId;
        String categoryId;
        int modelYear;
        double listPrice;
        for (int i = 0; i < rs.size(); i++) {
            //String format: id, name, brand_id, category_id, model_year, list_price
            String[] tmp = rs.get(i).split(", ");
            id = tmp[0];
            name = tmp[1];
            brandId = tmp[2];
            categoryId = tmp[3];
            modelYear = Integer.parseInt(tmp[4]);
            listPrice = Double.parseDouble(tmp[5]);
            Product p = new Product(id, name, brandId, categoryId, modelYear, listPrice);
            this.put(id, p);
        }
    }
    
//    public static void main(String[] args) {
//        ProductMap tmp = new ProductMap();
//        tmp.addAllProductsFromFile("Product.txt");
//        tmp.displayAllProducts();
//        System.out.println("----------------------");
//        CategoryList tmp1 = new CategoryList();
//        tmp1.addCategoriesFromFile("Category.txt");
//        BrandList tmp2 = new BrandList();
//        tmp2.addBrandsFromFile("Brand.txt");
//        
//        tmp.addNewProduct(tmp2, tmp1);
//        System.out.println("----------------------");
//        tmp.addNewProduct(tmp2, tmp1);
//        System.out.println("----------------------");
//        tmp.addNewProduct(tmp2, tmp1);
//        System.out.println("----------------------");
//        
//        tmp.displayAllProducts(tmp1, tmp2);
//    }
    
    
    public void writeToFile(String fileName) {
        Collection<Product> tmp = this.values();
        FileWriter fW = null;
        BufferedWriter bW = null;
        try {
            fW = new FileWriter(fileName);
            bW = new BufferedWriter(fW);
            for (Product p : tmp) {
                bW.write(p.toString());
                bW.newLine();
            }
        } catch (Exception e) {
            System.out.println("writing file error");
        } finally {
            try {
                if(bW != null) bW.close();
                if(fW != null) fW.close();
            } catch (Exception e) {
                System.out.println("Closing file error");
            }
        }
    }
}
