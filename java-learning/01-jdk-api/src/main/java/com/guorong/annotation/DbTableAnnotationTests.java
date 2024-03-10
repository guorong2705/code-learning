package com.guorong.annotation;

import java.io.File;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 表明注解使用
 */
public class DbTableAnnotationTests {
    public static void main(String[] args) throws ClassNotFoundException {
        String packageName = "com.guorong.annotation";
        DbTableParser.parse(packageName);
    }

}

// 解析器
final class DbTableParser {

    public static void parse(String packageName) throws ClassNotFoundException {
        List<Class<?>> classList = parseClass(packageName);
        parseDbTable(classList);
    }

    private static void parseDbTable(List<Class<?>> classList) {
        for (Class<?> aClass : classList) {
            StringBuilder sb = new StringBuilder();
            // 判断是否使用了 @DbTable 注解类型
            DbTable dbTableAnnotation = aClass.getAnnotation(DbTable.class);
            if (Objects.isNull(dbTableAnnotation)) {
                continue;
            }
            // 获取表名
            String tableName = dbTableAnnotation.value();
            if (tableName.length() < 1) {
                // 使用类名称大写替代
                tableName = aClass.getSimpleName().toUpperCase();
            }
            sb.append("CREATE TABLE ").append(tableName).append("(").append("\n");
            // 获取当前类声明的字段
            Field[] fields = aClass.getDeclaredFields();
            for (Field field : fields) {
                // 字段类型
                TableColumn tableColumnAnnotation = field.getAnnotation(TableColumn.class);
                if (Objects.isNull(tableColumnAnnotation)) {
                    continue;
                }
                sb.append(filedSql(tableColumnAnnotation)).append("\n");
            }
            sb.append(");").append("\n");
            System.out.println(sb.toString());
        }
    }

    private static String filedSql(TableColumn annotation) {
        StringBuilder sb = new StringBuilder();
        String name = annotation.name();
        sb.append(name);
        TableColumn.FileType type = annotation.type();
        int length = annotation.length();
        if (type == TableColumn.FileType.INTEGER) {
            sb.append(" ").append("INT");
        } else if (type == TableColumn.FileType.STRING) {
            sb.append(" ").append("varchar(").append(length).append(")");
        }
        Constraints constraint = annotation.constraint();
        if (constraint.primaryKey()) {
            sb.append(" ").append("primary key");
        }
        if (!constraint.allowNull()) {
            sb.append(" ").append("NOT NULL");
        }
        if (constraint.unique()) {
            sb.append(" ").append("UNIQUE");
        }
        return sb.toString();
    }


    private static List<Class<?>> parseClass(String packageName) throws ClassNotFoundException {
        List<Class<?>> classList = new ArrayList<>();
        String rootPath = DbTableParser.class.getResource("/").getPath();
        String classDir = rootPath + packageName.replace(".", "/");
        File dirFile = new File(classDir);
        for (File file : dirFile.listFiles()) {
            if (file.getName().contains(".class")) {
                String className = packageName + "." + file.getName().replace(".class", "");
                classList.add(Class.forName(className));
            }
        }
        return classList;
    }



}


@DbTable("PERSON")
class Person {
    @TableColumn(
            name = "ID", type = TableColumn.FileType.STRING, length = 32,
            constraint = @Constraints(primaryKey = true, unique = true, allowNull = false)
    )
    private String id;

    @TableColumn(name = "NAME", type = TableColumn.FileType.STRING, length = 100)
    private String name;

    @TableColumn(name = "AGE", type = TableColumn.FileType.INTEGER)
    private Integer age;
}

// 定义数据库表注解
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface DbTable {
    // 表名
    String value() default "";
}

// 定义字段约束
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Constraints {
    // 是否为主键
    boolean primaryKey() default false;

    // 是否允许为null
    boolean allowNull() default true;

    // 是否唯一
    boolean unique() default false;
}

// 定义字段注解
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface TableColumn {
    // 字段名称
    String name();
    // 字段类型
    FileType type();
    // 长度
    int length() default 255;
    // 约束
    Constraints constraint() default @Constraints;
    enum FileType {
        STRING, INTEGER,
        ;
    }
}
