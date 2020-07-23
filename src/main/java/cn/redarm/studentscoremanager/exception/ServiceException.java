package cn.redarm.studentscoremanager.exception;

/**
 * @Author redarm
 * @Date 2020/6/24 6:36 下午
 **/
public class ServiceException extends AbstructException {

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable e) {
        super(message, e);
    }

}
