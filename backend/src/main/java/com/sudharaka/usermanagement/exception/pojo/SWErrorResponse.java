package com.sudharaka.usermanagement.exception.pojo;

import com.sudharaka.usermanagement.model.dto.ErrorRespDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SWErrorResponse {

    private ErrorRespDto error;

    public SWErrorResponse(String code, String type, String message) {
        ErrorRespDto errorResponse = new ErrorRespDto();
        errorResponse.setCode(code);
        errorResponse.setType(type);
        errorResponse.setMessage(message);

        this.error = errorResponse;
    }
}
