
package data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

public class Product implements Comparable<Product>, Serializable{
    private String id;
    private String name;
    private String brandId;
    private String categoryId;
    private int modelYear;
    private double listPrice;

    public Product(String id, String name, String brandId, String categoryId, int modelYear, double listPrice) {
        this.id = id;
        this.name = name;
        this.brandId = brandId;
        this.categoryId = categoryId;
        this.modelYear = modelYear;
        this.listPrice = listPrice;
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(!name.isEmpty()) {
            this.name = name;
        }
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        if(!brandId.isEmpty()) {
            this.brandId = brandId;
        }
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        if(!categoryId.isEmpty()) {
            this.categoryId = categoryId;
        }
    }

    public int getModelYear() {
        return modelYear;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    public double getListPrice() {
        return listPrice;
    }

    public void setListPrice(double listPrice) {
        this.listPrice = listPrice;
    }

    @Override
    public String toString() {
        return id + ", " + name + ", " + brandId + ", " + categoryId + ", " + modelYear + ", " + listPrice;
    }
    
    

    /**
     * print information of product that contains brand_name and category_name
     * @param catList
     * @param braList 
     */
    public void printInfor(CategoryList catList, BrandList braList) {
        String brandName = braList.getBrandById(brandId).getBrandName();
        String catName = catList.getCategoryById(categoryId).getCategoryName();
        System.out.println(id + ", " + name + ", " + brandName + ", " + catName + ", " + modelYear + ", " + listPrice + "$");
    }
    

    /**
     * sort by model year by ascending
     * @param o
     * @return 
     */
    @Override
    public int compareTo(Product o) {
        if(this.modelYear > o.modelYear) return 1;
        else if(this.modelYear < o.modelYear) return -1;
        else return 0;
    }

    /**
     * contains compare method to sort Product List by list price descending
     * if the same price, then order by name ascending
     */
    public static Comparator<Product> compareProduct = new Comparator<Product>() {
        @Override
        public int compare(Product o1, Product o2) {
            if(o1.getListPrice() < o2.getListPrice()) return 1;
            else if(o1.getListPrice() == o2.getListPrice()) {
                return o1.getName().compareTo(o2.getName());//o1 > o2 -- return > 0 ---> swap
            } else 
                return -1;
        }
    };
    
    /**
     * order by id ascending
     */
    public static Comparator<Product> compareProduct2 = new Comparator<Product>() {
        @Override
        public int compare(Product o1, Product o2) {
            return o1.getId().compareTo(o2.getId());
        }
    };
}


//    public static void main(String[] args) {
//        CategoryList tmp = new CategoryList();
//        tmp.addCategoriesFromFile("Category.txt");
//        System.out.println(tmp.getCategoryById("C001").getCategoryName());
//        BrandList tmp2 = new BrandList();
//        tmp2.addBrandsFromFile("Brand.txt");
//        System.out.println(tmp2.getBrandById("B001").getBrandName());
//    }