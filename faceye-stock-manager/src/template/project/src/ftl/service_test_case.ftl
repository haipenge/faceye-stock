package ${project.packageName}.test.component.${component.packageName}.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.Assert;

import ${project.packageName}.component.${component.packageName}.entity.${entity.className};
import ${project.packageName}.component.${component.packageName}.service.${entity.className}Service;
import ${project.packageName}.test.feature.service.BaseServiceTestCase;


/**
 * ${entity.className}  服务层测试用例
 * 
 * @author ${project.author} 
 * 联系人:haipenge@gmail.com 
 * 创建日期:${.now}
 */
public class ${entity.className}ServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private ${entity.className}Service ${entity.className?uncap_first}Service = null;
	/**
	 * 初始化
	 * @todo
	 * @throws Exception
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年5月20日
	 */
	@Before
	public void set() throws Exception {
		Assert.isTrue(${entity.className?uncap_first}Service != null);
	}

	/**
	 * 清理
	 * @todo
	 * @throws Exception
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年5月20日
	 */
	@After
	public void after() throws Exception {
		//this.${entity.className?uncap_first}Service.removeAllInBatch();
	}

	/**
	 *  保存测试
	 * @todo
	 * @throws Exception
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年5月20日
	 */
	@Test
	public void testSave() throws Exception {
		${entity.className} ${entity.className?uncap_first} = new ${entity.className}();
		this.${entity.className?uncap_first}Service.save(${entity.className?uncap_first});
		List<${entity.className}> ${entity.className?uncap_first}s = this.${entity.className?uncap_first}Service.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(${entity.className?uncap_first}s));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		${entity.className} ${entity.className?uncap_first} = new ${entity.className}();
		this.${entity.className?uncap_first}Service.save(${entity.className?uncap_first});
		List<${entity.className}> ${entity.className?uncap_first}s = this.${entity.className?uncap_first}Service.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(${entity.className?uncap_first}s));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			${entity.className} ${entity.className?uncap_first} = new ${entity.className}();
			this.${entity.className?uncap_first}Service.save(${entity.className?uncap_first});
		}
		List<${entity.className}> ${entity.className?uncap_first}s = this.${entity.className?uncap_first}Service.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(${entity.className?uncap_first}s) && ${entity.className?uncap_first}s.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		${entity.className} ${entity.className?uncap_first} = new ${entity.className}();
		this.${entity.className?uncap_first}Service.save(${entity.className?uncap_first});
		logger.debug(">>Entity id is:" + ${entity.className?uncap_first}.getId());
		${entity.className} e = this.${entity.className?uncap_first}Service.get(${entity.className?uncap_first}.getId());
		Assert.isTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		${entity.className} ${entity.className?uncap_first} = new ${entity.className}();
		this.${entity.className?uncap_first}Service.save(${entity.className?uncap_first});
		this.${entity.className?uncap_first}Service.remove(${entity.className?uncap_first});
		List<${entity.className}> ${entity.className?uncap_first}s = this.${entity.className?uncap_first}Service.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(${entity.className?uncap_first}s));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			${entity.className} ${entity.className?uncap_first} = new ${entity.className}();
			this.${entity.className?uncap_first}Service.save(${entity.className?uncap_first});
		}
		List<${entity.className}> ${entity.className?uncap_first}s = this.${entity.className?uncap_first}Service.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(${entity.className?uncap_first}s) && ${entity.className?uncap_first}s.size() == 5);
		this.${entity.className?uncap_first}Service.removeAllInBatch();
		${entity.className?uncap_first}s = this.${entity.className?uncap_first}Service.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(${entity.className?uncap_first}s));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			${entity.className} ${entity.className?uncap_first} = new ${entity.className}();
			this.${entity.className?uncap_first}Service.save(${entity.className?uncap_first});
		}
		this.${entity.className?uncap_first}Service.removeAll();
		List<${entity.className}> ${entity.className?uncap_first}s = this.${entity.className?uncap_first}Service.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(${entity.className?uncap_first}s));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<${entity.className}> ${entity.className?uncap_first}s = new ArrayList<${entity.className}>();
		for (int i = 0; i < 5; i++) {
			${entity.className} ${entity.className?uncap_first} = new ${entity.className}();
			
			this.${entity.className?uncap_first}Service.save(${entity.className?uncap_first});
			${entity.className?uncap_first}s.add(${entity.className?uncap_first});
		}
		this.${entity.className?uncap_first}Service.removeInBatch(${entity.className?uncap_first}s);
		${entity.className?uncap_first}s = this.${entity.className?uncap_first}Service.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(${entity.className?uncap_first}s));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			${entity.className} ${entity.className?uncap_first} = new ${entity.className}();
			this.${entity.className?uncap_first}Service.save(${entity.className?uncap_first});
		}
		List<${entity.className}> ${entity.className?uncap_first}s = this.${entity.className?uncap_first}Service.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(${entity.className?uncap_first}s) && ${entity.className?uncap_first}s.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			${entity.className} ${entity.className?uncap_first} = new ${entity.className}();
			this.${entity.className?uncap_first}Service.save(${entity.className?uncap_first});
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<${entity.className}> page = this.${entity.className?uncap_first}Service.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.${entity.className?uncap_first}Service.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.${entity.className?uncap_first}Service.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);
	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			${entity.className} ${entity.className?uncap_first} = new ${entity.className}();
			this.${entity.className?uncap_first}Service.save(${entity.className?uncap_first});
			id = ${entity.className?uncap_first}.getId();
		}
		${entity.className} e = this.${entity.className?uncap_first}Service.get(id);
		Assert.isTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			${entity.className} ${entity.className?uncap_first} = new ${entity.className}();
			this.${entity.className?uncap_first}Service.save(${entity.className?uncap_first});
			if (i < 5) {
				ids.add(${entity.className?uncap_first}.getId());
			}
		}
		List<${entity.className}> ${entity.className?uncap_first}s = this.${entity.className?uncap_first}Service.getAll(ids);
		Assert.isTrue(${entity.className?uncap_first}s != null && ${entity.className?uncap_first}s.size() == 5);
	}
}
