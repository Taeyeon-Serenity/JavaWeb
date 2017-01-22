package cn.web.JSTL;

/**
 * Created by Taeyeon-Serenity on 2016/12/6.
 */
public class HelloJSTL {
    /*JSTL
    *     1.什么是JSTL
    *           JSTL是EL表达式的扩展（也就是说JSTL依赖EL），JSTL是标签语言
    *           使用JSTL需要导入jstl1.2的jar包
    *           导入标签库
    *               jar包
    *               在jsp页面中：<%@taglib prefix="前缀" uri="路径"%>
    *     2.JSTL标签库
    *           JSTL一共包含四大标签库
    *               core：核心标签库
    *               fmt：格式化标签库，日期、数字
    *               sql：数据库标签库，已过时
    *               xml：xml标签库，已过时
    *           core标签库常用标签 --> c标签
    *               1.out和set
    *                   *<c:out>：输出
    *                       >value：可以是字符串常亮，也可以是EL表达式
    *                       >default：当要输出的内容为null时，会输出default指定的值
    *                       >escapeXml：默认值为true，表示转义
    *                   *<c:set>：设置（创建域的属性）
    *                       >var：变量名
    *                       >value：变量值，可以是EL表达式
    *                       >scope：域，默认为page，可选值：page、request、session、application
    *               2.remove
    *                   *<remove>：删除域变量
    *                       >var：变量名
    *                       >scope：如果不给出scope，表示删除所有域中的该名称的变量，如果指定了域，那么只会删除该域中的该名称的变量
    *               3.url
    *                   *value：指定一个路径，它会在路径前面自动添加项目名
    *                        <c:url value="/index.jsp"/>，它会输出/JSTL/index.jsp
    *                   *子标签：<c:param>，用来给url后面添加参数，例如：
    *                       <c:url value="/index.jsp">
    *                           <c:param name="username" value="张三"/>   可以对参数进行url编码
    *                       </c:url>
    *                       结果为：/JSTL/index.jsp?username=
    *                   *var：指定变量名，一旦添加了这个属性，那么url标签就不会再输出到页面，而是把生成的url保存到域中
    *                   *scope：它与var一起使用，用来保存url
    *               4.if：对应java里面的if语句
    *                   <c:if test="布尔类型">...</c:if>，当test为真时，执行标签体内容
    *               5.choose：对应java里面的if/else if/.../else
    *                   例如：
    *                       <c:choose>
    *                           <c:when test="">...</c:when>
    *                           <c:when test="">...</c:when>
    *                           <c:when test="">...</c:when>
    *                           ...
    *                           <c:otherwise>...</c:otherwise>
    *                       </c:choose>
    *               6.forEach
    *                   它用来循环遍历数组、集合。
    *                   可以用计数方式来循环
    *                   计数方式：
    *                       for(int i = 0 ; i <= 10 ; i++){
    *                           ...
    *                       }
    *                       <c:forEach> var="i" begin="1" end="10">
    *                       </c:forEach>
    *                       属性：
    *                           *var：循环变量
    *                           *begin：设置循环变量从几开始
    *                           *end：设置循环变量到几结束
    *                           *step：设置步长，等同于java中的i++，或i+=2，step默认为1
    *                   用来输出数组和集合：
      *                     <c:forEach items="${strs}" var="str">
      *                         ${str} <br/>
      *                     </c:forEach>
      *                     等同于
      *                     for(String str : strs){
      *                         ...
      *                     }
      *                     属性：
      *                         *item：指定要循环谁，它可以是一个数组或一个集合
      *                         *var：把数组或集合中的每个元素赋值给var指定的变量
      *
      *                     循环状态：
      *                         可以使用varStatus来创建循环状态变量
      *                         循环状态变量有如下属性：
      *                             *count：循环元素的个数
      *                             *index：循环元素的下标
      *                             *first：是否为第一个元素
      *                             *last：是否为最后一个元素
      *                             *current：当前元素
      *                    <%
      *                         ArrayList<String> list=new ArrayList<String>();
      *                         list.add("一");
      *                         list.add("二");
      *                         list.add("三");
      *                         pageContext.setAttribute("list",list);
      *                    %>
      *                    <c:forEach items="${list}" var="ele" varStatus="vs">1
      *                        ${vs.index} ${vs.count} ${vs.first} ${vs.last} ${vs.current} <br/>
      *                    </c:forEach>
      *
      *         fmt库
      *             它是格式化库
      *                 <fmt:formatDate value="" pattern=""/>
      *                     value：指定一个Date类型的变量
      *                     pattern：用来指定输出的模板，例如：yyyy-MM-dd HH:mm:ss
      *
      *                 <fmt:formatNumber value="${num1}" pattern="0.00"/>
      *                     保留小数点后2位，它会四舍五入，如果不足2位，以0补位
      *                 <fmt:formatNumber value="${num1}" pattern="#.##"/>
      *                     保留小数点后2位，它会四舍五入，如果不足2位，不补位
      *
      *
      *       自定义标签
        *       1.步骤
        *           *标签处理类（标签也是一个对象，那么就需要先有类）
        *           *tld文件，它是一个xml
        *           *页面中使用<%@ taglib%>来指定tld文件的位置
        *       2.标签处理类
        *           SimpleTag接口：
        *               void doTag():每次执行标签时都会调用这个方法；
        *               JspTag getParent():返回父标签
        *               void setParent(JspTag):设置父标签
        *               void setJspBody(JspFragment):设置标签体
        *               void setJspContext(JspContext):设置jsp上下文对象，它儿子是PageContext
        *               其中doTage()会在其他三个方法之后被tomcat调用
        *
        *       3.配置tld文件
        *       tld文件一般都放到WEB-INF之下，这样保证客户端访问不到！
        *           <tag>
                        <name>myTag1</name> 指定当前标签名称
                        <tag-class>cn.web.tag.MyTag1</tag-class>    指定当前标签的标签处理类
                        <body-content>empty</body-content>  指定标签体的类型，我们这里使用的是空标签类型
                    </tag>

                4.页面中指定tld文件位置
                    <%@ taglib prefix="it" uri="/WEB-INF/tlds/web-1.tld" %>
                    导标签库，就是为页面指定tld文件的位置！

                标签体内容
                    empty：无标签体！
                    JSP：JSP2.0已经不再支持这个类型了，表示标签体内容可以是：java脚本，可以是标签，可以是EL表达式
                    scriptless：只能是EL表达式，也可以是其他的标签！
                    tagdependent：标签体内容不会被执行，而是直接赋值标签处理类

                不再执行标签下面内容的标签！
                   在标签处理类中的doTage()中使用SkipPageException来结束！
                   tomcat会调用标签处理类的doTage()方法，然后Tomcat会得到SkipPageException， 它会跳过本页面的其他内容

                标签属性
                    步骤：
                      1.给你的标签处理类添加属性！
                        为标签处理类添加属性，属性至少要有一个set方法，这个set方法会在doTag()方法之前被tomcat执行，所在doTag()中就可以使用属性了
                      2.在tld文件中对属性进行配置
                        <attribute>
                            <name>test</name>   指定属性名
                            <required>true</required>   指定属性是否为必需的
                            <rtexprvalue>true</rtexprvalue> 指定属性是否可以使用EL
                        </attribute>

                    MVC
                        它不是java独有，所有的B/S结构的项目都在使用它

                        M--model 模型(自己写代码)
                        V--View   视图(jsp)
                        C--Cotroller 控制器(Servlet)

                    JavaWeb三层框架
                        Web层 --> 与Web相关的内容(Servlet、JSP、Servlet相关的API：request、response、session、ServletContext)
                        业务层 --> 业务对象(Service)
                        数据层 --> 操作数据库(DAO Data Access Object)(所有对数据库的操作不能跳出DAO之外)
    *               */
}
