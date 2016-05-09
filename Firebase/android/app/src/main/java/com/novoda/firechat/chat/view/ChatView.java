package com.novoda.firechat.chat.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.novoda.firechat.R;
import com.novoda.firechat.chat.displayer.ChatDisplayer;
import com.novoda.firechat.chat.model.Chat;
import com.novoda.firechat.chat.model.Message;
import com.novoda.notils.caster.Views;

public class ChatView extends LinearLayout implements ChatDisplayer {

    private TextView messageView;
    private ChatAdapter chatAdapter;
    private View submitButton;

    public ChatView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View.inflate(getContext(), R.layout.merge_chat_view, this);
        chatAdapter = new ChatAdapter(getContext());
        messageView = Views.findById(this, R.id.messageEdit);
        submitButton = Views.findById(this, R.id.submitButton);
        RecyclerView recyclerView = Views.findById(this, R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(chatAdapter);
    }

    @Override
    public void attach(final ChatActionListener actionListener) {
        submitButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                actionListener.onSubmitMessage(new Message("Bob", messageView.getText().toString())); //TODO sort out username
            }
        });
    }

    @Override
    public void detach(ChatActionListener actionListener) {
        submitButton.setOnClickListener(null);
    }

    @Override
    public void display(Chat chat) {
        chatAdapter.update(chat);
    }

}
