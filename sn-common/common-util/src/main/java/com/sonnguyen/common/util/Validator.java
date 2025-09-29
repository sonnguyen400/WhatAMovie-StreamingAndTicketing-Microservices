package com.sonnguyen.common.util;

public interface Validator {
    public static interface Length{
        public static final int UUID_MAX_LENGTH = 36;
        public static final int MAIL_MAX_LENGTH = 320;
        public static final int NAME_MAX_LENGTH = 255;
        public static final int PHONE_MAX_LENGTH = 15;
        public static final int ADDRESS_MAX_LENGTH = 500;
        public static final int PASSWORD_MAX_LENGTH = 500;
        public static final int PASSWORD_MIN_LENGTH = 8;
        public static final int DESCRIPTION_MAX_LENGTH = 1000;
        public static final int TITLE_MAX_LENGTH = 255;
        public static final int NOTE_MAX_LENGTH = 2000;
        public static final int USERNAME_MAX_LENGTH = 50;
        public static final int USERNAME_MIN_LENGTH = 5;
        public static final int CODE_MAX_LENGTH = 50;
        public static final int CODE_MIN_LENGTH = 2;
        public static final int URL_MAX_LENGTH = 2000;
        public static final int PATH_MAX_LENGTH = 2000;
        public static final int MIME_TYPE_MAX_LENGTH = 100;
        public static final int FILE_NAME_MAX_LENGTH = 255;
        public static final int EXTENSION_MAX_LENGTH = 10;
        public static final int OTP_MAX_LENGTH = 6;
        public static final int OTP_MIN_LENGTH = 4;
        public static final int CONTENT_MAX_LENGTH = 5000;
        public static final int CONTENT_MIN_LENGTH = 1;
        public static final int REFERRER_MAX_LENGTH = 2000;
        public static final int USER_AGENT_MAX_LENGTH = 1000;
        public static final int IP_ADDRESS_MAX_LENGTH = 45; // IPv6 max length
        public static final int FIRST_NAME_MAX_LENGTH = 50;
        public static final int LAST_NAME_MAX_LENGTH = 50;
        public static final int ENUM_MAX_LENGTH = 50;
        public static final int EMAIL_MAX_LENGTH = 320;
        public static final int PHONE_NUMBER_MAX_LENGTH = 15;
    }
    public static interface  Regex{
        public static final String UUID_REGEX = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";
        public static final String MAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        public static final String PHONE_REGEX = "^[+]?[(]?[0-9]{1,4}[)]?[-s./0-9]*$";
        public static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$"; // Minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character
        public static final String MEDIUM_PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,}$"; // Minimum eight characters, at least one uppercase letter, one lowercase letter and one number
        public static final String LOW_PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*\\d)[A-Za-z\\d]{8,}$"; // Minimum eight characters, at least one letter and one number
        public static final String SIMPLE_PASSWORD_REGEX = "^[A-Za-z\\d]{8,}$"; // Minimum eight characters
        public static final String URL_REGEX = "^(https?|ftp)://[^\\s/$.?#].[^\\s]*$";
        public static final String IP_ADDRESS_REGEX = "^((25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)\\.(?!$)|\\.$){4}$"; // IPv4
        public static final String IPV6_ADDRESS_REGEX = "^(?:[a-fA-F0-9]{1,4}:){7}[a-fA-F0-9]{1,4}$"; // IPv6
        public static final String OTP_REGEX = "^\\d{4,6}$"; // 4 to 6 digits
    }
}
