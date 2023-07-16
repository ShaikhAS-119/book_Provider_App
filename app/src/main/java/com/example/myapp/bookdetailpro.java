package com.example.myapp;

public class bookdetailpro {
    private String bookName,publisherName,writerName,price,pages,contact,Url;
      //create constructor from generate
    public bookdetailpro(){

    }

    public bookdetailpro(String bookName, String publisherName, String writerName, String price,String pages, String contact, String Url) {
        this.bookName = bookName;
        this.publisherName = publisherName;
        this.writerName = writerName;
        this.price = price;
        this.pages = pages;
        this.contact = contact;
        this.Url = Url;

    }
    //create getter setter from generate



    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPages() { return pages; }

    public void setPages(String pages) { this.pages = pages; }

    public String getContact() { return contact; }

    public void setContact(String contact) { this.contact = contact; }

    public String getUrl() { return Url; }

    public void setUrl(String url) { this.Url = url; }
}