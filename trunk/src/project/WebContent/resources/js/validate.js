function validateRequired(elementId, elementName) {
	if (Ext.get(elementId).dom.value.trim() == '') {
		Ext.Msg.alert('提示', '\" ' + elementName + " \"  输入项不允许为空！", function(
				button) {
			clear(button, elementId);
		});
		return false;
	} else {
		return true;
	}
}
function validateIP(elementId, elementName) {
	if(Ext.get(elementId).dom.value.trim() == ''){
		return true;
	}
	var ip = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
	if (ip.test(Ext.get(elementId).dom.value) == true) {
		return true;
	} else {
		Ext.Msg.alert('提示', '\" ' + elementName
				+ " \"  输入项为非法IP格式，正确格式如：192.168.0.10", function(button) {
			clear(button, elementId);
		});
		return false;
	}
}
function validateNum(elementId, elementName) {
	var ip = /^[0-9]*$/;
	if (ip.test(Ext.get(elementId).dom.value) == true) {
		return true;
	} else {
		Ext.Msg.alert('提示', '\" ' + elementName + " \"  输入项要求为数字格式", function(
				button) {
			clear(button, elementId);
		});
		return false;
	}
}
function validateURL(elementId, elementName) {
	if(Ext.get(elementId).dom.value.trim() == ''){
		return true;
	}
	
	//if (Ext.form.VTypes.url(Ext.get(elementId).dom.value) == true) {
	//	return true;
	//} else {
	//	Ext.Msg.alert('提示', elementName + " : " + Ext.form.VTypes.urlText,
	//			function(button) {
	//				clear(button, elementId);
	//			});
	//	return false;
	//}
	return true;
}
function validateEmail(elementId, elementName) {
	if(Ext.get(elementId).dom.value.trim() == ''){
		return true;
	}
	if (Ext.form.VTypes.email(Ext.get(elementId).dom.value) == true) {
		return true;
	} else {
		Ext.Msg.alert('提示', elementName + " : " + Ext.form.VTypes.emailText,
				function(button) {
					clear(button, elementId);
				});
		return false;
	}
}
function validateDateRange(elementId1, elementId2) {
	
	if (Ext.get(elementId1).dom.value <= Ext.get(elementId2).dom.value) {
		return true;
	} else {
		Ext.Msg.alert('提示', "生效时间大于失效时间，请重新输入!", function(
				button) {
			clear(button, elementId1);
			clear(button, elementId2);
		});
		return false;
	}
}

function validateAlpha(elementId, elementName) {
	if(Ext.get(elementId).dom.value.trim() == ''){
		return true;
	}
	if (Ext.form.VTypes.alpha(Ext.get(elementId).dom.value) == true) {
		return true;
	} else {
		Ext.Msg.alert('提示', elementName + " : " + Ext.form.VTypes.alphaText,
				function(button) {
					clear(button, elementId);
				});
		return false;
	}
}
function validateAlphanum(elementId, elementName) {
	if(Ext.get(elementId).dom.value.trim() == ''){
		return true;
	}
	if (Ext.form.VTypes.alphanum(Ext.get(elementId).dom.value) == true) {
		return true;
	} else {
		Ext.Msg.alert('提示', elementName + " : " + Ext.form.VTypes.alphanumText,
				function(button) {
					clear(button, elementId);
				});
		return false;
	}
}
function clear(button, elementId) {
	if (button == 'ok') {
		Ext.get(elementId).dom.value = '';
		Ext.get(elementId).focus();
	}
}



 function floatCheck(input)
  {
    return check(input,"0123456789.");
  }
   function check(input,checkString)
  {
    var ok = true;
    if(input==null||input.length==0) return ok;
    for (var i = 0; i < input.length; i++)
    {
      var chr = input.charAt(i);
      var found = false;
      for (var j = 0; j < checkString.length; j++)
      {
        if (chr == checkString.charAt(j)) found = true;
      }
      if (!found) ok = false;
    }
    return ok;
  }
function validateFloat(fieldId,name) {
	

    var field = document.getElementById(fieldId);
        
    if(field.value ==""){
		Ext.Msg.alert('提示', '\" ' + name + " \"  输入项不允许为空！", function(
				button) {
			clear(button, fieldId);
		});
		return false;
	}
   
    if (!floatCheck(field.value))
    {
        Ext.Msg.alert('提示', name + " : " + "不是正确的格式",
			function(button) {
				clear(button, fieldId);
			});
		return false;
    }
    
    return true;
}
function validateIntNum(fieldId,name) {
	
    var Ret = true;
    var NumStr = "0123456789";
    var chr;
    var field = document.getElementById(fieldId);
    if(field.value ==""){
		Ext.Msg.alert('提示', '\" ' + name + " \"  输入项不允许为空！", function(
				button) {
			clear(button, fieldId);
		});
		return false;
	}
    for (i = 0; i < field.value.length; ++i)
    {
        chr = field.value.charAt(i);
        if (NumStr.indexOf(chr, 0) == -1)
        {
            Ext.Msg.alert('提示', name + " : " + "必须是大于零的数字",
				function(button) {
					clear(button, fieldId);
				});
			return false;
        }
    }
    if (Number(field.value) > 2147483647 || Number(field.value) < 0) {
         Ext.Msg.alert('提示', name + " : " + "数字过大或者为负数",
				function(button) {
					clear(button, fieldId);
				});
			return false;
    }
    return true;
}
function validateNotNullURL(elementId, elementName) {
	if(Ext.get(elementId).dom.value.trim() == ''){
		Ext.Msg.alert('提示', '\" ' + elementName + " \"  输入项不允许为空！", function(
				button) {
			clear(button, elementId);
		});
		return false;
	}
	//if (Ext.form.VTypes.url(Ext.get(elementId).dom.value) == true) {
	//	return true;
	//} else {
	//	Ext.Msg.alert('提示', elementName + " : " + Ext.form.VTypes.urlText,
	//			function(button) {
	//				clear(button, elementId);
	//			});
	//	return false;
	//}
	return true;
}
function validateMaxText(fieldId,name,num) {
 
   if (Ext.get(fieldId).dom.value.trim() == '') {
		Ext.Msg.alert('提示', '\" ' + name + " \"  输入项不允许为空！", function(
				button) {
			clear(button, fieldId);
		});
		return false;
	} 
	if (Ext.get(fieldId).dom.value.trim().length >num) {
		Ext.Msg.alert('提示', '\" ' + name + " \" 长度不能大于"+num, function(
				button) {
			clear(button, fieldId);
		});
		return false;
	} 
	
	
	
	return true;
}
function validateMinLength(fieldId,name,num) {
	 
	if (Ext.get(fieldId).dom.value.trim().length <num) {
		Ext.Msg.alert('提示', '\" ' + name + " \" 长度不能小于"+num, function(
				button) {
			clear(button, fieldId);
		});
		return false;
	} 
		
	return true;
}
function getFilePostfix(oFile)
{

    if (oFile == null)
        return null;
    var pattern = /(.*)\.(.*)$/gi;
    if (typeof(oFile) == "object")
    {
        if (oFile.value == null || oFile.value == "")
            return null;
        var arr = pattern.exec(oFile.value);
        return RegExp.$2;
    }
    else if (typeof(oFile) == "string")
    {
        var arr = pattern.exec(oFile);
        return RegExp.$2;
    }
    else
        return null;

}
function validatePic(fieldId,name,checkNull){
   
	var pix=new Array('jpg','gif');
	var pic = document.getElementById(fieldId);
	if (pic.value == "" && checkNull == true)
	{
		Ext.Msg.alert('提示', '\" ' + name + " \"  文件不能为空", function(
				button) {
			clear(button, fieldId);
		}); 
		return false;
	}else if(pic.value == "" && checkNull == false)
	{
	
	
		//修改文件运行为空
		return true;
	}   
	
	var  filePix = getFilePostfix(pic);
	
	if(filePix == null || filePix =="")
	{
		Ext.Msg.alert('提示', '\" ' + name + " \"  文件类型必须是jpg,gif！", function(
				button) {
			clear(button, fieldId);
		});
		return false;
	}
	
	var flag = false;
	for(var i=0;i<pix.length;i++)
	{
		if(filePix.toLowerCase() == pix[i].toLowerCase())
		{
			flag = true;
			break;
		}
	}
	
	if(!flag)
	{
		Ext.Msg.alert('提示', '\" ' + name + " \"  文件类型必须是jpg,gif！", function(
				button) {
			clear(button, fieldId);
		});
		return false;
	}

	return true;
} 
function validateFlash(fieldId,name,checkNull){
	var pix=new Array('swf','fla');
	var file = document.getElementById(fieldId);
	if (file.value == "" && checkNull == true)
	{
		Ext.Msg.alert('提示', '\" ' + name + " \"  文件不能为空", function(
				button) {
			clear(button, fieldId);
		});
		return false;
	}else if (file.value == "" && checkNull == false)
	{
		//修改文件运行为空
		return true;
	}   
	var  filePix = getFilePostfix(file);
	if(filePix == null || filePix =="")
	{
		Ext.Msg.alert('提示', '\" ' + name + " \"  文件类型必须是 swf,fla", function(
				button) {
			clear(button, fieldId);
		});
		return false;
	}
	var flag = false;
	for(var i=0;i<pix.length;i++)
	{
		if(filePix.toLowerCase() == pix[i].toLowerCase())
		{
			flag = true;
			break;
		}
	}
	if(!flag)
	{
		Ext.Msg.alert('提示', '\" ' + name + " \"  文件类型必须是 swf,fla！", function(
				button) {
			clear(button, fieldId);
		});
		return false;
	}
	
	return true;
}
function validateVideo(fieldId,name,checkNull){
	
	var file = document.getElementById(fieldId);
	
	if (file.value == "" && checkNull == true)
	{
		Ext.Msg.alert('提示', '\" ' + name + " \"  文件不能为空", function(
				button) {
			clear(button, fieldId);
		});
		return false;
	}else if (file.value == "" && checkNull == false)
	{
		//修改文件运行为空
		return true;
	}   
	
	return true;
}
function validatePassword(elementId1, elementId2) {
	
	if (Ext.get(elementId1).dom.value == Ext.get(elementId2).dom.value) {
		return true;
	} else {
		Ext.Msg.alert('提示', "两次输入的密码不一致，请重新输入!", function(
				button) {
			clear(button, elementId1);
			clear(button, elementId2);
		});
		return false;
	}
}

function validateAudio(fieldId,name,checkNull){
var file = document.getElementById(fieldId);
	
	if (file.value == "" && checkNull == true)
	{
		Ext.Msg.alert('提示', '\" ' + name + " \"  文件不能为空", function(
				button) {
			clear(button, fieldId);
		});
		return false;
	}else if (file.value == "" && checkNull == false)
	{
		//修改文件运行为空
		return true;
	}   
	
	return true;
}
function validateRichFile(fieldId,name,checkNull){
	var file = document.getElementById(fieldId);
	
	if (file.value == "" && checkNull == true)
	{
		Ext.Msg.alert('提示', '\" ' + name + " \"  文件不能为空", function(
				button) {
			clear(button, fieldId);
		});
		return false;
	}else if (file.value == "" && checkNull == false)
	{
		//修改文件运行为空
		return true;
	}   
	
	return true;
}