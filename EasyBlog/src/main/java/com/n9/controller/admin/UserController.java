package com.n9.controller.admin;


import com.n9.service.UserService;
import com.n9.util.PageQueryUtil;
import com.n9.util.Result;
import com.n9.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 数据列表显示
     *
     * @param params
     * @return
     */
    @RequestMapping("/users/list")
    @ResponseBody
    public Result doFindObject(@RequestParam Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(userService.getUsersPage(pageUtil));
    }


    /**
     * 添加用户
     * @param userName 用户名
     * @param userPassword 用户密码
     * @param nickName 用户昵称
     * @return 结果
     */
    @RequestMapping("/users/save")
    @ResponseBody
    public Result doSave(@RequestParam("loginUserName") String userName,
                         @RequestParam("loginPassword") String userPassword,
                         @RequestParam("nickName") String nickName) {
        if (StringUtils.isEmpty(userName)) {
            return ResultGenerator.genFailResult("请输入用户名称！");
        }
        if (StringUtils.isEmpty(userPassword)) {
            return ResultGenerator.genFailResult("请输入用户密码！");
        }
        if (userService.saveUser(userName, userPassword, nickName)) {
            return ResultGenerator.genSuccessResult();
        }
        if (StringUtils.isEmpty(nickName)) {
            return ResultGenerator.genFailResult("请输入用户昵称");
        } else {
            return ResultGenerator.genFailResult("用户名称重复");
        }
    }

    /**
     * 用户删除
     */
    @RequestMapping(value = "/users/delete", method = RequestMethod.POST)
    @ResponseBody
    public Result delete(@RequestBody Integer[] ids) {
        if (ids.length < 1) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        if (userService.deleteBatch(ids)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("删除失败");
        }
    }


    @GetMapping("/users")
    public String list(HttpServletRequest request) {
        request.setAttribute("path", "users");
        return "admin/user";
    }
}
