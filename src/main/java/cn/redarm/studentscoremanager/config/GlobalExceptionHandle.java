package cn.redarm.studentscoremanager.config;

import cn.redarm.studentscoremanager.comm.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author redarm
 * @Date 2020/6/19 10:50 下午
 **/
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandle {

    /**
     * @return cn.redarm.studentscoremanager.comm.CommonResult
     * @Author redarm
     * @Description //TODO handle @valid exception
     * @Date 10:52 下午 2020/6/19
     * @Param [request, e]
     **/
    @ExceptionHandler(value = Exception.class)
    public CommonResult exceptionHandle(HttpServletRequest request, Exception e) {

        if (e instanceof MethodArgumentNotValidException) {

            return CommonResult.failed(((MethodArgumentNotValidException) e).getBindingResult().getFieldError().getDefaultMessage());
        }

        if (e instanceof AccessDeniedException){

            return CommonResult.failed("权限不够，无法操作");
        }

        return CommonResult.failed("qaq");
    }
}
