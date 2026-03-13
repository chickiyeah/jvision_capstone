package egovframework.yakbang.service.vo;

import java.util.Date;
import lombok.Data;

/**
 * 디바이스 정보 VO
 */
@Data
public class DeviceVO {

    /** 디바이스 ID */
    private String deviceId;

    /** 사용자 ID (FK) */
    private int userId;

    /** 모델명 */
    private String modelName;

    /** 회당 최대 주입량 */
    private double hardLimitPerDose;

    /** 남은 인슐린, 배터리 잔량, 바늘 교체 횟수 */
    private double insulinRemaining;
    private int batteryLevel;
    private int needleCount;
    private Date lastUpdate;
}