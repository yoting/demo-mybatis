package com.gusi.demo.interceptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import com.gusi.demo.utils.PageInfo;

//首先通过注解定义该拦截器的切入点，对那个类的哪个方法进行拦截，防止方法重载需要声明参数类型以及个数
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class PageInterceptor implements Interceptor {
	public String sqlIdByPageRegex = "";// 这则表达式用了筛选所有分页的sql语句

	public Object intercept(Invocation invocation) throws Throwable {
		// 通过拦截器得到被拦截的对象,就是上面配置的注解的对象
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		// 为了获取以及设置某些对象的属性值（某些对象的属性是没有getter/setter的），mybatis提供的快捷的通过反射设置获取属性只的工具类，当然也可以通过自己写反射完成
		MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY,
				SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());
		// 得到当前的mapper对象信息,即为各种select，update，delete，insert语句的映射配置信息，通过上面的工具类获取属性对象
		MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
		// 对映射语句进行选择过滤，如果是以ByPage结尾就拦截，否则不拦截
		String sqlId = mappedStatement.getId();
		if (sqlId.matches(sqlIdByPageRegex)) {
			// sql语句在对象BoundSql对象中，这个对象有get方法可以直接获取
			BoundSql boundSql = statementHandler.getBoundSql();
			// 获取原始sql，该sql是预处理的，有参数还没有被设置，被问好代替了
			String sql = boundSql.getSql();

			// 拿到我们给sql传入的参数对象，我们那儿写的Map类型，所以这里就是使用map接收，当然也可以是其他类型
			Map<?, ?> parameterMap = (Map<?, ?>) boundSql.getParameterObject();
			// 参数对象中的pageInfo对象信息拿到
			PageInfo pageInfo = (PageInfo) parameterMap.get("pageInfo");
			// 获取总条数,通过自己写sql查询，然后设置给pageinfo对象
			String countSql = "select count(*) from (" + sql + ") alias";// 注意这里通过子查询需要给字结果设置别名
			// 同jdbc一个流程查询sql语句
			Connection connection = (Connection) invocation.getArgs()[0];
			PreparedStatement preparedStatement = connection.prepareStatement(countSql);
			// 为了先查询总条数，所以需要先统计原始sql结果，但是原始sql中参数还没赋值，所以就需要先拿到原始sql的参数处理对象，通过反射工具
			ParameterHandler parameterHandler = (ParameterHandler) metaObject.getValue("delegate.parameterHandler");
			parameterHandler.setParameters(preparedStatement);
			// 参数被设置以后，直接执行sql语句得到结果集合
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				// 将查询到的结果集合设置到pageInfo中
				pageInfo.setTotalReacordNumber(resultSet.getInt(1));
			}

			// 最后改造分页查询sql
			String pageSql = sql + " limit " + pageInfo.getDbIndex() + "," + pageInfo.getPageSize();

			// 通过反射将原来的sql给换成加入分页的sql
			metaObject.setValue("delegate.boundSql.sql", pageSql);
		}

		// 连接器是链式结构的，我们完成我们的拦截处理以后，还要保证接下来的其他拦截器或者代码继续执行
		return invocation.proceed();
	}

	public Object plugin(Object target) {
		// 表示给一个目标对象织入一个拦截器，该代码织入的的拦截器对象就是本身this对象
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
		// 可读取到配置文件中定义的属性以及属性值
		sqlIdByPageRegex = (String) properties.get("sqlIdByPageRegex");
		System.out.println(sqlIdByPageRegex);
	}
}
