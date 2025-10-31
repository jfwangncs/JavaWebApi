package jfwang.api.constant;

public enum ErrorCode {
    SYSTEM_ERROR("1000", "System error"),
    VALIDATION_ERROR("1000", "Validation error"),
    OUTOFSTOCK("1001", "The product is out of stock."),;

    private final String code;
    private final String defaultMsg;

    ErrorCode(String code, String defaultMsg) {
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
