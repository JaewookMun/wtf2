package com.wtf2.erp.common.util;

public enum WebPage {

    /**
     * login
     */
    LOGIN("login"),
    /**
     * boardIndex/noticeMain
     */
    NOTICE_BOARD("boardIndex/noticeMain"),
    /**
     * boardIndex/noticeRegister
     */
    NOTICE_REGISTER("boardIndex/noticeRegister"),
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
