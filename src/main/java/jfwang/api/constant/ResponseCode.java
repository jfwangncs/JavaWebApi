package jfwang.api.constant;

public enum ResponseCode {
    SUCCESS("200", "success"),
    PENDING("300", "pending"),
    FAILED("400", "failed");

    private final String code;
    private final String defaultMsg;

    ResponseCode(String code, String defaultMsg) {
        this.code = code;
        this.defaultMsg = defaultMsg;
    }

    public String GetCode() {
        return code;
    }

    public String GetMsg() {
        return defaultMsg;
    }
}
