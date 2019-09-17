package turncodr.rocks;

public class Coordinate{

    private int firstLevelIndex;
    private int secondLevelIndex;

    public Coordinate(int firstLevelIndex, int secondLevelIndex){
        this.firstLevelIndex = firstLevelIndex;
        this.secondLevelIndex = secondLevelIndex;
    }

    public int getFirstLevelIndex(){
        return firstLevelIndex;
    }

    public int getSecondLevelIndex(){
        return secondLevelIndex;
    }

    public void setFirstLevelIndex(int newFirstLevelIndex){
        firstLevelIndex = newFirstLevelIndex;
    }

    public void setSecondLevelIndex(int newSecondLevelIndex){
        secondLevelIndex = newSecondLevelIndex;
    }

    public void increaseFirstLevelIndexByOne(){
        firstLevelIndex++;
    }

    public void increaseSecondevelIndexByOne(){
        firstLevelIndex++;
    }


}