package <%=groupId%>.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.transfer.TransferManager;

@Configuration
@ConfigurationProperties("amazon.s3")
public class S3Configuration {
    
    private String accessKey;
    private String secretKey;
    private String bucketName;
    private String imageFolder;
    
    private AWSCredentials getAWSCredentials(){
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
        return awsCredentials;
    }

    @Bean
    public AmazonS3Client getAmazonS3Client(){
        AmazonS3Client amazonClient = new AmazonS3Client(getAWSCredentials());
        return amazonClient;
    }
    
    @Bean
    public TransferManager getTransferManager(AmazonS3Client amazonS3Client){
        TransferManager tx = new TransferManager(getAWSCredentials());
        return tx;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getImageFolder() {
        return imageFolder;
    }

    public void setImageFolder(String imageFolder) {
        this.imageFolder = imageFolder;
    }

}
