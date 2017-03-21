package com.ootb.web.security.error.form;

/**
 * Created by Adam on 2017-03-21.
 */
public class ErrorForm {

    private String header;

    private String main;

    private String helper;

    public ErrorForm() {
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getHelper() {
        return helper;
    }

    public void setHelper(String helper) {
        this.helper = helper;
    }

    public static final class ErrorFormBuilder {
        private String header;
        private String main;
        private String helper;

        private ErrorFormBuilder() {
        }

        public static ErrorFormBuilder anErrorForm() {
            return new ErrorFormBuilder();
        }

        public ErrorFormBuilder withHeader(String header) {
            this.header = header;
            return this;
        }

        public ErrorFormBuilder withMain(String main) {
            this.main = main;
            return this;
        }

        public ErrorFormBuilder withHelper(String helper) {
            this.helper = helper;
            return this;
        }

        public ErrorForm build() {
            ErrorForm errorForm = new ErrorForm();
            errorForm.setHeader(header);
            errorForm.setMain(main);
            errorForm.setHelper(helper);
            return errorForm;
        }
    }
}
