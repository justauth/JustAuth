package me.zhyd.oauth.model;

import lombok.Builder;
import lombok.Data;

/**
 * 授权成功后的用户信息，根据授权平台的不同，获取的数据完整性也不同
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.8
 */
@Builder
@Data
public class AuthUser {
    private String username;
    private String avatar;
    private String blog;
    private String nickname;
    private String company;
    private String location;
    private String email;
    private String remark;
    private AuthUserGender gender;
    private AuthSource source;
    private AuthToken token;
    /**
     * 用户第三方系统的唯一id
     */
    private String uuid;
}
