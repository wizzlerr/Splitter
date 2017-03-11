package com.ootb.service.infotainment.email.type;

/**
 * Created by Adam on 2017-03-11.
 */
public class Email {

    private String recipentAddress;
    private String subject;
    private String message;

    public Email() {
    }

    public String getRecipentAddress() {
        return recipentAddress;
    }

    public void setRecipentAddress(String recipentAddress) {
        this.recipentAddress = recipentAddress;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public static final class EmailBuilder {
        private String recipentAddress;
        private String subject;
        private String message;

        private EmailBuilder() {
        }

        public static EmailBuilder anEmail() {
            return new EmailBuilder();
        }

        public EmailBuilder withRecipentAddress(String recipentAddress) {
            this.recipentAddress = recipentAddress;
            return this;
        }

        public EmailBuilder withSubject(String subject) {
            this.subject = subject;
            return this;
        }

        public EmailBuilder withMessage(String message) {
            this.message = message;
            return this;
        }

        public Email build() {
            Email email = new Email();
            email.setRecipentAddress(recipentAddress);
            email.setSubject(subject);
            email.setMessage(message);
            return email;
        }
    }
}
