package com.aajju.memoserverexam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NewMemoActivity extends AppCompatActivity {
    EditText mEditText1, mEditText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_memo);

        mEditText1 = (EditText) findViewById(R.id.new_subject_et);
        mEditText2 = (EditText) findViewById(R.id.new_content_et);

        findViewById(R.id.new_ok_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(mEditText1.getText().toString()) || TextUtils.isEmpty(mEditText2.getText().toString())){
                    Toast.makeText(NewMemoActivity.this, "둘 다 입력 해라", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent();
                    intent.putExtra("subject", mEditText1.getText().toString());
                    intent.putExtra("content", mEditText2.getText().toString());
                    setResult(RESULT_OK, intent);
                    finish();
                }


            }
        });
    }
}
