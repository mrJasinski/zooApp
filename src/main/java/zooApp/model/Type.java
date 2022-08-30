package zooApp.model;

public enum Type
{
    ELEPHANT("Słoń", 20),
    LION("Lew", 11),
    RABBIT("Królik", 4);

    private String type;
    private int foodUsage;

    Type(String type, int foodUsage) {
        this.type = type;
        this.foodUsage = foodUsage;
    }

    @Override
    public String toString()
    {
        return this.type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getFoodUsage() {
        return foodUsage;
    }

    public void setFoodUsage(int foodUsage) {
        this.foodUsage = foodUsage;
    }
}
