
# 代码介绍：
```
//自定义注解路径
@BindPath("main/main")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    //跳转代码
    public void jumpActivity(View view) {
        ARouter.getInstance().jumpActivity("login/login", null);
    }
}

```
![image text](https://github.com/xuxinjiang/Netease_Arouter/blob/master/gif/test1.png)

## 通过指定的路由自动创建指定的路径类方法《核心代码》
```
/**
     * 通过Java自动生成代码
     *
     * @param set
     * @param roundEnvironment
     * @return
     */
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(BindPath.class);
        //TypeElement 类节点
        //初始化数据
        //VariableElement成员变量节点
        Map<String, String> map = new HashMap<>();
        for (Element element : elements) {
            TypeElement typeElement = (TypeElement) element;
            //获取到key
            String key = typeElement.getAnnotation(BindPath.class).value();
            //获取到Activity的类对象的名字
            String activityName = typeElement.getQualifiedName().toString();
            map.put(key, activityName);
        }
        //开始写文件
        if (map.size() > 0) {
            Writer wrapper = null;
            //创建一个新类名
            String utilName = "ActivityUtil" + System.currentTimeMillis();
            //创建文件
            try {
                JavaFileObject sourceFile = filer.createSourceFile("com.skl.login." + utilName);
                wrapper = sourceFile.openWriter();
                wrapper.write("package com.skl.login;\n");
                wrapper.write("import com.skl.arouter.ARouter;\n" +
                        "import com.skl.arouter.IRouter;\n" +
                        "\n" +
                        "public class " + utilName + " implements IRouter {\n" +
                        "\n" +
                        "    @Override\n" +
                        "    public void putActivity() {");
                Iterator<String> iterator = map.keySet().iterator();
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    String activityName = map.get(key);
                    wrapper.write("ARouter.getInstance().putActivity(\"" + key + "\"," + activityName + ".class);\n");
                }
                wrapper.write("}\n}\n");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (wrapper != null) {
                    try {
                        wrapper.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return false;
    }
    
```



# 演示（请star支持）

![image text](https://github.com/xuxinjiang/Netease_Arouter/blob/master/gif/netease.gif)

# 联系方式

本群旨在为使用我github项目的人提供方便，如果遇到问题欢迎在群里提问。

### 欢迎加入QQ交流群（Q群574181465）

![image text](https://github.com/xuxinjiang/NetworkCue-/blob/master/gif/xxjqq.png)



