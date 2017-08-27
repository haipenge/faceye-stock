package com.faceye.component.generate.service;
/**
 * 源码生成服务类
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2015年4月9日
 */
public interface GenerateService {

	/**
	 * 生成项目基础源码
	 * @todo
	 * @param projectId
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2015年4月9日
	 */
	public void generate(Long projectId);
	
	/**
	 * 将一个项目打成tar.gz包，供用户下载
	 * @todo
	 * @param projectId
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年4月17日
	 */
	public String gzip(Long projectId);
	
	
	/**
	 * 启动项目
	 * @todo
	 * @param projectId
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年4月17日
	 */
	public String run(Long projectId);
	
	/**
	 * 停止项目
	 * @todo
	 * @param projectId
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年4月17日
	 */
	public String stop(Long projectId);
}
