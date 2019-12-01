package com.adityaayatusy.testpraktekduithape.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataModel {
    @SerializedName("content")
    List<UserModel> content;
    @SerializedName("totalPages")
    int totalPages;
    @SerializedName("totalElements")
    int totalElements;
    @SerializedName("numberOfElements")
    int numberOfElements;
    @SerializedName("size")
    int size;
    @SerializedName("number")
    int number;
    @SerializedName("lastPage")
    boolean lastPage;
    @SerializedName("firstPage")
    boolean firstPage;
    @SerializedName("last")
    boolean last;
    @SerializedName("sort")
    boolean sort;
    @SerializedName("first")
    boolean first;

    public List<UserModel> getContent() {
        return content;
    }

    public void setContent(List<UserModel> content) {
        this.content = content;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isLastPage() {
        return lastPage;
    }

    public void setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
    }

    public boolean isFirstPage() {
        return firstPage;
    }

    public void setFirstPage(boolean firstPage) {
        this.firstPage = firstPage;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public boolean isSort() {
        return sort;
    }

    public void setSort(boolean sort) {
        this.sort = sort;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }
}
