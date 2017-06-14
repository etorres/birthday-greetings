package com.github.etorres.birthdaygreetings;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class S3EmployeeRepository implements EmployeeRepository {

    private final String bucketName;
    private final String objectKey;
    private final EmployeeReader employeeReader;

    public S3EmployeeRepository(String bucketName, String objectKey, EmployeeReader employeeReader) {
        this.bucketName = bucketName;
        this.objectKey = objectKey;
        this.employeeReader = employeeReader;
    }

    @Override
    public List<Employee> findEmployeesBornOn(LocalDate date) {
        AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                .withCredentials(new ProfileCredentialsProvider())
                .withRegion(Regions.EU_CENTRAL_1)
                .build();

        S3Object employeesS3Object = s3.getObject(new GetObjectRequest(bucketName, objectKey));

        try (S3ObjectInputStream employeesInputStream = employeesS3Object.getObjectContent()) {
            return employeeReader.filterEmployeesByDateOfBirth(employeesInputStream, date);
        } catch (IOException e) {
            throw new IllegalStateException(String.format("Failed to read employees records from S3: %s/%s",
                    bucketName, objectKey), e);
        }
    }

}
