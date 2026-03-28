/** AppConfig.java
 * 
 * The dataSource() method in the AppConfig class is annotated with @Bean. 
 * Spring will create a bean of type DataSource using the method 
 * and will store it in the application context.
 * 
 * When a component in the application needs to connect to the database, 
 * it simply requests the DataSource from the Spring application context.
 * 
 * Don't keep secrets in play text, use environment variables.
 * Use environment variables:
 * 
 * gedit ~/.bashrc
 *   export SB_ENCRYPT_PASSWORD_KEY=mysecretkey
 *   export SB_ENCRYPT_PASSWORD_IV=mysecretiv
 * source ~/.bashrc
 * echo $MYAPP_ENCRYPTION_KEY
 * 
 */
package com.minte9.encrypt_password;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class AppConfig {

    @Autowired
    private Environment env;

    private Boolean ENCRYPT = false;
    private Boolean GENKEYS = false;

    public void genkeys() throws Exception {
        String key = AES_GCM.createKey(256);
        String iv = AES_GCM.createIv();
        
        System.out.println(key); // AO5uMsQyKeVfwkVF5L6n0S...
        System.out.println(iv); // DnGotRRpb6xlzeu5
        System.exit(0); 
    }

    private void encrypt() throws Exception {
        String plainText = "The??...";
        String key = System.getenv("SB_ENCRYPT_PASSWORD_KEY");
        String iv = System.getenv("SB_ENCRYPT_PASSWORD_IV");        
        String encrypted = AES_GCM.encrypt(plainText, key, iv); // Look Here

        System.out.println(encrypted); // FMmsKpM1DizBbpxKuQl5deeA...
        System.exit(0); 
    }

    @Bean
    public DataSource dataSource () throws Exception {

        if (ENCRYPT) encrypt();
        if (GENKEYS) genkeys();

        String encrypted = env.getProperty("spring.datasource.password");
        String key = System.getenv("SB_ENCRYPT_PASSWORD_KEY");
        String iv = System.getenv("SB_ENCRYPT_PASSWORD_IV");
        String decrypted = AES_GCM.decrypt(encrypted, key, iv);  // Look Here

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver"); 
                                    // oracle.jdbc.OracleDriver
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(decrypted);

        return dataSource;
    }

}
