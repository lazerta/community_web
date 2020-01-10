package Const;

public interface Const {
    String smsCode = "SMS_CODE_";
    String USER_TOKEN = "user";
    String ADMIN_TOKEN = "admin";
    String CLAIM = "claim";
    String HEADER ="Authorization";
    String SECRET_HEADER_TOKEN = "HEADER";

    public interface Role {
        String ADMIN = "admin";
        String USER = "user";
    }
}
