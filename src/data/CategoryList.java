
package data;

import java.util.ArrayList;
import util.Inputer;

public class CategoryList extends ArrayList<Category>{  
    public void displayAllCategories() {
        for (Category c : this) {
            System.out.println(c);
        }
    }
    
    public Category getCategoryById(String id) {
        Category rs = null;
        for (Category ca : this) {
            if(ca.getCategoryId().equalsIgnoreCase(id))
                rs = ca;
        }
        return rs;
    }
    
    /**
     * add categories from file to list
     * @param fileName 
     */
    public void addCategoriesFromFile(String fileName) {
        ArrayList<String> rs = Inputer.readFile(fileName);
        //mỗi string trong rs tượng trưng cho 1 object Category, chúng ta sẽ
        //lấy nó ra, phân chia từng field và tạo thành object
        String categoryName = "";
        String categoryId = "";
        for (int i = 0; i < rs.size(); i++) {
            String[] tmp = rs.get(i).split(", ");
            categoryId = tmp[0];
            categoryName = tmp[1];
            this.add(new Category(categoryId, categoryName));
        }
    }
    
//    public static void main(String[] args) {
//        CategoryList c1 = new CategoryList();
//        c1.addCategoriesFromFile("Category.txt");
//        c1.displayAllCategories();
//    }
}
