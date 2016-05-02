package com.epicodus.discussionforum.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.epicodus.discussionforum.Constants;
import com.epicodus.discussionforum.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Firebase mEnteredCommentRef;
    private ValueEventListener mEnteredCommentRefListener;
    @Bind(R.id.commentButton) Button mCommentButton;
    @Bind(R.id.commentEditText) EditText mCommentEditText;
    @Bind(R.id.viewCommentsButton) Button mViewCommentsButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mCommentButton.setOnClickListener(this);
        mViewCommentsButton.setOnClickListener(this);
        mEnteredCommentRef = new Firebase(Constants.FIREBASE_URL_ENTERED_COMMENT);

        mEnteredCommentRefListener =  mEnteredCommentRef.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String comments = dataSnapshot.getValue().toString();
                Log.d("Comment updated", comments);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mEnteredCommentRef.removeEventListener(mEnteredCommentRefListener);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.commentButton:
                String comment = mCommentEditText.getText().toString();
                Intent intent = new Intent(MainActivity.this, CommentListActivity.class);
                saveCommentToFirebase(comment);
                startActivity(intent);
                break;
            case R.id.viewCommentsButton:
                Intent intent1 = new Intent(MainActivity.this, CommentListActivity.class);
                startActivity(intent1);
            default:
                break;
        }
    }

    private void saveCommentToFirebase(String comment) {
        Firebase enteredCommentRef = new Firebase(Constants.FIREBASE_URL_ENTERED_COMMENT);
        enteredCommentRef.push().setValue(comment);
    }
}
