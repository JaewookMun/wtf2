package com.wtf2.erp.common.util;

public enum WebPage {

    /**
     * login
     */
    LOGIN("login"),
    /**
     * groupRegistration
     */
    GROUP_REGISTRATION("groupRegistration"),
    /**
     * boardIndex/noticeMain
     */
    NOTICE_BOARD("boardIndex/noticeMain"),
    /**
     * boardIndex/noticeRegister
     */
    NOTICE_REGISTER("boardIndex/noticeRegister"),
    /**
     * boardIndex/noticeDetails
     */
    NOTICE_DETAILS("boardIndex/noticeDetails"),
    /**
     * boardIndex/pageDetails
     */
    PAGE_DETAILS("boardIndex/pageDetails"),
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
