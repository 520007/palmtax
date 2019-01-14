package com.hzxckj.common.mybatis;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.sql.ResultSet;
import java.util.*;

public class AutoMybatisGenerator {

    private static ResourceBundle rb = ResourceBundle.getBundle("application");// 获取配置文件信息

    /**
     * Mybatis 代码生成器
     * @param args
     */
    public static void main(String[] args) {
        Map map = dataSourceConfig();// 获取数据库信息
        PackageConfig packageConfig = packageConfig();// 生成代码位置配置
        AutoGenerator autoGenerator = new AutoGenerator();
        autoGenerator.setGlobalConfig(globalConfig()).setDataSource((DataSourceConfig) map.get("config")).setStrategy(strategyConfig((String[]) map.get("tabies"), packageConfig.getModuleName() + "_"))
                .setPackageInfo(packageConfig);
        autoGenerator.execute();// 生成代码
    }

    /**
     * 配置代码生成路径
     *
     * @return GlobalConfig
     */
    private static GlobalConfig globalConfig() {
        return new GlobalConfig().setOutputDir(System.getProperty("user.dir") + "/src/main/java").setAuthor("lw").setOpen(false).setFileOverride(true);// 设置生成路径、生成文件作者名字
    }

    /**
     * 配置数据库信息
     *
     * @return Map
     */
    private static Map dataSourceConfig() {
        Map map = new HashMap();
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl(rb.getString("spring.datasource.url")).setUsername(rb.getString("spring.datasource.username")).setPassword(rb.getString("spring.datasource.password")).setDriverName("com.mysql.cj.jdbc.Driver");// 数据库连接配置
        ResultSet rs;
        List list = new ArrayList();
        try {
            // 获取指定数据库所有表名
            rs = dataSourceConfig.getConn().getMetaData().getTables(rb.getString("spring.datasource.url").split("[?]")[0].split("/")[3], null, null, new String[]{"TABLE"});
            while (true) {
                if (!rs.next()) {
                    break;
                } else {
                    list.add(rs.getString(3));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] tables = new String[list.size()];
        list.toArray(tables);
        map.put("config", dataSourceConfig);// 数据库配置
        map.put("tables", tables);// 数据库所有表名
        return map;
    }

    /**
     * 包生成路径配置
     *
     * @return
     */
    public static PackageConfig packageConfig() {
        return new PackageConfig().setParent("com.generator").setXml("mappers").setMapper("mapper").setController("controllers");
    }

    /**
     * 生成策略
     *
     * @param tableNames  表名
     * @param tablePrefix 表前缀
     * @return
     */
    public static StrategyConfig strategyConfig(String[] tableNames, String tablePrefix) {
        return new StrategyConfig().setInclude(tableNames).setNaming(NamingStrategy.underline_to_camel).setColumnNaming(NamingStrategy.underline_to_camel).
                setControllerMappingHyphenStyle(true).setEntityLombokModel(true).entityTableFieldAnnotationEnable(true).setTablePrefix(tablePrefix);
    }

}
