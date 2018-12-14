# MiniFaceBook
高级数据库作业

## 项目启动配置
- 安装并启动 Neo4j
- 在application.yaml中修改
  - spring.data.neo4j.uri 改成自己的Neo4j路径
  - spring.data.neo4j.username
  - spring.data.neo4j.password
  - application.file.baseStaticResourcesPath 修改成自己的本地静态资源路径，并在该路径下解压static.tar

## static.tar
static.tar 是包含前端页面在内的静态资源目录。包含用户头像，发帖的图片，html和js文件等。
