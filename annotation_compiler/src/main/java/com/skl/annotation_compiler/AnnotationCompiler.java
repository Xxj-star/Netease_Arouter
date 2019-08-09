package com.skl.annotation_compiler;


import com.google.auto.service.AutoService;
import com.skl.annotations.BindPath;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;

/**
 * 注解处理器
 */

@AutoService(Processor.class)//注册注解处理器
public class AnnotationCompiler extends AbstractProcessor {

    //生成文件的对象
    private Filer filer;


    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        filer = processingEnvironment.getFiler();

    }

    /**
     * 申明注解处理器支持Java版本
     *
     * @return
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return processingEnv.getSourceVersion();
    }

    /**
     * 声明注解处理器要处理的注解
     *
     * @return
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new HashSet<>();
        types.add(BindPath.class.getCanonicalName());
        return types;
    }

    /**
     * 自动生成代码
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
}
