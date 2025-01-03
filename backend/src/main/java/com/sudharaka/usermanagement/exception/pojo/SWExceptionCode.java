package com.sudharaka.usermanagement.exception.pojo;

import com.sudharaka.usermanagement.utils.Constant;
import lombok.Getter;


@Getter
public enum SWExceptionCode {

    UMSW001(Constant.ERROR_TYPE_SERVER),
    UMSW002(Constant.ERROR_TYPE_INVALID_INPUT), //No resource found exception occurs
    UMSW003(Constant.ERROR_TYPE_INVALID_INPUT), //Method Argument Not Valid Exception

    // user
    UMUS001(Constant.ERROR_TYPE_INVALID_INPUT);// User not found with the given user id


    private final String errorType;

    SWExceptionCode(String type) {
        this.errorType = type;
    }

    public String getType() {
        return errorType;
    }
}

