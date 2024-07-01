package com.wtf2.erp.common.util;

public enum WebPage {

    /**
     * login
     */
    LOGIN("login"),
    /**
     * organization/orgMain
     */
    ORGANIZATION("organization/orgMain");


    private String path;

    WebPage(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
