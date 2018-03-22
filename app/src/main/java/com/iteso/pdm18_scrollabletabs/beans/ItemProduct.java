package com.iteso.pdm18_scrollabletabs.beans;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemProduct implements Parcelable{
    private String title;
    private String description;
    private int image;
    private int code;
    private Store store;
    private Category category;


    public ItemProduct() {

    }

    public ItemProduct(String title, String description, int image, int code, Store store, Category category) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.code = code;
        this.store = store;
        this.category = category;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeInt(this.image);
        dest.writeInt(this.code);
        dest.writeParcelable(this.store, flags);
        dest.writeParcelable(this.category, flags);
    }

    protected ItemProduct(Parcel in) {
        this.title = in.readString();
        this.description = in.readString();
        this.image = in.readInt();
        this.code = in.readInt();
        this.store = in.readParcelable(Store.class.getClassLoader());
        this.category = in.readParcelable(Category.class.getClassLoader());
    }

    public static final Creator<ItemProduct> CREATOR = new Creator<ItemProduct>() {
        @Override
        public ItemProduct createFromParcel(Parcel source) {
            return new ItemProduct(source);
        }

        @Override
        public ItemProduct[] newArray(int size) {
            return new ItemProduct[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public static Creator<ItemProduct> getCREATOR() {
        return CREATOR;
    }

    @Override
    public String toString() {
        return "ItemProduct{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", image=" + image +
                ", code=" + code +
                ", store=" + store +
                ", category=" + category +
                '}';
    }
}
