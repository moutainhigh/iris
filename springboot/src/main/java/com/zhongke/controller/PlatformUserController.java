package com.zhongke.controller;

import com.github.pagehelper.PageInfo;
import com.zhongke.entity.Result;
import com.zhongke.pojo.Announcement;
import com.zhongke.pojo.Menu;
import com.zhongke.pojo.PlatformUser;
import com.zhongke.service.AnnouncementService;
import com.zhongke.service.PlatformUserService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName PlatformUserController
 * @Description 平台用户
 * @Author liuli
 * @Date 2020/4/2 17:06
 * @Version 1.0
 **/

@RestController
@RequestMapping("/platUser")
@Api(value = "平台用户相关")
public class PlatformUserController {

    private final Logger logger = LoggerFactory.getLogger(PlatformUserController.class);

    @Autowired(required = false)
    private PlatformUserService platformUserService;

    /**
     * @Description springseurity验证成功后跳转首页加载菜单
     * @author liuli
     * @date 2020/5/11 16:59
     * @param
     * @return com.zhongke.entity.Result
     **/
    @RequestMapping("/getMenu")
    public Result getMenu(){
        List<Menu> menus = platformUserService.getMenu();
        return new Result(0,"查询成功",menus);
    }

    /**
     * @Description 根据条件查询平台用户列表并分页
     * @author liuli
     * @date 2020/4/2 18:05
     * @param page
     * @param size
     * @return com.zhongke.entity.Result<com.github.pagehelper.PageInfo>
     **/
    @RequestMapping("/platUsers/{page}/{size}")
    public Result<PageInfo> platUsers(@RequestParam(required = false) String name,
                                      @RequestParam(required = false) String tel,
                                      @PathVariable int page, @PathVariable int size){
        try {
            PageInfo<PlatformUser> platUsers = platformUserService.platUsers(name,tel,page,size);
            return new Result<>(0,"查询平台用户列表成功",platUsers);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("PlatformUserController.platUsers(): "+e.getMessage());
            return new Result<>(-1,"查询平台用户列表失败：{}",e.getMessage());
        }
    }

    /**
     * @Description 更新用户
     * @author liuli
     * @date 2020/4/2 18:13
     * @param platformUser
     * @param id
     * @return com.zhongke.entity.Result
     **/
    @PostMapping("/update/{id}")
    public Result update(@RequestBody PlatformUser platformUser,@PathVariable int id){
        try {
            platformUser.setId(id);
            if (!StringUtils.isEmpty(platformUser.getPassword())){
                String password = new BCryptPasswordEncoder().encode(platformUser.getPassword());
                platformUser.setPassword(password);
            }
            platformUserService.update(platformUser,id);
            return new Result(0,"更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("PlatformUserController.update():{}",e.getMessage());
            return new Result(-1,"更新失败{}",e.getMessage());
        }
    }

    /**
     * @Description 根据id查询
     * @author liuli
     * @date 2020/4/2 18:26
     * @param id
     * @return com.zhongke.entity.Result<com.zhongke.pojo.PlatformUser>
     **/
    @GetMapping("/findById/{id}")
    public Result<PlatformUser> findById(@PathVariable int id){
        try {
            PlatformUser platformUser = platformUserService.findById(id);
            return new Result<PlatformUser>(0,"查询成功",platformUser);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("PlatformUserController.findById():{}",e.getMessage());
            return new Result(-1,"查询失败{}",e.getMessage());
        }
    }

    //获取当前登录用户的账号名
    @RequestMapping("/getName")
    public Result getUsername()throws Exception{
        try{
            org.springframework.security.core.userdetails.User user =
                    (org.springframework.security.core.userdetails.User)
                            SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return new Result(0,"获取成功",user.getUsername());
        }catch (Exception e){
            logger.error("PlatformUserController.getUsername():{},"+e.getMessage());
            return new Result(-1,"获取失败");
        }
    }

    //添加用户
    @PostMapping("/add")
    public Result add(@RequestBody PlatformUser platformUser){
        try{
           platformUserService.add(platformUser);
            return new Result(0,"添加成功");
        }catch (Exception e){
            logger.error("PlatformUserController.add():{},"+e.getMessage());
            return new Result(-1,"添加失败");
        }
    }
}
