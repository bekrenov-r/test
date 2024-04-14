package com.lordsofcookies.moodapp.user;

import com.lordsofcookies.moodapp.model.TelegramUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TelegramUserMapper {
    TelegramUserResponse entityToResponse(TelegramUser entity);
}
