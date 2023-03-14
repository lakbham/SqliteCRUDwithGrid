package com.lak.android.sqlcrudactivitywithimage;

public class Recipe{
    private int id;
    private String name;
    private String ingred;
    private int  preptime;
    private int nutvalue;
    private byte[] image;

    public Recipe(String name, String ingred, Integer preptime, Integer nutvalue, byte[] image, int id) {
        this.name = name;
        this.ingred = ingred;
        this.preptime = preptime;
        this.nutvalue = nutvalue;
        this.image = image;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngred() {
        return ingred;
    }

    public void setIngred(String ingred) {
        this.ingred = ingred;
    }

    public int getPreptime() {
        return preptime;
    }

    public void setPreptime(int preptime) {
        this.preptime = preptime;
    }

    public int getNutvalue() {
        return nutvalue;
    }

    public void setNutvalue(int nutvalue) {
        this.nutvalue = nutvalue;
    }


    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}





