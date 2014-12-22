package ApiTests.ObjectClasses;

// * Created for W-xmlm by Fill on 11.12.2014.
public class Product {
    private int id;
    private int categoryId;
    private int ownerId;
    private int creatorId;
    private String title;
    private String description;
    private double price;
    private int status;
    private int type;
    private String createdDate;
    private Object imageUrl;
    private Object available;
    private Object discSpace;
    private Object timeOnline;
    private int basicIncome;
    private int basicIncomePeriod;
    private double profit;
    private int investmentPeriod;
    private String start;
    private int[] requiredForTrial;
    private String trialPeriod;
    private String quotaPrefix;
    private int quota;
    private String quotaMeasurement;
    private int serviceId;
    // Constructors
    public Product(int id, int categoryId, int ownerId, int creatorId, String title, String description, double price, int status, int type, String createdDate, Object imageUrl, Object available, Object discSpace, Object timeOnline, int basicIncome, int basicIncomePeriod, double profit, int investmentPeriod, String start)
    {
        this.setId(id);
        this.setCategoryId(categoryId);
        this.setOwnerId(ownerId);
        this.setCreatorId(creatorId);
        this.setTitle(title);
        this.setDescription(description);
        this.setPrice(price);
        this.setStatus(status);
        this.setType(type);
        this.setCreatedDate(createdDate);
        this.setImageUrl(imageUrl);
        this.setAvailable(available);
        this.setDiscSpace(discSpace);
        this.setTimeOnline(timeOnline);
        this.setBasicIncome(basicIncome);
        this.setBasicIncomePeriod(basicIncomePeriod);
        this.setProfit(profit);
        this.setInvestmentPeriod(investmentPeriod);
        this.setStart(start);
    }
    public Product(int id, int categoryId, int ownerId, int creatorId, String title, String description, double price, int status, int type, String createdDate, Object imageUrl, int[] requiredForTrial, String trialPeriod, String quotaPrefix, int quota, String quotaMeasurement, int serviceId)
    {
        this.setId(id);
        this.setCategoryId(categoryId);
        this.setOwnerId(ownerId);
        this.setCreatorId(creatorId);
        this.setTitle(title);
        this.setDescription(description);
        this.setPrice(price);
        this.setStatus(status);
        this.setType(type);
        this.setCreatedDate(createdDate);
        this.setImageUrl(imageUrl);
        this.setRequiredForTrial(requiredForTrial);
        this.setTrialPeriod(trialPeriod);
        this.setQuotaPrefix(quotaPrefix);
        this.setQuota(quota);
        this.setQuotaMeasurement(quotaMeasurement);
        this.setServiceId(serviceId);
    }
    // Getters
    public int getId() {return id;}
    public int getCategoryId() {return categoryId;}
    public int getOwnerId() {return ownerId;}
    public int getCreatorId() {return creatorId;}
    public String getTitle() {return title;}
    public String getDescription() {return description;}
    public double getPrice() {return price;}
    public int getStatus() {return status;}
    public int getType() {return type;}
    public String getCreatedDate() {return createdDate;}
    public Object getImageUrl() {return imageUrl;}
    public Object getAvailable() {return available;}
    public Object getDiscSpace() {return discSpace;}
    public Object getTimeOnline() {return timeOnline;}
    public int getBasicIncome() {return basicIncome;}
    public int getBasicIncomePeriod() {return basicIncomePeriod;}
    public double getProfit() {return profit;}
    public int getInvestmentPeriod() {return investmentPeriod;}
    public String getStart() {return start;}
    public int[] getRequiredForTrial() {return requiredForTrial;}
    public int getRequiredForTrial(int i) {return requiredForTrial[i];}
    public String getTrialPeriod() {return trialPeriod;}
    public String getQuotaPrefix() {return quotaPrefix;}
    public int getQuota() {return quota;}
    public String getQuotaMeasurement() {return quotaMeasurement;}
    public int getServiceId() {return serviceId;}

    // Setters
    public void setId(int a) {this.id = a;}
    public void setCategoryId(int a) {this.categoryId = a;}
    public void setOwnerId(int a) {this.ownerId = a;}
    public void setCreatorId(int a) {this.creatorId = a;}
    public void setTitle(String a) {this.title = a;}
    public void setDescription(String a) {this.description = a;}
    public void setPrice(double a) {this.price = (double)((Math.round(a * 100))/100);}
    public void setStatus(int a) {this.status = a;}
    public void setType(int a) {this.type = a;}
    public void setCreatedDate(String a) {this.createdDate = a;}
    public void setImageUrl(Object a) {this.imageUrl = a;}
    public void setAvailable(Object a) {this.available = a;}
    public void setDiscSpace(Object a) {this.discSpace = a;}
    public void setTimeOnline(Object a) {this.timeOnline = a;}
    public void setBasicIncome(int a) {this.basicIncome = a;}
    public void setBasicIncomePeriod(int a) {this.basicIncomePeriod = a;}
    public void setProfit(double a) {this.profit = (double)((Math.round(a * 100))/100);}
    public void setInvestmentPeriod(int a) {this.investmentPeriod = a;}
    public void setStart(String a) {this.start = a;}
    public void setRequiredForTrial(int[] a) {this.requiredForTrial = a;}
    public void setRequiredForTrial(int elementNumber, int a) {this.requiredForTrial[elementNumber] = a;}
    public void setTrialPeriod(String a) {this.trialPeriod = a;}
    public void setQuotaPrefix(String a) {this.quotaPrefix = a;}
    public void setQuota(int a) {this.quota = a;}
    public void setQuotaMeasurement(String a) {this.quotaMeasurement = a;}
    public void setServiceId(int a) {this.serviceId = a;}

    public boolean equalsExceptUpdatedDate(Product a)
    {
        if (this == a)
            return true;
        if (a == null) {
            System.out.println("1");
            return false;
        }
        if (getClass() != a.getClass()) {
            System.out.println("2");
            return false;
        }
        if (getId() != a.getId()) {
            System.out.println("3");
            return false;
        }
        if (getCategoryId() != a.getCategoryId()) {
            System.out.println("4");
            return false;
        }
        if (getOwnerId() != a.getOwnerId()){
            System.out.println("5");
            return false;
        }
        if (getCreatorId() != a.getCreatorId()) {
            System.out.println("6");
            return false;
        }
        if (!getTitle().equals(a.getTitle())) {
            System.out.println("7");
            return false;
        }
        if (!getDescription().equals(a.getDescription())) {
            System.out.println("8");
            return false;
        }
        if (getPrice() != a.getPrice()) {
            System.out.println("9");
            return false;
        }
        if (getStatus() != a.getStatus()) {
            System.out.println("10");
            return false;
        }
        if (getType() != a.getType()) {
            System.out.println("11");
            return false;
        }
        if (!getCreatedDate().equals(a.getCreatedDate())) {
            System.out.println("12");
            return false;
        }
        if (!(getImageUrl() == a.getImageUrl() || getImageUrl().equals(a.getImageUrl()))) {
            System.out.println(getImageUrl() == a.getImageUrl());
            System.out.println(getImageUrl().equals(a.getImageUrl()));
            System.out.println(getImageUrl() == null && a.getImageUrl() == null);
            System.out.println(getImageUrl() == null);
            System.out.println(a.getImageUrl() == null);
            System.out.println(getImageUrl().equals(null));
            System.out.println(a.getImageUrl().equals(null));
            System.out.println(getImageUrl());
            System.out.println(a.getImageUrl());
            System.out.println("13");
            return false;
        }
        if (getCategoryId() == 1)
        {
            if (!String.valueOf(getAvailable()).equals(String.valueOf(a.getAvailable()))) {
                System.out.println(getAvailable());
                System.out.println(a.getAvailable());
                System.out.println(getAvailable().getClass());
                System.out.println(a.getAvailable().getClass());
                System.out.println("14");
                return false;
            }
            if (getDiscSpace() != a.getDiscSpace() && !getDiscSpace().equals(a.getDiscSpace())) {
                System.out.println("15");
                return false;
            }
            if (getTimeOnline() != a.getTimeOnline() && !getTimeOnline().equals(a.getTimeOnline())) {
                System.out.println("16");
                return false;
            }
            if (getBasicIncome() != a.getBasicIncome()) {
                System.out.println("17");
                return false;
            }
            if (getBasicIncomePeriod() != a.getBasicIncomePeriod()) {
                System.out.println("17");
                return false;
            }
            if (getProfit() != a.getProfit()) {
                System.out.println("18");
                return false;
            }
            if (getInvestmentPeriod() != a.getInvestmentPeriod()) {
                System.out.println("19");
                return false;
            }
            if (!getStart().equals(a.getStart())) {
                System.out.println("20");
                return false;
            }
        }
        else if (getCategoryId() == 2) {
            for (int i = 0; i < getRequiredForTrial().length; i++) {
                if (getRequiredForTrial(i) != a.getRequiredForTrial(i)) {
                    System.out.println("21");
                    return false;
                }
            }
            if (!getTrialPeriod().equals(a.getTrialPeriod())) {
                System.out.println("22");
                return false;
            }
            if (!getQuotaPrefix().equals(a.getQuotaPrefix())) {
                System.out.println(getQuotaPrefix());
                System.out.println(a.getQuotaPrefix());
                System.out.println("23");
                return false;
            }
            if (getQuota() != a.getQuota()) {
                System.out.println("24");
                return false;
            }
            if (!getQuotaMeasurement().equals(a.getQuotaMeasurement())) {
                System.out.println(getQuotaMeasurement());
                System.out.println(a.getQuotaMeasurement());
                System.out.println("25");
                return false;
            }
            if (getServiceId() != a.getServiceId()) {
                System.out.println("26");
                return false;
            }
        }
        return true;
    }
}