package com.example.spring_data_demo.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
public class JdbcController {
    @Resource
    JdbcTemplate jdbcTemplate;

    /**
     * 查询数据库的所有信息
     * 没有实体类，获取数据库中的内容 使用map
     * @return List
     */
    @RequestMapping(value = "/userList", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> userList() {
        String sql = "SELECT * FROM user";
        return jdbcTemplate.queryForList(sql);
    }

    @RequestMapping("/addUser")
    @ResponseBody
    public String addUser() {
        String sql = "INSERT INTO mybatis.user(id, name, passwd) VALUES (5, '赵六', '111222')";
        jdbcTemplate.update(sql);
        return "add ok";
    }

    @RequestMapping("/updateUser/{id}")
    @ResponseBody
    public String updateUser(@PathVariable("id") int id) {
        String sql = "UPDATE mybatis.user SET name=?, passwd=? WHERE id = " + id;
        Object[] objects = new Object[2];
        objects[0] = "狂徒";
        objects[1] = "222333";
        jdbcTemplate.update(sql, objects);
        return "update ok";
    }

    @RequestMapping("/deleteUser/{id}")
    @ResponseBody
    public String deleteUser(@PathVariable("id") int id) {
        String sql = "DELETE FROM mybatis.user WHERE id = " + id;
        jdbcTemplate.update(sql, id);
        return "delete ok";
    }
}
