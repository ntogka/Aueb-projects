package com.example.georgiosmoschovis.aceindemo.ui;


/**
 * This class implements a dymamic graphical data container for Insurances.
 */
public class Preview_DATA {
    private int nodalID;
    private int ImageId;
    private String name;
    private String description;

    public Preview_DATA(int imageId, String name, String description) {
        ImageId = imageId;
        this.name = name;
        this.description = description;
    }

    public void setID(int ID) {
        this.nodalID = ID;
    }

    public int getID() {
        return this.nodalID;
    }

    public int getImageId() {
        return ImageId;
    }

    public void setImageId(int imageId) {
        ImageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
