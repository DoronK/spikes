package com.novoda.firechat.chat.service;

import com.novoda.firechat.chat.model.Chat;
import com.novoda.firechat.chat.model.Message;

import rx.Observable;

public interface ChatService {

    Observable<Chat> getChat();

    void sendMessage(Message message);

}
