# Junit

在非spring项目中：

* 在Junit5前，使用@RunWith(MockitoJUnitRunner.class)及@InjectMocks/@Mock/@Spy注解来配合写单元测试用例。

* 在Junit5中，使用@ExtendWith(MockitoExtension.class)及@InjectMocks/@Mock/@Spy注解来配合写单元测试用例。

在spring项目中：

* 在Junit5前，使用@RunWith(SpringJUnit4ClassRunner.class)
  及@InjectMocks/@MockBean/@SpyBean注解来配合写单元测试用例。

* 在Junit5中，使用@ExtendWith(SpringExtension.class)及@InjectMocks/@MockBean/@SpyBean注解来配合写单元测试用例。

# Apache MINA

Apache sshd是一个SSH协议的100%纯Java库，支持客户端和服务器。sshd库基于Apache MINA项目（可伸缩高性能的异步IO库）。

Apache MINA 是一个网络应用框架，用于开发高性能、高可靠性、高可伸缩性的网络服务器和客户端。

官方网站：http://mina.apache.org/sshd-project/documentation.html