# SPM-9
在线学习网站

## DAO层封装API食用方法（代码模板） 
    使用前建议先学习MyBatis框架并安装MyBatisX插件，https://www.bilibili.com/video/BV1Qf4y1T7Hx/?p=56&spm_id_from=333.880.my_history.page.click&vd_source=3e618718bf59b4a402f62ef57de8dabe

    
    // 1.获取 单例 的SqlSessionFactory
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    // 2.获取SqlSession对象，用它执行sql
    // openSession()传入true参数可以指定是否自动提交事务，为false或不填时增删改数据库后必须commit，否则修改无效
    SqlSession sqlSession = sqlSessionFactory.openSession(true);

    // 3.1获取Mapper接口的代理对象（每一张表所使用的Mapper不一样）
    UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
    
    // 3.2愉快地调用API（可自由发挥的区域，注意方法返回值类型和null值处理）
    List<User> users = userMapper.selectAll();
    System.out.println(users);

    User user1 = userMapper.selectByUID(1);
    User user2 = userMapper.selectByUID(2);
    User user3 = userMapper.selectByUID(114514);
    System.out.println(user1);
    System.out.println(user2);
    System.out.println(user3);

    // 4.释放资源，必须！！！
    sqlSession.close();

## ~~DAO层封装API食用方法（代码模板，可直接复制使用）~~ 写单元测试还可以，正式项目中不允许创建多个SqlSessionFactory对象！！！
    使用前建议先学习MyBatis框架并安装MyBatisX插件，https://www.bilibili.com/video/BV1Qf4y1T7Hx/?p=56&spm_id_from=333.880.my_history.page.click&vd_source=3e618718bf59b4a402f62ef57de8dabe

    // 1.加载mybatis配置文件，获取SqlSessionFactory
    String resource = "mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

    // 2.获取SqlSession对象，用它执行sql
    SqlSession sqlSession = sqlSessionFactory.openSession();

    // 3.1获取Mapper接口的代理对象（每一张表所使用的Mapper不一样，）
    UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
    
    // 3.2愉快地调用API（可自由发挥的区域，注意方法返回值类型和null值处理）
    List<User> users = userMapper.selectAll();
    System.out.println(users);

    User user1 = userMapper.selectByUID(1);
    User user2 = userMapper.selectByUID(2);
    User user3 = userMapper.selectByUID(114514);
    System.out.println(user1);
    System.out.println(user2);
    System.out.println(user3);

    // 4.释放资源
    sqlSession.close();
  
