package com.feng.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feng.mybatisplus.entity.User;
import com.feng.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Springboot的@RunWith(SpringRunner.class)
 * 注解的意义在于Test测试类要使用注入的类，比如@Autowired注入的类，
 * 有了@RunWith(SpringRunner.class)这些类才能实例化到spring容器中，自动注入才能生效，
 * 不然直接一个NullPointerExecption
 */
//@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisPlusApplicationTests
{
    @Autowired
    private UserMapper userMapper;

    //查询所有User
    @Test
    public void findAll()
    {
        //UserMapper 中的 selectList() 方法的参数为 MP 内置的条件封装器 Wrapper
        //所以不填写就是无任何条件
        List<User> users = userMapper.selectList(null);
        System.out.println("users = " + users);

    }

    //查询所有User
    @Test
    public void queryAllUser()
    {
        //UserMapper 中的 selectList() 方法的参数为 MP 内置的条件封装器 Wrapper
        //所以不填写就是无任何条件
        List<User> users = userMapper.queryAllUser();
        System.out.println("users = " + users);

    }


    //添加User id默认雪花算法生成
    @Test
    public void addUser()
    {
        User user = new User();
        user.setName("黄忠");
        user.setAge(26);
        user.setEmail("huangzhong@qq.com");

        int result = userMapper.insert(user);
        System.out.println(result); //影响的行数
        System.out.println(user); //id自动回填
    }

    @Test
    public void testUpdateById()
    {

        User user = new User();
        user.setId(1350750552279072769L);
        user.setAge(66);

        int result = userMapper.updateById(user);
        System.out.println(result);

    }

    /**
     * 测试乐观锁插件 失败
     */
    @Test
    public void testOptimisticLockerFail()
    {

        //查询
        User user = userMapper.selectById(1350750552279072769L);
        //修改数据
        user.setAge(66);

        //模拟取出数据后，数据库中version实际数据比取出的值大，即已被其它线程修改并更新了version
        //user.setVersion(user.getVersion() - 1);

        //执行更新
        userMapper.updateById(user);
    }

    /**
     * 批量查询
     */
    @Test
    public void testSelectBatchIds()
    {

        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        users.forEach(System.out::println);
    }

    /**
     * 条件查询
     */
    @Test
    public void testSelectByMap()
    {

        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "Helen");
        map.put("age", 18);
        List<User> users = userMapper.selectByMap(map);

        users.forEach(System.out::println);
    }

    @Test
    public void testSelectPage()
    {
        //创建page对象
        //传入两个参数：当前页和每页显示记录数
        Page<User> page = new Page<>(1, 5);

        //调用mybatisplus分页查询方法
        //调用mybatisplus分页查询过程中底层封装，把分页所有数据封装到page对象里面
        userMapper.selectPage(page, null);

        page.getRecords().forEach(System.out::println);
        System.out.println(page.getCurrent());//当前页
        System.out.println(page.getRecords());//每页数据list集合
        System.out.println(page.getPages());//总页数
        System.out.println(page.getSize());//每页显示记录数
        System.out.println(page.getTotal());//总记录数
        System.out.println(page.hasNext());//下一页
        System.out.println(page.hasPrevious());//上一页
    }

    /**
     * 根据id删除
     */
    @Test
    public void testDeleteById()
    {
        int result = userMapper.deleteById(8L);
        System.out.println(result);
    }

    /**
     * 批量删除
     */
    @Test
    public void testDeleteBatchIds()
    {
        int result = userMapper.deleteBatchIds(Arrays.asList(8, 9, 10));
        System.out.println(result);
    }

    /**
     * 条件删除
     */
    @Test
    public void testDeleteByMap()
    {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "Helen");
        map.put("age", 18);

        int result = userMapper.deleteByMap(map);
        System.out.println(result);
    }

    /**
     * 测试 逻辑删除
     */
    @Test
    public void testLogicDelete()
    {
        int result = userMapper.deleteById(1L);
        System.out.println(result);
    }

    /**
     * 测试 逻辑删除后的查询：
     * 不包括被逻辑删除的记录
     */
    @Test
    public void testLogicDeleteSelect()
    {
        User user = new User();
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    /**
     * 复杂条件查询
     */
    @Test
    public void testQuery()
    {
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        /**
         * ge >=
         * gt >
         * le <=
         * lt <
         * eq =
         * ne != 或 <>
         * like 模糊查询
         * orderByDesc/orderByAsc 降序/升序
         * select 指定列
         */
//        wrapper.ge("age","20");
//        List<User> users = userMapper.selectList(wrapper);
//        System.out.println("users = " + users);

//        wrapper.eq("name","马超");
//        User user = userMapper.selectOne(wrapper);
//        System.out.println("user = " + user);


//        wrapper.between("age", 20, 30);
//        List<User> users = userMapper.selectList(wrapper);
//        System.out.println("users = " + users);

//        Map<String, Object> map = new HashMap<>();
//        map.put("id", 2);
//        map.put("name", "Jack");
//        map.put("age", 20);
//
//        wrapper.allEq(map);
//        List<User> users = userMapper.selectList(wrapper);
//
//        users.forEach(System.out::println);

//        wrapper.orderByAsc("id")

        wrapper.select("id", "name", "age");
        List<User> users = userMapper.selectList(wrapper);
        System.out.println("users = " + users);

    }

    @Test
    public void testUpdateSet()
    {

        //修改值
        User user = new User();
        user.setAge(99);

        //修改条件
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.like("name", "h").set("name", "老李头")//除了可以查询还可以使用set设置修改的字段
                .setSql(" email = '123@qq.com'");//可以有子查询

        int result = userMapper.update(user, userUpdateWrapper);

        /**
         * Execute SQL：
         *     UPDATE
         *         user
         *     SET
         *         age=99,
         *         update_time='2021-01-23 20:52:53.105',
         *         name='老李头',
         *         email = '123@qq.com'
         *     WHERE
         *         deleted=0
         *         AND name LIKE '%h%'
         */
    }

}
