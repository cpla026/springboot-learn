select
===
* @ 和回车符号是定界符号，可以在里面写beetl语句。
* isEmpty是beetl的一个函数，用来判断变量是否为空或者是否不存在
* "#" 是占位符号
* 文件名约定为类名，首字母小写
select * from user where 1=1
@if(!isEmpty(age)){
and age = #age#
@}
@if(!isEmpty(name)){
and name = #name#
@}

getUserListPageQuery
===
	select  
	@pageTag(){
	    a.*
	@}  
	FROM  user a
    where  #use("condition")#
  
selectUserByName
===
* 根据name获user
    select * from user where name= #name#

sample
===
* 注释

	select #use("cols")# from user  where  #use("condition")#

cols
===
	id,age,name,description,cityId

updateSample
===
	id=#id#,age=#age#,name=#name#,description=#description#,cityId=#cityid#

condition
===
	1 = 2  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(age)){
	 and age=#age#
	@}
	@if(!isEmpty(name)){
	 and name like #'%'+name+'%'#
	@}
	@if(!isEmpty(description)){
	 and description=#description#
	@}
	@if(!isEmpty(cityid)){
	 and cityId=#cityid#
	@}
