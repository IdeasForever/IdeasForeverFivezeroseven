/**
 * IdeasForever，2017。
 * 
 * 项目名：	IdeasForeverFivezeroseven
 * 文件名：	UserMapperDao.java
 * 模块说明：	
 * 修改历史：
 * 2017年12月27日 - wangyibo - 创建。
 */
package five.zero.seven.foreveryb.server.mappers.user;

import java.util.List;

import five.zero.seven.foreveryb.footstone.base.query.QueryFilter;
import five.zero.seven.foreveryb.footstone.mapper.MyMapper;
import five.zero.seven.foreveryb.server.pojo.user.User;

/**
 * User 类与SQL的映射关系 （目前最简单的使用） 以面向对象的方式对类User的操作映射成为对应的持久化操作
 * 
 * @author wangyibo
 *
 */
public interface UserMapper extends MyMapper<User> {

  public List<User> query(QueryFilter filter);
  
  public User getUserByUuid(String uuid);
  
  public void changeState(String uuid, Long version, String state);

}
