package com.alexdefreitas.validate.user.restclient.model;

import lombok.Getter;

@Getter
public enum UserStatusEnum {
    ABLE_TO_VOTE,
    INVALID,
    UNABLE_TO_VOTE
}
