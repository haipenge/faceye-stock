package com.faceye.component.generate.service.model;

import java.util.List;

import com.faceye.component.generate.entity.Component;
import com.faceye.component.generate.entity.DataType;
import com.faceye.component.generate.entity.Entity;
import com.faceye.component.generate.entity.Project;
import com.faceye.component.generate.entity.Property;
import com.faceye.component.generate.entity.Validate;

/**
 * 用于进行代码生成的描述文档 
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2015年4月10日
 */
public class Document implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6898205716112908111L;
	private Project project = null;
	private Component component = null;
	private Entity entity = null;
	private List<Property> properties = null;
	private List<DataType> dataTypes = null;
	private List<Validate> validates = null;
	

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Component getComponent() {
		return component;
	}

	public void setComponent(Component component) {
		this.component = component;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public List<Property> getProperties() {
		return properties;
	}

	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}

	public List<DataType> getDataTypes() {
		return dataTypes;
	}

	public void setDataTypes(List<DataType> dataTypes) {
		this.dataTypes = dataTypes;
	}

	public List<Validate> getValidates() {
		return validates;
	}

	public void setValidates(List<Validate> validates) {
		this.validates = validates;
	}

	

}
