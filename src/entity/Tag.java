package entity;

public class Tag {
    public String tagName;

    public Tag (String tagName){
        this.tagName = tagName;
    }

    @Override
    public String toString() {
        return tagName;
    }
}
