var digits = "0123456789";
var lowercaseLetters = "abcdefghijklmnopqrstuvwxyz"
var uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

// whitespace characters
var whitespace = " \t\n\r";

function selCount(){
	var checkObj=document.getElementsByName("chkItem");
	if(checkObj==null){
		return 0;
	}
	if(typeof(checkObj.length)=="undefined"){
		if(checkObj.checked){
			return 1;
		}
		return 0;
	}else{
		var j=0;
		for(var i=0;i<checkObj.length;i++){
			if(checkObj[i].checked){
				j++;
			}
		}
		return j;
	}
}

function selCount1(name){
	var checkObj=document.getElementsByName(name);
	if(checkObj==null){
		return 0;
	}
	if(typeof(checkObj.length)=="undefined"){
		if(checkObj.checked){
			return 1;
		}
		return 0;
	}else{
		var j=0;
		for(var i=0;i<checkObj.length;i++){
			if(checkObj[i].checked){
				j++;
			}
		}
		return j;
	}
}

function getCheckBoxValue(name){
	var checkObj=document.getElementsByName(name);
	if(checkObj==null){
		return "";
	}
	if(typeof(checkObj.length)=="undefined"){
		if(checkObj.checked){
			return checkObj.value;
		}
		return "";
	}else{
		var val="";
		var j=0;
		for(var i=0;i<checkObj.length;i++){
			if(checkObj[i].checked){
				j++;
				if(j==1){
					val=checkObj[i].value;
				}else{
					val=val+","+checkObj[i].value;
				}
			}
		}
		return val;
	}
}


function trim(str){
	return str.replace(/(^\s*)|(\s*$)/g, "");
}

function isInt(n) {
	var i = parseInt(n*1);
	if (i == NaN) {
		return false;
	}
	if (i != n){
		return false;
	}
	return true;
}



function isMail(str) {
	if (trim(str) == ""){
		return true;
	}
    var a=str.indexOf("@")+1;
    var p=str.indexOf(".")+1;
    if(str.indexOf("'") > 0)
		return false;
	if(str.indexOf('"') > 0)
		return false;
    if (a<2)
       return false;
    if (p<1)
       return false;
    if (p<a+2)
       return false;
    if (str.length==p)
       return false;
    return true;
}



function isPhone(str){
	var intIndex;
	var intCharCount;
	for(intIndex = 0; intIndex < str.length; intIndex++){
		if(str.charCodeAt(intIndex) < 32)
			return false;
		if(str.charCodeAt(intIndex) == 34)
			return false;
		if(str.charCodeAt(intIndex) == 39)
			return false;
		if(str.charCodeAt(intIndex) > 126)
			return false;
	}
	return true;
}




function isDecimal(str,f,n) {
    var p=str.indexOf(".");
    var int,flt;

    if(str=="") return true;
    if (p<0) { p=str.length ;}
    int=str.substr(0,p);
    flt=str.substr(p+1);
    if (isInt(int)==false) {
       return false;
    }
    if (flt!='') {
       if (isInt(flt)==false) {
          return false;
       }
    }
    if ((int.length > f-n) || (flt.length > n)) {
       return false;
    }
    return true;
}



function isFloat(str) {
	var ch=str.charAt(0);
	if( ch == "." ) return false;
    for (var i=0; i < str.length; i++)
	{	ch=str.charAt(i);
		if ((ch != ".") && (ch != "0") && (ch != "1") && (ch != "2") && (ch != "3") && (ch != "4") && (ch != "5") && (ch != "6") && (ch != "7") && (ch != "8") && (ch != "9"))
			return false;
	}
    return true;
}

function isNumber(str) {
    for (var i=0; i < str.length; i++)
	{	var ch=str.charAt(i);
		if ((ch != "0") && (ch != "1") && (ch != "2") && (ch != "3") && (ch != "4") && (ch != "5") && (ch != "6") && (ch != "7") && (ch != "8") && (ch != "9"))
			return false;
	}
    return true;
}



function loadOption(value,id){   
	var selectObj=document.getElementById(id);
	for(var i=0;i<selectObj.length;i++)
	{
		if(selectObj.options[i].value == value)
		{
			selectObj.options[i].selected = true;
			return;
		}
	}
}




function loadRadiaBox(value,name){
	   
	var selectObj=document.getElementsByName(name);
	for(var i=0;i<selectObj.length;i++)
	{
		if(selectObj[i].value == value)
		{	
			selectObj[i].checked = true;
			return;
		}
	}
}	 	  	

function loadCheckBox(value,name){  

	var selectObj=document.getElementsByName(name);
	var valueArray=value.split(",");
	for(var i=0;i<selectObj.length;i++)
	{	for(var j=0;j<valueArray.length;j++){
			if(selectObj[i].value == valueArray[j])
			{	
				selectObj[i].checked = true;
			}
		}
		
	}
}


function getRadiaBoxValue(name){
	var value="";   
	var selectObj=document.getElementsByName(name);
	for(var i=0;i<selectObj.length;i++)
	{
		if(selectObj[i].checked == true){
			value=selectObj[i].value;
			break;
		}
	}
	return value;
}




/**
 * 检查字符串的合法性
 * input为需要检查的字符串，checkString为合法字符串
 * Ted Fan 2003-04-06
 * @return
 */
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
  
    /**
   * 检查非法字符
   * input为输入的字符串，checkString为非法的字符串
 * Ted Fan 2003-04-06
   * @return
   */
  function errCheck(input,checkString)
  {
    var ok = true;
    for (var i = 0; i < input.length; i++)
    {
      var chr = input.charAt(i);
      var found = true;
      for (var j = 0; j < checkString.length; j++)
      {
        if (chr == checkString.charAt(j)) found = false;
      }
      if (!found) ok = false;
    }
    return ok;
  }
  /**
   * 检查登陆名，只能a-z,A-Z,_,0-9字母开头
   * Ted Fan 2003-04-06
   * @return
   */
  function loginCheck(input)
  {
    result=true;
    result=check(input,"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_");
    if(!check(input.charAt(0),"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ")) result=false;
    return result;
  }
  
  
  /*check ip address wenhui 2003-05-01*/
  function ipCheck(input)
  {
	if (!check(input,"0123456789."))
		return false;
	
	var temp=0;
	var npos=0;
	for (var i=0;i<input.length;i++)
	{
		var chr = input.charAt(i);
		if ((i==0)&&(chr=="."))
		   return false;
		if (chr=="."){
		  if ((npos==0)&&(temp==0)) return false;
		  npos++;
                  temp=0;
                  if ((i==input.length-1)&&(npos!=3)) return false;
                  if (i==input.length-1) return false;
		  continue;
		}
		temp +=chr;
		if ((i==input.length-1)&&(temp==0)) return false;
                if ((i==input.length-1)&&(npos!=3)) return false;
		if (temp>255)
		   return false;
	}
	return true;
  }
  /**
   * 纯数字检查
   * Ted Fan 2003-04-06
   * @return
   */
  function digCheck(input)
  {
    return check(input,"0123456789");
  }
  
  /**
   * 小数判断
   * Ted Fan 2003-04-06
   * @return
   */
  function floatCheck(input)
  {
    return check(input,"0123456789.");
  }
  
  /**
   * 检查邮政编码
   * @return
   */
  function zipcodeCheck(input)
  {
  	if (!digCheck(input))
		return false
  	
  	if (input > 999999)
  		return false;
  	
  	return true
  }
  
/**
  * 取得字符串str的长度 
  * @param str : 被检查字串
  * @return 字串长度
  */
function strLength(str)
{
	y=0;
	for(i=0;i<str.length;i++)
	{
	if(str.charCodeAt(i)> 0 && str.charCodeAt(i) <255)        //如果是ASCII码
		y++;
	else
		y+=2;
	}
	return y;
}

/**
  * 判断字符串是否超出指定的长度
  * @param : str - 被检查的字符串
  * @param : maxlen - 指定的最大长度
  * return : 超出-true，没有超出-false 
  */
function isUpLength(str,maxlen)
{
	if(strLength(str)>maxlen)
		return true;
	else
		return false;
}

//-----------------------------------------------------------------------------------
//本函数用于对sString字符串进行前空格截除
//-----------------------------------------------------------------------------------
function JHshLTrim(sString)
{ 
	var sStr,i,iStart,sResult = ""; 
	sStr = sString.split("");
	iStart = -1 ;
	for (i = 0 ; i < sStr.length ; i++)
	{
		if (sStr[i] != " ") 
		{
			iStart = i;
			break;
		}
	}
	if (iStart == -1) 
		return "" ;    //表示sString中的所有字符均是空格,则返回空串
	else 
		return sString.substring(iStart) ;
}

//-----------------------------------------------------------------------------------
//本函数用于对sString字符串进行后空格截除
// -----------------------------------------------------------------------------------
function JHshRTrim(sString)
{ 
	var sStr,i,sResult = "",sTemp = "" ; 
	// if (sString.length == 0) { return "" ;} // 参数sString是空串
	sStr = sString.split("");
	for (i = sStr.length - 1 ; i >= 0 ; i-- )  // 将字符串进行倒序
	{ 
 		sResult = sResult + sStr[i]; 
	}
	sTemp = JHshLTrim(sResult) ; // 进行字符串前空格截除
	if (sTemp == "") 
		return "" ;
	sStr = sTemp.split("");
	sResult = "" ;
	for (i = sStr.length - 1 ; i >= 0 ; i--) // 将经处理后的字符串再进行倒序
		sResult = sResult + sStr[i];
	return sResult ;
} 

// 截除字符串前后空格
function JHshTrim(sString)
{
	var strTmp ; 
	strTmp = JHshRTrim(JHshLTrim(sString)) ;
	return strTmp ;
}


function isValidDate(day, month, year) {
	        if (month < 1 || month > 12) {
                    return false;
                }
                if (day < 1 || day > 31) {
                    return false;
                }
                if ((month == 4 || month == 6 || month == 9 || month == 11) &&
                    (day == 31)) {
                    return false;
                }
                if (month == 2) {
                    var leap = (year % 4 == 0 &&
                               (year % 100 != 0 || year % 400 == 0));
                    if (day>29 || (day == 29 && !leap)) {
                        return false;
                    }
                }
                return true;
            }

// Check whether string s is empty.
function isEmpty(s)
{   return ((s == null) || (s.length == 0))
}

// Returns true if string s is empty or
// whitespace characters only.
function isWhitespace (s)

{   var i;

    // Is s empty?
    if (isEmpty(s)) return true;

    // Search through string's characters one by one
    // until we find a non-whitespace character.
    // When we do, return false; if we don't, return true.

    for (i = 0; i < s.length; i++)
    {
        // Check that current character isn't whitespace.
        var c = s.charAt(i);

        if (whitespace.indexOf(c) == -1) return false;
    }

    // All characters are whitespace.
    return true;
}

/**
   * 检查电话号码
   * Ted Fan 2003-04-06
   * @return
   */
  function telCheck(input)
  {
    return check(input,"0123456789-()+");
  }