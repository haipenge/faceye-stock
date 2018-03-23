package ${project.packageName}.test.component.${component.packageName}.repository.mongo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Assert;

import ${project.packageName}.component.${component.packageName}.entity.${entity.className};
import ${project.packageName}.component.${component.packageName}.repository.mongo.${entity.className}Repository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * ${entity.className} Repository 测试
 * @author ${project.author} 
 * 联系:haipenge@gmail.com
*  创建日期:${.now}<br>
 */
public class ${entity.className}RepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private ${entity.className}Repository ${entity.className?uncap_first}Repository = null;

	@Before
	public void before() throws Exception {
		//this.${entity.className?uncap_first}Repository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		${entity.className} ${entity.className?uncap_first} = new ${entity.className}();
		this.${entity.className?uncap_first}Repository.save(${entity.className?uncap_first});
		Iterable<${entity.className}> ${entity.className?uncap_first}s = this.${entity.className?uncap_first}Repository.findAll();
		Assert.assertTrue(${entity.className?uncap_first}s.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		${entity.className} ${entity.className?uncap_first} = new ${entity.className}();
		this.${entity.className?uncap_first}Repository.save(${entity.className?uncap_first});
        this.${entity.className?uncap_first}Repository.deleteById(${entity.className?uncap_first}.getId());
        Iterable<${entity.className}> ${entity.className?uncap_first}s = this.${entity.className?uncap_first}Repository.findAll();
		Assert.assertTrue(!${entity.className?uncap_first}s.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		${entity.className} ${entity.className?uncap_first} = new ${entity.className}();
		this.${entity.className?uncap_first}Repository.save(${entity.className?uncap_first});
		${entity.className?uncap_first}=this.${entity.className?uncap_first}Repository.findById(${entity.className?uncap_first}.getId());
		Assert.assertTrue(${entity.className?uncap_first}!=null);
	}

	
}
