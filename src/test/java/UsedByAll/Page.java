package UsedByAll;

// * Created for W-xmlm by Fill on 26.03.2015.
public class Page {
    private String route;
    private String pageName;
    private String[] elements;

    // Сеттеры
    public void setRoute(String a)
    { this.route = a; }
    public void setPageName(String a)
    { this.pageName = a; }
    public void setElements(String[] a)
    { this.elements = a; }
    public void setElement(int elementNumber, String a)
    { this.elements[elementNumber] = a; }

    // Геттеры
    public String getRoute()
    { return route; }
    public String getPageName()
    { return pageName; }
    public String[] getElements()
    { return elements; }
    public String getElement(int elementNumber)
    { return elements[elementNumber]; }
}