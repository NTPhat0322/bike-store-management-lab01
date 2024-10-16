
package data;

import java.util.ArrayList;
import util.Inputer;

public class BrandList extends ArrayList<Brand>{
    public void displayAllBrands() {
        for (Brand b : this) {
            System.out.println(b);
        }
    }
    
    public Brand getBrandById(String id) {
        Brand rs = null;
        for (Brand br : this) {
            if(br.getBrandId().equalsIgnoreCase(id))
                rs = br;
        }
        return rs;
    }
    
    /**
     * add all brands from file to this array
     * @param fileName 
     */
    public void addBrandsFromFile(String fileName) {
        ArrayList<String> rs = Inputer.readFile(fileName);
        String brandId = "";
        String brandName = "";
        String brandCountry = "";
        for (int i = 0; i < rs.size(); i++) {
            String[] tmp = rs.get(i).split(", ");
            brandId = tmp[0];
            brandName = tmp[1];
            brandCountry = tmp[2];
            this.add(new Brand(brandId, brandName, brandCountry));
        }
    }
    
//    public static void main(String[] args) {
//        BrandList tmp = new BrandList();
//        tmp.addBrandsFromFile("Brand.txt");
//        tmp.displayAllBrands();
//    }
}
