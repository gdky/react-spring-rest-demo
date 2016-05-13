# react-spring-rest-demo
基于rest的前后端分离demo，后端spring mvc，前端使用react

##技术栈
java 1.7+  
nodejs 5+  
spring mvc 4  
react.js@ 0.14  
##UI
ant-design@ 0.12.15
##构建
maven + webpack
##AJAX
reqwest@2.0.5
##前端路由
react-router  

#安装运行
先下载项目代码
```  
$ git clone https://github.com/gdky/react-spring-rest-demo
```
安装前端代码所需依赖
```
$ cd font-end  
$ npm install
```
back-end为后端代码，可以导入为maven工程，自动安装依赖库  

运行
```
$ cd font-end
$ npm run dev  开发模式，通过http://localhost:8001/ 访问  
$ npm run test 测试模式，通过http://localhost:8002/ 访问
$ npm run build 构建代码
```



