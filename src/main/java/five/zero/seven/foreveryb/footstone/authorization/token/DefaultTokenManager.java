/**
 * IdeasForever，2017。
 * 
 * 项目名：	IdeasForeverFivezeroseven
 * 文件名：	DefaultTokenManager.java
 * 模块说明：	
 * 修改历史：
 * 2017年12月25日 - wangyibo - 创建。
 */
package five.zero.seven.foreveryb.footstone.authorization.token;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import five.zero.seven.foreveryb.footstone.base.login.param.LoginParameter;
import five.zero.seven.foreveryb.footstone.jwt.JWT;
import five.zero.seven.foreveryb.footstone.redis.RedisCatch;

/**
 * Title: TokenManager的默认实现 Description: 管理 Token
 * 
 * @author wangyibo
 *
 */
@Service(value = TokenManager.DEFAULT_CONTEXT_ID)
public class DefaultTokenManager implements TokenManager {

  @Autowired
  private RedisCatch redisCatch;

 /* *//** 将token存储到JVM内存(ConcurrentHashMap)中 *//*
  private static Map<String, String> tokenMap = new ConcurrentHashMap<String, String>();*/

  /**
   * 利用UUID创建Token(用户登录时，创建Token)
   */
  public String createToken(LoginParameter loginInfo) {
    // 给用户jwt加密生成token
    String token = JWT.sign(loginInfo, 60L * 1000L * 30L);
    redisCatch.put(loginInfo.getCode(), token);
    return token;
  }

  /**
   * Token验证(用户登录验证)
   * */
  public boolean checkToken(String loginCode, String token) {
    String tokenSaved = redisCatch.get(loginCode);
    if(StringUtils.isEmpty(token)|| StringUtils.isEmpty(tokenSaved) || !tokenSaved.equals(token)) {
      return false;
    }
    LoginParameter loginInfo = JWT.unsign(token, LoginParameter.class);
    if(loginInfo == null || StringUtils.isBlank(loginInfo.getCode())) {
      return false;
    }
    return loginCode.equals(loginInfo.getCode());
  }

  /**
   * Token删除(用户登出时，删除Token)
   */
  public void deleteToken(String loginCode) {
    redisCatch.delete(loginCode);;
  }

}
