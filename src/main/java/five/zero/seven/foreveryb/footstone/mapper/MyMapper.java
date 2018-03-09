/**
 * IdeasForever，2018。
 * 
 * 项目名：	IdeasForeverFivezeroseven
 * 文件名：	MyMapper.java
 * 模块说明：	
 * 修改历史：
 * 2018年3月6日 - wangyibo - 创建。
 */
package five.zero.seven.foreveryb.footstone.mapper;

import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 继承通用mapper的接口，以便于灵活使用
 * 
 * @author wangyibo
 *
 */
public interface MyMapper<T> extends Mapper<T>,IdsMapper<T>,MySqlMapper<T>{

}
