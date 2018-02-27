/**
 * IdeasForever，2017。
 * 
 * 项目名：	IdeasForeverFivezeroseven
 * 文件名：	UserController.java
 * 模块说明：	
 * 修改历史：
 * 2017年12月27日 - wangyibo - 创建。
 */
package five.zero.seven.foreveryb.web.user;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import five.zero.seven.foreveryb.footstone.base.annotation.IgnoreSecurity;
import five.zero.seven.foreveryb.footstone.base.query.QueryFilter;
import five.zero.seven.foreveryb.server.pojo.user.User;
import five.zero.seven.foreveryb.server.service.user.UserService;

/**
 * Title: 用户管理 Description: 对用户资源的增删改查
 *
 * @author wangyibo
 *
 */
@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;

  /** Log4j日志处理 */
  private static final Logger log = Logger.getLogger(UserController.class);

  /**
   * @description 获取指定Id的用户
   * @param uuid
   * @return
   */
  @RequestMapping(value = "/user/{uuid}", method = RequestMethod.GET, produces = "application/json")
  @IgnoreSecurity
  public User getUser(@PathVariable("uuid") String uuid) {
    User user = userService.getUser(uuid);
    log.debug("查询用户 :" + user);
    return user;
  }

  /**
   * @description 添加指定Id的用户
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/user", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
  @IgnoreSecurity
  public User addUser(@RequestBody @Valid User user) throws Exception {
    if (user == null)
      return null;

    if (StringUtils.isBlank(user.getUuid())) {
      log.debug("添加用户 :" + user);
    } else {
      log.debug("保存用户 :" + user);
    }
    userService.saveUser(user);
    return userService.getUser(user.getUuid());
  }

  /**
   * @description 用户查询（按照条件）
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/query", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
  @IgnoreSecurity
  public PageInfo<User> queryUser(@RequestBody QueryFilter filter) throws Exception {
    if (filter == null)
      return new PageInfo<User>();

    PageInfo<User> queryResult = userService.queryUser(filter);
    return queryResult;
  }

  /**
   * @description 用户查询（按照条件）(试验2)
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/select", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
  @IgnoreSecurity
  public PageInfo<User> selectAllUsers(@RequestBody QueryFilter filter) throws Exception {
    if (filter == null)
      return new PageInfo<User>();

    PageInfo<User> queryResult = userService.selectAllUsers(filter);
    return queryResult;
  }

}
