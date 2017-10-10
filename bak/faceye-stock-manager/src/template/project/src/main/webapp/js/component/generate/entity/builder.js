/**
 * 说明:FormBuilder js 脚本 作者:@haipenge
 */
var FormBuilder = {
	init : function() {
		FormBuilder.addElement();
		FormBuilder.removeElement();
	},
	/**
	 * 向Form中添加元素
	 */
	addElement : function() {
		$('[data-ele="text"]').click(function() {
			FormBuilder.addText();
		});
		$('[data-ele="textarea"]').click(function() {
			FormBuilder.addTextArea();
		});
		$('[data-ele="radio"]').click(function() {
			FormBuilder.addRadio();
		});
		$('[data-ele="checkbox"]').click(function() {
			FormBuilder.addCheckBox();
		});
		$('[data-ele="select"]').click(function() {
			FormBuilder.addSelect();
		});
	},
	/**
	 * 添加输入框
	 */
	addText : function() {
		var html = '<input type="text" class="form-control" placeholder="请输入...">';
		FormBuilder.add2Form(html);
	},
	/**
	 * 添加文本框
	 */
	addTextArea : function() {
		var html = '<textarea class="form-control" rows="3" placeholder="请输入文本内容..."></textarea>';
		FormBuilder.add2Form(html);
	},
	/**
	 * 添加单选按钮
	 */
	addRadio : function() {
		var html = '<label class="radio-inline">';
		html += '<input type="radio" name="radio" id="radio1" value="1">选项1';
		html += '</label>';
		html += '<label class="radio-inline">';
		html += '<input type="radio" name="radio" id="radio2" value="2">选荐2';
		html += '</label>';
		html += '<label class="radio-inline">';
		html += '<input type="radio" name="radio" id="radio3" value="2">选项3';
		html += '</label>';
		FormBuilder.add2Form(html);
	},
	/**
	 * 添加复选框
	 */
	addCheckBox : function() {
		var html = '<label class="checkbox-inline">';
		html += '<input type="checkbox" name="radio" id="radio1" value="1">选项1';
		html += '</label>';
		html += '<label class="checkbox-inline">';
		html += '<input type="checkbox" name="radio" id="radio2" value="2">选项2';
		html += '</label>';
		html += '<label class="checkbox-inline">';
		html += '<input type="checkbox" name="radio" id="radio3" value="2">选项3';
		html += '</label>';
		FormBuilder.add2Form(html);
	},
	/**
	 * 选择下拉选择框
	 */
	addSelect : function() {
		var html = '<select class="form-control">';
		html += '<option value="1">选项1</option>';
		html += '<option value="2">选项2</option>';
		html += '<option value="3">选项3</option>';
		html += '</select>';
		FormBuilder.add2Form(html);
	},
	/**
	 * 将Html添加到表单
	 * 
	 * @param html
	 */
	add2Form : function(ele) {
		var html = '<div class="form-group">';
		html += '<label class="col-sm-2 control-label">Field</label>';
		html += '<div class="col-sm-8">';
		html += ele;
		html += '</div>';
		html += '<div class="col-sm-2">';
		html += '<span class="ele-reduce">-</span>';
		html += '</div>';
		html += '</div>';
		$('#form-container form').append(html);
	},
	/**
	 * 删除Form组件
	 */
	removeElement : function() {
		$('#form-container').delegate('.ele-reduce', 'click', function() {
			$(this).parent().parent().remove();
		});
	},
	/**
	 * 设置表单元素的属性
	 */
	setElementProperties:function(elementType){
		
	}
};

$(document).ready(function() {
	FormBuilder.init();
});
