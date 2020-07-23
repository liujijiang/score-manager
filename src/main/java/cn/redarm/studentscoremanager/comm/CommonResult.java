package cn.redarm.studentscoremanager.comm;

import cn.redarm.studentscoremanager.enums.ResultEnum;
import lombok.*;

/**
 * @Author redarm
 * @Date 2020/6/19 4:04 下午
 **/
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> {

    private Integer code;

    private String message;

    private T data;

    /**
     * @return cn.redarm.studentscoremanager.comm.CommonResult<T>
     * @Author redarm
     * @Description //TODO return success and add data
     * @Date 4:12 下午 2020/6/19
     * @Param [data]
     **/
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), data);
    }

    /**
     * @return cn.redarm.studentscoremanager.comm.CommonResult<T>
     * @Author redarm
     * @Description //TODO return success and add data , message
     * @Date 4:12 下午 2020/6/19
     * @Param [data, message]
     **/
    public static <T> CommonResult<T> success(T data, String message) {
        return new CommonResult<>(ResultEnum.SUCCESS.getCode(), message, data);
    }

    /**
     * @return cn.redarm.studentscoremanager.comm.CommonResult<T>
     * @Author redarm
     * @Description //TODO return failed with nothing
     * @Date 4:12 下午 2020/6/19
     * @Param []
     **/
    public static <T> CommonResult<T> failed() {
        return new CommonResult<>(ResultEnum.FAILED.getCode(), ResultEnum.FAILED.getMessage(), null);
    }

    /**
     * @return cn.redarm.studentscoremanager.comm.CommonResult<T>
     * @Author redarm
     * @Description //TODO return failed and add message
     * @Date 4:13 下午 2020/6/19
     * @Param [message]
     **/
    public static <T> CommonResult<T> failed(String message) {
        return new CommonResult<>(ResultEnum.FAILED.getCode(), message, null);
    }

}
