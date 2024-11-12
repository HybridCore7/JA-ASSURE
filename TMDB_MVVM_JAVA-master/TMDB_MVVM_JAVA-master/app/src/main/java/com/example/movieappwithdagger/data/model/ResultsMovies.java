package com.example.movieappwithdagger.data.model;

import java.util.ArrayList;

public class ResultsMovies {

    private String page ;
    private String total_results;
    private  String total_pages;
    private ArrayList<Moviedata> results;

    public ResultsMovies(String page, String total_results, String total_pages, ArrayList<Moviedata> results) {
        this.page = page;
        this.total_results = total_results;
        this.total_pages = total_pages;
        this.results = results;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getTotal_results() {
        return total_results;
    }

    public void setTotal_results(String total_results) {
        this.total_results = total_results;
    }

    public String getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(String total_pages) {
        this.total_pages = total_pages;
    }

    public ArrayList<Moviedata> getResults() {
        return results;
    }

    public void setResults(ArrayList<Moviedata> results) {
        this.results = results;
    }
}
