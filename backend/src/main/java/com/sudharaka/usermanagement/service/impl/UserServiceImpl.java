package com.sudharaka.usermanagement.service.impl;

import com.sudharaka.usermanagement.exception.SWException;
import com.sudharaka.usermanagement.exception.pojo.SWExceptionCode;
import com.sudharaka.usermanagement.model.dto.UserReqDto;
import com.sudharaka.usermanagement.model.dto.UserRespDto;
import com.sudharaka.usermanagement.model.entity.User;
import com.sudharaka.usermanagement.model.repository.UserRepository;
import com.sudharaka.usermanagement.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserRespDto getUserDetails(long id) throws SWException {
        User user = getUserDetailsById(id);
        return modelMapper.map(user, UserRespDto.class);
    }

    @Override
    public List<UserRespDto> getAllUsersDetails() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserRespDto.class)).toList();
    }

    @Override
    public void createNewUser(UserReqDto userReqDto) {
        log.debug("createNewUser method started");
        User user = modelMapper.map(userReqDto, User.class);
        userRepository.save(user);
        log.debug("createNewUser method finished");
    }

    @Override
    public void updateUser(long id, UserReqDto userUpdateReqDto) throws SWException {
        log.debug("updateUser method started");
        User user = getUserDetailsById(id);
        user.setUserName(userUpdateReqDto.getUserName());
        user.setPassword(userUpdateReqDto.getPassword());
        user.setFirstName(userUpdateReqDto.getFirstName());
        user.setLastName(userUpdateReqDto.getLastName());
        userRepository.save(user);
        log.debug("updateUser method finished");
    }

    private User getUserDetailsById(long id) throws SWException {
        log.debug("getUserDetailsById method started");
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new SWException(
                    HttpStatus.BAD_REQUEST,
                    SWExceptionCode.UMUS001,
                    "User not found with user-id: " + id
            );
        }
        return optionalUser.get();

    }
}
