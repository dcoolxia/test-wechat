package name.nvshen.menu;

/**
 * 点击型按钮
 * 
 * @author David
 *
 */
public class ClickButton extends Button {
    private String type = "click";
    private String key;

    public String getType() {
        return type;
    }

//    public void setType(String type) {
//        this.type = type;
//    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}