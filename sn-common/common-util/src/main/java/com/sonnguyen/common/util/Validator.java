package com.sonnguyen.common.util;

public interface Validator {
    interface Length {
        int UUID_MAX_LENGTH = 36;
        int MAIL_MAX_LENGTH = 320;
        int NAME_MAX_LENGTH = 255;
        int PHONE_MAX_LENGTH = 15;
        int ADDRESS_MAX_LENGTH = 500;
        int PASSWORD_MAX_LENGTH = 500;
        int PASSWORD_MIN_LENGTH = 8;
        int DESCRIPTION_MAX_LENGTH = 1000;
        int TITLE_MAX_LENGTH = 255;
        int NOTE_MAX_LENGTH = 2000;
        int USERNAME_MAX_LENGTH = 50;
        int USERNAME_MIN_LENGTH = 5;
        int CODE_MAX_LENGTH = 50;
        int CODE_MIN_LENGTH = 2;
        int URL_MAX_LENGTH = 2000;
        int PATH_MAX_LENGTH = 2000;
        int MIME_TYPE_MAX_LENGTH = 100;
        int FILE_NAME_MAX_LENGTH = 255;
        int EXTENSION_MAX_LENGTH = 10;
        int OTP_MAX_LENGTH = 6;
        int OTP_MIN_LENGTH = 4;
        int CONTENT_MAX_LENGTH = 5000;
        int CONTENT_MIN_LENGTH = 1;
        int REFERRER_MAX_LENGTH = 2000;
        int USER_AGENT_MAX_LENGTH = 1000;
        int IP_ADDRESS_MAX_LENGTH = 45; // IPv6 max length
        int FIRST_NAME_MAX_LENGTH = 50;
        int LAST_NAME_MAX_LENGTH = 50;
        int ENUM_MAX_LENGTH = 50;
        int EMAIL_MAX_LENGTH = 320;
        int PHONE_NUMBER_MAX_LENGTH = 15;
        int CURRENCY_MAX_LENGTH = 8;
    }

    interface Regex {
        String UUID_REGEX = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";
        String MAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        String PHONE_REGEX = "^[+]?[(]?[0-9]{1,4}[)]?[-s./0-9]*$";
        String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$"; // Minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character
        String MEDIUM_PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,}$"; // Minimum eight characters, at least one uppercase letter, one lowercase letter and one number
        String LOW_PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*\\d)[A-Za-z\\d]{8,}$"; // Minimum eight characters, at least one letter and one number
        String SIMPLE_PASSWORD_REGEX = "^[A-Za-z\\d]{8,}$"; // Minimum eight characters
        String URL_REGEX = "^(https?|ftp)://[^\\s/$.?#].[^\\s]*$";
        String IP_ADDRESS_REGEX = "^((25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)\\.(?!$)|\\.$){4}$"; // IPv4
        String IPV6_ADDRESS_REGEX = "^(?:[a-fA-F0-9]{1,4}:){7}[a-fA-F0-9]{1,4}$"; // IPv6
        String OTP_REGEX = "^\\d{4,6}$"; // 4 to 6 digits
    }
}
