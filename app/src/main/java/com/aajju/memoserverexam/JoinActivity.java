package com.aajju.memoserverexam;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JoinActivity extends AppCompatActivity {

    private TextView mIdCheckStateTextView;
    private EditText mEmail, mPassword;
    private boolean mIsDuplicated;
    private Api mApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        mEmail = (EditText) findViewById(R.id.join_email_et);
        mPassword = (EditText) findViewById(R.id.join_password_et);
        mIdCheckStateTextView = (TextView) findViewById(R.id.join_check_state_tv);

        mEmail.addTextChangedListener(mEmailTextWatcher);

        mApi = HttpHelper.getAPI();

        // 회원 가입 완료 버튼
        findViewById(R.id.join_ok_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(mEmail.getText().toString()) || TextUtils.isEmpty(mPassword.getText().toString())) {
                    Toast.makeText(JoinActivity.this, "둘다 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!mIsDuplicated) {
                    Toast.makeText(JoinActivity.this, "중복된 계정입니다!", Toast.LENGTH_SHORT).show();
                    return;
                }

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
        });

    }

    private TextWatcher mEmailTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            String email = editable.toString();

            if (email.length() > 1) {
                mApi.duplicateCheck(email).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            mIsDuplicated = true;
                            mIdCheckStateTextView.setTextColor(Color.parseColor("#2196f3"));
                            mIdCheckStateTextView.setText("가입 가능한 계정입니다!");
                        } else {
                            mIsDuplicated = false;
                            mIdCheckStateTextView.setTextColor(Color.parseColor("#F44336"));
                            mIdCheckStateTextView.setText("이미 가입된 계정입니다!");
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }

            if (email.length() == 0) {
                mIdCheckStateTextView.setText("아이디를 입력해주세요");
            }
        }
    };


}
