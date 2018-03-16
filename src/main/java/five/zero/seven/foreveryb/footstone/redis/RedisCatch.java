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
  
  /**
   * @description 保存字符串（使用hash类型）
   * @param key string, value string
   * */
  public void put(String key, String value);
  
  /**
   * @description 保存对象（使用hash类型）
   * @param key string, value object
   * */
  public void put(String key, Object value);
  
  /**
   * @description 获取对象
   * @param key string, class<T>
   * */
  public <T> T get(String key, Class<T> className);
  
  /**
   * @description 获取字符串
   * @param key string
   * */
  public String get(String key);
  
  /**
   * @description 删除
   * @param key string
   * */
  public void delete(String key);
  
  /**
   * @description 保存列表（list集合）
   * @param key string, List<T>
   * */
  public void setList(String key, List<?> value);
  
  /**
   * @description 获取列表（list集合）
   * @param key string, List<T>
   * */
  public <T> List<T> getList(String key, Class<T> className);

}
