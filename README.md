# Greendao
Greendao， demo练习
在你的build中添加sourceSets {
        main {
            java.srcDirs = ['src/main/java', 'src/main/java-gen']
        }
    }
   在 dependencies  添加compile 'de.greenrobot:greendao:2.1.0'
   建立liabrary 
   在app中建立java-gen
