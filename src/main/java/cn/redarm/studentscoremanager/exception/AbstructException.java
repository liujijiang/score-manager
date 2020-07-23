package cn.redarm.studentscoremanager.exception;

/**
 * @Author redarm
 * @Date 2020/6/24 6:35 下午
 **/
public abstract class AbstructException extends RuntimeException {

    public AbstructException(String message) {
        super(message);
    }

    public AbstructException(String message, Throwable e) {
        super(message, e);
    }
}
