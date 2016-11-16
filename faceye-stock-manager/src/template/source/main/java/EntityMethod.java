   /**
    * 说明:@property.desc@
    * 属性名: @property.name@
    * 类型: @property.type@
    * 数据库字段:@column.name@
    * @author haipenge
    */
    @Column(name="@column.name@")
	private  @property.type@ @property.name@;
	public @property.type@ get@property.method@() {
		return @property.name@;
	}
	public void set@property.method@(@property.type@ @property.name@) {
		this.@property.name@ = @property.name@;
	}
	
}/**@generate-entity-source@**/
	
