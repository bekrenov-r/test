package com.lordsofcookies.moodapp.user;

public record TelegramUserResponse(
    String telegramId,
    String firstName,
    String lastName,
    String username
) { }
