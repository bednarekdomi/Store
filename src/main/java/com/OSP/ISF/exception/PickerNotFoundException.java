package com.OSP.ISF.exception;

public class PickerNotFoundException extends RuntimeException{
    private static final String PICKER_NOT_FOUND_EXCEPTION = "No free pickers";

    public PickerNotFoundException(){
        super(PICKER_NOT_FOUND_EXCEPTION);
    }
}
