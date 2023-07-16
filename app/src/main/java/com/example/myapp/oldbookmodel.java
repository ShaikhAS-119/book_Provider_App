package com.example.myapp;

import android.media.Image;
import android.widget.ImageView;

public class oldbookmodel {
    // string name should be as it is in database
   private String bookName,price,publisherName,writerName,pages,contact;


    private Image image;

    public oldbookmodel() {

    }
    
    public oldbookmodel(String bookName, String price, String publisherName, String writerName, String pages, String contact,Image image) {
        this.bookName = bookName;
        this.price = price;
        this.publisherName = publisherName;
        this.writerName = writerName;
        this.pages = pages;
        this.contact = contact;
        this.image = image;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getWriterName() {
        return writerName;
    }

    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }

    public String getPages() { return pages; }

    public void setPages(String pages) { this.pages = pages; }

    public String getContact() { return contact; }

    public void setContact(String contact) { this.contact = contact; }

    public Image getImage() {return image;}

    public void setImage(Image image) {this.image = image;}

}
