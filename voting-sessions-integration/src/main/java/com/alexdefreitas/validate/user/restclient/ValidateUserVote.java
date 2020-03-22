package com.alexdefreitas.validate.user.restclient;

import com.alexdefreitas.validate.user.restclient.model.UserResponse;
import com.alexdefreitas.validate.user.restclient.model.UserStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Component
public class ValidateUserVote {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${users.validation.heroku}")
    private String userValidationUrl;

    public void verifyIfUserIsAbleToVote(String cpf) {
        String url = userValidationUrl.concat(cpf);
        try {
            var userResponse = restTemplate.getForEntity(url, UserResponse.class).getBody();
            if (ObjectUtils.isEmpty(userResponse.getStatus()) ||
                    !userResponse.getStatus().equals(UserStatusEnum.ABLE_TO_VOTE)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User isn't able to vote");
            }
        } catch (HttpStatusCodeException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
        }
    }
}
