package com.project.TasteTreasure.classes;

public class Recipe {
    
    private String label;
    private String source;
    private String uri;
    private String image;

    public Recipe() {}

    public Recipe(String label, String source, String uri) {
        this.label = label;
        this.source = source;
        this.uri = uri;
    }
    
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
}
