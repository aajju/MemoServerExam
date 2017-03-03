package com.aajju.memoserverexam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ModifyMemoActivity extends AppCompatActivity {
    EditText mEditText1, mEditText2;
    int mId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_memo);

        mEditText1 = (EditText) findViewById(R.id.modi_subject_et);
        mEditText2 = (EditText) findViewById(R.id.modi_content_et);

        Intent intent = getIntent();
        Memo memo = (Memo) intent.getSerializableExtra("memo");

        mEditText1.setText(memo.getTitle());
        mEditText2.setText(memo.getContents());
        mId = memo.getId();

        findViewById(R.id.modi_ok_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(mEditText1.getText().toString()) || TextUtils.isEmpty(mEditText2.getText().toString())){
                    Toast.makeText(ModifyMemoActivity.this, "둘 다 입력 해라", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent();
                    intent.putExtra("subject", mEditText1.getText().toString());
                    intent.putExtra("content", mEditText2.getText().toString());
                    intent.putExtra("id", mId);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

    }
}
