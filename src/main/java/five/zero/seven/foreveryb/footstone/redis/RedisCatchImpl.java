/**
 * IdeasForever，2018。
 * 
 * 项目名：	IdeasForeverFivezeroseven
 * 文件名：	RedisCatchImpl.java
 * 模块说明：	
 * 修改历史：
 * 2018年3月1日 - wangyibo - 创建。
 */
package five.zero.seven.foreveryb.footstone.redis;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import five.zero.seven.foreveryb.footstone.util.JsonUtil;

/**
 * redis 存储操作使用的服务
 * 
 * @author wangyibo
 *
 */
@Service(value = RedisCatch.DEFAULT_CONTEXT_ID)
public class RedisCatchImpl implements RedisCatch {

  @Autowired 
  private StringRedisTemplate redisTemplate;

  /** redisTemplate： */
  public void setRedisTemplate(StringRedisTemplate redisTemplate) {
    this.redisTemplate = redisTemplate;
  }
  
  public StringRedisTemplate getRedisTemplate() {
    return this.redisTemplate;
  }

  public void put(String key, String value) {
    if (StringUtils.isBlank(key)) {
      return;
    }
    value.toString();
    redisTemplate.expire(key, 30, TimeUnit.MINUTES);
    redisTemplate.opsForHash().put(key, key, value);
  }

  public void put(String key, Object value) {
    if (StringUtils.isBlank(key)) {
      return;
    }
    redisTemplate.expire(key, 30, TimeUnit.MINUTES);
    redisTemplate.opsForHash().put(key, key, JsonUtil.objectToJson(value));
  }

  public <T> T get(String key, Class<T> className) {
    Object object = redisTemplate.opsForHash().get(key, key);
    if (object == null) {
      return null;
    }
    String jsonData = String.valueOf(object);
    return JsonUtil.jsonToObject(jsonData, className);
  }

  public String get(String key) {
    Object object = redisTemplate.opsForHash().get(key, key);
    if (object == null) {
      return null;
    }
    return String.valueOf(object);
  }

  public void delete(String key) {
    redisTemplate.delete(key);
  }

  public void setList(String key, List<?> value) {
    if (value == null) {
      return;
    }

    ListOperations listOperations = redisTemplate.opsForList();
    redisTemplate.expire(key, 30, TimeUnit.MINUTES);
    listOperations.leftPush(key, value);
  }

  public <T> List<T> getList(String key, Class<T> className) {
    Object object = redisTemplate.opsForList().leftPop(key);
    if (object == null) {
      return null;
    }
    String listString = String.valueOf(object);

    return JsonUtil.jsonToArrayList(listString, className);
  }

}
