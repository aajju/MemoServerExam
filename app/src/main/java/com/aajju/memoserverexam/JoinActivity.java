package com.aajju.memoserverexam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JoinActivity extends AppCompatActivity {

    private EditText mEmail, mPassword;
    private int mCheck = -1;
    private Api mApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        mEmail = (EditText) findViewById(R.id.join_email_et);
        mPassword = (EditText) findViewById(R.id.join_password_et);

        mApi = HttpHelper.getAPI();

        // 중복체크 버튼
        findViewById(R.id.check_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(mEmail.getText().toString())){
                    Toast.makeText(JoinActivity.this, "이메일을 입력해주세요", Toast.LENGTH_SHORT).show();
                } else {
                    mApi.duplicateCheck(mEmail.getText().toString()).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if(response.code() == 200){  // 중복 아님
                                mCheck = 1;
                                Toast.makeText(JoinActivity.this, "사용 가능한 이메일 주소입니다", Toast.LENGTH_SHORT).show();
                            } else if (response.code() == 409){   // 중복
                                Toast.makeText(JoinActivity.this, "이미 존재하는 이메일 주소입니다. \n 다른 이메일을 입력해주세요", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(JoinActivity.this, "onFailure", Toast.LENGTH_SHORT).show();

                        }
                    });
                }

            }
        });

        // 회원 가입 완료 버튼
        findViewById(R.id.join_ok_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(mEmail.getText().toString()) || TextUtils.isEmpty(mPassword.getText().toString())){
                    Toast.makeText(JoinActivity.this, "둘다 입력하세요", Toast.LENGTH_SHORT).show();
                } else {
                    mApi.join(mEmail.getText().toString(), mPassword.getText().toString()).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            setResult(RESULT_OK);
                            finish();
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {

                        }
                    });
                }
            }
        });



    }
}
