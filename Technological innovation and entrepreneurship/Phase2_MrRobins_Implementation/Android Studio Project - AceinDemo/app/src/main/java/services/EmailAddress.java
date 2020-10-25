package com.example.georgiosmoschovis.aceindemo.services;

/**
 * This class illustrates an E-mail Address.
 */
public class EmailAddress {
	private String value;

    public EmailAddress(String email) {
        this.value = email;
    }

    public String getAddress() {
        return value;
    }

    public boolean isValid() {
        return true;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (this == other) {
            return true;
        }

        if (!(other instanceof EmailAddress)) {
            return false;
        }

        EmailAddress theEmail = (EmailAddress) other;
        return value == null ? theEmail.getAddress() == null
                : value.equals(theEmail.getAddress());
    }

    @Override
    public int hashCode() {
        return value == null ? 0 : value.hashCode();
    }

    @Override
    public String toString() {
        return value;
    }
}
