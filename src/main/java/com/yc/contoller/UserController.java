package com.yc.contoller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.po.User;
import com.yc.serviceimpl.UserServiceImpl;

@Controller
public class UserController {
	@Autowired
	private UserServiceImpl userimpl;

	@RequestMapping(value = "/getUsers")
	public String getAllUsers(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int page = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
		int rows = request.getParameter("rows") == null ? 10 : Integer.parseInt(request.getParameter("rows"));
		// 筛选条件
		String search_name = request.getParameter("search_name") == null ? "" : request.getParameter("search_name");
		String search_account = request.getParameter("search_account") == null ? ""
				: request.getParameter("search_account");
		// 调用服务
		List<User> users = userimpl.getAllUser(page, rows, search_name, search_account);
		// 封装json数据,total存放数据总数,rows存放数据数组,以提供给easyUI的datagrid
		JSONObject json = new JSONObject();
		// 筛选后的总数
		json.put("total", userimpl.getCount(search_name, search_account));
		JSONArray row = new JSONArray();
		for (User info : users) {
			JSONObject jo = new JSONObject();
			jo.put("id", info.getId());
			jo.put("account", info.getAccount());
			jo.put("name", info.getName());
			jo.put("phone", info.getPhone());
			jo.put("email", info.getEmail());
			jo.put("address", info.getAddress());
			row.put(jo);
		}
		json.put("rows", row);
		System.out.println(json.toString());
		// 写入响应
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(json.toString());
		// 没有对应的jsp文件,故直接返回null
		return null;
	}

	/***
	 * public String addUser(HttpServletRequest request,HttpServletResponse
	 * response) throws IOException{
	 * 
	 * 
	 **/
	@RequestMapping(value = "/add")
	public String addUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		User user = new User();
		user.setName(request.getParameter("name"));
		user.setAccount(request.getParameter("account"));
		user.setAddress(request.getParameter("address"));
		user.setEmail(request.getParameter("email"));
		user.setPhone(request.getParameter("phone"));

		JSONObject result = new JSONObject();
		try {
			userimpl.addUser(user);
			result.put("success", true);
			result.put("msg", "添加成功");
		} catch (Exception e) {
			result.put("success", false);
			result.put("msg", "帐号已补占用");
		}

		// 回应
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(result.toString());
		return null;
	}

	@RequestMapping(value = "/update/{id}")
	public String updateUser(@PathVariable int id, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		User user = new User();
		user.setId(id);
		user.setName(request.getParameter("name"));
		user.setAccount(request.getParameter("account"));
		user.setPhone(request.getParameter("phone"));
		user.setEmail(request.getParameter("email"));
		user.setAddress(request.getParameter("address"));

		// 封装操作结果, 包括success是否成功和msg消息提示
		JSONObject result = new JSONObject();
		try {
			userimpl.updateUser(user);
			result.put("success", true);
			result.put("msg", "修改成功");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			result.put("success", false);
			result.put("msg", "帐号已被占用");
		}

		// 响应
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(result.toString());

		return null;
	}

	@RequestMapping(value = "/delete")
	public String deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 封装操作结果
		JSONObject result = new JSONObject();
		try {
			userimpl.deleteUserById(Integer.parseInt(request.getParameter("id")));
			result.put("success", true);
			result.put("msg", "删除成功!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			result.put("success", false);
			result.put("msg", "删除失败");
		}

		// 响应
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(result.toString());
		return null;
	}

	/*
	 * 用户管理界面
	 */
	@RequestMapping(value = "/list")
	public String showAllUsers(HttpServletRequest request, HttpServletResponse response) {
		return "list";
	}
}
