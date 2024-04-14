package com.lordsofcookies.moodapp.post;

import com.lordsofcookies.moodapp.model.Post;
import com.lordsofcookies.moodapp.user.TelegramUserMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = TelegramUserMapper.class)
public interface PostMapper {
    PostResponse entityToResponse(Post entity);
}
