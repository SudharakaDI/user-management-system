package com.sudharaka.usermanagement.service;

import com.sudharaka.usermanagement.model.dto.UserReqDto;
import com.sudharaka.usermanagement.model.dto.UserRespDto;

import java.util.List;


public interface UserService {
    UserRespDto getUserDetails(long id);
    List<UserRespDto> getAllUsersDetails();
    void createNewUser(UserReqDto userReqDto);
    void updateUser(long id, UserReqDto userUpdateReqDto);
}
