/**
*说明:${component.name}->${component.packageName} -> ${entity.name} ${entity.className}  js 脚本
*作者:${project.author}
*/
var ${entity.className}={
  init:function(){
       /**
       *全选、全不选
       */
       ${r'$('}'input[name="check-all"]').click(function(){
	     Check.onCheck(${r'$('}'input[name="check-all"]'),${r'$('}'input[name="check-single"]'));
	    });
	    /**
	    *执行删除
	    */
	    ${r'$('}'.multi-remove').click(function(){
	       ${entity.className}.multiRemove();
	    });
  },
  /**
   * 批量删除
   */
  multiRemove:function(){
	  var checkedIds=Check.getCheckedIds(${r'$('}'input[name="check-single"]'));
	  if(checkedIds!=''){
		  ${r'$'}.ajax({
			  url:'/${component.packageName}/${entity.className?uncap_first}/multiRemove',
			  type:'post',
			  dataType:'json',
			  data:{
				  ids:checkedIds
			  },
			  success:function(data,textStatux,xhr){
				  var msg=new Msg({msg:'数据删除成功'});
				  var idArray=checkedIds.split(',');
				  for(var i=0;i<idArray.length;i++){
				     var id=idArray[i];
				     ${r'$('}'#'+id).remove();
				  }
				  msg.show();
			  }
		  });
	  }else{
		  var msg=new Msg({msg:'请选择要删除的数据',type:'warning'});
		  msg.show();
	  }
  }
};

$(document).ready(function(){
	${entity.className}.init();
});
