package com.milatcam.site.model;

//import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ItemAddDTO {

    @NotEmpty
    @Size(min = 2, max = 50)
    private String itemName;

    @NotEmpty
    @Size(min = 2, max = 50)
    private String itemType;

    @NotEmpty
    @Size(min = 2, max = 50)
    private String itemlength;

    @NotEmpty
    @Size(min = 2, max = 50)
    private String itemwidth;

    @NotNull
    private int amount = 1; //default

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemlength() {
        return itemlength;
    }

    public void setItemlength(String itemlength) {
        this.itemlength = itemlength;
    }

    public String getItemwidth() {
        return itemwidth;
    }

    public void setItemwidth(String itemwidth) {
        this.itemwidth = itemwidth;
    }
}
