package cn.redarm.studentscoremanager.enums;

public enum ResultEnum {
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(404, "参数检验失败");

    private Integer code;

    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    ResultEnum() {

    }
}
