Freemaker reload 설정
- build.gradle > dependencies > compile('org.springframework.boot:spring-boot-starter-freemarker') 추가 
- build.gradle > dependencies > compile("org.springframework.boot:spring-boot-devtools") 추가 
- Intellij > Preferences > Build, Execution, Deployment > Compiler > Build project automatically 체크 후 OK
- Intellij > Help > Find Action > Registry 입력 >  compiler.automake.allow.when.app.running 체크 후 Close
- application.properties 수정
  - spring.devtools.livereload.enabled=true
  - spring.freemarker.cache=false
  - 사용중인 브라우저의 캐시삭제
  
  
Lombok 설정
- build.gradle > dependencies > compileOnly('org.projectlombok:lombok') 추가 
- Intellij > Preferences > Build, Execution, Deployment > Compiler > Annotation Processors > Enable annotation processing 체크 후 OK