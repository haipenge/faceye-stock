   /**<br>
 * 说明:@property.desc@<br>
 * 属性名: @property.name@<br>
 * 类型: @property.type@<br>
 * 数据库字段:@column.name@<br>
 * @author haipenge<br>
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
	
