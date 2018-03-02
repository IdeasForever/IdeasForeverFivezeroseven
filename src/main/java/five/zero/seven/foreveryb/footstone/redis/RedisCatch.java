/**
 * IdeasForever，2018。
 * 
 * 项目名：	IdeasForeverFivezeroseven
 * 文件名：	RedisCatch.java
 * 模块说明：	
 * 修改历史：
 * 2018年3月1日 - wangyibo - 创建。
 */
package five.zero.seven.foreveryb.footstone.redis;

import java.util.List;

/**
 * @author wangyibo
 *
 */
public interface RedisCatch {
  
  /** 用于在Spring容器的默认ID。 */
  public static final String DEFAULT_CONTEXT_ID = "redis-service.redisCatch";
  
  public void put(String key, String value);
  
  public void put(String key, Object value);
  
  public <T> T get(String key, Class<T> className);
  
  public String get(String key);
  
  public void delete(String key);
  
  public void setList(String key, List<?> value);
  
  public <T> List<T> getList(String key, Class<T> className);

}
