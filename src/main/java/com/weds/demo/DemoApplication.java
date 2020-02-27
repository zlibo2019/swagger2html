package com.weds.demo;

import com.weds.core.annotation.MyBatisDao;

import io.github.swagger2markup.GroupBy;
import io.github.swagger2markup.Language;
import io.github.swagger2markup.Swagger2MarkupConfig;
import io.github.swagger2markup.Swagger2MarkupConverter;
import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
import io.github.swagger2markup.markup.builder.MarkupLanguage;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.net.URL;
import java.nio.file.Paths;

@SpringBootApplication
@ComponentScan(basePackages = {"com.weds.*"})
@MapperScan(basePackages = {"com.weds.**.mapper"}, annotationClass = MyBatisDao.class)
public class DemoApplication {

    public static void main(String[] args) throws Exception {
//        SpringApplication springApplication = new SpringApplication(DemoApplication.class);
//        springApplication.setBannerMode(Banner.Mode.OFF);
//        springApplication.run(args);
        generateAsciiDocs();
    }

    public static void generateAsciiDocs() throws Exception {
        //  输出Markdown到单文件
        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withMarkupLanguage(MarkupLanguage.ASCIIDOC)
                .withOutputLanguage(Language.ZH)
                .withPathsGroupedBy(GroupBy.TAGS)
                .withGeneratedExamples()
                .withoutInlineSchema()
                .build();

        Swagger2MarkupConverter.from(new URL("http://localhost:8093/api/v2/api-docs"))
                .withConfig(config)
                .build()
                .toFile(Paths.get("docs/asciidoc/generated/all"));
    }
}
