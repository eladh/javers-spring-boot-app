package com.idi.errors;

public class AppException extends Exception {

   private String error;

   public AppException (String message) {
      super(message);
      error = message;
   }

   public String getError() {
      return error;
   }
}