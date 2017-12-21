
package com.tongji.testserver.domain.result;

public enum ExceptionMsg {
	SUCCESS("200", "操作成功"),
	FAILED("400","操作失败"),
    ERROR("400","发生异常"),
    ParamError("400", "参数错误！"),
    ParamNull("400", "参数有空值！"),
    DesParamError("400", "destination参数不完整"),
    PathParamError("400", "path参数不完整"),
    DesIdError("400", "destination id找不到"),
    PathIdError("400", "path id 找不到"),
    LoadHotIdError("400", "loadHotNode id找不到"),
    PsgHotIdError("400", "PsgHotNode id 找不到"),
    LoginNameOrPassWordError("400", "用户名或者密码错误！"),
    GetUserError("400", "用户无法获取"),
    GetPathError("400", "路径id查找错误"),
    EmailUsed("400","该邮箱已被注册"),
    UserNameUsed("400","该登录名称已存在"),
    EmailNotRegister("201","该邮箱地址未注册"),
    LinkOutdated("404","该链接已过期，请重新请求"),
    PassWordError("400","密码输入错误"),
    UserNameLengthLimit("400","用户名长度超限"),
    LoginNameNotExists("401","该用户未注册"),
    UserNameSame("400","新用户名与原用户名一致"),

    FavoritesNameIsNull("400","收藏夹名称不能为空"),
    FavoritesNameUsed("400","收藏夹名称已被创建"),
    
    CollectExist("400","该文章已被收藏"),
    
    FileEmpty("400","上传文件为空"),
    LimitPictureSize("401","图片大小必须小于2M"),
    LimitPictureType("402","图片格式必须为'jpg'、'png'、'jpge'、'gif'、'bmp'")
    ;
   private ExceptionMsg(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    private String code;
    private String msg;
    
	public String getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}

    
}

