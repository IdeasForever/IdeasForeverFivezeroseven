/**
 * IdeasForever，2017。
 * 
 * 项目名：	IdeasForeverFivezeroseven
 * 文件名：	TokenController.java
 * 模块说明：	
 * 修改历史：
 * 2017年12月27日 - wangyibo - 创建。
 */
package five.zero.seven.foreveryb.footstone.authorization.token;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import five.zero.seven.foreveryb.footstone.base.annotation.IgnoreSecurity;
import five.zero.seven.foreveryb.footstone.base.login.param.LoginParameter;
import five.zero.seven.foreveryb.footstone.base.login.param.Response;
import five.zero.seven.foreveryb.footstone.util.Constants;
import five.zero.seven.foreveryb.server.service.user.UserService;

/**
 * token的管理 处理用户的登录、登出操作
 * 
 * @author wangyibo
 *
 */
@RestController
@RequestMapping("/tokens")
public class TokenController {

  private static final Logger log = Logger.getLogger(TokenController.class);

  @Autowired
  private TokenManager tokenManager;
  @Autowired
  private UserService userService;

  /*
   * public UserService getUserService() { return userService; }
   * 
   * @Resource(name = "userService") public void setUserService(UserService
   * userService) { this.userService = userService; }
   * 
   * public TokenManager getTokenManager() { return tokenManager; }
   * 
   * @Resource(name = "tokenManager") public void setTokenManager(TokenManager
   * tokenManager) { this.tokenManager = tokenManager; }
   */

  /**
   * @description 登录处理
   * @author rico
   * @created 2017年7月4日 下午4:53:58
   */
  @RequestMapping(method = RequestMethod.POST)
  @IgnoreSecurity
  public Response login(@RequestBody LoginParameter loginUser) {
    if (loginUser == null)
      return new Response().failure("缺少必要参数");
    

    String code = loginUser.getCode();
    String passwd = loginUser.getPasswd();

    boolean flag = userService.login(code, passwd);
    if (flag) {
      String token = tokenManager.createToken(loginUser);
      log.debug("Write Token return to the Client : " + token);
      Response response = new Response().success(); 
      response.putDataValue(Constants.DEFAULT_TOKEN_NAME, token);
      response.putDataValue(Constants.DEFAULT_LOGIN_CODE, loginUser.getCode());
      response.putDataValue("message", "login Success...");
      return response;
    }
    return new Response().failure("Login Failure...");
  }

  /**
   * @description 登出处理
   * @author rico
   * @created 2017年7月4日 下午4:53:58
   */
  @RequestMapping(method = RequestMethod.DELETE)
  @IgnoreSecurity
  public Response logout(HttpServletRequest request) {
    String loginCode = request.getHeader(Constants.DEFAULT_LOGIN_CODE);
    tokenManager.deleteToken(loginCode);
    log.debug("Logout Success...");
    Response response = new Response().success(); 
    response.putDataValue("message", "Logout Success...");
    return response;
  }

}
