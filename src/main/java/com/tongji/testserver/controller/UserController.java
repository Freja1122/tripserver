package com.tongji.testserver.controller;
import com.tongji.testserver.comm.Const;
import com.tongji.testserver.comm.aop.LoggerManage;
import com.tongji.testserver.domain.*;
import com.tongji.testserver.domain.result.ExceptionMsg;
import com.tongji.testserver.domain.result.Response;
import com.tongji.testserver.domain.result.ResponseData;
import com.tongji.testserver.repository.PathRepository;
import com.tongji.testserver.repository.UserRepository;
import com.tongji.testserver.service.HotNodeService;
import com.tongji.testserver.utils.DateUtils;
import com.tongji.testserver.utils.FileUtil;
import com.tongji.testserver.utils.MD5Util;
import com.tongji.testserver.utils.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import sun.misc.BASE64Decoder;
import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * @program: testserver
 * @description:
 * @author: Annntn
 * @create: 2017-12-19 17:13
 **/
@RestController
@RequestMapping("/users")
@EnableSwagger2
public class UserController extends BaseController{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PathRepository pathRepository;
    @Resource
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String mailFrom;
    @Value("${mail.subject.forgotpassword}")
    private String mailSubject;
    @Value("${mail.content.forgotpassword}")
    private String mailContent;
    @Value("${forgotpassword.url}")
    private String forgotpasswordUrl;
    @Value("${static.url}")
    private String staticUrl;
    @Value("${file.profilepictures.url}")
    private String fileProfilepicturesUrl;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @LoggerManage(description="登陆")
    public ResponseData login(User user, HttpServletResponse response) {
        try {
            //通过用户名或者邮箱登录
            if (user.getPassWord()==null) {
                return new ResponseData(ExceptionMsg.PassWordError);
            }
            User loginUser = userRepository.findByUserNameOrEmail(user.getUserName(), user.getEmail());
            if (loginUser == null ) {
                return new ResponseData(ExceptionMsg.LoginNameNotExists);
            }else if(!loginUser.getPassWord().equals(getPwd(user.getPassWord()))){
                return new ResponseData(ExceptionMsg.LoginNameOrPassWordError);
            }
            //创建cookie保存登录用户信息
            //设置cookie寿命30天
            Cookie cookie = new Cookie(Const.LOGIN_SESSION_KEY, cookieSign(loginUser.getId().toString()));
            cookie.setMaxAge(Const.COOKIE_TIMEOUT);
            cookie.setPath("/");
            response.addCookie(cookie);
            getSession().setAttribute(Const.LOGIN_SESSION_KEY, loginUser);
            //获取登录前的界面
            String preUrl = "/";
            if(null != getSession().getAttribute(Const.LAST_REFERER)){
                preUrl = String.valueOf(getSession().getAttribute(Const.LAST_REFERER));
            }
            return new ResponseData(ExceptionMsg.SUCCESS, preUrl);
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("login failed, ", e);
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }

    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    @LoggerManage(description="注册")
    public ResponseData create(User user) {
        try {
            User registUser = userRepository.findByEmail(user.getEmail());
            //检查邮箱是否注册过
            if (null != registUser) {
                return new ResponseData(ExceptionMsg.EmailUsed);
            }
            //检查用户名是否注册过
            User userNameUser = userRepository.findByUserName(user.getUserName());
            if (null != userNameUser) {
                return new ResponseData(ExceptionMsg.UserNameUsed);
            }
            if (user.getPassWord()==null) {
                return new ResponseData(ExceptionMsg.PassWordError);
            }
            user.setPassWord(getPwd(user.getPassWord()));
            user.setCreateTime(DateUtils.getCurrentTime());
            user.setLastModifyTime(DateUtils.getCurrentTime());
            user.setProfilePicture("img/favicon.png");

//            //创建四个默认收藏夹
//            user.setDestinationFavorites(new DestinationFavorites());
//            user.setPathFavorites(new PathFavorites());
//            user.setLoadHotFavorites(new LoadHotFavorites());
//            user.setPsgHotFavorites(new PsgHotFavorites());

            userRepository.save(user);
            getSession().setAttribute(Const.LOGIN_SESSION_KEY, user);
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("create user failed, ", e);
            return new ResponseData(ExceptionMsg.FAILED);
        }
        return new ResponseData(ExceptionMsg.SUCCESS);
    }

    /**
     * 忘记密码-发送重置邮件
     * @param email
     * @return
     */
    @RequestMapping(value = "/forgotPasswordEmail", method = RequestMethod.POST)
    @LoggerManage(description="发送忘记密码邮件")
    public ResponseData sendForgotPasswordEmail(String email) {
        try {
            User registUser = userRepository.findByEmail(email);
            if (null == registUser) {
                return new ResponseData(ExceptionMsg.EmailNotRegister);
            }
            String secretKey = UUID.randomUUID().toString(); // 密钥
            Timestamp outDate = new Timestamp(System.currentTimeMillis() + 30 * 60 * 1000);// 30分钟后过期
            long date = outDate.getTime() / 1000 * 1000;
            userRepository.setOutDateAndValidataCode(outDate+"", secretKey, email);
            String key =email + "$" + date + "$" + secretKey;
            String digitalSignature = MD5Util.encrypt(key);// 数字签名
//            String basePath = this.getRequest().getScheme() + "://" + this.getRequest().getServerName() + ":" + this.getRequest().getServerPort() + this.getRequest().getContextPath() + "/newPassword";
            String resetPassHref = forgotpasswordUrl + "?sid="
                    + digitalSignature +"&email="+email;
            String emailContent = MessageUtil.getMessage(mailContent, resetPassHref);
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(mailFrom);
            helper.setTo(email);
            helper.setSubject(mailSubject);
            helper.setText(emailContent, true);
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("sendForgotPasswordEmail failed, ", e);
            return new ResponseData(ExceptionMsg.FAILED);
        }
        return new ResponseData(ExceptionMsg.SUCCESS);
    }

    /**
     * 忘记密码-设置新密码
     * @param newpwd
     * @param email
     * @param sid
     * @return
     */
    @RequestMapping(value = "/newPasswordWithCode", method = RequestMethod.POST)
    @LoggerManage(description="设置新密码")
    public ResponseData setNewPassword(String newpwd, String email, String sid) {
        try {
            User user = userRepository.findByEmail(email);
            Timestamp outDate = Timestamp.valueOf(user.getOutDate());
            if(outDate.getTime() <= System.currentTimeMillis()){ //表示已经过期
                return new ResponseData(ExceptionMsg.LinkOutdated);
            }
            String key = user.getEmail()+"$"+outDate.getTime()/1000*1000+"$"+user.getValidataCode();//数字签名
            String digitalSignature = MD5Util.encrypt(key);
            if(!digitalSignature.equals(sid)) {
                return new ResponseData(ExceptionMsg.LinkOutdated);
            }
            userRepository.setNewPassword(getPwd(newpwd), email);
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("setNewPassword failed, ", e);
            return new ResponseData(ExceptionMsg.FAILED);
        }
        return new ResponseData(ExceptionMsg.SUCCESS);
    }

    /**
     * 修改密码
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @RequestMapping(value = "/newPassword", method = RequestMethod.POST)
    @LoggerManage(description="修改密码")
    public ResponseData updatePassword(String oldPassword, String newPassword) {
        try {
            User user = getUser();
            String password = user.getPassWord();
            String newpwd = getPwd(newPassword);
            if(password.equals(getPwd(oldPassword))){
                userRepository.setNewPassword(newpwd, user.getEmail());
                user.setPassWord(newpwd);
                getSession().setAttribute(Const.LOGIN_SESSION_KEY, user);
            }else{
                return new ResponseData(ExceptionMsg.PassWordError);
            }
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("updatePassword failed, ", e);
            return new ResponseData(ExceptionMsg.FAILED);
        }
        return new ResponseData(ExceptionMsg.SUCCESS);
    }

    /**
     * 修改个人简介
     * @param introduction
     * @return
     */
    @RequestMapping(value = "/introduction", method = RequestMethod.POST)
    @LoggerManage(description="修改个人简介")
    public ResponseData updateIntroduction(String introduction) {
        try {
            User user = getUser();
            userRepository.setIntroduction(introduction, user.getEmail());
            user.setIntroduction(introduction);
            return new ResponseData(ExceptionMsg.SUCCESS, introduction);
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("updateIntroduction failed, ", e);
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }

    /**
     * 修改昵称
     * @param userName
     * @return
     */
    @RequestMapping(value = "/userName", method = RequestMethod.POST)
    @LoggerManage(description="修改昵称")
    public ResponseData updateUserName(String userName) {
        try {
            User loginUser = getUser();
            if(userName.equals(loginUser.getUserName())){
                return new ResponseData(ExceptionMsg.UserNameSame);
            }
            User user = userRepository.findByUserName(userName);
            if(null != user && user.getUserName().equals(userName)){
                return new ResponseData(ExceptionMsg.UserNameUsed);
            }
            if(userName.length()>12){
                return new ResponseData(ExceptionMsg.UserNameLengthLimit);
            }
            userRepository.setUserName(userName, loginUser.getEmail());
            loginUser.setUserName(userName);
            return new ResponseData(ExceptionMsg.SUCCESS, userName);
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("updateUserName failed, ", e);
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }

    /**
     * 上传头像
     * @param dataUrl
     * @return
     */
    @RequestMapping(value = "/headPortrait", method = RequestMethod.POST)
    public ResponseData uploadHeadPortrait(String dataUrl){
        logger.info("执行 上传头像 开始");
        try {
            String filePath=staticUrl+fileProfilepicturesUrl;
            String fileName=UUID.randomUUID().toString()+".png";
            String savePath = fileProfilepicturesUrl+fileName;
            String image = dataUrl;
            String header ="data:image";
            String[] imageArr=image.split(",");
            if(imageArr[0].contains(header)){
                image=imageArr[1];
                BASE64Decoder decoder = new BASE64Decoder();
                byte[] decodedBytes = decoder.decodeBuffer(image);
                 FileUtil.uploadFile(decodedBytes, filePath, fileName);
                User user = getUser();
                userRepository.setProfilePicture(savePath, user.getId());
                user.setProfilePicture(savePath);
            }
            logger.info("头像地址：" + savePath);
            logger.info("执行 上传头像 结束");
            return new ResponseData(ExceptionMsg.SUCCESS, savePath);
        } catch (Exception e) {
            logger.error("upload head portrait failed, ", e);
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }

    @RequestMapping(value="/logout",method=RequestMethod.GET)
    @LoggerManage(description="登出")
    public ResponseData logout(HttpServletResponse response) {
        getSession().removeAttribute(Const.LOGIN_SESSION_KEY);
        getSession().removeAttribute(Const.LAST_REFERER);
        Cookie cookie = new Cookie(Const.LOGIN_SESSION_KEY, "");
        cookie.setMaxAge(0);
//        cookie.setPath("/");
        response.addCookie(cookie);
        return new ResponseData(ExceptionMsg.SUCCESS);
    }

    @RequestMapping(value="/logout",method=RequestMethod.POST)
    @LoggerManage(description="登出")
    public ResponseData logoutPost(HttpServletResponse response) {
        logout(response);
        return new ResponseData(ExceptionMsg.SUCCESS);
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @LoggerManage(description="查看用户个人信息")
    public ResponseData info() {
        try {
            User user = getUser();
            //检查邮箱是否注册过
            if (null == user) {
                return new ResponseData(ExceptionMsg.FAILED);
            }
            User tempUser = new User();
            tempUser.setId(user.getId());
            tempUser.setUserName(user.getUserName());
            tempUser.setEmail(user.getEmail());
            tempUser.setProfilePicture(user.getProfilePicture());
            tempUser.setUserPhone(user.getUserPhone());
            tempUser.setIntroduction(user.getIntroduction());
            tempUser.setCreateTime(user.getCreateTime());
            tempUser.setLastModifyTime(user.getLastModifyTime());
            return new ResponseData(ExceptionMsg.SUCCESS, tempUser);
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("get info failed, ", e);
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }


    @RequestMapping(value="",method=RequestMethod.DELETE)
    @LoggerManage(description="删除")
    public ResponseData delete(HttpServletResponse response) {
        User user = getUser();
        if (user == null){
            return new ResponseData(ExceptionMsg.FAILED);
        }
        userRepository.delete(user);
        getSession().removeAttribute(Const.LOGIN_SESSION_KEY);
        getSession().removeAttribute(Const.LAST_REFERER);
        Cookie cookie = new Cookie(Const.LOGIN_SESSION_KEY, "");
        cookie.setMaxAge(0);
//        cookie.setPath("/");
        response.addCookie(cookie);
        return new ResponseData(ExceptionMsg.SUCCESS);
    }

    @RequestMapping(value = "/loadFavorites", method = RequestMethod.GET)
    @LoggerManage(description="查看载客热点收藏夹")
    public ResponseData getLoadFv() {
        try {
            User user = getUser();
            return new ResponseData(ExceptionMsg.SUCCESS, user.getLoadHotFavorites());
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("get info failed, ", e);
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }

    @RequestMapping(value = "/psgFavorites", method = RequestMethod.GET)
    @LoggerManage(description="查看候车热点收藏夹")
    public ResponseData getPsgFv() {
        try {
            User user = getUser();
            return new ResponseData(ExceptionMsg.SUCCESS, user.getPsgHotFavorites());
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("get info failed, ", e);
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }

    @RequestMapping(value = "/pathFavorites", method = RequestMethod.GET)
    @LoggerManage(description="查看路径收藏夹")
    public ResponseData getPath() {
        try {
            User user = getUser();
            return new ResponseData(ExceptionMsg.SUCCESS, user.getPathFavorites());
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("get info failed, ", e);
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }

    @RequestMapping(value = "/desFavorites", method = RequestMethod.GET)
    @LoggerManage(description="查看目的地热点收藏夹")
    public ResponseData getDesFv() {
        try {
            User user = getUser();
            return new ResponseData(ExceptionMsg.SUCCESS, user.getDestinationFavorites());
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("get info failed, ", e);
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }

    @RequestMapping(value = "/pathHistory", method = RequestMethod.GET)
    @LoggerManage(description="查看目的地历史记录")
    public ResponseData getPathHistory() {
        try {
            User user = getUser();
            return new ResponseData(ExceptionMsg.SUCCESS, user.getPaths());
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("get info failed, ", e);
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }

    @RequestMapping(value = "/path", method = RequestMethod.DELETE)
    @LoggerManage(description="删除路径历史根据id")
    public ResponseData deletePathFromFavorites(Long id) {
        try {
            User user = getUser();
            Path path = pathRepository.findById(id);
            if (path==null){
                new ResponseData(ExceptionMsg.PsgHotIdError);
            }

            if(user.deletePath(path)==false){
                return new ResponseData(ExceptionMsg.FAILED, "删除失败");
            }
            userRepository.save(user);
            return new ResponseData(ExceptionMsg.SUCCESS, user.getDestinationFavorites());
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("updateIntroduction failed, ", e);
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }


}
