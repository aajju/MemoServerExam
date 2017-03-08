package com.aajju.memoserverexam;

/**
 * Created by massivcode@gmail.com on 2017. 3. 8. 14:46
 */

public class Token {
    private long iat;
    private long exp;
    private String emai;

    public long getIat() {
        return iat;
    }

    public void setIat(long iat) {
        this.iat = iat;
    }

    public long getExp() {
        return exp;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }

    public String getEmai() {
        return emai;
    }

    public void setEmai(String emai) {
        this.emai = emai;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Token{");
        sb.append("iat=").append(iat);
        sb.append(", exp=").append(exp);
        sb.append(", emai='").append(emai).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
