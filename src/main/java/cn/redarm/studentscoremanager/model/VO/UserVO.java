package cn.redarm.studentscoremanager.model.VO;

import cn.redarm.studentscoremanager.model.entity.User;
import lombok.Data;
import lombok.ToString;

import java.util.Objects;

/**
 * @Author Redarm
 * @Date 2020/7/7 8:58 上午
 **/
@Data
@ToString
public class UserVO {

    private String username;

    private String password;

    private String nickName;

    private String email;

    private String roles;

    public UserVO(String username) {
        this.username = username;
    }

    public UserVO(){

    }

    public UserVO(User user, String roles){
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.nickName = user.getNickName();
        this.email = user.getEmail();
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserVO)) return false;
        UserVO userVO = (UserVO) o;
        return Objects.equals(getUsername(), userVO.getUsername()) &&
                Objects.equals(getPassword(), userVO.getPassword()) &&
                Objects.equals(getNickName(), userVO.getNickName()) &&
                Objects.equals(getEmail(), userVO.getEmail()) &&
                Objects.equals(getRoles(), userVO.getRoles());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword(), getNickName(), getEmail(), getRoles());
    }
}
