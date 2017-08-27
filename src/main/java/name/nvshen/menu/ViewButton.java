package name.nvshen.menu;

/**
 * 视图型按钮
 * 
 * @author David
 *
 */
public class ViewButton extends Button {
    private String type = "view";
    private String url;

    public String getType() {
        return type;
    }

//    public void setType(String type) {
//        this.type = type;
//    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}