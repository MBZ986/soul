package cn.smartrick.soul.config;

import cn.smartrick.soul.util.UploadFileQiniu;
import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShareConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DruidDataSource druidDataSource(){
        return new DruidDataSource();
    }

    /**
     * mybatisplus 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 七牛云工具类
     * @param uploadProperties
     * @return
     */
    @Bean
    public UploadFileQiniu uploadFileQiniu(UploadProperties uploadProperties){
        return new UploadFileQiniu(uploadProperties.getQiniu());
    }

}
