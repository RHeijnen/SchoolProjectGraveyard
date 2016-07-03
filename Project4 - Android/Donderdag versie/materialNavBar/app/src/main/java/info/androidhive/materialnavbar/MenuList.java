package info.androidhive.materialnavbar;

/**
 * Created by bart on 28-5-2015
 * 2015 - 05
 * Class represents data for the rows inside the Navigation drawer
 * the rows are specified inside: custom_row.xml
 */
public class MenuList {

    private String category;
    private int icon;

    public MenuList(){

    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
