package com.project.chatapp.data;

import android.content.Context;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.project.chatapp.R;
import com.project.chatapp.model.Message;
import com.squareup.picasso.Picasso;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.List;
import java.util.Random;

public class ChatAdapter extends ArrayAdapter<Message> {
    private String mUserId;
    Random r;
    Integer[] avatar ={ R.drawable.avaa,R.drawable.avaa, R.drawable.avab, R.drawable.avac, R.drawable.avad, R.drawable.avae, R.drawable.avaf, R.drawable.avag};



    public ChatAdapter(Context context, String userId, List<Message> messages) {
        super(context, 0, messages);
        mUserId = userId;
        r = new Random();
    }

    public View getView(int position, View convertView, ViewGroup parent) {



        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.chat_row, parent, false);

            final ViewHolder holder = new ViewHolder();
            holder.imageLeft = (ImageView) convertView.findViewById(R.id.ProfileLeft);
            holder.imageRight = (ImageView) convertView.findViewById(R.id.ProfileRight);
            holder.body = (TextView) convertView.findViewById(R.id.tvBody);
            convertView.setTag(holder);
        }

        final Message message = (Message)getItem(position);
        final ViewHolder holder = (ViewHolder) convertView.getTag();
        final boolean isMe = message.getUserId().equals(mUserId);

        if (isMe) {
            holder.imageRight.setVisibility(View.VISIBLE);
            holder.imageLeft.setVisibility(View.GONE);
            holder.body.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);

        }else {

            holder.imageLeft.setVisibility(View.VISIBLE);
            holder.imageRight.setVisibility(View.GONE);
            holder.body.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);

        }

        final ImageView profileView = isMe ? holder.imageRight : holder.imageLeft;
        profileView.setImageResource(avatar[r.nextInt(avatar.length)]);
        holder.body.setText(message.getBody());


        return convertView;
    }


    class ViewHolder {
        public ImageView imageLeft;
        public ImageView imageRight;
        public TextView body;
    }
}
