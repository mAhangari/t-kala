package com.tosan.tkala.exception;

public class ProductNotFoundException extends Exception {

        private final String errorCode;
        private final String errorMessage;


        public ProductNotFoundException(String errorCode, String errorMessage) {
            super(errorMessage);
            this.errorCode = errorCode;
            this.errorMessage = errorMessage;
        }


        public ProductNotFoundException(String errorCode, String errorMessage, Throwable cause) {
            super(errorMessage, cause);
            this.errorCode = errorCode;
            this.errorMessage = errorMessage;
        }

        public String getErrorCode() {
            return errorCode;
        }

        public String getErrorMessage() {
            return errorMessage;
        }
}
