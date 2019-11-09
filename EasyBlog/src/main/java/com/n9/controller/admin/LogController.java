package com.n9.controller.admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.n9.service.LogService;
import com.n9.util.PageQueryUtil;
import com.n9.util.Result;
import com.n9.util.ResultGenerator;

@Controller
@RequestMapping("/admin")
public class LogController {

	@Autowired
	private LogService logService;

	@GetMapping("/logs/list")
	@ResponseBody
	public Result list(@RequestParam Map<String, Object> params) {
		if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
			return ResultGenerator.genFailResult("参数异常！");
		}
		PageQueryUtil pageUtil = new PageQueryUtil(params);
		return ResultGenerator.genSuccessResult(logService.getBlogsPage(pageUtil));
	}

	@GetMapping("/logs")
	public String logs(HttpServletRequest request) {
		request.setAttribute("path", "logs");
		return "admin/log";
	}

	
    @PostMapping("/logs/delete")
    @ResponseBody
    public Result deleteLog(@RequestBody Integer[] ids) {
        if (ids.length < 1) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        if (logService.deleteBatch(ids)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("删除失败");
        }
    }


}
