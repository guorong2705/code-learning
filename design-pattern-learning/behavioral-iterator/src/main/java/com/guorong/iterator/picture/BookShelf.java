package com.guorong.iterator.picture;

class BookShelf {
    private Book[] books;

    private int last;

    public BookShelf(int maxLength) {
        books = new Book[maxLength];
    }

    public Book getBookAt(int index) {
        return books[index];
    }

    public void appendBook(Book book) {
        books[last] = book;
        last++;
    }
}
