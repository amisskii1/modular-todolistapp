package com.misskii.todolistapp.dao.api;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;

public interface LicenseApi {
    void save(String email, String licenseValue) throws SQLException;
    void update(String email, String licenseValue);
    String getLicenseValueByUserID(int userId);
    void updateLicenseStatus(String licenseStatus, LocalDateTime expireDate , int id);
    String getLicenseStatus(int id);
    ChronoLocalDateTime<?> getExpireDate(int id);
    void updateLicenseStatus(String licenseStatus, int id);
}
